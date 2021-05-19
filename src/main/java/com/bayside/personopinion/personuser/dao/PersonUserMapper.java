package com.bayside.personopinion.personuser.dao;

import java.util.List;

import com.bayside.personopinion.personuser.model.PersonUser;

public interface PersonUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(PersonUser record);

    int insertSelective(PersonUser record);

    PersonUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PersonUser record);

    int updateByPrimaryKey(PersonUser record);
    
    PersonUser checkUserlogin(PersonUser record);
    int updatePersonUser(PersonUser record);
    /** 遍历用户信息 */
    List<PersonUser> selectPersonUserList();
    PersonUser selectByPrimaryPhone(String talphone);
    int updateByPrimaryTalphone(PersonUser record);
    
    PersonUser loginWithEscrowAccount(PersonUser record);
}