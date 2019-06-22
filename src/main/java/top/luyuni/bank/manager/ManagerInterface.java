package top.luyuni.bank.manager;

public interface ManagerInterface {
	/**
	 * 取款
	 * @param money 取款金额
	 */
	void deposit(double money);
	/**
	 * 存款
	 * @param money 存款金额
	 */
	void withdrawals(double money);
	/**
	 * 查询余额
	 * @return 余额
	 */
	double inquiry();
	/**
	 * 退出系统
	 */
	void exitSystem();
	/**
	 * 转账
	 * @param userName 收钱账户用户名
	 * @param money 转账钱数
	 * @return 成功返回true，失败返回false
	 */
	boolean transfer(String userName, double money);
	/**
	 * 注册
	 * @param userName 用户名
	 * @param password 密码
	 * @return 成功true，失败false（用户名已存在）
	 */
	boolean register(String userName, String password);
	/**
	 * 登录
	 * @param userName 用户名
	 * @param password 密码
	 * @return 成功返回true，失败返回false（用户名不存在or密码错误）
	 */
	boolean login(String userName, String password);
}
