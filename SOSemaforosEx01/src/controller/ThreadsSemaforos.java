package controller;

import java.util.concurrent.Semaphore;

public class ThreadsSemaforos extends Thread {

	private Semaphore semaforo;
	private int idThread;
	private int calculo;
	private int transacao;

	public ThreadsSemaforos(Semaphore semaforo, int idThread, int calculo, int transacao) {
		this.semaforo = semaforo;
		this.idThread = idThread;
		this.calculo = calculo;
		this.transacao = transacao;
	}

	@Override
	public void run() {
		if (transacao == 1.5) {
			for (int i = 0; i < 3; i++) {
				calculo();
				try {
					semaforo.acquire();
					transacao();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaforo.release();
				}
			}
		} else {
			for (int i = 0; i < 2; i++) {
				calculo();
				try {
					semaforo.acquire();
					transacao();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaforo.release();
				}
			}

		}

	}

	private void transacao() {
		try {
			sleep(transacao);
			System.out.println("#" + idThread + " executou a operação de Transação no Banco de Dados");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void calculo() {
		try {
			sleep(calculo);
			System.out.println("#" + idThread + " executou a operação de Cálculo");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
