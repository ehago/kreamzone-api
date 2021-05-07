package com.ehago.kreamzone;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KreamzoneApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KreamzoneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(System.getProperty("jasypt.encryptor.password"));
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setIvGeneratorClassName("org.jasypt.iv.NoIvGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
		String url = "jdbc:postgresql://swagger.synology.me:35432/kream";
		String username = "kreamadmin";
		String password = "kreamehago";
		System.out.println("기존 URL :: " + url + " | 변경 URL :: " + encryptor.encrypt(url));
		System.out.println("기존 username :: " + username + " | 변경 username :: " + encryptor.encrypt(username));
		System.out.println("기존 password :: " + password + " | 변경 password :: " + encryptor.encrypt(password));
	}
}
