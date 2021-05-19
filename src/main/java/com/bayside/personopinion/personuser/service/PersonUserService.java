
package com.bayside.personopinion.personuser.service;

import java.util.List;

import com.bayside.personopinion.personuser.bo.PersonmanagerBo;
import com.bayside.personopinion.personuser.dao.PersonPowerMapper;
import com.bayside.personopinion.personuser.model.Feedback;
import com.bayside.personopinion.personuser.model.PersonPayHistoty;
import com.bayside.personopinion.personuser.model.PersonPower;
import com.bayside.personopinion.personuser.model.PersonUser;
import com.bayside.personopinion.personuser.model.Personexpirence;
import com.bayside.personopinion.personuser.model.Personmanage;

public interface PersonUserService {
   int registerPersonUser(PersonUser record);
   PersonUser checkUserlogin(PersonUser record);
   int updatePersonmanagerInfo(PersonmanagerBo record);
   int addPersonmanager(PersonmanagerBo record);
   int updatePersonUser(PersonUser record);
   List<Personexpirence> selectExpirencePage(Personexpirence record);
   /** 遍历用户信息 */
   List<PersonUser> selectPersonUserList();
   PersonUser selectByPrimaryPhone(String talphone);
   PersonUser selectByPrimaryKey(String id);
   int updateByPrimaryTalphone(PersonUser record);
   
   List<Personmanage> selectPersonmanage(Personmanage personmanage);

   int insertSelective(Personmanage personmanage);
   
   PersonUser loginWithEscrowAccount(PersonUser record);
   
   int insertFeedback(Feedback feedback);


   
   int insertPayHistoty(PersonPayHistoty personPayHistoty);
   int updatePayHistoty(PersonPayHistoty personPayHistoty);
   PersonPayHistoty selectPayHistotyByOrderNumber(PersonPayHistoty personPayHistoty);
   
   int insertPersonPower(PersonPower personPower);
   int updatePersonPower(PersonPower personPower);
   PersonPower selectPersonPowerCiShu(PersonPower personPower);

}
