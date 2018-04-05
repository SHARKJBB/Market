package view;

import dao.CustomerServiceImplTry;
import service.ChangeInformation;
import service.Lottery;

import java.util.Scanner;

/**
 * Created by 捷宝宝 on 2017/4/27.
 */
public class CustomerMenu {
    Scanner s ;

    public void begin() {
        CustomerServiceImplTry a = new CustomerServiceImplTry();
        s = new Scanner(System.in);
        System.out.println("=============================");
        System.out.println("欢迎进入虚拟超市Market会员系统！");
        System.out.println("请选择服务：");
//        System.out.println("1.查看会员信息");
//        System.out.println("2.修改会员信息");
        System.out.println("3.充值");
        System.out.println("4.抽奖");
        System.out.println("0.退出");
        System.out.println("=============================");
//        this.anInt = (new KeyboardIn()).run(Arrays.asList(new Integer[]{Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5)}));
        int i = s.nextInt();
        switch (i) {
            case 0:
                System.out.println("您已退出虚拟超市Market会员系统。");
                new MainMune().showMenu();
                return;
            case 1:
//                a.findCust(c.getCustNum());
//                break;
                System.out.println("您选择的服务尚未推出，敬请期待");
                new CustomerMenu().begin();
                break;
            case 2:
                System.out.println("您选择的服务尚未推出，敬请期待");
                new CustomerMenu().begin();
            case 3:
                new ChangeInformation().changeMoney();

//                System.out.println("请输入修改的会员号：");
//                int card = s.nextInt();
//                s.nextLine();
//                System.out.println("请输入修改的金额");
//                double money = s.nextDouble();
//                s.nextLine();
//                new CustomerServiceImplTry().updaCust(card, money);
//                ChangeInformation cm = new ChangeInformation();
//                cm.changeMoney();
                new CustomerMenu().begin();
                break;
            case 4:
                new Lottery().lottery();
                break;
        }
    }

}
