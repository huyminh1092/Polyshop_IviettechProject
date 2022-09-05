package poly.edu.shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import poly.edu.shop.config.StorageProperties;
import poly.edu.shop.service.StorageService;

@SpringBootApplication
@EnableScheduling
//@EnableConfigurationProperties(StorageProperties.class)
public class PolyShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolyShopApplication.class, args);
	}

//	@Bean
//	CommandLineRunner init(StorageService storageService) {
//		return (args-> {
//			storageService.init();
//		});
//	}
}
