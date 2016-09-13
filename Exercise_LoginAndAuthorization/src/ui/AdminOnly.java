package ui;

import control.Control;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminOnly extends Stage  {
	public final static AdminOnly INSTANCE = new AdminOnly();
	
	Text messageBar = new Text();
	private boolean initialized = false;
	public boolean initialized() {
		return initialized;
	}
	private AdminOnly() {}
	public void init() {

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Admin Window");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Button public1Btn = new Button("Public Screen #1");
		grid.add(public1Btn, 0, 2);
		Button public2Btn = new Button("Public Screen #2");
		grid.add(public2Btn, 1, 2);

		Button privateBtn = new Button("Private Screen");
		grid.add(privateBtn, 0, 3);
		Button loginBtn = new Button("Login Window");
		grid.add(loginBtn, 1, 3);
		grid.add(messageBar, 1, 5);
		GridPane.setHalignment(messageBar, HPos.RIGHT);
		
		loginBtn.setOnAction(Control.INSTANCE.getRequestLoginHandler());
        public1Btn.setOnAction(Control.INSTANCE.getPublic1Handler());
        public2Btn.setOnAction(Control.INSTANCE.getPublic2Handler());
        privateBtn.setOnAction(Control.INSTANCE.getPrivateHandler());

        initialized = true;
		// Scene scene = new Scene(grid, 300, 200);
		Scene scene = new Scene(grid);
		setScene(scene);
	}


	public Text getMessageBar() {
		return messageBar;
	}
}
