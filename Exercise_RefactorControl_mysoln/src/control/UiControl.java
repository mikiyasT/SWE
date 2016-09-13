package control;

import print.PrintApp;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UiControl {
	
	
	public static void printHandler(String data, Label messageBar){
		
		System.out.println(data);		
		messageBar.setText("Data printed to console");       
	}
	
	public static void clearHandler(TextField tf,Label messageBar){
		
		tf.clear();        
        messageBar.setText("Data cleared");  
	}

}
