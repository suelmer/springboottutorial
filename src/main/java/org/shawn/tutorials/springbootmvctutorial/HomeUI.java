package org.shawn.tutorials.springbootmvctutorial;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;

import java.security.Principal;


/**
 * Created by Shawn on 15/11/22.
 */

@SpringUI
public class HomeUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Principal user = vaadinRequest.getUserPrincipal();
        Authentication authentication = (Authentication) user;
        if (VaadinService.getCurrentRequest().isUserInRole("ADMIN"))
            setContent(new Label(String.format("<h1>Vaadin Home [%s]</h1>", user.getName()), ContentMode.HTML));
        else
            setContent(new Label(String.format("<h1>Unauthorize access</h1>", user.getName()), ContentMode.HTML));

        System.err.println(authentication.getName());
        System.err.println(authentication.getAuthorities().size());
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            System.err.println(authority.getAuthority());
        }

    }
}
