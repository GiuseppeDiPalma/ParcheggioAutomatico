package dipalma.parcheggioautomatico.model;

public class ContextPrenotazione {

	private PrenotazioneStrategy Prenotazione;
	
	public ContextPrenotazione(PrenotazioneStrategy Prenotazione)
	{
		this.Prenotazione = Prenotazione;
	}
	
	public int eseguiPrenotazione(String email, String numeroCell)
	{
		return Prenotazione.prenotazione(email, numeroCell);
	}
}