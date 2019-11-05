package king.easyfileX.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import king.gen.entity.Account;
import king.gen.entity.AccountExample;
import king.gen.mapper.AccountMapper;

@Service
public class LoginService{

  @Autowired
  private AccountMapper accountMapper;

  public boolean login(Account account){
     AccountExample ae=new AccountExample();
     ae.
     createCriteria().
     andNameEqualTo(account.getName()).
     andPasswordEqualTo(account.getPassword());
     return (accountMapper.countByExample(ae)>0);
  }
}
