package dipalma.parcheggioautomatico.view;

import java.net.URL;
import java.util.ResourceBundle;

import dipalma.parcheggioautomatico.model.VisualizzaStatistiche;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class UsoMezziGraph implements Initializable {

	
	@FXML
    private BarChart<?, ?> statisticheChart;
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//grafico per vedere tipo mezzo parcheggiato
		VisualizzaStatistiche prova = new VisualizzaStatistiche();
		XYChart.Series set1 = new XYChart.Series<>();
		set1.getData().add(new XYChart.Data("Suv", prova.numeroSuv()));
		set1.getData().add(new XYChart.Data("Utilitaria", prova.numeroUtilitaria()));
		set1.getData().add(new XYChart.Data("Motoveicolo",  prova.numeroMotoveicolo()));
		statisticheChart.getData().addAll(set1);
		
	}
}