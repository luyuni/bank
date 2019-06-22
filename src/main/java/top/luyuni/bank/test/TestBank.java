package top.luyuni.bank.test;

import java.util.Scanner;

import top.luyuni.bank.manager.ManagerInterface;
import top.luyuni.bank.manager.impl.ManagerImpl;
import top.luyuni.bank.util.AccountOverDrawnException;
import top.luyuni.bank.util.InvalidDepositException;

public class TestBank {
	static ManagerInterface manager = ManagerImpl.getInstance();
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		welcome();
		boolean f = false;
		while(f == false) {
			int k = in.nextInt();
			switch (k) {
			case 1:
				f = login();
				break;
			case 2:
				register();
				break;
			case 3:
				welcome();
				break;
			}
		}
		menu();
		while (f) {
			int i = in.nextInt();
			switch (i) {
			case 1:
				inquiry();
				break;
			case 2:
				withdrawals();
				break;
			case 3:
				deposit();
				break;
			case 4:
				tranfer();
				break;
			case 5:
				exit();
				break;
			case 6:
				menu();
			}
		}
	}
	public static void welcome() {
		System.out.println("----------------------------------------------------------");
		System.out.println("-----    1、登录                                   -------");
		System.out.println("-----    2、注册                                   -------");
		System.out.println("-----    3、菜单                                   -------");
		System.out.println("----------------------------------------------------------");
	}
	public static void menu() {
		System.out.println("----------------------------------------------------------");
		System.out.println("-----    1、查询                                   -------");
		System.out.println("-----    2、取款                                   -------");
		System.out.println("-----    3、存款                                   -------");
		System.out.println("-----    4、转账                                   -------");
		System.out.println("-----    5、退出                                   -------");
		System.out.println("-----    6、菜单                                   -------");
		System.out.println("----------------------------------------------------------");
	}

	public static void inquiry() {
		System.out.println("您的余额为：" + manager.inquiry());
	}

	public static void withdrawals() {
		System.out.println("请输入要取出的金额");
		double money = in.nextDouble();
		try {
			manager.withdrawals(money);
			System.out.println("你已成功取出");
		}catch(AccountOverDrawnException e) {
			e.printStackTrace();
		}
		
	}

	public static void deposit() {
		System.out.println("请输入要存入的金额");
		double money = in.nextDouble();
		try {
			manager.deposit(money);
			System.out.println("你已成功存入");
		}catch(InvalidDepositException e) {
			e.printStackTrace();
		}
	}

	public static void exit() {
		System.out.println("886");
		manager.exitSystem();
	}
	public static boolean login() {
		System.out.println("请输入用户名");
		String userName = in.next();
		System.out.println("请输入密码");
		String password = in.next();
		try {
			boolean f = manager.login(userName, password);
			if(f == true) {
				System.out.println("登录成功");
				return true;
			}else {
				System.err.println("登录失败！用户名或密码错误");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static void register() {
		System.out.println("请输入用户名");
		String userName = in.next();
		System.out.println("请输入密码");
		String password = in.next();
		try {
			boolean f = manager.register(userName, password);
			if(f == true) {
				System.out.println("注册成功");
			}else {
				System.err.println("注册失败！用户名已存在");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void tranfer() {
		System.out.println("请输入转入账户用户名");
		String userName = in.next();
		System.out.println("请输入转账金额");
		double money = in.nextDouble();
		try {
			System.out.println(money);
			boolean f = manager.transfer(userName, money);
			if(f == true) {
				System.out.println("转账成功");
			}else {
				System.out.println("转账失败");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
