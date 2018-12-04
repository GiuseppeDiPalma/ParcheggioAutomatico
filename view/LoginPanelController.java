package dipalma.parcheggioautomatico.view;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class LoginPanelController {

	@FXML
	private Button amministratoreButton;
	@FXML
	private Button clienteButton;
	@FXML
	private Button checkOutButton;
	@FXML
	private ImageView imageView;
	
	
	public LoginPanelController()
	{
		
	}
	
	@FXML
	private void initialize()
	{
		
	}
	
	@FXML
	public void handleAmministratore()
	{
		AmministratorePanelController loginAmm = new AmministratorePanelController();
		loginAmm.inizializzaAmministratorePanelView();
	}
	
	
	@FXML
	public void handleCliente()
	{
		ClientePanelController loginCliente = new ClientePanelController();
		loginCliente.inizializzaClientePanelView();

	}
	
	@FXML
	public void handlecheckOut()
	{
		CheckOutController uscitaEpagamento = new CheckOutController();
		uscitaEpagamento.inizializzaChekOutController();
	}
	
}
