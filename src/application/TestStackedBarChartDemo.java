package application;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestStackedBarChartDemo extends Application {
	private TableView<Tacher> tacherTable = new TableView<Tacher>();
	private static 	ArrayList<Tacher> list;
	private static  StackedBarChart<Number,String> barChart;
	private static XYChart.Series<Number,String> dataSeries1;
	private static XYChart.Series<Number,String> dataSeries2;
	@Override
	public void start(Stage primaryStage) throws Exception {



		TextField field=new TextField();
		field.setPromptText("Name of Tacher");
		TextField field2=new TextField();
		field2.setPromptText("Length");
		TextField field3=new TextField();
		field3.setPromptText("Previous List  uses: ' , '");


		Button Add = new Button("Add tacher");
		Add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// You can get the new data from DB
				List<Tacher> newProducts = new ArrayList<Tacher>();
				String Name=field.getText().trim();
				String length=field2.getText().trim();

				if (!Name.equals(null)) {
					Tacher tacher=new Tacher();
					tacher.setName(Name);
					tacher.setLength(Integer.parseInt(length));

					ArrayList<String> previousList=new ArrayList<String>();
					previousList.add("A");
					tacher.setPreviousList(previousList);
					list.add(tacher);
					tacherTable.getItems().clear();
					tacherTable.getItems().addAll(list);
					//barChart.getData().clear();
					dataSeries1.getData().clear();
					dataSeries2.getData().clear();

					configCHart();

				}
			}
		});

		TableColumn nameCol = new TableColumn("Name");
		nameCol.setMinWidth(100);
		nameCol.setCellValueFactory(new PropertyValueFactory<Tacher, String>("name"));

		TableColumn lengthCol = new TableColumn("length");
		lengthCol.setCellValueFactory(new PropertyValueFactory<Tacher, String>("length"));

		TableColumn previousListCol = new TableColumn("previousList");
		previousListCol.setCellValueFactory(new PropertyValueFactory<Tacher, String>("previousList"));
		tacherTable.getColumns().addAll(nameCol, lengthCol,previousListCol);
		tacherTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		// You can get the data from DB
		List<Tacher> tacher = lowdDdata();




		//productTable.getItems().addAll(products);
		tacherTable.setItems(FXCollections.observableArrayList(tacher));


		HBox configbox=new HBox(field,field2,field3,Add);
		configbox.setPadding(new Insets(15, 12, 15, 12));
		configbox.setSpacing(10);		

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.getChildren().addAll(tacherTable,configbox);







		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("les tâches");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Temps");

		// Create a StackedBarChart
		barChart = new StackedBarChart<Number,String>( yAxis,xAxis);
		dataSeries1 = new XYChart.Series<Number,String>();
		dataSeries1.setName("--");

		// dataSeries1.getNode().setStyle("-fx-bar-gap: 0.0");
		dataSeries2 = new XYChart.Series<Number,String>();
		dataSeries2.setName("durée");

		/*   ArrayList l1= new ArrayList<String >() ;
  	  l1.add("A");
  	  l1.add("B");
  	  ArrayList l2= new ArrayList<String >() ;
  	  l2.add("D");
  	  l2.add("A");
       List<Tacher> list= new ArrayList<Tacher>();
  	 list.add(new Tacher("A", null, 2));
  	 list.add(new Tacher("B",null, 6));
  	 list.add(new Tacher("C", l1, 3));
   	 list.add(new Tacher("D", l1, 5));
  	 list.add(new Tacher("E", l2, 3));
  	  ArrayList lengthList= new ArrayList<Integer >() ;
  	  l1.add("A");
  	  l1.add("B");
  	 list.forEach(x ->{
  		 //System.out.println(x.length);
  		 lengthList.add(x.length);

  	});
  	 System.out.println(max(lengthList));

		 */
		/**Lowd DATA in the LISTE */
		list=lowdDdata();
		/**configuration du list */
		configCHart();  



		barChart.setTitle("Le Diagramme De Gantt");

		HBox hbox = new HBox(barChart);

		primaryStage.setTitle("Le Diagramme De Gantt (JavaFX StackedBarChart)");
		VBox box=new VBox(vbox,hbox);
		box.setPadding(new Insets(15, 12, 15, 12));
		box.setSpacing(10);		
		Scene scene = new Scene(box);

		primaryStage.setScene(scene);
		primaryStage.setHeight(700);
		primaryStage.setWidth(800);

		primaryStage.show();
	}

	private void configCHart() {
		for (int i = 0; i < list.size() ; i++) {

			if (list.get(i).getPreviousList()==null) {

				dataSeries1.getData().add(new XYChart.Data<Number,String>(0,list.get(i).getName()));
				dataSeries2.getData().add(new XYChart.Data< Number,String>( list.get(i).getLength(),list.get(i).getName()));
				list.get(i).setDatefin(list.get(i).getLength());
			} else {


				ArrayList lengthList2= new ArrayList<Integer >() ;
				for (String nameTacher : list.get(i).getPreviousList()) {
					for (Tacher t : list) {
						if (t.getName().equals(nameTacher)) {
							lengthList2.add(t.getDatefin());
						}
					}

				}
				System.out.println(max(lengthList2));
				dataSeries1.getData().add(new XYChart.Data<Number,String>( max(lengthList2),list.get(i).getName()));
				dataSeries2.getData().add(new XYChart.Data< Number,String>( list.get(i).getLength() ,list.get(i).getName()));

				list.get(i).setDatefin(max(lengthList2)+list.get(i).getLength());

			}



		}
		barChart.getData().add(dataSeries1);
		barChart.getData().add(dataSeries2);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
	public static int max(ArrayList<Integer> lengthList) {
		if (lengthList.size() == 0) {
			// ...
		}

		int max =   (int) lengthList.get(0);

		for (Integer a : lengthList) {
			if (a > max)
				max = a;
		}

		return max;
	}
	public static ArrayList<Tacher>lowdDdata(){
		ArrayList<Tacher> list= new ArrayList<Tacher>();
		list.add(new Tacher("C", null, 5));
		list.add(new Tacher("E", null, 9));
		list.add(new Tacher("J", new ArrayList<String>(Arrays.asList("E")), 11));      
		list.add(new Tacher("A", new ArrayList<String>(Arrays.asList("J")), 3));
		list.add(new Tacher("B",new ArrayList<String>(Arrays.asList("E")), 6));
		list.add(new Tacher("D", new ArrayList<String>(Arrays.asList("B")), 2));
		list.add(new Tacher("F", new ArrayList<String>(Arrays.asList("D","J")), 3));
		list.add(new Tacher("G", new ArrayList<String>(Arrays.asList("E","K")), 12));
		list.add(new Tacher("H", new ArrayList<String>(Arrays.asList("K","E")), 5));     
		list.add(new Tacher("I", new ArrayList<String>(Arrays.asList("A","G")), 7));      
		list.add(new Tacher("K", new ArrayList<String>(Arrays.asList("C","E")), 2));      

		return list;	 
	}

	/*

	  public static ArrayList<Tacher>lowdDdata(){
		ArrayList<Tacher> list= new ArrayList<Tacher>();
		list.add(new Tacher("A", null, 3));
		list.add(new Tacher("B",null, 6));
		list.add(new Tacher("C", null, 2));
		list.add(new Tacher("D", new ArrayList<String>(Arrays.asList("A")), 4));
		list.add(new Tacher("E", new ArrayList<String>(Arrays.asList("A")), 2));
		list.add(new Tacher("F", new ArrayList<String>(Arrays.asList("A")), 2));
		list.add(new Tacher("G", new ArrayList<String>(Arrays.asList("B","D")), 4));
		list.add(new Tacher("H", new ArrayList<String>(Arrays.asList("C","E")), 3));      
		return list;	 
	}

	 */
}