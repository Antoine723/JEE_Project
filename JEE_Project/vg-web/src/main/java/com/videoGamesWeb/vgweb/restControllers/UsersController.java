package com.videoGamesWeb.vgweb.restControllers;

import com.videoGamesWeb.vgcore.dto.UserDTO;
import com.videoGamesWeb.vgcore.entity.User;
import com.videoGamesWeb.vgcore.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.videoGamesWeb.vgweb.VgWebApplication.B_CRYPT_PASSWORD_ENCODER;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService){
        this.userService = userService;
    }

    @PostMapping( "/id")
    public ResponseEntity<String> postAdd(@RequestBody UserDTO userDTO){
        Optional<User> userOpt = userService.findByName(userDTO.getName());
        if (userOpt.isEmpty() || !B_CRYPT_PASSWORD_ENCODER.matches(userDTO.getPassword(), userOpt.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("-1");
        }
        return ResponseEntity.ok(Long.toString(userOpt.get().getId()));
    }

}
