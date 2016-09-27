package dta.springmvc;

public class RequeteBody {

	private String operation;
	private double montant;
	
	public RequeteBody() {
		super();
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	public boolean isDebit() {
		if(operation.equals("DEBIT")){
			return true;
		}
		else{
			return false;
		}
	}
	
}
