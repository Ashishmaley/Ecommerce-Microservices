package com.e.inventry;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.e.inventry.model.Inventry;
import com.e.inventry.repository.InventryRepo;

@SpringBootApplication
public class InventryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventryApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventryRepo inventryRepo) {
		return arg -> {
			Inventry i1 = new Inventry();
			i1.setSkuCode("iphone 13");
			i1.setQuantity(100);

			Inventry i2 = new Inventry();
			i2.setSkuCode("iphone 14");
			i2.setQuantity(10);

			Inventry i3 = new Inventry();
			i3.setSkuCode("iphone15");
			i3.setQuantity(1);
			inventryRepo.save(i1);
			inventryRepo.save(i2);
			inventryRepo.save(i3);
		};
	}

}
