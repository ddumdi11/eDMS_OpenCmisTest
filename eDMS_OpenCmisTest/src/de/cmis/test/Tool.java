package de.cmis.test;


public class Tool {

	public static void printAndLog(String messageString) {
		System.out.println(messageString);
		System.out.println("\tKlasse der Message: " + Thread.currentThread().getStackTrace()[2].toString());
	}

}
