package entity;

/**
 * Created by 捷宝宝 on 2017/4/20.
 */
public class Customer {
    int CustNum;
    //会员卡号
    String name;
    //会员名字
    double money;
    //会员余额
    String level;

    public Customer() {

    }

    @Override
    public String toString() {
        return "------------------Customer--------------------" + '\n' +
                "会员卡号：" + CustNum + '\n' +
                "会员名:" + name + '\n' +
                "会员余额：" + money + '\n' +
                "会员等级：" + level + '\n' +
                "--------------------------------------" + '\n';
    }

    public Customer(int custNum, String name, double money, String level) {
        CustNum = custNum;
        this.name = name;
        this.money = money;
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getCustNum() {
        return CustNum;
    }

    public void setCustNum(int custNum) {
        CustNum = custNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}