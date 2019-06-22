package top.luyuni.bank.util;

public class InvalidDepositException extends IllegalArgumentException{
	public InvalidDepositException() {
		super("存款为负数qaq");
	}
}
