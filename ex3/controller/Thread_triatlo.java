package controller;

import java.util.concurrent.Semaphore;

public class Thread_triatlo extends Thread
{
	int id_atleta;
	Semaphore semaforo;
	int pontuacao_tiro = 0;
	static int chegada = 0;
	static int podio[][] = new int[2][25];
	
	public Thread_triatlo(int id_atleta, Semaphore semaforo)
	{
		this.id_atleta = id_atleta;
		this.semaforo = semaforo;
	}
	
	public void run()
	{
		corrida();
		try 
		{
			semaforo.acquire();
			tiro();
		} 
		catch (Exception e) 
		{
			System.err.println(e.getMessage());
		}
		finally
		{
			semaforo.release();
		}
		ciclismo();
		
		if(chegada==25)
		{
			resultados();
		}
		
	}
	
	private void corrida()
	{
		int posicao_atual = 0;
		int movimento;
		
		System.out.println("Thread#" + getId() + ", Atleta n" + id_atleta + ", iniciou a CORRIDA");
		while(posicao_atual <= 3000)
		{
			movimento = (int)(Math.random()*(26-20)+20);
			posicao_atual = posicao_atual + movimento;
			System.out.println("Thread#" + getId() + ", Atleta n" + id_atleta + ", moveu " + movimento + "m e esta na posicao " + posicao_atual + "m (corrida)");
			try 
			{
				sleep(30);
			} 
			catch (InterruptedException e) 
			{
				System.err.println(e.getMessage());
			}
		}
		System.out.println("Thread#" + getId() + ", Atleta n" + id_atleta + ", finalizou a CORRIDA");
	
	}
	
	private void tiro()
	{
		int pontos;
		
		System.out.println("Thread#" + getId() + ", Atleta n" + id_atleta + ", iniciou o TIRO AO ALVO");
		for(int i=1; i<4; i++)
		{
			pontos = (int)(Math.random()*11);
			pontuacao_tiro = pontuacao_tiro + pontos;
			System.out.println("Thread#" + getId() + ", Atleta n" + id_atleta + ", utilizou seu " + i + " tiro e ganhou " + pontos + " pontos");
		}
		System.out.println("Thread#" + getId() + ", Atleta n" + id_atleta + ", finalizou o TIRO AO ALVO");
		
	}
	
	private void ciclismo()
	{
		int posicao_atual = 0;
		int movimento;
		
		System.out.println("Thread#" + getId() + ", Atleta n" + id_atleta + ", iniciou o CICLISMO");
		while(posicao_atual <= 5000)
		{
			movimento = (int)(Math.random()*(41-30)+30);
			posicao_atual = posicao_atual + movimento;
			System.out.println("Thread#" + getId() + ", Atleta n" + id_atleta + ", moveu " + movimento + "m e esta na posicao " + posicao_atual + "m (ciclismo)");
			try 
			{
				sleep(40);
			} 
			catch (InterruptedException e) 
			{
				System.err.println(e.getMessage());
			}
		}
		System.out.println("Thread#" + getId() + ", Atleta n" + id_atleta + ", finalizou o CICLISMO");
		
		podio[0][chegada] = id_atleta;
		podio[1][chegada] = ((250 - (10*chegada)) + pontuacao_tiro);
		chegada = chegada + 1;
	}
	
	private void resultados()
	{
		int aux[][] = new int [2][1];
		
		for(int i=0; i<25; i++)
		{
			for(int j=(i+1); j<24; j++)
			{
				if(podio[1][i]<podio[1][j])
				{
					aux[0][0] = podio[0][j];
					aux[1][0] = podio[1][j];
					podio[0][j] = podio[0][i];
					podio[1][j] = podio[1][i];
					podio[0][i] = aux[0][0];
					podio[1][i] = aux[1][0];
				}
			}
		}
		
		System.out.println("PODIO");
		for(int i=0; i<25; i++)
		{
			System.err.println("atleta " + podio[0][i] + ", pontuacao " + podio[1][i]);
		}
	}
}
