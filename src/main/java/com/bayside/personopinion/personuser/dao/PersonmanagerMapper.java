package com.bayside.personopinion.personuser.dao;

import com.bayside.personopinion.personuser.bo.PersonmanagerBo;
import com.bayside.personopinion.personuser.model.Personmanager;

public interface PersonmanagerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Personmanager record);

    int insertSelective(PersonmanagerBo record);

    Personmanager selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PersonmanagerBo record);

    int updateByPrimaryKey(Personmanager record);
}