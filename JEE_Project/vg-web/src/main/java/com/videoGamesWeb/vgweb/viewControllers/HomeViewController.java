package com.videoGamesWeb.vgweb.viewControllers;

import com.videoGamesWeb.vgcore.service.ConsoleService;
import com.videoGamesWeb.vgcore.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomeViewController extends GenericViewController {

    private static final Logger logger = LoggerFactory.getLogger(HomeViewController.class);

    private final static String HOME_PAGE = "home";

    private final ProductService productService;
    private final ConsoleService consoleService;

    public HomeViewController(ProductService productService, ConsoleService consoleService) {
        this.productService = productService;
        this.consoleService = consoleService;
    }

    @GetMapping("")
    public String getPage(Model model){
        model.addAttribute("price_min", productService.getPriceMin());
        model.addAttribute("price_max", productService.getPriceMax());
        model.addAttribute("console_names", consoleService.getNames());
        return HOME_PAGE;
    }
}
