package bitc.fullstack503.java503_team3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("bitc.fullstack503.java503_team3.mapper") // 매퍼 인터페이스 스캔
public class Java503Team3Application {
    public static void main(String[] args) {
        SpringApplication.run(Java503Team3Application.class, args);
    }
}

