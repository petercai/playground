package cai.peter.concurrency.sync;

public class MainThreadSync
{
	static public void main(String[] args)
	{
		MainThreadSync main =
				new MainThreadSync();

		Account account =
				main.new Account();
		account.balance = 1000;

		Company company =
				main.new Company(account);

		Bank bank =
				main.new Bank(account);

		Thread bt =
				new Thread(bank);
		Thread ct =
				new Thread(company);

		System.out.printf("Account : Initial Balance: %f\n", account.balance);
		ct.start();
		bt.start();

		try
		{
			ct.join();
			bt.join();
			System.out.printf("Account: Final balance: %f\n", account.balance);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class Account
	{
		double balance;

		public synchronized  void addAmount(double amount)
		{
			double tmp = balance;
			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tmp+=amount;
			balance = tmp;
		}

		public synchronized void substractAmount(double amount)
		{
			double tmp = balance;
			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tmp-=amount;
			balance = tmp;
		}


	}

	class Bank implements Runnable
	{
		Account account;
		public Bank(Account act)
		{
			account = act;
		}
		public void run()
		{
			for(int i=0;i<100;i++)
				account.substractAmount(1000);
		}
	}

	class Company implements Runnable
	{
		Account account;
		public Company(Account act)
		{
			account = act;
		}
		public void run()
		{
			for( int i=0;i<100;i++)
				account.addAmount(1000);
		}
	}
}
