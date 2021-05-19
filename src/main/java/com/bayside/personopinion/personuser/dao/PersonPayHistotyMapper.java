package com.bayside.personopinion.personuser.dao;

import com.bayside.personopinion.personuser.model.PersonPayHistoty;

public interface PersonPayHistotyMapper {
	int insertPayHistoty(PersonPayHistoty personPayHistoty);
	int updatePayHistoty(PersonPayHistoty personPayHistoty);
	PersonPayHistoty selectPayHistotyByOrderNumber(PersonPayHistoty personPayHistoty);
}
