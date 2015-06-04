package kr.ac.shinhan.csp.entity;

public class UserAccount {
	
	private Long key;
	private String account;
	private String nicName;
	private String password;
	
	public UserAccount() {}
	
	public UserAccount(String account, String nicName, String password) {
		super();
		this.account = account;
		this.nicName = nicName;
		this.password = password;
	}
	
	public UserAccount(String account, String password)
	{
		this.account = account;
		this.password = password;
	}
	
	public Long getKey()
	{
		return this.key;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNicName() {
		return nicName;
	}

	public void setNicName(String nicName) {
		this.nicName = nicName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean checkLogin(String account, String password)
	{
		if(this.account.equals(account) && this.password.equals(password))
			return true;
		else
			return false;
	}
	
}