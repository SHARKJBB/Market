package dao;

import entity.Customer;
import view.AdminMenu;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by 捷宝宝 on 2017/4/27.
 */
public class CustomerServiceImplTry {

    private Connection conn;
    static String driver = null;
    static String url = null;
    static String user = null;
    static String pass = null;

    static {
        Properties p = new Properties();
        InputStream in = CustomerServiceImplTry.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            p.load(in);
            driver = p.getProperty("driver");
            url = p.getProperty("url");
            user = p.getProperty("user");
            pass = p.getProperty("pass");
//            System.out.println(driver);
//            System.out.println(url);
//            System.out.println(user);
//            System.out.println(pass);
        } catch (IOException e) {
            System.out.println("lianjieuuf");
            e.printStackTrace();
        }
    }

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

    public List<Customer>  page(int page, int pgesize) {
        List<Customer> l = new ArrayList<Customer>();
        String sql = "SELECT * FROM market limit ?,?";
                //limit从0开始
        try {
            //1获取驱动程序
//            Class.forName("com.mysql.jdbc.Driver");
            //2获得一个数据库链接
//            Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.117.129:3306/market", "root", "root");
            //3新建所需的SQL语句 常用Statement  PreparedStatement
            conn = CustomerServiceImpl.getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            //limit的第一个参数 从第几行开始查询（limit从0开始编号 0对应第一行）
            ps.setInt(1,(page-1)* pgesize);
            //limit的第二个参数 当前页一共连续显示多少行
            ps.setInt(2,pgesize);
            //4执行SQL语句 execute executeUpdate DML DDL executeQuery DQL
            ResultSet rs = ps.executeQuery();
            //5处理结果集
            while(rs.next()){
                Customer e = new Customer(rs.getInt(2),rs.getString(1),rs.getDouble(3),rs.getString(4));
           l.add(e);
            }
            return l;
        } catch (SQLException e) {
            e.printStackTrace();
        }
return  null;
    }
//    @Test
//    public void test1(){
//
//    }
    public static void main(String args[]) {
        CustomerServiceImplTry c = new CustomerServiceImplTry();
        List<Customer> li = new ArrayList<Customer>();
        li = c.page(1,5);
        System.out.println(li.toString());
    }

    public void addCust(String name, double money) {
        try {
            conn = CustomerServiceImplTry.getConn();
            String sql = "insert into market(name, money) values(?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, money);
            ps.executeUpdate();
            sql = "select auto_increment -1 AS cardnum "
                    + "from information_schema.tables "
                    + "where table_schema = 'market' and table_name = 'market';";
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next()) {
                System.out.println(name + "您的新会员卡号为：" + rs.getInt("cardnum"));
            }


//            String sql2 = "insert into customer values(?,null,?,normal)";
//            PreparedStatement ps2 = conn.prepareStatement(sql2);
//            ps.setString(1, name);
//            ps.setDouble(2, money);
//            ps.addBatch();
//            ps.setString(1, name);
//            ps.setDouble(2, money);
//            ps.addBatch();
//            ps.setString(1, name);
//            ps.setDouble(2, money);
//            ps.addBatch();
//            ps.execute();
//
//            Statement s = conn.createStatement();
//            s.addBatch("insert into customer values('miao',null,0,normal)");
//            s.addBatch("insert into customer values(?,null,0,normal)");
//            s.addBatch("insert into customer values(?,null,0,normal)");
//            s.executeBatch();

        } catch (Exception error) {
            System.err.println("ConnectMysql Error");
        }
    }


    public void findCust(String name) {
        //获得链接
        try {
            conn = CustomerServiceImplTry.getConn();
            Customer cus = new Customer();
            //生成语句
            String sql = "select * from market where name = ?";
            //statement preparedstatement有预编译，适合大规模相似查找
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            //执行语句
            ResultSet rs = ps.executeQuery();
            //访问结果集
            while (rs.next()) {
                System.out.println("会员名:" + rs.getString("name"));
                System.out.println("会员余额：" + rs.getDouble("money"));//输入列号或列名
                System.out.println("会员卡号：" + rs.getInt("cardnum"));
                System.out.println("会员等级：" + rs.getString("level"));
                cus.setName(rs.getString("name"));
            }
            new AdminMenu().begin();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Customer findCust(int Cardnum) {
        try {
            conn = CustomerServiceImplTry.getConn();
            Customer cus = new Customer();
            String sql = "select * from market where cardnum = ?";
            //statement preparedstatement有预编译，适合大规模相似查找
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Cardnum);
            //执行语句
            ResultSet rs = ps.executeQuery();
            //访问结果集
            while (rs.next()) {
                System.out.println("会员名:" + rs.getString("name"));
                System.out.println("会员余额：" + rs.getDouble("money"));//输入列号或列名
                System.out.println("会员卡号：" + rs.getInt("cardnum"));
                System.out.println("会员等级：" + rs.getString("level"));
                cus.setName(rs.getString("name"));
                cus.setCustNum(rs.getInt("cardnum"));
                cus.setMoney(rs.getDouble("money"));
            }
            new AdminMenu().begin();
            return cus;
        } catch (Exception error) {
            System.err.println("ConnectMysql Error");
            return null;
        }
    }


    public boolean deleCust(int Cardnum) {
        try {
            conn = CustomerServiceImplTry.getConn();
            String sql = "delete from market  where cardnum = ?";
            //statement preparedstatement有预编译，适合大规模相似查找
            Customer cus = new Customer();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Cardnum);
            //执行语句
            ps.executeUpdate();
            conn.close();
            new AdminMenu().begin();

            return true;
        } catch (Exception error) {
            System.err.println("ConnectMysql Error");
            return false;
        }
    }


    public boolean updaCust(int cardnum, double money) {
        try {
            conn = CustomerServiceImplTry.getConn();
            String sql = "update market set money = money+? where cardnum = ?";
            if (money >= 5000) {
                sql = "update market  set level ='VIP' ,money = money+? where cardnum = ?";
            }
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, cardnum);
//            ps.setDouble(2, money);
//            conn.close();
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
            new AdminMenu().begin();
            return true;
        } catch (Exception error) {
            System.err.println("ConnectMysql Error");
            return false;
        }
    }


    public void FindAll() {
        try {
            conn = CustomerServiceImplTry.getConn();
            String sql = "select * from market ";
            //statement preparedstatement有预编译，适合大规模相似查找
            Customer cus = new Customer();
            PreparedStatement ps = conn.prepareStatement(sql);
            //执行语句
            ResultSet rs = ps.executeQuery();
//            ResultSetMetaData r = rs.getMetaData();
//            System.out.println("共有" + r.getColumnCount() + "行\n" + "第一行：" + r.getColumnName(1) + "第二行：" + r.getColumnName(2) + "第三行：" + r.getColumnName(3)
//                    + "链接名" + r.getSchemaName(1) + "表名" + r.getTableName(1) + "列类型" + r.getColumnTypeName(1));//列数
            /*
             */
            //访问结果集
            while (rs.next()) {
                System.out.println("--------------------------------------");
                System.out.println("会员名:" + rs.getString("name"));
                System.out.println("会员余额：" + rs.getDouble("money"));
                System.out.println("会员卡号：" + rs.getInt("cardnum"));
                System.out.println("会员等级：" + rs.getString("level"));
                System.out.println("--------------------------------------");
            }
            new AdminMenu().begin();
        } catch (Exception error) {
            System.err.println("ConnectMysql Error");
        }
    }


//    @Override
//    public boolean savaCust(String name, double money) throws SQLException {
//        try {
//            conn = CustomerServiceImplTry.getConn();
//            String sql = "insert into market(name, money) values(?, ?)";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, name);
//            ps.setDouble(2, money);
//            ps.executeUpdate();
//            sql = "select auto_increment -1 AS cardnum "
//                    + "from information_schema.tables "
//                    + "where table_schema = 'market' and table_name = 'market';";
//            ResultSet rs = ps.executeQuery(sql);
//            if (rs.next()) {
//                System.out.println(name + "您的新会员卡号为：" + rs.getInt("cardnum"));
//            }


//            String sql2 = "insert into customer values(?,null,?,normal)";
//            PreparedStatement ps2 = conn.prepareStatement(sql2);
//            ps.setString(1, name);
//            ps.setDouble(2, money);
//            ps.addBatch();
//            ps.setString(1, name);
//            ps.setDouble(2, money);
//            ps.addBatch();
//            ps.setString(1, name);
//            ps.setDouble(2, money);
//            ps.addBatch();
//            ps.execute();
//
//            Statement s = conn.createStatement();
//            s.addBatch("insert into customer values('miao',null,0,normal)");
//            s.addBatch("insert into customer values(?,null,0,normal)");
//            s.addBatch("insert into customer values(?,null,0,normal)");
//            s.executeBatch();

//        } catch (Exception error) {
//            System.err.println("ConnectMysql Error");
//        }
//        return false;
//    }

//    @Override
//    public boolean removeCust(int Cardmun) throws SQLException {
//        return false;
//    }
//
//    @Override
//    public boolean updateCust(int cardnum, double money) throws SQLException {
//        return false;
//    }
//
//    @Override
//    public boolean updateCust(Customer c) throws SQLException {
//        return false;
//    }
//
//    @Override
//    public Customer getCust(String name) throws SQLException {
//        return null;
//    }
//
//    @Override
//    public Customer getCust(int Cardnum) throws SQLException {
//        return null;
//    }
//
//    @Override
//    public List listCust() throws SQLException {
//        return null;
//    }
//
//    @Override
//    public int countCust() throws SQLException {
//        return null;
//    }
}
