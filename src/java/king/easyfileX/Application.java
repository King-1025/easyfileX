package king.easyfileX;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import king.easyfileX.service.StorageService;

@SpringBootApplication
@MapperScan(basePackages = "king.gen.mapper")
public class Application {

  public static void main(String[] args){

      SpringApplication.run(Application.class,args);

  }
 
  @Bean
  public CommandLineRunner init(StorageService storageService){
      return (args) -> {
//         storageService.deleteAll();
         storageService.init();
      };
  }
}
