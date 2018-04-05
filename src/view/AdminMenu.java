package view;

import dao.CustomerServiceImpl;
import service.AddCard;
import service.ChangeInformation;
import service.DeleteCustomer;
import service.SelectCustomer;

import java.util.Scanner;

/**
 * Created by 捷宝宝 on 2017/4/20.
 */
public class AdminMenu {
    Scanner s;
    private int anInt;

    public void begin() {
        s = new Scanner(System.in);
        System.out.println("-------欢迎进入虚拟超市Market管理员系统！-----");
//        System.out.println("");
        System.out.println("请选择服务：");
        System.out.println("1.增加新的会员信息");
        System.out.println("2.查看指定会员信息（根据姓名查找）");
        System.out.println("3.查看指定会员信息（根据会员卡号查找）");
        System.out.println("4.查看所有会员信息");
        System.out.println("5.修改指定会员余额");
        System.out.println("6.修改指定会员卡号的信息");
        System.out.println("7.删除指定会员信息");
        System.out.println("0.退出");
        System.out.println("=============================");
//        this.anInt = (new KeyboardIn()).run(Arrays.asList(new Integer[]{Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5)}));
        int i = s.nextInt();
        switch (i) {
            case 0:
                System.out.println("您已退出虚拟超市Market管理员系统。");
                new MainMune().showMenu();
                return;
            case 1:
                AddCard a = new AddCard();
                a.addCard();
                break;
            case 2:
                new SelectCustomer().findCustomerByName();
                break;
            case 3:
                new SelectCustomer().findCustomerByCardnum();
                break;
            case 4:
                System.out.println(new CustomerServiceImpl().listCust().toString());
                new AdminMenu().begin();
                break;
            case 5:
                new ChangeInformation().changeMoney();
                new AdminMenu().begin();
                break;
            case 6:
                new ChangeInformation().changeName();
                break;
            case 7:
                new DeleteCustomer().delCust();
                break;
        }
    }

}
