package top.luyuni.bank.model;

import java.math.BigDecimal;

public class MoneyBean {
	private BigDecimal money;
	private static volatile MoneyBean instance;
	private MoneyBean() {
		money = new BigDecimal(0);
	}
	public static MoneyBean getInstance() {
		if(instance == null) {
			synchronized(MoneyBean.class) {
				if(instance == null) {
					instance = new MoneyBean();
				}
			}
		}
		return instance;
	}
	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
}
