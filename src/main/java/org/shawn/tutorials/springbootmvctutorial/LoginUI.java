package org.shawn.tutorials.springbootmvctutorial;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path = "/vaadinlogin")
@Theme("valo")
public class LoginUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(new Label("Vaadin login page.", ContentMode.HTML));
		layout.addComponent(new Button("Login as admin", e -> {
			Notification.show("Login...");
		}));
		setContent(layout);

	}

}
