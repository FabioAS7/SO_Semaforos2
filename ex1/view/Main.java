package view;

import java.util.concurrent.Semaphore;

import controller.Thread_cavaleiro;

public class Main 
{
	public static void main(String args[])
	{
		Semaphore semaforo = new Semaphore(1);
		for(int i=1;i<5;i++)
		{
			Thread_cavaleiro Thread = new Thread_cavaleiro(i,semaforo);
			Thread.start();
		}
	}

}
