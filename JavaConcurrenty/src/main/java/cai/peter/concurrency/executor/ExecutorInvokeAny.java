package cai.peter.concurrency.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ExecutorInvokeAny
{
	class UserValidator
	{
		String name;

		public String getName()
		{
			return name;
		}
		public UserValidator(String name)
		{
			super();
			this.name =
					name;
		}
		boolean validate(String name, String password)
		{
			Random random = new Random();
			try
			{
				long duration = (long)(Math.random()*10);
				System.out.printf("Validator %s: validating a usr during %d seconds\n", name, duration);
				TimeUnit.SECONDS.sleep(duration);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				return false;
			}

			return random.nextBoolean();

		}
	}

	class TaskValidator implements Callable<String>
	{
		UserValidator validator;
		String user;
		String password;
		public TaskValidator(UserValidator validator, String user, String password)
		{
			super();
			this.validator =
					validator;
			this.user =
					user;
			this.password =
					password;
		}

		public String call() throws Exception
		{
			if(!validator.validate(user,  password))
			{
				System.out.printf("%s The user has not been found.\n", validator.getName());
				throw new Exception("Error validating user");
			}
			System.out.printf("%s: The user has been found\n", validator.getName());

			return validator.getName();
		}
	}

	@Test
	public void test()
	{
		String username = "test";
		String password = "test";

		UserValidator ldapV =
				new UserValidator("LDAP");
		UserValidator dbV =
				new UserValidator("Database");

		TaskValidator ldapT =
				new TaskValidator(ldapV, username, password);
		TaskValidator dbT =
				new TaskValidator(dbV, username, password);


		List<TaskValidator> taskList = new ArrayList<>();
		taskList.add(ldapT);
		taskList.add(dbT);

		ExecutorService executor = (ExecutorService)Executors.newCachedThreadPool();
		String result = null;

		try
		{
			result = executor.invokeAny(taskList);
			System.out.printf("Main: Result: %s\n", result);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ExecutionException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		executor.shutdown();
		System.out.println("Main: end of the Execution");
	}
}
