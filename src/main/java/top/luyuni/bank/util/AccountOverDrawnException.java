package top.luyuni.bank.util;

public class AccountOverDrawnException extends IllegalArgumentException{
	public AccountOverDrawnException() {
		super("取款超出余额qaq");
	}
}
