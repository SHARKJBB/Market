package service;

import dao.CustomerDao;
import dao.CustomerServiceImpl;
import dao.CustomerServiceImplTry;
import entity.Customer;
import view.AdminMenu;

import java.util.Scanner;

/**
 * Created by 捷宝宝 on 2017/4/20.
 */
public class AddCard {
    Scanner s;
    CustomerDao c;
    CustomerServiceImplTry cjdbc;

    public AddCard() {
        this.s = new Scanner(System.in);
        this.c = new CustomerDao();
    }

    public void addCard() {
        Customer temp = new Customer();
        System.out.println("------------欢迎添加会员信息，请按提示输入相应内容------------");
        System.out.println("请输入会员名字");
        temp.setName(this.s.nextLine());
        System.out.println("请首次充值金额");
        temp.setMoney(this.s.nextDouble());
        int card = new CustomerServiceImpl().savaCust(temp.getName(), temp.getMoney());
        if (card != 0) {
            System.out.println("------------信息提交成功------------");
            System.out.println("您新建的卡号为：" + card);
        } else if(card == 0){
            System.out.println("============信息提交失败============");
        }
        new AdminMenu().begin();
//        this.c.addCust(temp);
    }
}
