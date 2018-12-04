package dipalma.parcheggioautomatico.model;

public class PagamentoBancomat implements PagamentoStrategy {

	private String email;
	private String password;
	
	public PagamentoBancomat(String mm, String pss) 
	{
		this.email = mm;
		this.password = pss;
	}
	
	public String getEmail() 
	{
		return email;
	}

	public String getPassword() 
	{
		return password;
	}
	
	@Override
	public float pagamento(float costo) 
	{
		System.out.println("PAGAMENTO EFFETTUATO CON BANCOMAT DI: "+ costo);
		return 0;
	}

}
