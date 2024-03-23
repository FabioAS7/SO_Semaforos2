package controller;

import java.util.concurrent.Semaphore;

public class Thread_cavaleiro extends Thread
{
	int id;
	Semaphore semaforo;
	static int tocha = 1;
	static int pedra = 1;
	static boolean[] portas = {true,true,true,true};
	
	public Thread_cavaleiro(int id, Semaphore semaforo)
	{
		this.id = id;
		this.semaforo = semaforo;
	}
	
	public void run()
	{
		movimento();
		try
		{
			semaforo.acquire();
			escolher_portas();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			semaforo.release();
		}
	}
	
	private void movimento()
	{
		int posicao_atual=0, vantagem_tocha = 0, vantagem_pedra =0;
		int movimento;
		String msgtocha = "", msgpedra = "";
		
		System.out.println("Thread#" + getId() + ", Cavaleiro " + id + ", COMECOU A SE MOVIMENTAR");
		while(posicao_atual <= 2000)
		{
			
			movimento = (int)(Math.random()*(5-2)+2+vantagem_tocha+vantagem_pedra);
			posicao_atual = posicao_atual + movimento;
			
			if(tocha == 1 && posicao_atual>=500)
			{
				tocha = 0;
				vantagem_tocha = 2;
				msgtocha = "(com o auxilou da tocha)";
			}
			if(pedra == 1 && posicao_atual>=1500)
			{
				pedra = 0;
				vantagem_pedra = 2;
				msgpedra = "(com o auxio da pedra)";
			}
			System.out.println("Thread#" + getId() + ", Cavaleiro " + id + ", se moveu +" + movimento + "m, esta na posicao " + posicao_atual + "m " + msgtocha + msgpedra);
			
			try 
			{
				sleep(20);
			} 
			catch (Exception e) 
			{
				System.err.println(e.getMessage());
			}
		}
		System.out.println("Thread#" + getId() + ", Cavaleiro " + id + ", CHEGOU AS PORTAS");
	}
	
	private void escolher_portas()
	{
		int escolha;
		do
		{
			escolha = (int)(Math.random()*4);
			if(portas[escolha] == true)
			{
				System.out.println("Thread#" + getId() + ", Cavaleiro " + id + " escolheu a porta " + (escolha+1) + (escolha == 3 && portas[escolha] == true?" e VIVEU":" e morreu..."));
				portas[escolha]=false;
				break;
			}		
		}while(portas[escolha] == false);
	}
}
