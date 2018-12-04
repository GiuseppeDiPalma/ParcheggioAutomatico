package dipalma.parcheggioautomatico.view;

import java.net.URL;
import java.util.ResourceBundle;


import dipalma.parcheggioautomatico.model.VisualizzaStatistiche;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


public class UsoDataGraph implements Initializable {

	
	@FXML
    private BarChart<?, ?> statisticheChart;
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;
    
	@FXML
	private Parent root;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	//@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		ObservableList<VisualizzaStatistiche> proviamo = VisualizzaStatistiche.usoDate();
		XYChart.Series set1 = new XYChart.Series<>();
		for(int i = 0; i< proviamo.size(); i++)
		{	
			set1.getData().add(new XYChart.Data<>(proviamo.get(i).getData(), proviamo.get(i).getFrequenza()));
		}
		statisticheChart.getData().addAll(set1);
		
		
	}
}
