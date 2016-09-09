package fr.pizzeria.ihm;

public class ActionClient {

	private String libelle;
	protected IhmHelperClient helper;

	public ActionClient(String libelle, IhmHelperClient helper) {
		super();
		this.libelle = libelle;
		this.helper = helper;
	}

	public void execute() {

	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
