package uz.zako.zakoBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uz.zako.zakoBackend.model.Result;

@SpringBootApplication
public class ZakoBackendApplication {
    @Bean
    Result result(){return new Result();}

    public static void main(String[] args) {
        SpringApplication.run(ZakoBackendApplication.class, args);
    }

}
