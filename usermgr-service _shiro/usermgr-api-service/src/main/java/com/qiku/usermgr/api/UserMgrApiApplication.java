package com.qiku.usermgr.api;

		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.qiku.usermgr"})
public class UserMgrApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMgrApiApplication.class, args);
	}

}

