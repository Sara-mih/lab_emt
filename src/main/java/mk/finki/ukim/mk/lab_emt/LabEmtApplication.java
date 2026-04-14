package mk.finki.ukim.mk.lab_emt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class LabEmtApplication {

    public static void main(String[] args) {

        SpringApplication.run(LabEmtApplication.class, args);
    }

}
