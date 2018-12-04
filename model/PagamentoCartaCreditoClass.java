package dipalma.parcheggioautomatico.model;

public class PagamentoCartaCreditoClass implements PagamentoStrategy {

	private String name;
	private String cardNumber;
	private String cvv;
	private String dateOfExpiry;

	public PagamentoCartaCreditoClass(String nm, String ccNum, String cvv,String expiryDate)
	{
		this.setName(nm);
		this.setCardNumber(ccNum);
		this.setCvv(cvv);
		this.setDateOfExpiry(expiryDate);
	}

	@Override
	public float pagamento(float costo) {
		
		System.out.println("Pagamento effetuato con carta di credito di "+costo);
		return 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getDateOfExpiry() {
		return dateOfExpiry;
	}

	public void setDateOfExpiry(String dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}
}