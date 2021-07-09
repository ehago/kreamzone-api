package com.ehago.kreamzone.config;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class JasyptConfigTest {

    @Autowired
    private StringEncryptor jasyptStringEncryptor;

    @Test
    void jasyptEncryptor_createdBean_isNotNull() {
        assertThat(jasyptStringEncryptor).isNotNull();
    }

    @Test
    void jasyptEncryptor_encryptAndDecrypt_isSuccess() {
        String target = "test";
        log.info("target = " + target);

        String encryptText = jasyptStringEncryptor.encrypt(target);
        log.info("encryptText = " + encryptText);

        String decryptText = jasyptStringEncryptor.decrypt(encryptText);
        log.info("decryptText = " + decryptText);

        assertThat(decryptText).isEqualTo(target);
    }

}
