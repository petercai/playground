package models;

public class Contact
{
	public Contact(String nickName, boolean isBuddy, String firstName,
		String lastName, String homePhone, String mobilePhone,
		String businessPhone)
	{
		super();
		this.nickName = nickName;
		this.buddy = isBuddy;
		this.firstName = firstName;
		this.lastName = lastName;
		this.homePhone = homePhone;
		this.mobilePhone = mobilePhone;
		this.businessPhone = businessPhone;
	}
	public Contact()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	String nickName;
	boolean buddy;
	String firstName;
	String lastName;
	String homePhone;
	String mobilePhone;
	String businessPhone;
	public String getNickName()
	{
		return nickName;
	}
	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}
	public boolean isBuddy()
	{
		return buddy;
	}
	public void setBuddy(boolean isBuddy)
	{
		this.buddy = isBuddy;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public String getHomePhone()
	{
		return homePhone;
	}
	public void setHomePhone(String homePhone)
	{
		this.homePhone = homePhone;
	}
	public String getMobilePhone()
	{
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone)
	{
		this.mobilePhone = mobilePhone;
	}
	public String getBusinessPhone()
	{
		return businessPhone;
	}
	public void setBusinessPhone(String businessPhone)
	{
		this.businessPhone = businessPhone;
	}
}
