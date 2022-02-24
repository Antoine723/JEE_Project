package com.videoGamesWeb.vgweb.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.videoGamesWeb.vgcore.entity.Basket;
import com.videoGamesWeb.vgcore.entity.Product;
import com.videoGamesWeb.vgcore.entity.User;
import com.videoGamesWeb.vgcore.service.ProductService;
import com.videoGamesWeb.vgcore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.videoGamesWeb.vgweb.VgWebApplication.SESSION_BASKET;
import static com.videoGamesWeb.vgweb.VgWebApplication.SESSION_USER_ID;

@Controller
@RequestMapping("/basket")
public class BasketViewController extends GenericController{

    private final static Logger logger = LoggerFactory.getLogger(BasketViewController.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UserService userService;
    private final ProductService productService;

    public BasketViewController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("")
    public String getPage(Model model, HttpSession session) throws JsonProcessingException {
        long userId;
        try {
            userId = (long) session.getAttribute(SESSION_USER_ID);
        } catch (NullPointerException | NumberFormatException ignore) {
            return "redirect:/user/connect";
        }

        Optional<User> userOpt = userService.findById(userId);
        if (userOpt.isEmpty()) {
            return "redirect:/user/disconnect";
        }

        JsonNode json_basket = (JsonNode) session.getAttribute(SESSION_BASKET);
        Basket basket = json_basket == null ? new Basket() : new ObjectMapper().treeToValue(json_basket, Basket.class);

        List<Product> products = basket.getQtyByProduct()
                .keySet()
                .stream()
                .map(this.productService::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        float total = 0;
        Map<Product, Integer> qtyByProduct = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : basket.getQtyByProduct().entrySet()) {
            Optional<Product> productOpt = products.stream().filter(product -> product.getId() == entry.getKey()).findFirst();
            if (productOpt.isEmpty()) continue;

            int qty = entry.getValue();
            Product product = productOpt.get();
            total += product.getPrice() * qty;
            qtyByProduct.put(product, qty);
        }

        model.addAttribute("qtyByProduct", qtyByProduct);
        model.addAttribute("totalAmount", total);
        model.addAttribute("prefix", this.prefix);
        return "basket";
    }

    @PostMapping("/update/{productId}")
    public String postUpdateProduct(@PathVariable long productId,
                                    @RequestParam String redirect,
                                    @RequestParam int quantity,
                                    HttpSession session) throws JsonProcessingException {
        if (!UserViewController.userInSession(session)) return "redirect:/user/profile";

        Optional<Product> productOpt = this.productService.findById(productId);
        if (productOpt.isEmpty()) return "redirect:/home";

        JsonNode json_basket = (JsonNode) session.getAttribute(SESSION_BASKET);
        Basket basket = json_basket == null ? new Basket() : objectMapper.treeToValue(json_basket, Basket.class);

        basket.updateProductQty(productId, quantity, productOpt.get().getQuantity());

        session.setAttribute(SESSION_BASKET, objectMapper.valueToTree(basket));

        logger.info("Well added to basket");
        return "redirect:"+redirect;
    }

    @GetMapping("/remove/{productId}")
    public String getRemoveProduct(@PathVariable long productId, HttpSession session) throws JsonProcessingException {
        if (!UserViewController.userInSession(session)) return "redirect:/user/profile";

        Optional<Product> productOpt = this.productService.findById(productId);
        if (productOpt.isEmpty()) return "redirect:/home";

        JsonNode json_basket = (JsonNode) session.getAttribute(SESSION_BASKET);
        Basket basket = json_basket == null ? new Basket() : objectMapper.treeToValue(json_basket, Basket.class);

        basket.removeProduct(productId);

        session.setAttribute(SESSION_BASKET, objectMapper.valueToTree(basket));

        logger.info("Well removed from basket");
        return "redirect:/basket";
    }

    @PostMapping("/confirm")
    public String postConfirm(@RequestParam String selectedName,
                              @RequestParam(required = false) String otherName,
                              @RequestParam(required = false) String selectedAddress,
                              @RequestParam(required = false) String otherAddress,
                              HttpSession session) throws JsonProcessingException {
        long userId;
        try {
            userId = (long) session.getAttribute(SESSION_USER_ID);
        } catch (NullPointerException | NumberFormatException ignore) {
            return "redirect:/user/connect";
        }

        Optional<User> userOpt = userService.findById(userId);
        if (userOpt.isEmpty()) {
            return "redirect:/user/disconnect";
        }

        JsonNode json_basket = (JsonNode) session.getAttribute(SESSION_BASKET);
        if (json_basket == null) return "redirect:/user/disconnect";

        Basket basket = objectMapper.treeToValue(json_basket, Basket.class);

        String name = "current".equals(selectedName) ? userOpt.get().getName() : otherName;
        String address = "current".equals(selectedAddress) ? userOpt.get().getAddress() : otherAddress;

        if ("".equals(name) || "".equals(address)) return "redirect:/payment";

        logger.info("use '{}' '{}'", name, address);
        // convert basket to order

        //save order

        session.setAttribute(SESSION_BASKET, null);

        return "redirect:/user/profile"; //see order list
    }
}
