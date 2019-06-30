package top.luyuni.bank;

import top.luyuni.bank.model.MoneyBean;

import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("classInfo.properties");
        Properties p = new Properties();
        p.load(inputStream);
        String bankDaoInterface = p.getProperty("BankDaoInterface");
        System.out.println(bankDaoInterface);
    }
}
