package dipalma.parcheggioautomatico.model;

public class ContextPagamento {

	private PagamentoStrategy Pagamento;
	
	public ContextPagamento(PagamentoStrategy Pagamento) 
	{
		this.Pagamento = Pagamento;
	}
	
	public float eseguiPagamento(float costo)
	{
		return Pagamento.pagamento(costo);
	}
}
