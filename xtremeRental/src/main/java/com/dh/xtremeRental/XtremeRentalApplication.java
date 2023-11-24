package com.dh.xtremeRental;


import com.dh.xtremeRental.Jobs.CreaAdmin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class XtremeRentalApplication {

	private final CreaAdmin creaAdmin;

	public XtremeRentalApplication(CreaAdmin creaAdmin) {
		this.creaAdmin = creaAdmin;
	}

	public static void main(String[] args) throws Exception {

		ConfigurableApplicationContext context = SpringApplication.run(XtremeRentalApplication.class, args);
		CreaAdmin creaAdmin = context.getBean(CreaAdmin.class);
		creaAdmin.run();
//		SpringApplication.run(XtremeRentalApplication.class, args);
	}
}
