package com.projects.easyHome.service;

import com.projects.easyHome.model.TokenValidation;
import com.projects.easyHome.repository.TokenValidationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TokenValidationService {

    private final TokenValidationRepository tokenValidationRepository;

    public void storeToken(TokenValidation tokenValidation){
        tokenValidationRepository.save(tokenValidation);
    }

    public Optional<TokenValidation> getToken(String token){
        return tokenValidationRepository.findByToken(token);
    }

    public int setVerifiedAt(String token) {
        return tokenValidationRepository.updateVerifiedAt(
                token, LocalDateTime.now());
    }
}
