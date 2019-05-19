package king.easyfileX.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import king.easyfileX.service.LoginService;
import king.easyfileX.model.Result;
import king.gen.entity.Account;

@RestController
public class IndexController{
   
   @Autowired
   private LoginService loginService;

   @RequestMapping("/")
   public String index(){
      return "forward:html/index.html";
   }

   @RequestMapping("/doLogin")
   public Result login(@RequestParam(value="name") String name,@RequestParam(value="password") String password){
        Account account=new Account();
        account.setName(name);
        account.setPassword(password);
        return new Result(1,loginService.login(account));
   }
 
}
