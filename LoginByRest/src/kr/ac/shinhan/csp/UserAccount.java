package kr.ac.shinhan.csp;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class UserAccount {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long key;
	
	@Persistent
	private String account;
	
	@Persistent
	private String nicName;
	
	@Persistent
	private String password;
	
	public UserAccount() {}
	
	public UserAccount(String account, String nicName, String password) {
		super();
		this.account = account;
		this.nicName = nicName;
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