package co.com.devsoft.contable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan({"co.com.devsoft.contable"})
@EnableJpaRepositories({"co.com.devsoft.contable.services"})
@SpringBootApplication
public class ContableApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContableApplication.class, args);
    }

}
