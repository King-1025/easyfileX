package king.easyfileX;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "king.gen.mapper")
public class Application {

  public static void main(String[] args){

      SpringApplication.run(Application.class,args);

  }

}
