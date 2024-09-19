package ff.cimex.chrifacile.controller;

import ff.cimex.chrifacile.entity.UserEntity;
import ff.cimex.chrifacile.request.dto.LoginRequest;
import ff.cimex.chrifacile.request.dto.UserRegistrationDto;
import ff.cimex.chrifacile.request.dto.UserRegistrationFacebookDto;
import ff.cimex.chrifacile.request.dto.UserRegistrationGoogleDto;
import ff.cimex.chrifacile.response.dto.JwtAuthenticationResponse;
import ff.cimex.chrifacile.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto request) {
        UserEntity user = userService.registerUser(request);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            String jwt = userService.getJwtToken(request.getUsername(), request.getPassword());
            if (jwt != null) {
                return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }
    }

    @GetMapping("/login")
    public ResponseEntity<?> loginUser(LoginRequest request) {
        String jwt = userService.getJwtToken(request.getUsername(), request.getPassword());
        if (jwt != null) {
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register/google")
    public ResponseEntity<?> registerUserWithGoogle(@RequestBody UserRegistrationGoogleDto request) {

        UserEntity user = userService.registerUserWithGoogle(request);
        if (user != null) {
            String jwt = userService.getJwtToken(user.getUsername(), user.getPassword());
            if (jwt != null) {
                return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/login/google")
    public ResponseEntity<?> loginUserWithGoogle(UserRegistrationGoogleDto request) {
        UserEntity user = userService.loginWithGoogle(request);
        return getResponseEntity(user);
    }

    @PostMapping("/register/facebook")
    public ResponseEntity<?> registerUserWithFacebook(@RequestBody UserRegistrationFacebookDto request) {
        UserEntity user = userService.registerUserWithFacebook(request);
        return getResponseEntity(user);
    }

    @GetMapping("/login/facebook")
    public ResponseEntity<?> loginUserWithFacebook(@RequestBody UserRegistrationFacebookDto request) {
        UserEntity user = userService.loginWithFacebook(request);
        return getResponseEntity(user);
    }

    private ResponseEntity<?> getResponseEntity(UserEntity user) {
        if (user != null) {
            String jwt = userService.getJwtToken(user.getUsername(), user.getPassword());
            if (jwt != null) {
                return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
