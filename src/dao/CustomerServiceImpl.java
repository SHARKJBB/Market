package dao;

import entity.Customer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by 捷宝宝 on 2017/4/29.
 */
public class CustomerServiceImpl implements CustomerService {
    private Connection conn;
    static String driver = null;
    static String url = null;
    static String user = null;
    static String pass = null;

    //用properties里的内容链接数据库（driver驱动）
    static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    //方法的静态块，随CustomerServiceimpl调用使用一次，一次运行中只调用一次
    static {
        Properties p = new Properties();
        InputStream in = CustomerServiceImpl.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            p.load(in);
            driver = p.getProperty("driver");
            url = p.getProperty("url");
            user = p.getProperty("user");
            pass = p.getProperty("pass");
        } catch (IOException e) {
            System.err.println("数据库链接信息有误！");
            e.printStackTrace();
        }
    }

    @Override
    public int savaCust(String name, double money) {
        try {
            conn = CustomerServiceImplTry.getConn();
            String sql = "insert into market(name, money) values(?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, money);
            ps.executeUpdate();
            sql = "select auto_increment -1 AS cardnum from information_schema.tables where table_schema = 'jdbc' and table_name = 'market';";
            ResultSet rs = ps.executeQuery(sql);
            int res =0;
            while (rs.next()){
                res = rs.getInt("cardnum");
            }
            return  res;
        } catch (SQLException e) {
            System.err.println("添加会员失败");
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean removeCust(int cardnum) {
        try {
            conn = CustomerServiceImpl.getConn();
            String sql = "delete from market  where cardnum = ?";
            //statement preparedstatement有预编译，适合大规模相似查找
            Customer cus = new Customer();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cardnum);
            //执行语句
            ps.executeUpdate();
            conn.close();
//            new AdminMenu().begin();
            return true;
        } catch (Exception error) {
            System.err.println("删除" + cardnum + "用户失败");
            error.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean updateCust(int cardnum, double money) {
        try {
            conn = CustomerServiceImplTry.getConn();
            String sql = "update market set money = money+? where cardnum = ?";
            if (money >= 5000) {
                sql = "update market  set level ='VIP' ,money = money+? where cardnum = ?";
            }
            //事务三部曲
            //关闭自动提交
            conn.setAutoCommit(false);
            //编辑事务内容
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, money);
            ps.setInt(2, cardnum);
            ps.executeUpdate();
            conn.commit();
            conn.close();//手动提交
//            new AdminMenu().begin();
            return true;
        } catch (Exception error) {
            System.err.println("更新会员信息失败");
            return false;
        }
    }


    @Override
    public boolean updateCust(Customer c) {
        try {
            conn = CustomerServiceImpl.getConn();
            String sql = "update market set name = ?,money = ?,level = ? where cardnum  = ?";
            conn.setAutoCommit(false);
            //编辑事务内容
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getName());
            ps.setDouble(2, c.getMoney());
            ps.setString(3, c.getLevel());
            ps.setInt(4, c.getCustNum());
            ps.executeUpdate();
            conn.commit();
            conn.close();//手动提交
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List getCust(String name, int page, int pgesize) {
        List<Customer> l = new ArrayList<Customer>();
        try {
            conn = CustomerServiceImpl.getConn();

            //生成语句
            String sql = "select * from market where name = ? limit ?,?";
            //statement preparedstatement有预编译，适合大规模相似查找
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            //执行语句
            //limit的第一个参数 从第几行开始查询（limit从0开始编号 0对应第一行）
            ps.setInt(2, (page - 1) * pgesize);
            //limit的第二个参数 当前页一共连续显示多少行
            ps.setInt(3, pgesize);
            ResultSet rs = ps.executeQuery();
            //访问结果集
            while (rs.next()) {
                Customer cus = new Customer();
                cus.setName(rs.getString("name"));
                cus.setCustNum(rs.getInt("cardnum"));
                cus.setMoney(rs.getDouble("money"));
                cus.setLevel(rs.getString("level"));
                l.add(cus);
            }
            return l;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer getCust(int Cardnum) {
        try {
            conn = CustomerServiceImplTry.getConn();
            Customer cus = new Customer();
            String sql = "select * from market where cardnum = ?";
            //statement preparedstatement有预编译，适合大规模相似查找
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Cardnum);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cus.setName(rs.getString("name"));
                cus.setCustNum(rs.getInt("cardnum"));
                cus.setMoney(rs.getDouble("money"));
                cus.setLevel(rs.getString("level"));
            }
            return cus;
        } catch (Exception error) {
            System.err.println("获取会员信息失败");
            return null;
        }
    }

    @Override
    public List listCust() {
        try {
            List<Customer> l = new ArrayList<Customer>();
            //2获得一个数据库链接
            conn = CustomerServiceImpl.getConn();
            String sql = "select * from market";
            //statement preparedstatement有预编译，适合大规模相似查找
            Customer cus = new Customer();
            PreparedStatement ps = conn.prepareStatement(sql);
            //执行语句
//            ps.setInt(1, (page - 1) * pgesize);
//            ps.setInt(2, pgesize);
            ResultSet rs = ps.executeQuery();
//            ResultSetMetaData r = rs.getMetaData();
//            System.out.println("共有" + r.getColumnCount() + "行\n" + "第一行：" + r.getColumnName(1) + "第二行：" + r.getColumnName(2) + "第三行：" + r.getColumnName(3)
//                    + "链接名" + r.getSchemaName(1) + "表名" + r.getTableName(1) + "列类型" + r.getColumnTypeName(1));//列数
            while (rs.next()) {
                Customer e = new Customer(rs.getInt("cardnum"), rs.getString("name"), rs.getDouble("money"), rs.getString("level"));
                l.add(e);
            }
            return l;
        } catch (Exception error) {
            System.err.println("获取会员列表失败");
            return null;
        }
    }

    @Override
    public int countCust() {
        try {
            conn = CustomerServiceImpl.getConn();
            String sql = "SELECT COUNT(*)  FROM market ";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            int m = 0;
            while (rs.next()) {
                m = rs.getInt(1);
            }
            return m;
        } catch (SQLException e) {
            System.err.println("统计用户数量失败");
            e.printStackTrace();
        }
        return 0;
    }

}
