package application;


import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
 
public class StackedBarChartDemo extends Application {
 
    @Override
    public void start(Stage primaryStage) throws Exception {
 
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("les tâches");
 
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Temps");
 
        // Create a StackedBarChart
        StackedBarChart<Number,String> barChart = new StackedBarChart<Number,String>( yAxis,xAxis);

         XYChart.Series<Number,String> dataSeries1 = new XYChart.Series<Number,String>();
        dataSeries1.setName("--");
 
        
         XYChart.Series<Number,String> dataSeries2 = new XYChart.Series<Number,String>();
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
        ArrayList<Tacher> list=lowdDdata();
 for (int i = 0; i < list.size() ; i++) {
	
	 if (list.get(i).getPreviousList()==null) {
		
		 dataSeries1.getData().add(new XYChart.Data<Number,String>(0,list.get(i).getName()));
	     dataSeries2.getData().add(new XYChart.Data< Number,String>( list.get(i).getLength(),list.get(i).getName()));
	     list.get(i).setDatefin(list.get(i).getLength());
	 } else {
		
		
	  	  ArrayList lengthList2= new ArrayList<Integer >() ;
		for (String nameTacher : list.get(i).getPreviousList()) {
			 for (Tacher tacher : list) {
				if (tacher.getName().equals(nameTacher)) {
					lengthList2.add(tacher.getDatefin());
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
        
        barChart.setTitle("Le Diagramme De Gantt");
        
       HBox vbox = new HBox(barChart);
 
        primaryStage.setTitle("Le Diagramme De Gantt (JavaFX StackedBarChart)");
        Scene scene = new Scene(vbox, 400, 200);
 
        primaryStage.setScene(scene);
        primaryStage.setHeight(400);
        primaryStage.setWidth(600);
 
        primaryStage.show();
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
}