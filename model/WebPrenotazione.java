package dipalma.parcheggioautomatico.model;

public class WebPrenotazione implements PrenotazioneStrategy {

	@Override
	public int prenotazione(String email, String numeroCell) {
		System.out.println("Stai prenotando via WEB con "+email+" "+numeroCell);
		return 0;
	}

}
