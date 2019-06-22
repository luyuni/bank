package top.luyuni.bank.dao;

public interface BankDaoInterface {
	/**
	 * 存入银行
	 * 也就是将数据持久化到本地文件
	 */
	void addBank();
	/**
	 * 注册
	 * @param userName 用户名
	 * @param password 密码
	 * @return 成功返回true，失败返回false（用户名已存在）
	 */
	boolean register(String userName, String password);
	/**
	 * 登录
	 * @param userName 用户名
	 * @param password 密码
	 * @return 成功返回true，失败返回false
	 */
	boolean login(String userName, String password);
	/**
	 * 获取余额
	 * @return 余额
	 */
	double getBalance();
	/**
	 * 转账
	 * @param userName 收钱的用户名
	 * @param money 转账金额
	 * @return 成功返回true，失败返回false
	 */
	boolean transfer(String userName, double money);
}
