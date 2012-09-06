package cai.peter.playground;


public class QuickDial {
    public QuickDial(int quickCode, String telephoneNo)
    {
        super();
        this.quickCode = quickCode;
        this.telephoneNo = telephoneNo;
    }
    int quickCode;
    public int getQuickCode()
    {
        return quickCode;
    }
    public void setQuickCode(int quickCode)
    {
        this.quickCode = quickCode;
    }
    public String getTelephoneNo()
    {
        return telephoneNo;
    }
    public void setTelephoneNo(String telephoneNo)
    {
        this.telephoneNo = telephoneNo;
    }
    String telephoneNo;

}
