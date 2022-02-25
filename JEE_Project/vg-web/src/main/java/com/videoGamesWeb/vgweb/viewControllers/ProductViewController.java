package com.videoGamesWeb.vgweb.viewControllers;

import com.videoGamesWeb.vgcore.entity.*;
import com.videoGamesWeb.vgcore.service.CommentService;
import com.videoGamesWeb.vgcore.service.ConsoleService;
import com.videoGamesWeb.vgcore.service.ProductService;
import com.videoGamesWeb.vgcore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.videoGamesWeb.vgweb.VgWebApplication.SESSION_USER_ID;

@Controller
@RequestMapping(value = "/product")
public class ProductViewController extends GenericViewController {

    private final static Logger logger = LoggerFactory.getLogger(ProductViewController.class);

    private final static String PRODUCT_PAGE = "product";

    private final ConsoleService consoleService;
    private final ProductService productService;
    private final CommentService commentService;
    private final UserService userService;

    public ProductViewController(ConsoleService consoleService,
                                 ProductService productService,
                                 CommentService commentService,
                                 UserService userService){
        this.consoleService = consoleService;
        this.productService = productService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping({"/{id}", "/{id}/{consoleGameName}"})
    public String getProduct(@PathVariable long id, @PathVariable(required = false) String consoleGameName, Model model,
                             @ModelAttribute("productAdded") String productAdded,
                             @ModelAttribute("reviewAdded") String reviewAdded){
        Optional<Product> productOpt = this.productService.findById(id);
        if (productOpt.isEmpty()) return "redirect:/";

        Product product = productOpt.get();

        long consoleId = product.getId();
        if (consoleGameName != null) {
            model.addAttribute("consoleGameName", consoleGameName);
            consoleId = this.consoleService.findIdByName(consoleGameName);
            final long finalConsoleId = consoleId;
            List<GameConsole> gameConsoles = ((Game)product).getGameConsoles().stream()
                    .filter(gc -> gc.getConsole().getId() == finalConsoleId).collect(Collectors.toList());
            model.addAttribute("gameConsole", gameConsoles.get(0));
        }

        if (!productAdded.isEmpty()){
            model.addAttribute("addedToBasket", "Ajouté au panier avec succès");
        } else if(!reviewAdded.isEmpty()){
            model.addAttribute("reviewAdded", "Avis envoyé avec succès");
        }

        model.addAttribute("product", product);
        model.addAttribute("consoleId", consoleId);
        model.addAttribute("dateFormat", new SimpleDateFormat("dd MMM yyyy"));
        return PRODUCT_PAGE;
    }

    @PostMapping("/{productId}/comment")
    public String postProductComment(@PathVariable long productId,
                                     @RequestParam String consoleUrl,
                                     @RequestParam int rating,
                                     @RequestParam String newComment,
                                     HttpSession session,
                                     RedirectAttributes redirectAttributes) {
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

        Optional<Product> productOpt = productService.findById(productId);
        if (productOpt.isEmpty()){
            return "redirect:/home";
        }

        Comment comment = new Comment();
        comment.setUser(userOpt.get());
        comment.setProduct(productOpt.get());
        comment.setRating(rating);
        comment.setContent(newComment.replaceAll("\n", "<br>"));

        this.commentService.addComment(comment);
        redirectAttributes.addAttribute("reviewAdded", true);
        return "redirect:/product/"+productId+"/"+consoleUrl;
    }
}
