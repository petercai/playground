package cai.peter.concurrency.sync;

public class MainThreadClassSync
{
	static public void main(String[] args)
	{
		MainThreadClassSync main =
				new MainThreadClassSync();

		Cinema cinema =
				main.new Cinema();

		TicketOffice1 to1 = main.new TicketOffice1(cinema);
		Thread t1 =
				new Thread(to1,"TicketOffcie1");

		TicketOffice2 to2 =
				main.new TicketOffice2(cinema);
		Thread t2 =
				new Thread(to2, "TicketOffcie2");

		t1.start();
		t2.start();

		try
		{
			t1.join();
			t2.join();
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.printf("Room 1 Vacancies: %d\n", cinema.vacanciesCinema1);
		System.out.printf("Room 2 vacancies: %d\n", cinema.vacanciesCinema2);

	}

	class Cinema
	{
		long vacanciesCinema1;
		long vacanciesCinema2;

		final Object controlCinema1, controlCinema2;

		public Cinema()
		{
			controlCinema1 = new Object();
			controlCinema2 = new Object();
			vacanciesCinema1 = 20;
			vacanciesCinema2 = 20;
		}

		boolean sellTickets1(int number)
		{
			synchronized(controlCinema1)
			{
				if( number < vacanciesCinema1)
				{
					vacanciesCinema1 -= number;
					return true;
				}
				else
					 return false;
			}
		}

		boolean sellTickets2(int number)
		{
			synchronized(controlCinema2)
			{
				if( number < vacanciesCinema2 )
				{
					vacanciesCinema2 -= number;
					return true;
				}
				else
					return false;
			}
		}

		boolean returnTickets1(int number)
		{
			synchronized(controlCinema1)
			{
				vacanciesCinema1 += number;
				return true;
			}
		}

		boolean returnTickets2(int number)
		{
			synchronized(controlCinema2)
			{
				vacanciesCinema2 += number;
				return true;
			}
		}
	}

	class TicketOffice1 implements Runnable
	{
		Cinema cinema;
		public TicketOffice1(Cinema c)
		{
			cinema = c;
		}

		public void run()
		{
			cinema.sellTickets1(3);
			cinema.sellTickets1(2);
			cinema.sellTickets2(2);

			cinema.returnTickets1(3);

			cinema.sellTickets1(5);
			cinema.sellTickets2(2);
			cinema.sellTickets2(2);
			cinema.sellTickets2(2);


		}
	}

	class TicketOffice2 implements Runnable
	{
		Cinema cinema;

		public TicketOffice2(Cinema c)
		{
			cinema = c;
		}

		public void run()
		{
			cinema.sellTickets2(2);
			cinema.sellTickets2(4);
			cinema.sellTickets1(2);
			cinema.sellTickets1(1);
			cinema.returnTickets2(2);
			cinema.sellTickets1(3);
			cinema.sellTickets2(2);
			cinema.sellTickets1(2);

		}
	}
}
