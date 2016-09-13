
package ui;

import control.Control;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Start extends Application implements MessageableWindow {
	
    public static void main(String[] args) {
        launch(args);
    }
    Stage primaryStage;
    Text messageBar = new Text();
    @Override
    public void start(Stage primaryStage) {
    	this.primaryStage = primaryStage;
    	Control.INSTANCE.setStage(primaryStage);
    	Control.INSTANCE.setStart(this);
        primaryStage.setTitle("Login Techniques");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Options");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Button loginBtn = new Button("Log In");
        grid.add(loginBtn, 0, 1);
        Button logoutBtn = new Button("Log Out");
        grid.add(logoutBtn, 1, 1);
        
        Button public1Btn = new Button("Public Loc #1");
        grid.add(public1Btn, 0, 2);
        Button public2Btn = new Button("Public Loc #2");
        grid.add(public2Btn, 1, 2);

        Button privateBtn = new Button("Private Loc");
        grid.add(privateBtn, 0, 3);
        Button adminOnlyBtn = new Button("Admin Only Loc");
        grid.add(adminOnlyBtn, 1, 3);
        grid.add(messageBar, 1, 5);
        GridPane.setHalignment(messageBar, HPos.RIGHT);

        loginBtn.setOnAction(Control.INSTANCE.getRequestLoginHandler());
        logoutBtn.setOnAction(evt -> Control.INSTANCE.logout());
        public1Btn.setOnAction(Control.INSTANCE.getPublic1Handler(this));
        public2Btn.setOnAction(Control.INSTANCE.getPublic2Handler(this));
        privateBtn.setOnAction(Control.INSTANCE.getPrivateHandler(this));
        adminOnlyBtn.setOnAction(Control.INSTANCE.getAdminOnlyHandler(this));

        //Scene scene = new Scene(grid, 300, 200);
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	@Override
	public Text getMessageBar() {
		return messageBar;
	}
	@Override
	public void hide() {
		this.hide();
		
	}
	@Override
	public void show() {
		primaryStage.show();
		
	}
	@Override
	public void init() {
		//do nothing
		
	}
	@Override
	public boolean initialized() {
		return true;
	}
    
}