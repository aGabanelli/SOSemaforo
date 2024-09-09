package controller;

import java.util.concurrent.Semaphore;

public class ThreadsCozinha extends Thread {

	private int idComida;
	private String prato;
	private int tempoCozimento;
	private static int pratoPronto;
	private Semaphore semaforo;

	public ThreadsCozinha(int idComida, String prato, Semaphore semaforo, int tempoCozimento) {
		this.idComida = idComida;
		this.prato = prato;
		this.semaforo = semaforo;
		this.tempoCozimento = tempoCozimento;
	}

	@Override
	public void run() {
		try {
			semaforo.acquire();
			prepararPrato();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
			pratoPronto--;
			System.out.println("#" + idComida + " | O prato " + prato + " foi entregue!");
		}
	}

	private void prepararPrato() {
		int tempo = 100;
		int pProgressao = 0;

		System.out.println("#" + idComida + " | O prato " + prato + " iniciou o cozimento!");
		while (tempo < tempoCozimento) {
			pProgressao = (tempo * 100 ) / tempoCozimento;
			tempo += 100;
			System.out.println("#" + idComida + " | Prato " + prato + " está sendo preparado --> " + pProgressao + "%");
		}
		System.out.println("#" + idComida + " | O prato " + prato + " está pronto para ser entregue!");
		pratoPronto++;
	}

}
