package sfs.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sfs.domain.model.User;
import sfs.ports.api.UserService;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private RSAPrivateKey privateKey;

    public AuthController(UserService userService, @Value("${jwt.private.key.path}") String keyPath) {
        this.userService = userService;
        try {
            byte[] keyBytes = Files.readAllBytes(Paths.get(keyPath));
            String key = new String(keyBytes)
                    .replaceAll("-----BEGIN (.*)-----", "")
                    .replaceAll("-----END (.*)-----", "")
                    .replaceAll("\\s", "");

            byte[] decoded = Base64.getDecoder().decode(key);
            this.privateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        } catch (Exception e) {
            System.err.println("CRITICAL: Nie udało się załadować klucza prywatnego z pliku " + keyPath + ": " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String login) {
        if (privateKey == null) {
            return ResponseEntity.status(500).body("Błąd serwera: Brak skonfigurowanego klucza prywatnego.");
        }

        try {
            User user = userService.findUserByLogin(login);

            Algorithm algorithm = Algorithm.RSA256(null, privateKey);
            String token = JWT.create()
                    .withIssuer("SFS-Auth-Service")
                    .withSubject(user.getId())
                    .withClaim("login", user.getLogin())
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000)) // 1 godzina
                    .sign(algorithm);

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Błąd logowania: Nie znaleziono użytkownika");
        }
    }
}