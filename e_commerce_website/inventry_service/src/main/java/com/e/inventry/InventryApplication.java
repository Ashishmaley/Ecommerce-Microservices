package com.e.inventry;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.e.inventry.model.Inventory;
import com.e.inventry.repository.InventoryRepo;

@SpringBootApplication
public class InventryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventryApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepo inventryRepo) {
		return arg -> {
			Inventory i1 = new Inventory();
			i1.setSkuCode("iphone 13");
			i1.setQuantity(100);

			Inventory i2 = new Inventory();
			i2.setSkuCode("iphone 14");
			i2.setQuantity(10);

			Inventory i3 = new Inventory();
			i3.setSkuCode("iphone15");
			i3.setQuantity(1);
			inventryRepo.save(i1);
			inventryRepo.save(i2);
			inventryRepo.save(i3);
		};
	}

}
