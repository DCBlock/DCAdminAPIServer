package com.digicap.dcblock.admin;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DcAdminApiServerApplication {
	static {
		Security.addProvider(new BouncyCastleProvider());
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DcAdminApiServerApplication.class, args);
	}
}
