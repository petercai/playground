package cai.peter.tradeagent.backoffice;

public interface IBackOffice
{
	void buy(String name, float price, int quantity) throws BackOfficeException;
	void sell(String name, float price, int quantity) throws BackOfficeException;
}
