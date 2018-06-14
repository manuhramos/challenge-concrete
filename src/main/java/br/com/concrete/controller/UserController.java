package br.com.concrete.controller;

import br.com.concrete.model.Message;
import br.com.concrete.model.User;
import br.com.concrete.model.UserExcludeStrategy;
import br.com.concrete.model.exception.*;
import br.com.concrete.model.vo.Login;
import br.com.concrete.service.UserService;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Emanuella Cavalcante on 13/06/2018.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private Gson gson = new GsonBuilder()
            .setExclusionStrategies(new UserExcludeStrategy())
            .create();

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody String body){
        User user = gson.fromJson(body, User.class);

        if(userService.findByEmail(user.getEmail()) != null){
            String message = gson.toJson(new CustomException(Message.EMAIL_INVALID.getValue()));
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).contentType(MediaType.APPLICATION_JSON).body(message);
        }
        user = userService.create(user);
        String userStr = gson.toJson(user);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(userStr);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody String body){
        try {
            Login login = gson.fromJson(body, Login.class);

            User user = userService.login(login);

            String userStr = gson.toJson(user);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(userStr);
        } catch (UserNotFoundException e){
            String message = gson.toJson(new CustomException(Message.USER_INVALID.getValue()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(message);
        } catch (InvalidPasswordException e){
            String message = gson.toJson(new CustomException(Message.USER_INVALID.getValue()));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON).body(message);
        }
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUserProfile(@PathVariable("id") String id,
                                                 @RequestHeader(value = "token") String token) {
        try {
            if(token == null || token.equals(""))
                throw new InvalidTokenException(Message.UNAUTHORIZED.getValue());

            User user = userService.getUserProfile(token, id);
            String userStr = gson.toJson(user);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(userStr);
        } catch (InvalidTokenException e){
            String message = gson.toJson(new CustomException(Message.UNAUTHORIZED.getValue()));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON).body(message);
        } catch (InvalidSessionException e){
            String message = gson.toJson(new CustomException(Message.SESSION_INVALID.getValue()));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON).body(message);
        }
    }
}
