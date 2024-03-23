package view;

import java.util.concurrent.Semaphore;
import controller.Therad_aeroporto;

public class Main2 
{
	public static void main(String args[])
	{
		Semaphore semaforo_pista = new Semaphore(2);
		Semaphore semaforo_decolagem = new Semaphore(1);
		
		for(int i=0; i<12; i++)
		{
			Therad_aeroporto Thread = new Therad_aeroporto(i, semaforo_pista, semaforo_decolagem);
			Thread.start();
		}
	}
}
