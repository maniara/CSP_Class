package kr.ac.shinhan.csp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Result {
	private boolean isSuccess;
	private String message;
	
	
	public Result(){
		this.isSuccess = false;
		this.message="";
	}
	
	public Result(boolean status, String massage) {
		super();
		this.isSuccess = status;
		this.message = massage;
	}
	
	public void setSuccess()
	{
		this.isSuccess = true;
		this.setMessage("Success");
	}
	
	public void setFail(String msg)
	{
		this.isSuccess = false;
		this.message = msg;
	}
	
	public void setFail()
	{
		this.isSuccess = false;
	}

	public void setMessage(String msg)
	{
		this.message=msg;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}
	
}

