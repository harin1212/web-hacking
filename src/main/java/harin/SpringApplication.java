package harin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"harin.mapper"})
public class SpringApplication {

	public static void main(String[] args) {
		org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
	}
}
