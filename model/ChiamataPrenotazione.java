package dipalma.parcheggioautomatico.model;

public class ChiamataPrenotazione  implements PrenotazioneStrategy {

	@Override
	public int prenotazione(String email, String numeroCell) {
		System.out.println("Stai prenotando via CHIAMATA con " +numeroCell);
		return 0;
		
	}

}
