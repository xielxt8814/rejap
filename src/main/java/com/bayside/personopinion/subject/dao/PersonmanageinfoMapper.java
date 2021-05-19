package com.bayside.personopinion.subject.dao;

import com.bayside.personopinion.subject.model.Personmanageinfo;

public interface PersonmanageinfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(Personmanageinfo record);

    int insertSelective(Personmanageinfo record);

    Personmanageinfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Personmanageinfo record);

    int updateByPrimaryKey(Personmanageinfo record);
}