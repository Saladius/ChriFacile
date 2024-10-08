package ff.cimex.chrifacile.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    private final String SECRET_KEY = "Fahlkaria+632q9sd6aiosdfjzqf4zef14sfq6sf1zee14f1scv1xd35ssh";
    private static final long EXPIRATION_TIME = 60 * 60 * 1000; // 1 hour in milliseconds

    public String generateJwtToken(Authentication authentication) {

        UserDetails principal = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(principal.getUsername())
                .claim("roles", principal.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()) )
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public Claims validateJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes()) // Ensure the secret key is byte array
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Collection<SimpleGrantedAuthority> getRolesFromToken(String token) {
        var roles = getClaimFromToken(token, claims -> claims.get("roles", String.class));
        return Arrays.stream(roles.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes()) // Ensure the secret key is byte array
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}