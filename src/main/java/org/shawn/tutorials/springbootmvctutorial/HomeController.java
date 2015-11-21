package org.shawn.tutorials.springbootmvctutorial;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Shawn on 15/11/21.
 */

@Controller
public class HomeController {

    @RequestMapping("/home")
    public String getHomeView() {
        return "/WEB-INF/views/home.jsp";
    }
}
