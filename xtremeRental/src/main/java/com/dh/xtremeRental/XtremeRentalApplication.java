package com.dh.xtremeRental;


import com.dh.xtremeRental.Jobs.CreaAdmin;
import com.dh.xtremeRental.User.Role;
import com.dh.xtremeRental.User.User;
import com.dh.xtremeRental.dto.UserDto;
import com.dh.xtremeRental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class XtremeRentalApplication {


	private static CreaAdmin creaAdmin;
	public static void main(String[] args) {
		SpringApplication.run(XtremeRentalApplication.class, args);


	}
}
