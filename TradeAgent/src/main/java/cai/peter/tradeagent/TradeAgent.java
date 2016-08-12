package cai.peter.tradeagent;

import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentLinkedDeque;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import cai.peter.tradeagent.backoffice.BackOfficeException;
import cai.peter.tradeagent.backoffice.IBackOffice;
import cai.peter.tradeagent.framework.IPriceList;

public class TradeAgent
	extends Observable
		implements  ITradeAgent, IPriceList
{
	enum Event { Finished, Error };
	Observer observer;


	@Inject
	String stockName;
	@Inject
	Float targetPrice;
	@Inject
	Integer quantity;

	@Inject
	IBackOffice backOffice;

	public TradeAgent(Observer observer)
	{
		super();
		this.observer = observer;
	}
	Executor executor;
	@PostConstruct
	public void init()
	{
		executor = new Executor();
		executor.start();
	}
	@PreDestroy
	public void treadDown()
	{
		executor.interrupt();
	}

	class Stock
	{
		String stockName;
		public Stock(String stockName, float price)
		{
			super();
			this.stockName = stockName;
			this.price = price;
		}
		float price;
	}

	Deque<Stock> stack = new ConcurrentLinkedDeque<>();

	@Inject
	int pause;
	class Executor extends Thread
	{

		@Override
		public void run()
		{
			try
			{
				while(true)
				{
					try
					{
						Stock stock = stack.pop();
						backOffice.buy(stock.stockName, stock.price, quantity);
					}
					catch (BackOfficeException e)
					{
						notifyError();
					}
					catch(NoSuchElementException e1)
					{
						stack.clear();
						notifyComplete();
					}
					sleep(pause);
				}
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

	}

	@Override
	public void priceChanged(String name, float price)
	{
		if( name != null && name.equals(stockName) && price <= targetPrice)
		{
			stack.push(new Stock(name, price));
		}

	}

	@Override
	public void notifyComplete()
	{
		observer.update(this, Event.Finished);

	}

	@Override
	public void notifyError()
	{
		observer.update(this,  Event.Error);
	}
}
