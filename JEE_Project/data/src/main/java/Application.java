import entities.Console;
import entities.Game;
import entities.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.ConsoleService;
import services.GameService;
import services.UserService;

import java.util.HashMap;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("core.config");
        final UserService userService = context.getBean(UserService.class);
        final GameService gameService = context.getBean(GameService.class);
        final ConsoleService consoleService = context.getBean(ConsoleService.class);
        //final OrderService orderService = context.getBean(OrderService.class); //To remove at the end
        //final CommentService commentService = context.getBean(CommentService.class);
        cleanDB(userService, gameService, consoleService); //orderService
        final Map<String, User> users = registerUsers(userService);
        final Map<String, Game> games = registerGames(gameService);
        final Map<String, Console> consoles = registerConsoles(consoleService);
        //final Map<String, Order> orders = registerProjects(projectService, companies, customers);
    }


    private static void cleanDB(final UserService userService, final GameService gameService, final ConsoleService consoleService) {
        userService.deleteAll();
        gameService.deleteAll();
        //orderService.deleteAll();
        consoleService.deleteAll();
    }


    private static Map<String, User> registerUsers(final UserService userService) {
        //TODO adjust content
        Map<String, User> users = new HashMap<>();
        users.put("worldline", createUser("Worldline", userService));
        users.put("sopra", createUser("Sopra Steria", userService));
        users.put("atos", createUser("Atos", userService));
        users.put("cap", createUser("Cap Gemini", userService));
        users.put("cgi", createUser("CGI", userService));
        return users;
    }


    private static User createUser(String userName, final UserService userService) {
        //TODO adjust content
        System.out.println("Registring " + userName);
        User user = new User();
        user.setName(userName);
        userService.save(user);
        return user;
    }


    private static Map<String, Game> registerGames(final GameService gameService) {
        //TODO adjust content
        Map<String, Game> games = new HashMap<>();
        games.put("retail", createGame("retail", gameService));
        games.put("healthcare", createGame("healthcare", gameService));
        games.put("phone", createGame("phone", gameService));
        games.put("aero", createGame("aeronautics", gameService));
        games.put("food", createGame("food", gameService));
        return games;
    }


    private static Game createGame(String name, final GameService gameService) {
        //TODO adjust content
        System.out.println("Registring " + name);
        Game game = new Game();
        game.setName(name);
        gameService.save(game);
        return game;
    }

    private static Map<String, Console> registerConsoles(final ConsoleService consoleService) {
        //TODO adjust content
        Map<String, Console> consoles = new HashMap<>();
        consoles.put("retail", createConsole("retail", consoleService));
        consoles.put("healthcare", createConsole("healthcare", consoleService));
        consoles.put("phone", createConsole("phone", consoleService));
        consoles.put("aero", createConsole("aeronautics", consoleService));
        consoles.put("food", createConsole("food", consoleService));
        return consoles;
    }


    private static Console createConsole(String name, final ConsoleService consoleService) {
        //TODO adjust content
        System.out.println("Registring " + name);
        Console console = new Console();
        console.setName(name);
        consoleService.save(console);
        return console;
    }

}
