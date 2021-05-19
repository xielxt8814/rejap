package com.bayside.personopinion.subject.dao;

import java.util.List;

import com.bayside.personopinion.subject.bo.GetPersonHistoryBo;
import com.bayside.personopinion.subject.bo.PersonArticleAlisBo;
import com.bayside.personopinion.subject.bo.PersonArticleFormatsBo;
import com.bayside.personopinion.subject.bo.PersonmanageBo;
import com.bayside.personopinion.subject.bo.PersonmanageBoQingGan;
import com.bayside.personopinion.subject.bo.PersonmanageBoQuShi;
import com.bayside.personopinion.subject.bo.PersonmanagemarticleBo;
import com.bayside.personopinion.subject.bo.SelectPersonHistoryBo;
import com.bayside.personopinion.subject.model.Personmanagemarticle;

public interface PersonmanagemarticleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Personmanagemarticle record);

    int insertSelective(Personmanagemarticle record);

    Personmanagemarticle selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Personmanagemarticle record);

    int updateByPrimaryKeyWithBLOBs(Personmanagemarticle record);

    int updateByPrimaryKey(Personmanagemarticle record);
    
    List<PersonmanagemarticleBo> selectMArticleNew(PersonmanagemarticleBo record);
    
    PersonArticleFormatsBo selectFormatsNum(PersonmanagemarticleBo record);
    List<PersonArticleAlisBo> dayselectQushiAlis(PersonmanagemarticleBo record);
    List<PersonArticleAlisBo> selectQushiAlis(PersonmanagemarticleBo record);
    List<GetPersonHistoryBo> SelectMArticleHistory(SelectPersonHistoryBo record);
    
    List<PersonmanageBo> getfristmedia(PersonmanageBo personmanageBo);
    List<PersonmanageBo> getPubmsgtop(PersonmanageBo personmanageBo);
    List<PersonmanageBoQuShi> getMediatrend(PersonmanageBoQuShi personmanageBoQuShi);
    List<PersonmanageBoQuShi> getMediatrendDay(PersonmanageBoQuShi personmanageBoQuShi);
    PersonmanageBoQingGan getEmotionalAnalysis(PersonmanageBoQingGan personmanageBoQingGan);
    List<Personmanagemarticle> selectCollectInfo(Personmanagemarticle record);
}