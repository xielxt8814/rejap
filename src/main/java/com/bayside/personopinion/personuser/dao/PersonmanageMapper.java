package com.bayside.personopinion.personuser.dao;

import java.util.List;

import com.bayside.personopinion.personuser.model.Personmanage;

public interface PersonmanageMapper {

    List<Personmanage> selectPersonmanage(Personmanage personmanage);
    int insertSelective(Personmanage personmanage);

}