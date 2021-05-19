package com.bayside.personopinion.subject.service;

import java.util.List;

import com.bayside.personopinion.subject.bo.GetPersonHistoryBo;
import com.bayside.personopinion.subject.bo.PersonArticleAlisBo;
import com.bayside.personopinion.subject.bo.PersonArticleFormatsBo;
import com.bayside.personopinion.subject.bo.PersonmanageBo;
import com.bayside.personopinion.subject.bo.PersonmanageBoQingGan;
import com.bayside.personopinion.subject.bo.PersonmanageBoQuShi;
import com.bayside.personopinion.subject.bo.PersonmanagemarticleBo;
import com.bayside.personopinion.subject.bo.SelectPersonHistoryBo;

public interface PersonmanagemarticleService {
	
    List<PersonmanagemarticleBo> selectMArticleNew(PersonmanagemarticleBo record);
    
    PersonArticleFormatsBo selectFormatsNum(PersonmanagemarticleBo record);
    
    List<PersonArticleAlisBo> selectQushiAlis(PersonmanagemarticleBo record);
    
    List<GetPersonHistoryBo> SelectMArticleHistory(SelectPersonHistoryBo record);

    List<PersonmanageBo> getfristmedia(PersonmanageBo personmanageBo);
    List<PersonmanageBo> getPubmsgtop(PersonmanageBo personmanageBo);
    List<PersonmanageBoQuShi> getMediatrend(PersonmanageBoQuShi personmanageBoQuShi);
    PersonmanageBoQingGan getEmotionalAnalysis(PersonmanageBoQingGan personmanageBoQingGan);
}
