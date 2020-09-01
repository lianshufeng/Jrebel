package top.dzurl.jrebel.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("top.dzurl.jrebel.core")
public class JrebelApplication {

    public static void main(String[] args) {
        SpringApplication.run(JrebelApplication.class, args);
    }

}
