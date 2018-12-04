package dipalma.parcheggioautomatico.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dipalma.parcheggioautomatico.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AmministratoreVisStatsController implements Initializable {
		
    @FXML
    private Button usoData;
    @FXML
    private Button usoParcheggio;
    @FXML
    private Button usoMezzi;
	@FXML
	private Parent root;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	public void handleUsoDataButton() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/GraphUsoData.fxml"));
		BarChart<?, ?> UsoDataGraph = (BarChart<?, ?>) loader.load();

		Scene secondScene = new Scene(UsoDataGraph);
		
		// New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setScene(secondScene);
        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        newWindow.show();
	}
	
	@FXML
	public void handleUsoParcheggioButton() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/GraphUsoParcheggio.fxml"));
		BarChart<?, ?> UsoParcheggioGraph = (BarChart<?, ?>) loader.load();
		
		Scene secondScene = new Scene(UsoParcheggioGraph);
		
		// New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setScene(secondScene);
        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        newWindow.show();
	}
	
	@FXML
	public void handleUsoMezzi() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/GraphUsoMezzi.fxml"));
		BarChart<?, ?> UsoMezziGraph = (BarChart<?, ?>) loader.load();
		
		Scene secondScene = new Scene(UsoMezziGraph);
		
		// New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setScene(secondScene);
        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        newWindow.show();
	}
	
	@FXML
	private void handleIndietro() throws IOException
	{
		root = FXMLLoader.load(Main.class.getResource("view/AmministratoreModifica.fxml"));
		Main.getPrimaryStage().setScene(new Scene(root));
	}
	
}
