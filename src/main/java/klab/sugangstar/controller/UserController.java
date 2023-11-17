package klab.sugangstar.controller;

import klab.sugangstar.domain.User;
import klab.sugangstar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create-user")
    public void saveUser(@RequestBody @Valid User user){
        userService.createUser(user);
        //return user;
    }
}
