package com.example.proyecto.security.auth;

import com.example.proyecto.model.Usuario;
import com.example.proyecto.repository.UsuarioRepository;
import com.example.proyecto.security.dto.AuthRequest;
import com.example.proyecto.security.dto.AuthResponse;
import com.example.proyecto.security.jwt.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UsuarioRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse authenticate(AuthRequest request) {
        // 1. Buscamos el usuario en la base de datos
        Usuario user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario no encontrado"));

        // 2. Como ya tenemos al usuario, nos fijamos si validó su correo
        if (user.getVerificado() != null && !user.getVerificado()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Por favor, verifica tu cuenta en el correo electrónico antes de iniciar sesión.");
        }

        // 3. Si el usuario existe y está verificado, validamos los datos
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );



        return guardarYGenerarToken(user);
    }

    private AuthResponse guardarYGenerarToken(Usuario user) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("username", user.getUsername());
        claims.put("email", user.getEmail());

        return new AuthResponse(jwtService.generateToken(claims, user));
    }
}
