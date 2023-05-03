package fr.postscomments.authentification.login;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class LoginController {

    private final ILoginUserService loginUserService;

    public LoginController(ILoginUserService loginUserService) {
        this.loginUserService = loginUserService;
    }


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(loginUserService.login(loginRequest));
    }

}
