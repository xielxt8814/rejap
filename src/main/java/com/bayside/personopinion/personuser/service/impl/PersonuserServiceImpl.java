package com.bayside.personopinion.personuser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayside.personopinion.personuser.bo.PersonmanagerBo;
import com.bayside.personopinion.personuser.dao.FeedbackMapper;
import com.bayside.personopinion.personuser.dao.PersonPayHistotyMapper;
import com.bayside.personopinion.personuser.dao.PersonPowerMapper;
import com.bayside.personopinion.personuser.dao.PersonUserMapper;
import com.bayside.personopinion.personuser.dao.PersonexpirenceMapper;
import com.bayside.personopinion.personuser.dao.PersonmanageMapper;
import com.bayside.personopinion.personuser.dao.PersonmanagerMapper;
import com.bayside.personopinion.personuser.model.Feedback;
import com.bayside.personopinion.personuser.model.PersonPayHistoty;
import com.bayside.personopinion.personuser.model.PersonPower;
import com.bayside.personopinion.personuser.model.PersonUser;
import com.bayside.personopinion.personuser.model.Personexpirence;
import com.bayside.personopinion.personuser.model.Personmanage;
import com.bayside.personopinion.personuser.model.Personmanager;
import com.bayside.personopinion.personuser.service.PersonUserService;
@Service("personuserServiceImpl")
public class PersonuserServiceImpl implements PersonUserService {
	@Autowired
    private PersonUserMapper personUserMapper;
	@Autowired
	private PersonexpirenceMapper personexpirenceMapper;
	@Autowired
	private PersonmanagerMapper personmanagerMapper;
	@Autowired
    private PersonmanageMapper personmanageMapper;
	@Autowired
    private FeedbackMapper feedbackMapper;
	@Autowired
    private PersonPayHistotyMapper personPayHistotyMapper;
	@Autowired
    private PersonPowerMapper personPowerMapper;

	public int registerPersonUser(PersonUser record) {
		// TODO Auto-generated method stub
		return personUserMapper.insertSelective(record);
	}

	public PersonUser checkUserlogin(PersonUser record) {
		// TODO Auto-generated method stub
		return personUserMapper.checkUserlogin(record);
	}

	public int updatePersonmanagerInfo(PersonmanagerBo record) {
		// TODO Auto-generated method stub
		return personmanagerMapper.updateByPrimaryKeySelective(record);
	}

	public int addPersonmanager(PersonmanagerBo record) {
		// TODO Auto-generated method stub
		return personmanagerMapper.insertSelective(record);
	}

	public int updatePersonUser(PersonUser record) {
		// TODO Auto-generated method stub
		return personUserMapper.updateByPrimaryKeySelective(record);
	}

	public List<Personexpirence> selectExpirencePage(Personexpirence record) {
		// TODO Auto-generated method stub
		return personexpirenceMapper.selectExpirencePage(record);
	}
	/** 遍历用户信息*/
	public List<PersonUser> selectPersonUserList() {
		return personUserMapper.selectPersonUserList();
	}
	/** 根据手机号码查询密码*/
	public PersonUser selectByPrimaryPhone(String talphone) {
		return personUserMapper.selectByPrimaryPhone(talphone);
	}
	/** 根据手机号码修改密码*/
	public int updateByPrimaryTalphone(PersonUser record) {
		return personUserMapper.updateByPrimaryTalphone(record) ;
	}

	public List<Personmanage> selectPersonmanage(Personmanage personmanage) {
		// TODO Auto-generated method stub
		return personmanageMapper.selectPersonmanage(personmanage);
	}

	public int insertSelective(Personmanage personmanage) {
		// TODO Auto-generated method stub
		return personmanageMapper.insertSelective(personmanage);
	}

	public PersonUser loginWithEscrowAccount(PersonUser record) {
		return personUserMapper.loginWithEscrowAccount(record);
	}

	public int insertFeedback(Feedback feedback) {
		return feedbackMapper.insertFeedback(feedback);
	}


	public int insertPayHistoty(PersonPayHistoty personPayHistoty) {
		return personPayHistotyMapper.insertPayHistoty(personPayHistoty);
	}

	public int updatePayHistoty(PersonPayHistoty personPayHistoty) {
		return personPayHistotyMapper.updatePayHistoty(personPayHistoty);
	}

	public PersonPayHistoty selectPayHistotyByOrderNumber(PersonPayHistoty personPayHistoty) {
		return personPayHistotyMapper.selectPayHistotyByOrderNumber(personPayHistoty);
	}

	@Override
	public int insertPersonPower(PersonPower personPower) {
		return personPowerMapper.insertPersonPower(personPower);
	}

	@Override
	public int updatePersonPower(PersonPower personPower) {
		return personPowerMapper.updatePersonPower(personPower);
	}

	@Override
	public PersonPower selectPersonPowerCiShu(PersonPower personPower) {
		return personPowerMapper.selectPersonPowerCiShu(personPower);
	}

	@Override
	public PersonUser selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return personUserMapper.selectByPrimaryKey(id);
	}



}
