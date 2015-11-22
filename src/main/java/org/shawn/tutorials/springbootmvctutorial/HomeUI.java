package org.shawn.tutorials.springbootmvctutorial;

import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

/**
 * Created by Shawn on 15/11/22.
 */

@SpringUI
public class HomeUI extends UI {


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(new Label("<h1>Vaadin Home</h1>", ContentMode.HTML));
    }
}
