package cai.peter.playground;

import java.util.ArrayList;
import java.util.List;

public class CallAcceptance
{
	public CallAcceptance(boolean enable, List<String> telephoneNumList)
	{
		super();
		this.enable = enable;
		this.phoneList = new ArrayList<CallAcceptance.Phone>();
		for(String num : telephoneNumList)
			phoneList.add(new Phone(num));
	}
	boolean enable;
	List<Phone> phoneList;
	public boolean isEnable()
	{
		return enable;
	}
	public void setEnable(boolean enable)
	{
		this.enable = enable;
	}
	public List<Phone> getPhoneList()
	{
		return phoneList;
	}
	public void setPhoneList(List<Phone> telephoneNumList)
	{
		this.phoneList = telephoneNumList;
	}

	public class Phone
	{
		String phoneNo;

		public Phone(String telphoneNo)
		{
			super();
			this.phoneNo = telphoneNo;
		}

		public String getPhoneNo()
		{
			return phoneNo;
		}

		public void setPhoneNo(String telphoneNo)
		{
			this.phoneNo = telphoneNo;
		}

		@Override
		public String toString()
		{
			return "Phone [phoneNo=" + phoneNo
					+ "]";
		}
	}

	@Override
	public String toString()
	{
		return "CallAcceptance [enable=" + enable
				+ ", phoneList="
				+ phoneList
				+ "]";
	}
}
