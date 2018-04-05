package service;

import dao.CustomerDao;
import dao.CustomerServiceImpl;
import entity.Customer;
import view.AdminMenu;

import java.util.Scanner;

/**
 * Created by 捷宝宝 on 2017/4/20.
 */
public class ChangeInformation {
    Scanner s;
    CustomerDao c;

    Customer cus;

    public ChangeInformation() {
        this.s = new Scanner(System.in);
        this.c = new CustomerDao();
    }

    public void changeMoney() {
        System.out.println("请输入用户卡号：");
        int card = s.nextInt();
        s.nextLine();
        System.out.println("请输入改动的金额：（充值输入正数，消费输入负数 *不可撤销）");
        double money = s.nextDouble();
        s.nextLine();
//        c.changeMoney(card, money);
//
//        cus.setMoney(card);
//        cus.setMoney(money);
//        System.out.println("cardnum"+card+"   money"+money);
        if (new CustomerServiceImpl().updateCust(card, money)) {
            System.out.println("---------修改余额成功----------");
        } else {
            System.out.println("=========修改余额失败===========");
        }
//        new AdminMenu().begin();
//        CustomerMenu cmn = new CustomerMenu();
//        cmn.begin();
    }

    public void changeName() {
        Customer cus = new Customer();
        System.out.println("请输入用户卡号：");
        cus.setCustNum(s.nextInt());

        System.out.println("--------------请输入修改后的会员信息--------------");
        System.out.println("请输入改动后的会员姓名：（请注意是修改后的姓名 *不可撤销）");
        s.nextLine();
        cus.setName(s.nextLine());
        System.out.println("请输入改动后的会员余额：（请注意是充值或消费后的剩余金额 *不可撤销）");
        cus.setMoney(s.nextDouble());
        s.nextLine();
        System.out.println("请输入改动后的会员等级：（*不可撤销）");
        cus.setLevel(s.nextLine());
//        c.changeMoney(card, money);
//        cus.setMoney(card);
//        cus.setMoney(money);
        if (new CustomerServiceImpl().updateCust(cus)) {
            System.out.println("-----------修改会员信息成功----------");
        } else {
            System.out.println("===========修改会员信息失败==========");
        }
        new AdminMenu().begin();
//        CustomerMenu cmn = new CustomerMenu();
//        cmn.begin();

    }
}
