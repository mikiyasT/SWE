package ui;

import control.Control;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Public2 extends Stage implements MessageableWindow {
	public static final Public2 INSTANCE = new Public2();
	Text messageBar = new Text();
	private boolean initialized =false;
	private Public2() {}
	public void init() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Public Screen #2");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Button public1Btn = new Button("Public Loc #1");
		grid.add(public1Btn, 0, 2);
		Button privateBtn = new Button("Private Loc");
		grid.add(privateBtn, 1, 2);

		Button adminOnlyBtn = new Button("Admin Only Loc");
		grid.add(adminOnlyBtn, 0, 3);
		Button loginBtn = new Button("Login Window");
		grid.add(loginBtn, 1, 3);
		grid.add(messageBar, 1, 5);
		GridPane.setHalignment(messageBar, HPos.RIGHT);

		loginBtn.setOnAction(Control.INSTANCE.getRequestLoginHandler());
        public1Btn.setOnAction(Control.INSTANCE.getPublic1Handler(this));
        privateBtn.setOnAction(Control.INSTANCE.getPrivateHandler(this));
        adminOnlyBtn.setOnAction(Control.INSTANCE.getAdminOnlyHandler(this));
        initialized = true;
		// Scene scene = new Scene(grid, 300, 200);
		Scene scene = new Scene(grid);
		setScene(scene);
	}

	@Override
	public Text getMessageBar() {
		return messageBar;
	}
	@Override
	public boolean initialized() {
		return initialized;
		
	}
}
