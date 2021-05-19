package com.bayside.personopinion.subject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayside.personopinion.subject.dao.SubjectarticleMapper;
import com.bayside.personopinion.subject.model.Subjectarticle;
import com.bayside.personopinion.subject.service.SubjectarticleService;

@Service("subjectarticleServiceImpl")
public class SubjectarticleServiceImpl implements SubjectarticleService {

	@Autowired
	private SubjectarticleMapper subjectarticleMapper;
	
	public Subjectarticle selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return subjectarticleMapper.selectByPrimaryKey(id);
	}

}
