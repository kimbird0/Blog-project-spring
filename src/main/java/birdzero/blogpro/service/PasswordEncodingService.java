package birdzero.blogpro.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncodingService {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }
}