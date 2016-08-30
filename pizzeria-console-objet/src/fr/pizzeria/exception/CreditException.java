package fr.pizzeria.exception;

public class CreditException extends Exception {
	
	private static final String MSG = "Le solde ne peut pas dépasser 5 000 € !\n";

	public CreditException() {
		super(MSG);
		// TODO Auto-generated constructor stub
	}

	public CreditException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CreditException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CreditException(Throwable cause) {
		super(MSG, cause);
		// TODO Auto-generated constructor stub
	}

}
