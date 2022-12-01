package com.example.board.biz.domain.auth;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class CryptUtils {
    private final AesBytesEncryptor aesBytesEncryptor;

    public String encryptEmail(String email) {
        byte[] encrypt = aesBytesEncryptor.encrypt(email.getBytes(StandardCharsets.UTF_8));

        return new String(encrypt, StandardCharsets.UTF_8);
    }

    public String decryptEmail(String encryptedEmail) {
        byte[] decryptEmail = encryptedEmail.getBytes();
        byte[] decrypt = aesBytesEncryptor.decrypt(decryptEmail);
        return new String(decrypt, StandardCharsets.UTF_8);
    }

}
