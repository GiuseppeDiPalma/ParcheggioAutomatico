package dipalma.parcheggioautomatico.view;

import java.net.URL;
import java.util.ResourceBundle;

import dipalma.parcheggioautomatico.model.VisualizzaStatistiche;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class UsoParcheggioGraph implements Initializable {

	
	@FXML
    private BarChart<?, ?> statisticheChart;
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ObservableList<VisualizzaStatistiche> usoParcheggio = VisualizzaStatistiche.usoParcheggio();	
		//grafico per vedere i parcheggi più occupati
		
		XYChart.Series set1 = new XYChart.Series<>();
		for(int i=0 ; i< usoParcheggio.size(); i++)
		{
			String prova = Integer.toString(usoParcheggio.get(i).getIdPar());
			set1.getData().add(new XYChart.Data<>(prova, usoParcheggio.get(i).getPrenotazioni()));
				
		}
		statisticheChart.getData().addAll(set1);
	}
}