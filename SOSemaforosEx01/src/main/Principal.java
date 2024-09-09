package main;

import java.util.concurrent.Semaphore;

import controller.ThreadsSemaforos;

public class Principal {

	public static void main(String[] args) {

		int permissoes = 3;
		Semaphore semaforo = new Semaphore(permissoes);
		int calculos;

		for (int idThread = 0; idThread < 21; idThread++) {
			Thread thread;
			if (idThread % 3 == 1) {
				System.out.println("----------------- THREAD " + idThread + " TEM RESTO = 1");
				calculos = (int)(Math.random() * 1001) + 200;
				thread = new ThreadsSemaforos(semaforo, idThread, calculos, 1000);
			} else if (idThread % 3 == 2) {
				System.out.println("----------------- THREAD " + idThread + " TEM RESTO = 2");
				calculos = (int) (Math.random() * 1501) + 500;
				thread = new ThreadsSemaforos(semaforo, idThread, calculos, 1500);
			} else {
				System.out.println("----------------- THREAD " + idThread + " TEM RESTO = 0");
				calculos = (int) (Math.random() * 2001) + 1000;
				thread = new ThreadsSemaforos(semaforo, idThread, calculos, 1500);
			}
			thread.start();
		}
	}
}
