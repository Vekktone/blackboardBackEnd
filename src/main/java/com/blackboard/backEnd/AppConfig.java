package com.blackboard.backEnd;


import com.blackboard.backEnd.service.EmailService;
import com.blackboard.backEnd.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * This class contains the beans that are used throughout the application.
 */
@Configuration
public class AppConfig {

    /**
     *
     * @return EmailService
     */
    @Bean
    public EmailService emailService(){

        return new EmailService();

    }

    /**
     *
     * @return StorageService
     */
    @Bean
    public StorageService storageService(){

        return new StorageService();

    }

    /**
     *
     * @param storageService used for init storage purposes
     * @return CommandLineRunner
     */
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
