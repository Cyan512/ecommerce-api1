package com.ecommerce.config;

import com.ecommerce.user.domain.model.Administrador;
import com.ecommerce.user.domain.port.UserRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final UserRepositoryPort userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String defaultEmail;
    private final String defaultPassword;
    private final String defaultName;

    public DataInitializer(UserRepositoryPort userRepository,
                           PasswordEncoder passwordEncoder,
                           @Value("${app.default-admin.email}") String defaultEmail,
                           @Value("${app.default-admin.password}") String defaultPassword,
                           @Value("${app.default-admin.nombre}") String defaultName) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.defaultEmail = defaultEmail;
        this.defaultPassword = defaultPassword;
        this.defaultName = defaultName;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.count() > 0) {
            log.info("Usuarios existentes, se omite creación del admin por defecto");
            return;
        }

        Administrador admin = new Administrador();
        admin.setEmail(defaultEmail);
        admin.setPassword(passwordEncoder.encode(defaultPassword));
        admin.setNombre(defaultName);
        admin.setFechaRegistro(LocalDateTime.now());
        admin.setActivo(true);

        userRepository.save(admin);
        log.info("Admin por defecto creado: {} / {}", defaultEmail, defaultPassword);
    }
}
