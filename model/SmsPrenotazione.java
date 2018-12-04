package dipalma.parcheggioautomatico.model;

public class SmsPrenotazione  implements PrenotazioneStrategy {

	@Override
	public int prenotazione(String email, String numeroCell) {
		System.out.println("Stai prenotando via SMS con: "+numeroCell);
		return 0;
		
	}

}
