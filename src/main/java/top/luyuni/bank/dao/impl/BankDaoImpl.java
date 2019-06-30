package top.luyuni.bank.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

import top.luyuni.bank.dao.BankDaoInterface;
import top.luyuni.bank.model.MoneyBean;
import top.luyuni.bank.model.UserBean;
import top.luyuni.bank.util.MD5;

public class BankDaoImpl implements BankDaoInterface{
	private UserBean userBean;
	private MoneyBean moneyBean;
	public BankDaoImpl() {
		userBean = UserBean.getInstance();
		moneyBean = MoneyBean.getInstance();
	}
	@Override
	public void addBank() {
		Properties p = new Properties();
		p.put("userName", userBean.getUserName());
		p.put("password", userBean.getPassword());
		p.put("money", userBean.getMoneyBean().getMoney().doubleValue() + "");
		try {
			FileOutputStream fo = new FileOutputStream("E:" + File.separator + "bankdata" + File.separator + userBean.getUserName() + ".properties");
			p.store(fo, "");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean register(String userName, String password) {
		File file = new File("E:" + File.separator + "bankdata" + File.separator + userName + ".properties");
		if(file.exists()) {
			return false;
		}else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Properties p = new Properties();
		p.put("userName", userName);
		String pwd = MD5.getMD5(password + userName);
		p.put("password", pwd);
		p.put("money", "0.0");
		try {
			FileOutputStream fo = new FileOutputStream(file);
			p.store(fo, "");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean login(String userName, String password) {
		File file = new File("E:" + File.separator + "bankdata" + File.separator + userName + ".properties");
		if(file.exists() == false) {
			return false;
		}else {
			String name = "";
			String pwd = "";
			String money = "";
			try {
				FileInputStream fi = new FileInputStream(file);
				Properties p = new Properties();
				p.load(fi);
				name = p.getProperty("userName");
				pwd = p.getProperty("password");
				money = p.getProperty("money");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String dbPwd = MD5.getMD5(password + userName);
			if(name.equals(userName) && pwd.equals(dbPwd)) {
				moneyBean.setMoney(new BigDecimal(money));
				userBean.setMoneyBean(moneyBean);
				userBean.setPassword(dbPwd);
				userBean.setUserName(userName);
				return true;
			}else {
				return false;
			}
		}
	}

	@Override
	public double getBalance() {
		return userBean.getMoneyBean().getMoney().doubleValue();
	}

	@Override
	public boolean transfer(String userName, double money) {
		String path = "E:" + File.separator + "bankdata" + File.separator + userName + ".properties";
		File file = new File(path);
		if(file.exists() == false) {
			return false;
		}else {
			Properties p = null;
			FileInputStream fi = null;
			FileOutputStream fo = null;
			String pmoney = "";
			try {
				fi = new FileInputStream(file);
				p = new Properties();
				p.load(fi);
				pmoney = p.getProperty("money");
				BigDecimal b = new BigDecimal(pmoney);
				BigDecimal bb = new BigDecimal(Double.toString(money));
				b = b.add(bb);
				p.put("money", b.doubleValue() + "");
				fo = new FileOutputStream(file);
				p.store(fo, "");
				moneyBean.setMoney(moneyBean.getMoney().subtract(bb));
				addBank();
				return true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
