package com.videoGamesWeb.vgweb.viewControllers;

import com.videoGamesWeb.vgcore.entity.Order;
import com.videoGamesWeb.vgcore.entity.User;
import com.videoGamesWeb.vgcore.service.OrderService;
import com.videoGamesWeb.vgcore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.*;

import static com.videoGamesWeb.vgweb.VgWebApplication.*;

@Controller
@RequestMapping("/user")
public class UserViewController extends GenericViewController {

    private final static Logger logger = LoggerFactory.getLogger(UserViewController.class);

    private final static String CREATE_PAGE = "user_create";
    private final static String CONNECT_PAGE = "user_connect";
    private final static String PROFILE_PAGE = "user_profile";
    private final static String UPDATE_PAGE = "user_update";

    private final UserService userService;
    private final OrderService orderService;

    public static boolean userInSession(HttpSession session) {
        return session.getAttribute(SESSION_USER_ID) instanceof Long;
    }

    public UserViewController(UserService userService, OrderService orderService){
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/create")
    public String getCreate(HttpSession session) {
        //String logger_anchor = "VC-/user/create:get";

        if (UserViewController.userInSession(session)) return "redirect:/user/profile";

        return CREATE_PAGE;
    }

    @PostMapping("/create")
    public String PostCreate(Model model,
                             @RequestParam String name,
                             @RequestParam String password,
                             @RequestParam String confirm_password,
                             @RequestParam(required=false) String mail,
                             @RequestParam(required=false) String address,
                             HttpSession session) {
        String logger_anchor = "VC-/user/create:post";

        if (UserViewController.userInSession(session)) return "redirect:/user/profile";

        model.addAttribute("prefix", this.prefix);
        if (name.isEmpty() || password.isEmpty() || confirm_password.isEmpty()) {
            model.addAttribute(ERROR_MSG, "Champ(s) vide(s)");
            return CREATE_PAGE;
        }
        if (!Objects.equals(password, confirm_password)) {
            model.addAttribute(ERROR_MSG, "Mots de passes différents");
            return CREATE_PAGE;
        }
        if (this.userService.existByName(name)) {
            model.addAttribute(ERROR_MSG, "Nom déjà utilisé");
            return CREATE_PAGE;
        }

        logger.info("{} - start creating instance", logger_anchor);
        User user = new User();
        user.setName(name);
        user.setPassword(B_CRYPT_PASSWORD_ENCODER.encode(password));
        user.setMail(mail != null ? mail : "");
        user.setAddress(address != null ? address : "");
        this.userService.save(user);
        logger.info("{} - created user", logger_anchor);

        return "redirect:/user/connect";
    }

    @GetMapping("/connect")
    public String getConnect(HttpSession session){
        //String logger_anchor = "VC-/user/connect:get";

        if (UserViewController.userInSession(session)) return "redirect:/user/profile";

        return CONNECT_PAGE;
    }

    @PostMapping("/connect")
    public String PostConnect(Model model,
                              @RequestParam String name,
                              @RequestParam String password,
                              HttpSession session) {
        //String logger_anchor = "VC-/user/connect:post";

        if (UserViewController.userInSession(session)) return "redirect:/user/profile";

        if (name.isEmpty() || password.isEmpty()) {
            model.addAttribute(ERROR_MSG, "Champ(s) vide(s)");
            return CONNECT_PAGE;
        }
        Optional<User> userOpt = this.userService.findByName(name);
        if (userOpt.isEmpty() || !B_CRYPT_PASSWORD_ENCODER.matches(password, userOpt.get().getPassword())) {
            model.addAttribute(ERROR_MSG, "Informations invalides");
            return CONNECT_PAGE;
        }

        session.setAttribute(SESSION_USER_ID, userOpt.get().getId());

        return "redirect:/user/profile";
    }

    @GetMapping("/disconnect")
    public String getDisconnect(HttpSession session){
        //String logger_anchor = "VC-/user/connect:get";

        session.invalidate();

        return "redirect:/user/connect";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, HttpSession session){
        //String logger_anchor = "VC-/user/profile:get";

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
        List<Order> orders = orderService.findAllByUserId(userOpt.get().getId());
        Map<Order, Float> ordersAndPrice = this.orderService.computeTotalAmountOfOrders(orders);

        model.addAttribute("orders", ordersAndPrice);
        return PROFILE_PAGE;
    }

    @GetMapping("/update")
    public String getUpdate(Model model, HttpSession session){
        //String logger_anchor = "VC-/user/update:get";

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
        return UPDATE_PAGE;
    }

    @PostMapping("/update")
    public String postUpdate(Model model,
                             @RequestParam String name,
                             @RequestParam(required=false) String password,
                             @RequestParam(required=false) String confirm_password,
                             @RequestParam(required=false) String mail,
                             @RequestParam(required=false) String address,
                             HttpSession session) {
        //String logger_anchor = "VC-/user/update:post";

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

        if (!Objects.equals(password, confirm_password)) {
            model.addAttribute(ERROR_MSG, "Mots de passes différents");
            model.addAttribute("user", user);
            return UPDATE_PAGE;
        }

        boolean update = false;
        if (!name.isEmpty() && !Objects.equals(user.getName(), name)) {
            user.setName(name);
            update = true;
        }
        if (!password.isEmpty() && !B_CRYPT_PASSWORD_ENCODER.matches(password, user.getPassword())) {
            user.setPassword(B_CRYPT_PASSWORD_ENCODER.encode(password));
            update = true;
        }
        if (!Objects.equals(user.getMail(), mail)) {
            user.setMail(mail);
            update = true;
        }
        if (!Objects.equals(user.getAddress(), address)) {
            user.setAddress(address);
            update = true;
        }

        if (update) {
            this.userService.save(user);
        }

        return "redirect:/user/profile";
    }

    @GetMapping("/delete")
    public String getDelete(HttpSession session) {
        String logger_anchor = "VC-/user/delete:get";

        long userId;
        try {
            userId = (long) session.getAttribute(SESSION_USER_ID);
        } catch (NullPointerException | NumberFormatException ignore) {
            return "redirect:/user/connect";
        }
        if (this.userService.existById(userId)) {
            logger.info("{} - delete user {}", logger_anchor, userId);
            this.orderService.deleteAllByUserId(userId);
            this.userService.deleteById(userId);
        }

        return "redirect:/user/disconnect";
    }
}
