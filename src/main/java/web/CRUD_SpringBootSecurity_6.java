package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages="web.repository")
@EnableTransactionManagement
@EntityScan(basePackages="web.model")
public class CRUD_SpringBootSecurity_6 {
    public static void main(String[] args) {
        SpringApplication.run(CRUD_SpringBootSecurity_6.class, args);
    }
}
