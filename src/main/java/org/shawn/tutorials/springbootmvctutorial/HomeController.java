package org.shawn.tutorials.springbootmvctutorial;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Shawn on 15/11/21.
 */

@Controller
public class HomeController {

    @RequestMapping(value = "/myLogin")
    public String getMyLoginView() {
        return "mylogin";
    }

    @RequestMapping(value = "/home")
    public String getHomeView() {
        return "home";
    }
}
