package com.rodrigozietlow.desafiototvs.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DB Mock starting data
 * @author Rodrigo Zietlow
 */
@Configuration
public class MockDb {
  
  private static final Logger log = LoggerFactory.getLogger(MockDb.class);

  @Bean
  CommandLineRunner initDatabase(ClientRepository repository, PhoneRepository phoneRepository) {
    Client c1 = new Client(1L, "João da Silva", "Rua das Flores, 91", "Centro", List.of());
    Client c2 = new Client(2L,"Pedro Bial" , "Rua das Rosas, 44", "Centro", List.of());

    return args -> {
      log.info("Preloading " + repository.save(c1));
      log.info("Preloading " + repository.save(c2));
      log.info("Preloading " + phoneRepository.save(new Phone(1L, "(51) 99999-9999", c1)));
      log.info("Preloading " + phoneRepository.save(new Phone(2L, "(51) 88888-8888", c2)));
    };
  }
}
