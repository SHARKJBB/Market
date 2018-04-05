package dao;

import entity.Customer;

import java.util.List;

/**
 * Created by 捷宝宝 on 2017/4/27.
 */
public interface CustomerService {
    int savaCust(String name, double money);

    boolean removeCust(int Cardnum);


    boolean updateCust(int cardnum, double money);

    boolean updateCust(Customer c);

    List getCust(String name, int page, int pgesize);

    Customer getCust(int Cardnum);

    List listCust();

    int countCust();
}
