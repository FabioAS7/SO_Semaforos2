package view;

import java.util.concurrent.Semaphore;

import controller.Thread_triatlo;

public class Main3 
{
	public static void main(String args[]) 
	{
		Semaphore semaforo = new Semaphore(5);
		for(int i=0; i<25; i++)
		{
			Thread_triatlo Thread = new Thread_triatlo(i, semaforo);
			Thread.start();
		}
	}
}