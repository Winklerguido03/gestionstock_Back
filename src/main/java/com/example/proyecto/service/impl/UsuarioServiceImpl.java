package com.example.proyecto.service.impl;

import com.example.proyecto.dto.UsuarioResponseDTO;
import com.example.proyecto.model.Usuario;
import com.example.proyecto.repository.UsuarioRepository;
import com.example.proyecto.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getEmail()
        );
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(identifier)
                .or(() -> usuarioRepository.findByUsername(identifier))
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + identifier));
    }

    public Optional<Usuario> buscarPorIdentificador(String identificador){
        return usuarioRepository.findByEmail(identificador);
    }

}
