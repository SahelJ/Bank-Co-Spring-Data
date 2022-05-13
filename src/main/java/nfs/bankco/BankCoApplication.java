package nfs.bankco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class BankCoApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankCoApplication.class, args);
    }
}

