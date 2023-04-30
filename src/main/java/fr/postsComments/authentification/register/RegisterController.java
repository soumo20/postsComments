package fr.postsComments.authentification.register;

import fr.postsComments.authentification.login.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class RegisterController {
    private final RegisterUserService registerUserService;

    public RegisterController(RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }


    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        registerUserService.register(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
