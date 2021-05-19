package com.bayside.personopinion.subject.dao;

import com.bayside.personopinion.subject.model.Subjectarticle;
import com.bayside.personopinion.subject.model.SubjectarticleWithBLOBs;

public interface SubjectarticleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SubjectarticleWithBLOBs record);

    int insertSelective(SubjectarticleWithBLOBs record);

    Subjectarticle selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SubjectarticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SubjectarticleWithBLOBs record);

    int updateByPrimaryKey(Subjectarticle record);
}