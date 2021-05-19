package com.bayside.personopinion.personuser.dao;

import com.bayside.personopinion.personuser.model.PersonPower;

public interface PersonPowerMapper {
	int insertPersonPower(PersonPower personPower);
	int updatePersonPower(PersonPower personPower);
	PersonPower selectPersonPowerCiShu(PersonPower personPower);
}
