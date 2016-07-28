package cai.peter.concurrency.sync;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ThreadPhaserOnAdvance
{
	@Test
	public void test()
	{
		ThreadPhaserOnAdvance main =
				new ThreadPhaserOnAdvance();

		MyPhaser phaser =
				main.new MyPhaser();

		Student[] students = new Student[5];
		for( int i=0;i<students.length;i++)
		{
			students[i] = new Student(phaser);
			phaser.register();
		}

		Thread[] ts = new Thread[students.length];
		for(int i=0;i<students.length;i++)
		{
			ts[i] = new Thread(students[i], "Student "+i);
			ts[i].start();
		}

		for(int i=0;i<students.length;i++)
		{
			try
			{
				ts[i].join();
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.printf("Main: the phaser has finished: %s\n", phaser.isTerminated());
	}

	class MyPhaser extends Phaser
	{
		protected boolean onAdvance( int phase, int registeredParties)
		{
			switch(phase)
			{
			case 0:
				return studentArrived();
			case 1:
				return finishFirstExcercise();
			case 2:
				return finishSecondExcercise();
			case 3:
				return finishExam();
				default:
					return true;
			}
		}

		private boolean finishExam()
		{
			System.out.println("Phaser: all the students have finished the exam.");
			System.out.println("Phaser: thank you for your time.");
			return false;
		}

		private boolean finishSecondExcercise()
		{
			System.out.println("Phaser: All the students have finished the second exercise.");
			System.out.println("Phaser: It's time for the third one.");
			return false;
		}

		private boolean finishFirstExcercise()
		{
			System.out.println("Phaser: All the students have finished the first exercise.");
			System.out.printf("Phaser: it's time for the second one.\n");
			return false;
		}

		private boolean studentArrived()
		{
			System.out.println("Phaser: The exam are going to start. the students are ready.");
			System.out.printf("Phaser: we have %d studends\n", getRegisteredParties());
			return false;
		}
	}

	class Student implements Runnable
	{
		Phaser phaser;
		public Student(Phaser p)
		{
			phaser = p;
		}

		public void run()
		{
			System.out.printf("%s: has arrived to do the exam. %s\n", Thread.currentThread().getName(), new Date());
			phaser.arriveAndAwaitAdvance();

			System.out.printf("%s: Is going to do the first exeercise. %s\n", Thread.currentThread().getName(), new Date());
			doExercise1();
			System.out.printf("%s: has done the first exercise. %s\n", Thread.currentThread().getName(), new Date());
			phaser.arriveAndAwaitAdvance();

			System.out.printf("%s: is going to do the second exercise. %s\n", Thread.currentThread().getName(), new Date());
			doExercise2();
			System.out.printf("%s: has done the second exercise. %s\n", Thread.currentThread().getName(), new Date());
			phaser.arriveAndAwaitAdvance();

			System.out.printf("%s: is going to do the third exercise. %s\n", Thread.currentThread().getName(), new Date());
			doExercise3();
			System.out.printf("%s: has finished the exam. %s\n", Thread.currentThread().getName(), new Date(0));
			phaser.arriveAndAwaitAdvance();

		}

		private void doExercise3()
		{
			// TODO Auto-generated method stub
			try
			{
				long duration = (long)(Math.random()*10);
				TimeUnit.SECONDS.sleep(duration);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		private void doExercise2()
		{
			// TODO Auto-generated method stub
			try
			{
				long duration = (long)(Math.random()*10);
				TimeUnit.SECONDS.sleep(duration);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

		private void doExercise1()
		{
			try
			{
				long duration = (long)(Math.random()*10);
				TimeUnit.SECONDS.sleep(duration);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
