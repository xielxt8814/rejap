package com.bayside.personopinion.subject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bayside.app.util.AppConstant;
import com.bayside.app.util.Response;
import com.bayside.app.util.ResponseStatus;
import com.bayside.personopinion.subject.bo.GetPersonHistoryBo;
import com.bayside.personopinion.subject.bo.PersonArticleAlisBo;
import com.bayside.personopinion.subject.bo.PersonArticleFormatsBo;
import com.bayside.personopinion.subject.bo.PersonmanageBo;
import com.bayside.personopinion.subject.bo.PersonmanageBoQingGan;
import com.bayside.personopinion.subject.bo.PersonmanageBoQuShi;
import com.bayside.personopinion.subject.bo.PersonmanagemarticleBo;
import com.bayside.personopinion.subject.bo.SelectPersonHistoryBo;
import com.bayside.personopinion.subject.model.Subjectarticle;
import com.bayside.personopinion.subject.service.PersonmanageinfoService;
import com.bayside.personopinion.subject.service.PersonmanagemarticleService;
import com.bayside.personopinion.subject.service.SubjectarticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
public class SubjectController {
	
	@Autowired
	private PersonmanageinfoService personmanageinfoServiceImpl;
	@Autowired
	private SubjectarticleService subjectarticleServiceImpl;
	@Autowired
	private PersonmanagemarticleService personmanagemarticleServiceImpl;
	
	
	/**
	 * <p>方法名称：selectMArticleNew</p>
	 * <p>方法描述：查询用户监测的最新信息</p>
	 * @param session
	 * @param personid (人物 ID)
	 * @return
	 * @author peigd
	 * @since  2017年7月27日
	 * <p> history 2017年7月27日 administrator 创建   <p>
	 */
	@RequestMapping(value="/selectMArticleNew", method= RequestMethod.GET)
	public Response selectMArticleNew(HttpSession session, String persionid){
		PersonmanagemarticleBo record = new PersonmanagemarticleBo();
		//用户ID
		String userid = (String) session.getAttribute("userid");
		record.setPersionid(persionid);
		record.setUserid(userid);
		List<PersonmanagemarticleBo> list = personmanagemarticleServiceImpl.selectMArticleNew(record);
		if(list.size()>0){
			 return new Response(ResponseStatus.Success,list,true);
		 }else{
			 return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		 }
	}
	/**
	 * <p>方法名称：selectMArticleInfo</p>
	 * <p>方法描述：舆情信息详情</p>
	 * @return
	 * @author peigd
	 * @since  2017年7月27日
	 * <p> history 2017年7月27日 administrator  创建   <p>
	 */
	@RequestMapping(value="/selectMArticleInfo", method=RequestMethod.GET)
	public Response selectMArticleInfo(String id){
		Subjectarticle record = subjectarticleServiceImpl.selectByPrimaryKey(id);
		if(record != null){
			 return new Response(ResponseStatus.Success,record,true);
		 }else{
			 return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		 }
	}
	/**
	 * 
	 * <p>方法名称：selectArticleFormats</p>
	 * <p>方法描述：\数据分析 情感倾向性分析</p>
	 * @param record
	 * @return
	 * @author liuyy
	 * @since  2017年7月28日
	 * <p> history 2017年7月28日 Administrator  创建   <p>
	 */
	@RequestMapping(value="/selectArticleFormats", method=RequestMethod.GET)
    public Response selectArticleFormats(PersonmanagemarticleBo record){
      PersonArticleFormatsBo pab = personmanagemarticleServiceImpl.selectFormatsNum(record);
      if(null!=pab){
    	  return new Response(ResponseStatus.Success,pab,true);
      }else{
    	  return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
      }
    }
	@RequestMapping(value="/selectAlisArtilce", method=RequestMethod.GET)
	public Response selectAlisArtilce(PersonmanagemarticleBo record){
		List<PersonArticleAlisBo> list = personmanagemarticleServiceImpl.selectQushiAlis(record);
		if(list.size()>0){
			return new Response(ResponseStatus.Success,list,true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		}
	}
	/**
	 * 
	 * <p>方法名称：selectMArticleHistory</p>
	 * <p>方法描述：</p>
	 * @param record
	 * @return
	 * @author liuyy
	 * @since  2017年9月22日
	 * <p> history 2017年9月22日 Administrator  创建   <p>
	 */
	@RequestMapping(value="/selectMArticleHistory",method=RequestMethod.GET)
	public Response selectMArticleHistory (SelectPersonHistoryBo record, PageInfo page){
		PageHelper.startPage(page.getPageNum(),page.getPageSize());
		List<GetPersonHistoryBo> list = personmanagemarticleServiceImpl.SelectMArticleHistory(record);
		PageInfo<GetPersonHistoryBo> info = new PageInfo<GetPersonHistoryBo>(list);
		return new Response(ResponseStatus.Success,info,true);
	}
	
	/**
	 * 
	 * <p>方法名称：getfristmedia</p>
	 * <p>方法描述：首发媒体</p>
	 * @param personmanageBo
	 * @param request
	 * @return
	 * @author 常瑞
	 * @since  2017年9月22日
	 * <p> history 2017年9月22日 常瑞  创建   </p>
	 */
	@RequestMapping(value="/getfristmedia",method=RequestMethod.GET)
	public Response getfristmedia(PersonmanageBo personmanageBo,HttpServletRequest request){
		List<PersonmanageBo> list = personmanagemarticleServiceImpl.getfristmedia(personmanageBo);
		if(list.size()>0){
			return new Response(ResponseStatus.Success,list,true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		}
	}
	
	/**
	 * 
	 * <p>方法名称：getPubmsgtop</p>
	 * <p>方法描述：信息发布排行</p>
	 * @param personmanageBo
	 * @param request
	 * @return
	 * @author 常瑞
	 * @since  2017年9月22日
	 * <p> history 2017年9月22日 常瑞  创建   </p>
	 */
	@RequestMapping(value="/getPubmsgtop",method=RequestMethod.GET)
	public Response getPubmsgtop(PersonmanageBo personmanageBo,HttpServletRequest request){
		List<PersonmanageBo> list = personmanagemarticleServiceImpl.getPubmsgtop(personmanageBo);
		if(list.size()>0){
			return new Response(ResponseStatus.Success,list,true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		}
	}
	
	/**
	 * 
	 * <p>方法名称：getMediatrend</p>
	 * <p>方法描述：载体舆情趋势分析</p>
	 * @param record
	 * @return
	 * @author 常瑞
	 * @since  2017年9月22日
	 * <p> history 2017年9月22日 常瑞  创建   </p>
	 */
	@RequestMapping(value="/getMediatrend", method=RequestMethod.GET)
	public Response getMediatrend(PersonmanageBoQuShi record){
		List<PersonmanageBoQuShi> list = personmanagemarticleServiceImpl.getMediatrend(record);
		if(list.size()>0){
			return new Response(ResponseStatus.Success,list,true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		}
	}
	
	/**
	 * 
	 * <p>方法名称：getEmotionalAnalysis</p>
	 * <p>方法描述：情感倾向分析图</p>
	 * @param record
	 * @return
	 * @author 常瑞
	 * @since  2017年9月23日
	 * <p> history 2017年9月23日 常瑞  创建   </p>
	 */
	@RequestMapping(value="/getEmotionalAnalysis", method=RequestMethod.GET)
	public Response getEmotionalAnalysis(PersonmanageBoQingGan record){
		PersonmanageBoQingGan personmanageBoQingGan = personmanagemarticleServiceImpl.getEmotionalAnalysis(record);
		if(personmanageBoQingGan!=null){
			return new Response(ResponseStatus.Success,personmanageBoQingGan,true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		}
	}
}
