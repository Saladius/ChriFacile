package ff.cimex.chrifacile.controller;

import ff.cimex.chrifacile.request.dto.LoginRequest;
import ff.cimex.chrifacile.request.dto.UserRegistrationDto;
import ff.cimex.chrifacile.response.dto.JwtAuthenticationResponse;
import ff.cimex.chrifacile.service.UserService;
import ff.cimex.chrifacile.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto request) {
        userService.registerUser(request);
        String jwt = userService.getJwtToken(request.getUsername(),request.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyEmail(@RequestParam("token") String token) {
        // Code pour vérifier l'e-mail
        return null;
    }

   @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
       String jwt = userService.getJwtToken(request.getUsername(),request.getPassword());
       return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }


    /*@PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        // Code pour déconnecter l'utilisateur
        return null;
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        // Code pour obtenir les informations du profil utilisateur
        return null;
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateUserProfile(@RequestBody UserProfileUpdateRequest request) {
        // Code pour mettre à jour les informations du profil utilisateur
        return null;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        // Code pour initier la procédure de récupération de mot de passe
        return null;
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        // Code pour réinitialiser le mot de passe
        return null;
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        // Code pour permettre à l'utilisateur de changer son mot de passe
        return null;
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
        // Code pour rafraîchir le token d'authentification
        return null;
    }

    @PostMapping("/resend-verification-email")
    public ResponseEntity<?> resendVerificationEmail(@RequestBody ResendVerificationEmailRequest request) {
        // Code pour renvoyer l'e-mail de vérification
        return null;
    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<?> deleteAccount(@RequestBody DeleteAccountRequest request) {
        // Code pour permettre à l'utilisateur de supprimer son compte
        return null;
    }*/
}
