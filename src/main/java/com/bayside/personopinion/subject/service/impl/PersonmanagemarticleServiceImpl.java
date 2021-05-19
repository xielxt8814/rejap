package com.bayside.personopinion.subject.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayside.app.util.DateFormatUtil;
import com.bayside.personopinion.subject.bo.GetPersonHistoryBo;
import com.bayside.personopinion.subject.bo.PersonArticleAlisBo;
import com.bayside.personopinion.subject.bo.PersonArticleFormatsBo;
import com.bayside.personopinion.subject.bo.PersonmanageBo;
import com.bayside.personopinion.subject.bo.PersonmanageBoQingGan;
import com.bayside.personopinion.subject.bo.PersonmanageBoQuShi;
import com.bayside.personopinion.subject.bo.PersonmanagemarticleBo;
import com.bayside.personopinion.subject.bo.SelectPersonHistoryBo;
import com.bayside.personopinion.subject.dao.PersonmanagemarticleMapper;
import com.bayside.personopinion.subject.service.PersonmanagemarticleService;

@Service("personmanagemarticleServiceImpl")
public class PersonmanagemarticleServiceImpl implements PersonmanagemarticleService {

	@Autowired
	private PersonmanagemarticleMapper personmanagemarticleMapper;
	
	public List<PersonmanagemarticleBo> selectMArticleNew(PersonmanagemarticleBo record) {
		// TODO Auto-generated method stub
		return personmanagemarticleMapper.selectMArticleNew(record);
	}
	public PersonArticleFormatsBo selectFormatsNum(PersonmanagemarticleBo record) {
		// TODO Auto-generated method stub
		return personmanagemarticleMapper.selectFormatsNum(record);
	}
	public List<PersonArticleAlisBo> selectQushiAlis(PersonmanagemarticleBo record) {
		// TODO Auto-generated method stub
		return personmanagemarticleMapper.selectQushiAlis(record);
	}
	public List<GetPersonHistoryBo> SelectMArticleHistory(SelectPersonHistoryBo record) {
		
		return personmanagemarticleMapper.SelectMArticleHistory(record);
	}
	public List<PersonmanageBo> getfristmedia(PersonmanageBo personmanageBo) {
		return personmanagemarticleMapper.getfristmedia(personmanageBo);
	}
	public List<PersonmanageBo> getPubmsgtop(PersonmanageBo personmanageBo) {
		return personmanagemarticleMapper.getPubmsgtop(personmanageBo);
	}
	public List<PersonmanageBoQuShi> getMediatrend(PersonmanageBoQuShi personmanageBoQuShi) {
		List<PersonmanageBoQuShi> list = null;
		List<PersonmanageBoQuShi> listbc = new ArrayList<PersonmanageBoQuShi>();
		int t = 0;
		try {
			t = daysBetween(personmanageBoQuShi.getStartTime(), personmanageBoQuShi.getEndTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(t>1){
			list = personmanagemarticleMapper.getMediatrend(personmanageBoQuShi);
			List<String> dateList = DateFormatUtil.getDateList(personmanageBoQuShi.getStartTime(),personmanageBoQuShi.getEndTime(), t);
			
			for (String string : dateList) {
				PersonmanageBoQuShi sBo = new PersonmanageBoQuShi();
				sBo.setPubdate(string);
				sBo.setInfo_acount(0);
				sBo.setNews_acount(0);
				sBo.setBbs_acount(0);
				sBo.setBoke_acount(0);
				sBo.setWeibo_acount(0);
				sBo.setPingmei_acount(0);
				sBo.setWeixin_acount(0);
				sBo.setTieba_acount(0);
				sBo.setShipin_acount(0);
				sBo.setApp_acount(0);
				sBo.setPinglun_acount(0);
				sBo.setOther_acount(0);
				sBo.setAbroad_acount(0);
				sBo.setTrade_acount(0);
				
				for (PersonmanageBoQuShi subjectStatistical : list) {
//					String pubdate = DateFormatUtil.dateFormatString(subjectStatistical.getPubdate());
					if (string.equals(subjectStatistical.getPubdate())) {
							BeanUtils.copyProperties(subjectStatistical, sBo);
							sBo.setPubdate(string);
							break;
					}
				}
				listbc.add(sBo);
			}
			
			return listbc;
		}else{
			List<String> listtime = DateFormatUtil.getHourList();
			Collections.sort(listtime);
			List<String> odtime= new ArrayList<String>();
			list = personmanagemarticleMapper.getMediatrendDay(personmanageBoQuShi);
			for(int i=0;i<list.size();i++){
				String uptime = list.get(i).getPubdate();
				odtime.add(uptime);
			}
			for(int i=0;i<listtime.size();i++){
				PersonmanageBoQuShi ss = new PersonmanageBoQuShi();
				if(odtime.contains(listtime.get(i))){
					for(int k=0;k<list.size();k++){
						if(list.get(k).getPubdate().equals(listtime.get(i))){
							ss = list.get(k);
						}
					}
				}else{
					ss.setPubdate(listtime.get(i));
					ss.setInfo_acount(0);
					ss.setNews_acount(0);
					ss.setBbs_acount(0);
					ss.setBoke_acount(0);
					ss.setWeibo_acount(0);
					ss.setPingmei_acount(0);
					ss.setWeixin_acount(0);
					ss.setTieba_acount(0);
					ss.setShipin_acount(0);
					ss.setApp_acount(0);
					ss.setPinglun_acount(0);
					ss.setOther_acount(0);
					ss.setAbroad_acount(0);
					ss.setTrade_acount(0);
					
				}
				listbc.add(ss);
			}
			return listbc;
		}
	}
	
	public static int daysBetween(String smdate,String bdate) throws ParseException{  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(smdate));    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(bdate));    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));     
    }
	public PersonmanageBoQingGan getEmotionalAnalysis(PersonmanageBoQingGan personmanageBoQingGan) {
		// TODO Auto-generated method stub
		return personmanagemarticleMapper.getEmotionalAnalysis(personmanageBoQingGan);
	}
}
