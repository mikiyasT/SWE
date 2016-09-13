package control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import ui.*;
import data.*;

public enum Control {
	INSTANCE;
	Stage primaryStage;

	public void setStage(Stage st) {
		primaryStage = st;
	}

	public void setStart(Start start) {
		this.start = start;
	}

	// windows managed by Control
	//value set by setStart()
	Start start;
	Login login = Login.INSTANCE;
	AdminOnly adminOnly = AdminOnly.INSTANCE;
	Private privateWindow = Private.INSTANCE;
	Public1 publicWindow1 = Public1.INSTANCE;
	Public2 publicWindow2 = Public2.INSTANCE;
	MessageableWindow[] allWindows = {start, login, adminOnly, privateWindow, publicWindow1, publicWindow2};
	public void hideAll() {
		for(MessageableWindow w : allWindows) {
			if(w != null) {
				w.clearMessages();
				w.hide();
			}
		}
		primaryStage.hide();
	}
	
	private boolean requiresAdmin(MessageableWindow stage) {
		return stage == adminOnly;
	}

	private boolean requiresNoLogin(MessageableWindow stage) {
		return stage == publicWindow1 || stage == publicWindow2 || stage == login;
	}

	private boolean isLoggedIn = false;

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	private boolean hasAdminAccess = false;

	public void login(MessageableWindow origin, MessageableWindow target) {
		String username = login.getUserName();
		String password = login.getPassword();

		if (username.equals(PrivateLoginData.ADMIN_LOGIN) && password.equals(PrivateLoginData.ADMIN_PWD)) {
			isLoggedIn = true;
			hasAdminAccess = true;
		} else if (username.equals(PrivateLoginData.CUST_LOGIN) && password.equals(PrivateLoginData.CUST_PWD)) {
			isLoggedIn = true;
			hasAdminAccess = false;
		} else {
			isLoggedIn = false;
			hasAdminAccess = false;
		}
		if(!target.initialized()) target.init();
		if(target == login && !isLoggedIn) {
			login.displayError("Login failed");
		} else if (requiresNoLogin(target)) {
			hideAll();
			
			target.show();
			if (origin.equals(target)) { // must be login window
				login.displayInfo("Login successful");
			}
		} else if (requiresAdmin(target)) {
			if (hasAdminAccess) {
				hideAll();
				target.show();
			} else {
				hideAll();
				login.show();
				login.displayError("Access not authorized.");
			}
		} else { // requires login but not admin access
			if (isLoggedIn) {
				hideAll();
				target.show();
			} else {
				hideAll();
				login.show();
				login.displayError("Login failed");
			}
		}
	}

	public void logout() {
		isLoggedIn = false;
		hasAdminAccess = false;
		start.displayInfo("Logout successful");
	}

	public void backToStart(Stage stage) {
		stage.hide();
		primaryStage.show();
	}

	private class RequestLoginHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent evt) {
			System.out.println("login handler");
			if(!(login.initialized())) login.init();
			hideAll();
			login.show();
		}
	}

	public RequestLoginHandler getRequestLoginHandler() {
		return new RequestLoginHandler();
	}

	private class Public1Handler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent evt) {
			System.out.println("here");
			hideAll();
			publicWindow1.init();
			publicWindow1.show();
		}
	}

	public Public1Handler getPublic1Handler(MessageableWindow st) {
		return new Public1Handler();
	}

	private class Public2Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent evt) {
			hideAll();
			publicWindow2.init();
			publicWindow2.show();
		}
	}

	public Public2Handler getPublic2Handler(MessageableWindow st) {
		return new Public2Handler();
	}

	private class PrivateHandler implements EventHandler<ActionEvent> {
		private MessageableWindow comingFrom;

		public PrivateHandler(MessageableWindow st) {
			comingFrom = st;
		}

		public void handle(ActionEvent evt) {
			if(!privateWindow.initialized()) privateWindow.init();
			if (!isLoggedIn) {
				login.init(comingFrom, privateWindow);
				hideAll();
				login.show();
			} else {
				hideAll();
				privateWindow.show();
			}
		}
	}

	public PrivateHandler getPrivateHandler(MessageableWindow st) {
		return new PrivateHandler(st);
	}

	private class AdminOnlyHandler implements EventHandler<ActionEvent> {
		private MessageableWindow comingFrom;

		public AdminOnlyHandler(MessageableWindow st) {
			comingFrom = st;
		}

		public void handle(ActionEvent evt) {
			if(!adminOnly.initialized()) adminOnly.init();
			if (!hasAdminAccess) {
				login.init(comingFrom, adminOnly);
				hideAll();
				login.show();
			} else {
				hideAll();
				adminOnly.show();
			}
		}
	}

	public AdminOnlyHandler getAdminOnlyHandler(MessageableWindow st) {
		return new AdminOnlyHandler(st);
	}
	

}
