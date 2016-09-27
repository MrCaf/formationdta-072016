package dta.springmvc;

public class ReponseBody {

	private boolean success;
	private double solde;
	private String retour;
	
	public ReponseBody() {
		super();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public String getRetour() {
		return retour;
	}

	public void setRetour(String retour) {
		this.retour = retour;
	}
	
}
