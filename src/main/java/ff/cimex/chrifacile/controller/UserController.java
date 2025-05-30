package ff.cimex.chrifacile.controller;

import ff.cimex.chrifacile.constant.ConfigConstant;
import ff.cimex.chrifacile.entity.UserEntity;
import ff.cimex.chrifacile.request.dto.*;
import ff.cimex.chrifacile.response.dto.JwtAuthenticationResponse;
import ff.cimex.chrifacile.response.dto.UserDetailRecord;
import ff.cimex.chrifacile.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
        return getResponseEntity(user);
    }

    @GetMapping("/login/google")
    public ResponseEntity<?> loginUserWithGoogle(LoginRequestWithGoogle request) {
        UserEntity user = userService.loginWithGoogle(request);
        return getResponseEntity(user);
    }

    @PostMapping("/register/facebook")
    public ResponseEntity<?> registerUserWithFacebook(@RequestBody UserRegistrationFacebookDto request) {
        UserEntity user = userService.registerUserWithFacebook(request);
        return getResponseEntity(user);
    }

    @GetMapping("/login/facebook")
    public ResponseEntity<?> loginUserWithFacebook(@RequestBody LoginRequestWithFacebook request) {
        UserEntity user = userService.loginWithFacebook(request);
        return getResponseEntity(user);
    }

    @GetMapping("/verify-subscription")
    public ResponseEntity<?> verifySubscription(@RequestParam String token) {
        Date date = userService.userHaveValidSubscription();
        return ResponseEntity.ok(date);
    }


    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribeNewAbonnement(@RequestBody AbonnementDto abonnementDto) {
        userService.subscribNewAbonnement(abonnementDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{jwtToken}")
    public ResponseEntity<?> deleteUser(@PathVariable String jwtToken) {
        userService.deleteUser(jwtToken);
        return ResponseEntity.noContent().build();
    }



    @DeleteMapping
    public ResponseEntity<?> deleteUserByLoginAndPassword(@RequestBody String login, @RequestBody String password) {
        boolean success = userService.deleteUser(login, password);

        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping ("/user-detail")
    public ResponseEntity<?> getUserDetailRecord() {
        UserDetailRecord userDetailRecord = userService.getUserDetailRecord();
        return ResponseEntity.ok(userDetailRecord);
    }


    private ResponseEntity<?> getResponseEntity(UserEntity user) {
        if (user != null) {
            String jwt = userService.getJwtToken(user.getUsername(), ConfigConstant.SOCIAL_MEDIA_PASSWORD_ACCOUNT);
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
