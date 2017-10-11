package dev.console;

import java.util.Scanner;

import dev.service.CalculService;

public class AppLauncher {

	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in); 
			App app = new App(scanner, new CalculService());

		}catch(Exception e){}
	}

}
