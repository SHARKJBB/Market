package view;

import java.util.Scanner;

/**
 * Created by 捷宝宝 on 2017/4/20.
 */
public class MainMune {
    Scanner s = new Scanner(System.in);

    public void showMenu() {
        System.out.println("-------初始操作菜单-------");
        System.out.println("1.登录进入管理员页面");
        System.out.println("2.登录进入用户页面");
        System.out.println("---------------------");
        int i = this.s.nextInt();
        s.nextLine();
        switch (i) {
            case 1:
                new AdminLogin().login();
                break;
            case 2:
                new CustomerMenu().begin();
                break;
        }
    }

}
