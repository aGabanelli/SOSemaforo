package main;

import java.util.concurrent.Semaphore;

import controller.ThreadsCozinha;

public class Principal {

	public static void main(String[] args) {

		int permissoes = 3;
		Semaphore semaforo = new Semaphore(permissoes);
		int tempoCozimento;
		String prato;

		for (int idThread = 0; idThread < 5; idThread++) {
			Thread thread;
			if (idThread % 2 == 0) {
				tempoCozimento = (int) (Math.random() * 1201) + 600;
				prato = "Lasanha à Bolonhesa";
				thread = new ThreadsCozinha(idThread, prato, semaforo, tempoCozimento);
			} else {
				tempoCozimento = (int) (Math.random() * 801) + 500;
				prato = "Sopa de Cebola";
				thread = new ThreadsCozinha(idThread, prato, semaforo, tempoCozimento);
			}
			thread.start();
		}
	}
}
