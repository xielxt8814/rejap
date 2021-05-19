package com.bayside.personopinion.personuser.dao;

import java.util.List;

import com.bayside.personopinion.personuser.model.Personexpirence;

public interface PersonexpirenceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Personexpirence record);

    int insertSelective(Personexpirence record);

    Personexpirence selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Personexpirence record);

    int updateByPrimaryKey(Personexpirence record);
    List<Personexpirence> selectExpirencePage(Personexpirence record);
}