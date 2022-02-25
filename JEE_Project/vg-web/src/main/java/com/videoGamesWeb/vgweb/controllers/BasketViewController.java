package com.videoGamesWeb.vgweb.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.videoGamesWeb.vgcore.entity.*;
import com.videoGamesWeb.vgcore.service.ConsoleService;
import com.videoGamesWeb.vgcore.service.OrderService;
import com.videoGamesWeb.vgcore.service.ProductService;
import com.videoGamesWeb.vgcore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

import static com.videoGamesWeb.vgweb.VgWebApplication.*;

@Controller
@RequestMapping("/basket")
public class BasketViewController extends GenericController{

    private final static Logger logger = LoggerFactory.getLogger(BasketViewController.class);

    private final static String BASKET_PAGE = "basket";
    private final static String PAYMENT_PAGE = "payment";

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UserService userService;
    private final ProductService productService;
    private final ConsoleService consoleService;
    private final OrderService orderService;

    public BasketViewController(UserService userService,
                                ProductService productService,
                                ConsoleService consoleService,
                                OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.consoleService = consoleService;
        this.orderService = orderService;
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
        List<Console> consoles = this.consoleService.findAll();
        Map<Product, Map<Product, Integer>> qtyByConsoleByProduct = new HashMap<>();
        for (Map.Entry<Long, Map<Long, Integer>> entry : basket.getQtyByProduct().entrySet()) {
            Optional<Product> productOpt = products.stream().filter(p -> p.getId() == entry.getKey()).findFirst();
            if (productOpt.isEmpty()) continue;

            Product product = productOpt.get();
            qtyByConsoleByProduct.put(product, new HashMap<>());
            for (Map.Entry<Long, Integer> subEntry : entry.getValue().entrySet()) {
                Optional<Console> consoleOpt = consoles.stream().filter(p -> p.getId() == subEntry.getKey()).findFirst();
                if (consoleOpt.isEmpty()) continue;

                int qty = subEntry.getValue();
                Console console = consoleOpt.get();
                total += product.getPrice() * qty;
                qtyByConsoleByProduct.get(product).put(console, qty);
            }
        }

        model.addAttribute("qtyByConsoleByProduct", qtyByConsoleByProduct);
        model.addAttribute("totalAmount", total);
        model.addAttribute("prefix", this.prefix);
        return BASKET_PAGE;
    }

    @PostMapping("/update/{productId}/{consoleId}")
    public String postUpdateProduct(@PathVariable long productId,
                                    @PathVariable long consoleId,
                                    @RequestParam int quantity,
                                    @RequestParam String redirect,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes) throws JsonProcessingException {
        if (!UserViewController.userInSession(session)) return "redirect:/user/profile";

        Optional<Product> productOpt = this.productService.findById(productId);
        if (productOpt.isEmpty()) return "redirect:/home";

        JsonNode json_basket = (JsonNode) session.getAttribute(SESSION_BASKET);
        Basket basket = json_basket == null ? new Basket() : objectMapper.treeToValue(json_basket, Basket.class);

        basket.updateProductQty(productId, consoleId, quantity, productOpt.get().getQuantity());

        session.setAttribute(SESSION_BASKET, objectMapper.valueToTree(basket));
        redirectAttributes.addAttribute("productAdded",true);

        logger.info("Well updated basket");
        return "redirect:"+redirect;
    }

    @GetMapping("/remove/{productId}/{consoleId}")
    public String getRemoveProduct(@PathVariable long productId, @PathVariable long consoleId, HttpSession session) throws JsonProcessingException {
        if (!UserViewController.userInSession(session)) return "redirect:/user/profile";

        Optional<Product> productOpt = this.productService.findById(productId);
        if (productOpt.isEmpty()) return "redirect:/home";

        JsonNode json_basket = (JsonNode) session.getAttribute(SESSION_BASKET);
        Basket basket = json_basket == null ? new Basket() : objectMapper.treeToValue(json_basket, Basket.class);

        basket.removeProduct(productId, consoleId);

        session.setAttribute(SESSION_BASKET, objectMapper.valueToTree(basket));

        logger.info("Well removed from basket");
        return "redirect:/basket";
    }

    @GetMapping("/payment")
    public String getPayment(Model model, HttpSession session){
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

        model.addAttribute("user", userOpt.get());
        return PAYMENT_PAGE;
    }

    @PostMapping("/payment")
    public String postPayment(Model model,
                              @RequestParam String selectedName,
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
        User user = userOpt.get();

        JsonNode json_basket = (JsonNode) session.getAttribute(SESSION_BASKET);
        if (json_basket == null) return "redirect:/user/disconnect";

        Basket basket = objectMapper.treeToValue(json_basket, Basket.class);

        String name = "current".equals(selectedName) ? user.getName() : otherName;
        String address = "current".equals(selectedAddress) ? user.getAddress() : otherAddress;

        if ("".equals(name) || "".equals(address)) {
            model.addAttribute(ERROR_MSG, "Informations incomplètes");
            return "payment";
        }

        this.orderService.addOrder(user, address, basket);

        session.setAttribute(SESSION_BASKET, null);

        return "redirect:/user/profile"; //see order list
    }
}
