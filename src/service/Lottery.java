package service;

import dao.CustomerServiceImpl;
import entity.Customer;
import view.CustomerMenu;

/**
 * Created by 捷宝宝 on 2017/4/29.
 */
public class Lottery {
    Customer cus = new Customer();
    CustomerServiceImpl csi = new CustomerServiceImpl();

    public void lottery() {
        int sum = new CustomerServiceImpl().countCust();
        int num = (int) (Math.random() * sum);
        num += 123;
        System.out.println("中奖用户卡号"+num);
        ChangeInformation lucky = new ChangeInformation();
//        lucky.changeMoney();
        cus = csi.getCust(num);
        if(cus == null){
            System.out.println("无用户中奖!");
        }
        System.out.println("恭喜" + cus.getName() + "中奖!");
        new CustomerMenu().begin();
    }

}
