package dao;

import entity.Customer;
import view.AdminMenu;

/**
 * Created by 捷宝宝 on 2017/4/20.
 */
public class CustomerDao {
    static Customer[] cList = new Customer[500];
    static int index = 0;

    public CustomerDao() {
    }

    public void prinAll() {
        for (int i = 0; i < index; i++) {
            System.out.println("用户名：" + this.cList[i].getName() + "会员卡号" + this.cList[i].getCustNum() + "当前用户余额：" + cList[i].getMoney());
        }
        AdminMenu e = new AdminMenu();
        e.begin();
    }

    public void addCust(Customer c) {
        cList[this.index] = c;
        this.index++;
    }

    public void changeMoney(int cad, double money) {
        for (int i = 0; i < index; i++) {
            if (cList[i].getCustNum() == cad) {
                double mon = money + cList[i].getMoney();
                cList[i].setMoney(mon);
            }
        }
        AdminMenu e = new AdminMenu();
        e.begin();
    }

    public boolean delectCustomer(Customer c) {
        boolean t = true;
        for (int i = 0; i < index; i++) {
            if (cList[i].getName().equals(c.getName())) {
                for (int j = i; j < index-1; j++) {
                    cList[j] = cList[j + 1];
                }
                index--;
                t = false;
            }
        }
        return t;
    }
}
