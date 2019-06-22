package top.luyuni.bank.manager.impl;

import java.math.BigDecimal;

import top.luyuni.bank.dao.BankDaoInterface;
import top.luyuni.bank.dao.impl.BankDaoImpl;
import top.luyuni.bank.manager.ManagerInterface;
import top.luyuni.bank.model.MoneyBean;
import top.luyuni.bank.util.AccountOverDrawnException;
import top.luyuni.bank.util.InvalidDepositException;

public class ManagerImpl implements ManagerInterface{
	private MoneyBean moneyBean;
	private static volatile ManagerImpl instance;
	private BankDaoInterface bankDao;
	private ManagerImpl() {
		bankDao = BankDaoImpl.getInstance();
		moneyBean = MoneyBean.getInstance();
	}
	public static ManagerImpl getInstance() {
		if(instance == null) {
			synchronized(ManagerImpl.class) {
				if(instance == null) {
					instance = new ManagerImpl();
				}
			}
		}
		return instance;
	}
	public double inquiry() {
		return moneyBean.getMoney().doubleValue();
	}
	public void withdrawals(double money) {
		BigDecimal mo = new BigDecimal(Double.toString(money));
		if(moneyBean.getMoney().compareTo(mo) < 0) {
			throw new AccountOverDrawnException();
		}
		moneyBean.setMoney(moneyBean.getMoney().subtract(mo));
	}
	public void deposit(double money) {
		if(money < 0) {
			throw new InvalidDepositException();
		}
		BigDecimal mo = new BigDecimal(Double.toString(money));
		moneyBean.setMoney(moneyBean.getMoney().add(mo));
	}
	@Override
	public void exitSystem() {
		bankDao.addBank();
		System.exit(0);
	}
	@Override
	public boolean transfer(String userName, double money) {
		return bankDao.transfer(userName, money);
	}
	@Override
	public boolean register(String userName, String password) {
		return bankDao.register(userName, password);
	}
	@Override
	public boolean login(String userName, String password) {
		return bankDao.login(userName, password);
	}
	
}
