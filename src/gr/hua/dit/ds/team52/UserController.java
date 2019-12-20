package gr.hua.dit.ds.team52;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")

public class UserController {

    @RequestMapping("/")
    public String showMyPage() {
        return "index";
    }
}
