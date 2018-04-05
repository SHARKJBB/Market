package view;

import java.util.Scanner;

/**
 * Created by 捷宝宝 on 2017/4/21.
 */
public class AdminLogin {
    Scanner s = new Scanner(System.in);
    String sname = "admin";
    String spass = "123";

    public void login() {
        System.out.println("$$$$$$$$$欢迎管理员$$$$$$$$$");
        System.out.println("1.登录");
        System.out.println("2.退出");
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        int c = s.nextInt();
        switch (c) {
            case 1:
                check();
                break;
            case 2:
                System.exit(0);
                break;
            default:
                break;
        }

    }

    void check() {
        boolean t = true;
        while (t) {
            System.out.println("请输入管理员用戶名");
            String name = s.next();
            System.out.println("请输入管理员密码");
            String pass = s.next();
            if (name.equals(sname) && pass.equals(spass)) {
                t = false;
                AdminMenu a = new AdminMenu();
                a.begin();
//                break;
            } else {
                System.out.println("管理员信息有误，请核对后重新输入");
                continue;
            }
        }

    }
}
