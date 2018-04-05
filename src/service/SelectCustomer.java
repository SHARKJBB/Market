package service;

import dao.CustomerDao;
import dao.CustomerServiceImpl;
import entity.Customer;
import view.AdminMenu;

import java.util.List;
import java.util.Scanner;

/**
 * Created by 捷宝宝 on 2017/4/20.
 */
public class SelectCustomer {
    Scanner s = new Scanner(System.in);
    CustomerDao c;
    CustomerServiceImpl csi = new CustomerServiceImpl();
    Customer cus = new Customer();

//    public SelectCustomer() {
//        this.s = new Scanner(System.in);
//        this.c = new CustomerDao();
//    }

    public void selectAll() {
        System.out.println("当前用户列表：");
        List list = new CustomerServiceImpl().listCust();
        new AdminMenu().begin();
    }

    public void findCustomerByCardnum() {
        System.out.println("请输入要查询的会员卡号");
        int cardnum = s.nextInt();
        s.nextLine();
        cus = csi.getCust(cardnum);
        if (cus.getCustNum() == 0) {
            System.out.println("您查找的用户不存在!");
        } else {
            System.out.println("----------会员信息如下---------");
            System.out.println("会员名:" + cus.getName());
            System.out.println("会员余额：" + cus.getMoney());
            System.out.println("会员卡号：" + cus.getCustNum());
            System.out.println("会员等级：" + cus.getLevel());
            System.out.println("------------------------------");
        }
        new AdminMenu().begin();
    }

    public void findCustomerByName() {
        System.out.println("请输入要查询的会员姓名");
        String name = s.next();
        CustomerServiceImpl c = new CustomerServiceImpl();
        List<Customer> li = c.getCust(name, 1, 5);
        if ( li == null) {
            System.out.println("您查找的用户不存在!");
        } else {
            System.out.println(li.toString());
        }
        new AdminMenu().begin();
    }
}
