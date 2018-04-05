package service;

import dao.CustomerDao;
import dao.CustomerServiceImpl;
import entity.Customer;
import view.AdminMenu;

import java.util.Scanner;

/**
 * Created by 捷宝宝 on 2017/4/21.
 */
public class DeleteCustomer {
    CustomerDao c;
    Scanner s;

    public DeleteCustomer() {
        this.s = new Scanner(System.in);
        this.c = new CustomerDao();
    }

    public void delCust() {
        Customer temp = new Customer();
        System.out.println("请输入要删除的会员卡号：");
        int cardnum = s.nextInt();
        s.nextLine();
        if( new CustomerServiceImpl().removeCust(cardnum)){
            System.out.println("-------------删除"+cardnum+"会员成功--------------");
            new AdminMenu().begin();
        }
        else{
            System.out.println("=============删除"+cardnum+"会员失败===============");
        }
        new AdminMenu().begin();
    }
}
