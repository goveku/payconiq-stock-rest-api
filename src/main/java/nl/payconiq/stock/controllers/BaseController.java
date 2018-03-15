package nl.payconiq.stock.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/payconiq/api/stocks")

public class BaseController {

    @RequestMapping(value = "/view")
    public String index() {
        return "index";
    }

}
