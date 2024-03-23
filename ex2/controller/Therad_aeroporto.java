package controller;

import java.util.concurrent.Semaphore;

public class Therad_aeroporto extends Thread
{
	int aviao_id;
	Semaphore semaforo_limite_pista;
	Semaphore semaforo_limite_decolagem;
	

	public Therad_aeroporto(int aviao_id, Semaphore semaforo_limite_pista, Semaphore semaforo_limite_decolagem)
	{
		this.aviao_id = aviao_id;
		this.semaforo_limite_pista = semaforo_limite_pista;
		this.semaforo_limite_decolagem = semaforo_limite_decolagem;
	}
	
	public void run()
	{
		int num_pista = (int)(Math.random()*(3-1)+1);
		
		//2 para pista norte
		System.out.println(num_pista);
		if (num_pista == 1)
		{
			try
			{
				semaforo_limite_pista.acquire();
				procedimento_decolagem(num_pista);
			}
			catch(Exception e)
			{
				System.err.println(e.getMessage());
			}
			finally
			{
				semaforo_limite_pista.release();
			}
		}
		//2 para pista sul
		else
		{
			try
			{
				semaforo_limite_pista.acquire();
				procedimento_decolagem(num_pista);
			}
			catch(Exception e)
			{
				System.err.println(e.getMessage());
			}
			finally
			{
				semaforo_limite_pista.release();
			}
		}

	}
	
	private void procedimento_decolagem(int num_pista)
	{
		String pista = (num_pista==1?"Norte":"Sul");
		
		System.out.println("Thread#" + getId() + ", aviao " + aviao_id + " entrou na pista " + pista);
		
		try 
		{
			semaforo_limite_decolagem.acquire();
			manobrar(pista);
			taxiar(pista);
			decolar(pista);
			afastar(pista);
		} 
		catch (InterruptedException e) 
		{
			System.err.println(e.getMessage());
		}
		finally
		{
			semaforo_limite_decolagem.release();
		}
	}
	
	private void manobrar(String pista)
	{
		int tempo;
		tempo = (int)(Math.random()*(701-300)+300);
		
		System.out.println("Thread#" + getId() + ", aviao " + aviao_id + " na psiat " + pista + " esta MANOBRANDO, tempo estimado: " + tempo + "ms");
		
		try 
		{
			sleep(tempo);
		} 
		catch (InterruptedException e) 
		{
			System.err.println(e.getMessage());
		}
		
	}
	
	private void taxiar(String pista)
	{
		int tempo;
		tempo = (int)(Math.random()*(1001-500)+500);
		
		System.out.println("Thread#" + getId() + ", aviao " + aviao_id + " na psiat " + pista + " esta TAXIANDO, tempo estimado: " + tempo + "ms");
		
		try 
		{
			sleep(tempo);
		} 
		catch (InterruptedException e) 
		{
			System.err.println(e.getMessage());
		}
	}
	
	private void decolar(String pista)
	{
		int tempo;
		tempo = (int)(Math.random()*(801-600)+600);
		
		System.out.println("Thread#" + getId() + ", aviao " + aviao_id + " na psiat " + pista + " esta DECOLANDO, tempo estimado: " + tempo + "ms");
		
		try 
		{
			sleep(tempo);
		} 
		catch (InterruptedException e) 
		{
			System.err.println(e.getMessage());
		}
	}
	
	private void afastar(String pista)
	{
		int tempo;
		tempo = (int)(Math.random()*(801-600)+600);
		
		System.out.println("Thread#" + getId() + ", aviao " + aviao_id + " na psiat " + pista + " esta SE AFASTANDO, tempo estimado: " + tempo + "ms");
		
		try 
		{
			sleep(tempo);
		} 
		catch (InterruptedException e) 
		{
			System.err.println(e.getMessage());
		}
	}
}