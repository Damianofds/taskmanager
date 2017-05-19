package it.fds.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Launcher class for the Frontend, angular UI + REST API
 * 
 * @author fds
 *
 */
@EnableJpaRepositories
@Configuration
@SpringBootApplication
public class EndpointsMain {

    public static void main(String[] args) {
        SpringApplication.run(EndpointsMain.class, args);
    }
}