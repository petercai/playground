package cai.peter.concurrency.sync;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ThreadCountDownLatch
{
	static public void main(String[] args)
	{
		ThreadCountDownLatch main =
				new ThreadCountDownLatch();
		VideoConference conference = main.new VideoConference(10);

		Thread t =
				new Thread(conference);
		t.start();

		for( int i= 0;i<10;i++)
		{
			Participant participant =
					main.new Participant(conference, "Participant "+i);
			Thread t1 =
					new Thread(participant);
			t1.start();
		}

	}

	class VideoConference implements Runnable
	{
		private final CountDownLatch controller;

		public VideoConference(int number)
		{
			controller = new CountDownLatch(number);
		}

		public void arrive(String name)
		{
			System.out.printf("%s has arrived\n", name);
			controller.countDown();
			System.out.printf("VideoConference: wait for %d participants\n", controller.getCount());
		}

		public void run()
		{
			System.out.printf("VideoConference: initialization %d participants.\n", controller.getCount());
			try
			{
				controller.await();
				System.out.printf("VideoConference: all participants have come.\n");
				System.out.println("VideoConference: let's start it ...");
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	class Participant implements Runnable
	{
		VideoConference conference;

		String name;

		public Participant(VideoConference conference, String name)
		{
			this.conference = conference;
			this.name = name;
		}

		public void run()
		{
			long duration = (long)(Math.random()*10);
			try
			{
				TimeUnit.SECONDS.sleep(duration);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conference.arrive(name);
		}
	}
}
