package top.luyuni.bank.model;

public class UserBean {
	private String userName;
	private String password;
	private MoneyBean moneyBean;
	private static volatile UserBean instance;
	private UserBean() {	}
	public static UserBean getInstance() {
		if(instance == null) {
			synchronized(UserBean.class) {
				if(instance == null) {
					instance = new UserBean();
				}
			}
		}
		return instance;
	}
	public UserBean(String userName, String password, MoneyBean moneyBean) {
		super();
		this.userName = userName;
		this.password = password;
		this.moneyBean = moneyBean;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public MoneyBean getMoneyBean() {
		return moneyBean;
	}
	public void setMoneyBean(MoneyBean moneyBean) {
		this.moneyBean = moneyBean;
	}
	
	
}
