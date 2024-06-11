package lib.project.backend.controllers;

import lib.project.backend.model.dto.UserLoginDTO;
import lib.project.backend.model.dto.UserRegisterDTO;
import lib.project.backend.model.fdto.UserLoginFDTO;
import lib.project.backend.model.fdto.UserRegisterFDTO;
import lib.project.backend.repositories.UserRepository;
import lib.project.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private final UserService userService = new UserService(userRepository);

    @PostMapping(value = "/api/login")
    public ResponseEntity<UserLoginDTO> login(@RequestBody UserLoginFDTO userLoginFDTO) {
        UserLoginDTO loggedUser = userService.authUser(userLoginFDTO);
        if (loggedUser.getToken() == null) {
            return ResponseEntity.badRequest().body(loggedUser);
        }
        return ResponseEntity.ok(loggedUser);
    }

    @PostMapping(value = "/api/register")
    public ResponseEntity<UserRegisterDTO> register(@RequestBody UserRegisterFDTO userRegisterFDTO) {
        UserRegisterDTO addedUser = userService.registerUser(userRegisterFDTO);
        if (!addedUser.isSuccess()) {
            return ResponseEntity.badRequest().body(addedUser);
        }
        return ResponseEntity.ok(addedUser);
    }
}
