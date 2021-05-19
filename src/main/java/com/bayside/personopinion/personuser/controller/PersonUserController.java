package com.bayside.personopinion.personuser.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.config.AlipayConfig;
import com.bayside.app.util.AppConstant;
import com.bayside.app.util.DateFormatUtil;
import com.bayside.app.util.ImgBase64Decode;
import com.bayside.app.util.IpUtil;
import com.bayside.app.util.LoginIp;
import com.bayside.app.util.NumberFormat;
import com.bayside.app.util.PrecisionIsTheOnlyStandard;
import com.bayside.app.util.Response;
import com.bayside.app.util.ResponseStatus;
import com.bayside.app.util.SendCode;
import com.bayside.app.util.SendPost;
import com.bayside.app.util.UuidUtil;
import com.bayside.personopinion.personuser.bo.PersonmanagerBo;
import com.bayside.personopinion.personuser.model.Feedback;
import com.bayside.personopinion.personuser.model.PersonPayHistoty;
import com.bayside.personopinion.personuser.model.PersonPower;
import com.bayside.personopinion.personuser.model.PersonUser;
import com.bayside.personopinion.personuser.model.Personexpirence;
import com.bayside.personopinion.personuser.model.Personmanage;
import com.bayside.personopinion.personuser.service.PersonUserService;
import com.github.wxpay.sdk.WXPayUtil;
@RestController
public class PersonUserController {
	@Autowired
   private PersonUserService personuserServiceImpl;
	@Resource
	private Environment environment;
	/**
	 * 
	 * <p>方法名称：registerPersonUser</p>
	 * <p>方法描述：注册用户</p>
	 * @param record
	 * @return
	 * @author liuyy
	 * @since  2017年7月21日
	 * <p> history 2017年7月21日 Administrator  创建   <p>
	 */
  @RequestMapping(value="/registerPersonUser", method= RequestMethod.GET)
   public Response registerPersonUser(PersonUser record, HttpSession session, HttpServletRequest request, String SMSCode){
	   if(SMSCode==null||!SMSCode.equals((String)session.getAttribute("phonenum"))){
		   return new Response(ResponseStatus.Error,"短信验证码错误",false);
	   }
	   PersonUser personUser = personuserServiceImpl.selectByPrimaryPhone(record.getTelephone());
	   if(personUser!=null){
		   return new Response(ResponseStatus.Error,"手机号已注册",false);
	   }
	   record.setEndtime(null);
	   record.setStatus(0);
	   record.setIsvip(null);
	   record.setIsqq(null);
	   record.setIsweixin(null);
	   record.setIsweibo(null);
	   try {
		   record.setRegisterip(IpUtil.getIpAddr(request));
	   } catch (Exception e) {
		// TODO Auto-generated catch block
		   e.printStackTrace();
	   }
	   record.setWeibouid(null);
	   record.setWechatuid(null);
	   record.setQquid(null);
	   record.setVipexpiredtime(null);
	   record.setId(UuidUtil.getUUID());
	   record.setRegistertime(new Date());
	   record.setPersonid(null);
	   int num = personuserServiceImpl.registerPersonUser(record);
	   if(num > 0){
		   /*PersonmanagerBo personmanager = new PersonmanagerBo();
		   personmanager.setUserid(record.getId());
		   personmanager.setRegistertime(new Date());
		   personmanager.setId(UuidUtil.getUUID());
		   //添加人物
			 personuserServiceImpl.addPersonmanager(personmanager);*/
		   return new Response(ResponseStatus.Success,num,true);
	   }else{
		   return new Response(ResponseStatus.Error,AppConstant.responseInfo.SAVEERRO,false);
	   }
   }
  /**
   * 
   * <p>方法名称：loginPersonUser</p>
   * <p>方法描述：登录</p>
   * @param record
   * @return
   * @author liuyy
   * @since  2017年7月21日
   * <p> history 2017年7月21日 Administrator  创建   <p>
   */
  @RequestMapping(value="/loginPersonUser", method= RequestMethod.GET)
  public Response loginPersonUser(PersonUser record, HttpSession session){
	  PersonUser user = personuserServiceImpl.checkUserlogin(record);
	  if(null!=user){
		  session.setAttribute("userid", user.getId());
		  session.setAttribute("user", user);
		  return new Response(ResponseStatus.Success,user,true);
	  }else{
		  return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
	  }
  }
  /*
  @RequestMapping(value="/updatePersonmanagerInfo", method= RequestMethod.POST)
  public Response updatePersonmanagerInfo(PersonmanagerBo record,HttpServletRequest request){
	  String userid = (String)request.getSession().getAttribute("userid");
	  int num = personuserServiceImpl.updatePersonmanagerInfo(record);
	  PersonUser peruser = new PersonUser();
	  peruser.setId(userid);
	  if(null!=record){
		  if(null!=record.getName()){
			  peruser.setName(record.getName());
		  }
	  }
	  //修改用户表信息
	  int sum = personuserServiceImpl.updatePersonUser(peruser);
	if(num > 0 && sum> 0){
		return new Response(ResponseStatus.Success,num,true);
	}else{
		return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
	}
	 
  }*/
  

  /**
   * 
   * <p>方法名称：selectExperiencePage</p>
   * <p>方法描述查询体验中心人物/p>
   * @param record
   * @return
   * @author liuyy
   * @since  2017年7月22日
   * <p> history 2017年7月22日 Administrator  创建   <p>
   */
  @RequestMapping(value="/selectExperiencePage", method= RequestMethod.GET)
 public Response selectExperiencePage(Personmanage record){
//	 List<Personexpirence> list = personuserServiceImpl.selectExpirencePage(record);
	 List<Personmanage> list = personuserServiceImpl.selectPersonmanage(record);
	 if(list.size()>0){
		 return new Response(ResponseStatus.Success,list,true);
		 
	 }else{
		 return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
	 }
 }

  /**
   * 
   * <p>方法名称：updatePersonPass</p>
   * <p>方法描述：修改用户密码</p>
   * @param Puser
   * @param session
   * @param oldPassWD
   * @return
   * @author 常瑞
   * @since  2017年9月28日
   * <p> history 2017年9月28日 常瑞  创建   </p>
   */
  @RequestMapping(value="/updatePersonPass", method = RequestMethod.GET)
  public Response updatePersonPass(PersonUser Puser, HttpSession session,String oldPassWD){
	    if(null==(String)session.getAttribute("userid")||"".equals((String)session.getAttribute("userid"))){
		    return new Response(ResponseStatus.Error,"您未登录",false);
	    }
	  	if(!oldPassWD.equals(((PersonUser)session.getAttribute("user")).getPassword())){
	  		return new Response(ResponseStatus.Error,"原密码错误",false);
	  	}
	  	Puser.setTelephone(((PersonUser)session.getAttribute("user")).getTelephone());
	  	int i = personuserServiceImpl.updateByPrimaryTalphone(Puser);
		if(i>0){
			return new Response(ResponseStatus.Success,i,true);
		}else{
			return new Response(ResponseStatus.Error,"系统异常",false);
		}
  }
  
  	/**
  	 * 
  	 * <p>方法名称：verifyPhoneAuthCode</p>
  	 * <p>方法描述：验证手机验证码</p>
  	 * @param phonecode
  	 * @param session
  	 * @param request
  	 * @param response
  	 * @return
  	 * @author 常瑞
  	 * @since  2017年9月28日
  	 * <p> history 2017年9月28日 常瑞  创建   </p>
  	 */
	@RequestMapping(value="/verifyPhoneAuthCode", method=RequestMethod.GET)
	public Response verifyPhoneAuthCode(String phonecode,HttpSession session,HttpServletRequest request, HttpServletResponse response){
		if("".equals(phonecode) || phonecode == null ){
			String ssss = "手机验证码错误！";
			return new Response(ResponseStatus.Error,ssss,false);
		}else{
			String str = "";
	        session = request.getSession(true);  
	        str = (String) session.getAttribute("phonenum"); 
	        if(str != null){
	        	if(str.equals(phonecode)){
		        	String ssss = "手机验证码正确！";
					return new Response(ResponseStatus.Success,ssss,true);
				}else{
		        	String ssss = "手机验证码错误！";
					return new Response(ResponseStatus.Error,ssss,false);
				}
	        }else{
	        	String ssss = "没有获取到手机验证码！";
				return new Response(ResponseStatus.Error,ssss,false);
	        }
		}
	}
  
  /**
   * <p>方法名称：sendCode</p>
   * <p>方法描述：发送验证码</p>
   * @param String talphone(手机号)
   * @return
   * @author peigd
   * @since  2017年7月27日
   * <p> history 2017年7月27日 administrator  创建   <p>
   */
  @RequestMapping(value="/sendCode", method = RequestMethod.GET)
  public Response sendCode(String talphone, HttpSession session, HttpServletRequest request){
	  	PersonUser personUser = personuserServiceImpl.selectByPrimaryPhone(talphone);
	    if(personUser!=null){
		    return new Response(ResponseStatus.Error,"手机号已注册",false);
	    }
	  	boolean status = false;
		Random rd = new Random();
		int rdnum = rd.nextInt(900000)+100000;
		String number = rdnum+"";
	  	List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("Account", "lyy"));//账号
		formparams.add(new BasicNameValuePair("Pwd", "3355315CD86A2BC5B0A6F2114DC4"));//秘钥
		formparams.add(new BasicNameValuePair("Content",number));//参数
		formparams.add(new BasicNameValuePair("Mobile", talphone));//手机号
		formparams.add(new BasicNameValuePair("TemplateId", "30763"));//模板号
		formparams.add(new BasicNameValuePair("SignId", "30273"));//签名ID
		try {
			System.out.println("开始给"+talphone+"发送短信");
			session = request.getSession(true);  
	        session.setAttribute("phonenum", number);
			SendCode.Post(formparams);
			status = true;
			System.out.println("发送成功！");
		} catch (Exception e) {
			System.out.println("短信发送失败");
			e.printStackTrace();
		}
		if(status){
			return new Response(ResponseStatus.Success,null,true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.SENDERORR,false);
		}
  }
  
  /**
   * 
   * <p>方法名称：forgetPasswordSendCode</p>
   * <p>方法描述：发送验证码（忘记密码使用）</p>
   * @param talphone
   * @param session
   * @param request
   * @return
   * @author 常瑞
   * @since  2017年9月28日
   * <p> history 2017年9月28日 常瑞  创建   </p>
   */
  @RequestMapping(value="/forgetPasswordSendCode", method = RequestMethod.GET)
  public Response forgetPasswordSendCode(String telephone, HttpSession session, HttpServletRequest request){
	  if(telephone != null && !"".equals(telephone)){//验证手机号
		  PersonUser personUser = personuserServiceImpl.selectByPrimaryPhone(telephone);
		  if(personUser==null){
			  return new Response(ResponseStatus.Error,"您未注册",false);
		  }
	  }
	  	boolean status = false;
		Random rd = new Random();
		int rdnum = rd.nextInt(900000)+100000;
		String number = rdnum+"";
	  	List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	  	formparams.add(new BasicNameValuePair("Account", "lyy"));//账号
		formparams.add(new BasicNameValuePair("Pwd", "3355315CD86A2BC5B0A6F2114DC4"));//秘钥
		formparams.add(new BasicNameValuePair("Content",number));//参数
		formparams.add(new BasicNameValuePair("Mobile", telephone));//手机号
		formparams.add(new BasicNameValuePair("TemplateId", "30763"));//模板号
		formparams.add(new BasicNameValuePair("SignId", "30273"));//签名ID
		try {
			session = request.getSession(true);  
	        session.setAttribute("forgetPasswordCode", number);
	        session.setAttribute("forgetPasswordPhone", telephone);
			SendCode.Post(formparams);
			status = true;
			System.out.println("发送成功！");
		} catch (Exception e) {
			System.out.println("短信发送失败");
			e.printStackTrace();
		}
		if(status){
			return new Response(ResponseStatus.Success,null,true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.SENDERORR,false);
		}
  }
  
  @RequestMapping(value="/forgetPasswordReset", method= RequestMethod.GET)
  public Response forgetPasswordReset(HttpSession session, HttpServletRequest request, String newPassWD, String SMSCode){
	   if(SMSCode==null||!SMSCode.equals((String)session.getAttribute("forgetPasswordCode"))){
		   return new Response(ResponseStatus.Error,"短信验证码错误",false);
	   }
	   PersonUser puser = new PersonUser();
	   puser.setTelephone((String)session.getAttribute("forgetPasswordPhone"));
	   puser.setPassword(newPassWD);
	   int i = personuserServiceImpl.updateByPrimaryTalphone(puser);
	   if(i>0){
			return new Response(ResponseStatus.Success,null,true);
		}else{
			return new Response(ResponseStatus.Error,null,false);
		}
  }
  
  /**
   * 
   * <p>方法名称：selectByTalphone</p>
   * <p>方法描述：查询手机号是否重复</p>
   * @param talphone
   * @return
   * @author 常瑞
   * @since  2017年9月28日
   * <p> history 2017年9月28日 常瑞  创建   </p>
   */
  @RequestMapping(value="/selectByTalphone", method = RequestMethod.GET)
  public Response selectByTalphone(String talphone){
	  if(talphone != null && !"".equals(talphone)){//验证手机号
		  PersonUser personUser = personuserServiceImpl.selectByPrimaryPhone(talphone);
		  if(personUser==null){
			  return new Response(ResponseStatus.Success,null,true);
		  }
	  }
	  return new Response(ResponseStatus.Error,null,false);
  }
  /**
   * 
   * <p>方法名称：selectByTalphone</p>
   * <p>方法描述：验证用户原密码</p>
   * @param String password(密码)
   * @return
   * @author peigd
   * @since  2017年7月26日
   * <p> history 2017年7月26日 administrator  创建   <p>
   */
  @RequestMapping(value="/selectByPassword", method = RequestMethod.GET)
  public Response selectByPass(String password, String talphone){
	  if(talphone != null && !"".equals(talphone)){
		  if(password != null && !"".equals(password)){//验证原密码
				 PersonUser puser =  personuserServiceImpl.selectByPrimaryPhone(talphone);
				 if(password.equals(puser.getPassword())){
					 return new Response(ResponseStatus.Success,null,true);
				 }else{
					 return new Response(ResponseStatus.Error,AppConstant.responseInfo.PASSERORR,false);
				 }
		  }
	  }
	  return new Response(ResponseStatus.Error,null,false);
  }
  /**
   * 完善人物信息
   * <p>方法名称：selectPersonInfo</p>
   * <p>方法描述：完善人物信息</p>
   * @param record
   * @return
   * @author liuyy
   * @since  2017年9月27日
   * <p> history 2017年9月27日 Administrator  创建   <p>
   */
  @RequestMapping(value="/selectPersonInfo", method = RequestMethod.GET)
  public Response selectPersonInfo(Personmanage record,HttpServletRequest request){
	  String userid = (String)request.getSession().getAttribute("userid");
	  record.setUserid(userid);
	  record.setId(UuidUtil.getUUID());
	  int num = personuserServiceImpl.insertSelective(record);
	  if(num > 0){
		  //修改用户信息
		  PersonUser peruser = new PersonUser();
		  peruser.setId(userid);
		  if(null!=record){
			  if(null!=record.getName()){
				  peruser.setName(record.getName());
			  }
			  if(null!=record.getId()){
				  peruser.setPersonid(record.getId());
			  }
		  }
		  //修改用户表信息
		  int sum = personuserServiceImpl.updatePersonUser(peruser);
		  return new Response(ResponseStatus.Success,num,true);
	  }else{
		  return new Response(ResponseStatus.Error,num,false);
	  }
  }
  
  /**
   * 
   * <p>方法名称：updatePersonmanagerInfoOnly</p>
   * <p>方法描述：修改用户表信息（绑定第三方账户使用）</p>
   * @param peruser
   * @param request
   * @return
   * @author 常瑞
   * @since  2017年9月26日
   * <p> history 2017年9月26日 常瑞  创建   </p>
   */
  @RequestMapping(value="/updatePersonmanagerInfoOnly", method= RequestMethod.GET)
  public Response updatePersonmanagerInfoOnly(PersonUser peruser,HttpServletRequest request){
	  String userid = (String)request.getSession().getAttribute("userid");
	  peruser.setId(userid);
	  int sum = personuserServiceImpl.updatePersonUser(peruser);
	  if(sum > 0){
		  return new Response(ResponseStatus.Success,sum,true);
	  }else{
		  return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
	  }
  }
  
  /**
   * 
   * <p>方法名称：loginWithEscrowAccount</p>
   * <p>方法描述：第三方登录</p>
   * @param peruser
   * @param request
   * @return
   * @author 常瑞
   * @since  2017年9月26日
   * <p> history 2017年9月26日 常瑞  创建   </p>
   */
  @RequestMapping(value="/loginWithEscrowAccount", method= RequestMethod.GET)
  public Response loginWithEscrowAccount(PersonUser peruser,HttpServletRequest request, HttpSession session){
	  PersonUser loginUser = personuserServiceImpl.loginWithEscrowAccount(peruser);
	  if(null!=loginUser){
		  session.setAttribute("userid", loginUser.getId());
		  session.setAttribute("user", loginUser);
		  return new Response(ResponseStatus.Success,loginUser,true);
	  }else{
		  return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
	  }
  }
  
  /**
   * 
   * <p>方法名称：submitFeedback</p>
   * <p>方法描述：提交意见反馈</p>
   * @param feedback
   * @param request
   * @param session
   * @return
   * @author 常瑞
   * @since  2017年9月26日
   * <p> history 2017年9月26日 常瑞  创建   </p>
   */
  @RequestMapping(value="/submitFeedback", method= RequestMethod.GET)
  public Response submitFeedback(Feedback feedback,HttpServletRequest request, HttpSession session){
	  feedback.setId(UuidUtil.getUUID());
	  feedback.setUserid((String)request.getSession().getAttribute("userid"));
	  feedback.setCreatetime(new Date());
	  feedback.setAdminid(null);
	  feedback.setReply(null);
	  feedback.setReplytime(null);
	  int i = personuserServiceImpl.insertFeedback(feedback);
	  if(i>0){
		  return new Response(ResponseStatus.Success,i,true);
	  }else{
		  return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
	  }
  }
  /**
   * 
   * <p>方法名称：uploadFeedbackImg</p>
   * <p>方法描述：意见提交反馈，上传图片方法。</p>
   * @param img
   * @param request
   * @author 常瑞
   * @since  2017年9月27日
   * <p> history 2017年9月27日 常瑞  创建   </p>
   */
  @RequestMapping(value="/uploadFeedbackImg", method= RequestMethod.POST)
  public void uploadFeedbackImg(@RequestBody String img, HttpServletRequest request){
	    String imgfilename = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+UuidUtil.getUUID().substring(0, 5)+".jpg";
		String path = request.getSession().getServletContext().getRealPath("/FeedbackImg");
		System.out.println(path+"/"+imgfilename);
		File targetFile = new File(path,imgfilename);
		if (!targetFile.getParentFile().exists()) {
			targetFile.getParentFile().mkdirs();
		}
		if (!targetFile.exists()) {
			try {
				targetFile.createNewFile();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		ImgBase64Decode.generateImage(img, path+"/"+imgfilename);
  }
  
  /**
   * 
   * <p>方法名称：membershipOpenWxPay</p>
   * <p>方法描述：通过微信支付开通会员</p>
   * @param period
   * @param personPayHistoty
   * @param request
   * @param response
   * @param session
   * @return
   * @author 常瑞
   * @since  2017年9月27日
   * <p> history 2017年9月27日 常瑞  创建   </p>
   */
  @RequestMapping(value="/membershipOpenWxPay", method= RequestMethod.GET)
  public Response membershipOpenWxPay(PersonPayHistoty personPayHistoty, HttpServletRequest request, HttpServletResponse response, HttpSession session){
	  if(null==(String)session.getAttribute("userid")||"".equals((String)session.getAttribute("userid"))){
		  return new Response(ResponseStatus.Error,"您未登录",false);
	  }
	  double payables = 0.0;
	  if("1mo".equals(personPayHistoty.getViptype())){
		  payables = 22.0;
	  }else if("3mo".equals(personPayHistoty.getViptype())){
		  payables = 60.0;
	  }else if("6mo".equals(personPayHistoty.getViptype())){
		  payables = 110.0;
	  }else if("12mo".equals(personPayHistoty.getViptype())){
		  payables = 210.0;
	  }
	  if(payables==0.0){
		  return new Response(ResponseStatus.Error,"支付金额异常",false);
	  }
	  // 查询是否是首次使用微信支付
	  PersonPower personPower = new PersonPower();
	  personPower.setUserid((String)session.getAttribute("userid"));
	  personPower.setPaytypeid("微信");
	  personPower = personuserServiceImpl.selectPersonPowerCiShu(personPower);
	  if(personPower==null||personPower.getCishu()<1){
		  payables = PrecisionIsTheOnlyStandard.doubleCalculation(payables, "-", 5);
	  }
	  
	  int jyje = (int)PrecisionIsTheOnlyStandard.doubleCalculation(payables, "*", 100);
	  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
	  Date date1 = new Date();
	  String str = simpleDateFormat.format(date1);
	  Random random = new Random();
	  int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
	  String ordernum = str+rannum;
	  HashMap<String, String> data = new HashMap<String, String>();
      data.put("appid", "wxbbf9259beddfa2f7");
      data.put("body", "开发测试CR");
      data.put("device_info", "WEB");
      data.put("mch_id", "1484590662");
      data.put("nonce_str", WXPayUtil.generateNonceStr());
      data.put("notify_url", "http://www.bei-sai.com/wxpay.php");
      data.put("out_trade_no", ordernum);
      data.put("spbill_create_ip", LoginIp.getIpAddr(request));
      data.put("spbill_create_ip", "218.58.38.100");
      data.put("total_fee", ""+jyje);
      data.put("trade_type", "MWEB");
      try {
      	String sign = WXPayUtil.generateSignature(data, "adldkfifkr8fiujnhxUdjehgudjeuyyF");
	    data.put("sign", sign);
	  } catch (Exception e) {
		e.printStackTrace();
	  }
      Map<String, String> rtnmap = new HashMap<String, String>();
	  try {
			rtnmap = WXPayUtil.xmlToMap(SendPost.jsonPost("https://api.mch.weixin.qq.com/pay/unifiedorder", data));
	  } catch (Exception e) {
			e.printStackTrace();
	  }
	  if("SUCCESS".equals(rtnmap.get("return_code"))){
		  personPayHistoty.setId(UuidUtil.getUUID());
		  personPayHistoty.setUserid((String)session.getAttribute("userid"));
		  personPayHistoty.setReceipt_amount(payables);
		  personPayHistoty.setCostnumber(null);
		  personPayHistoty.setCosttype("1");
		  personPayHistoty.setRequestip(LoginIp.getIpAddr(request));
		  personPayHistoty.setStatus(0);
		  personPayHistoty.setRefundTime(null);
		  personPayHistoty.setRefund_fee(null);
		  personPayHistoty.setCostTime(null);
		  personPayHistoty.setPersonid(null);
		  personPayHistoty.setOrdernumber(ordernum);
		  personPayHistoty.setPaynumber(rtnmap.get("prepay_id"));
		  int i = personuserServiceImpl.insertPayHistoty(personPayHistoty);
		  if(i>0){
			  return new Response(ResponseStatus.Success,rtnmap.get("mweb_url")+","+ordernum,true);
		  }else{
			  return new Response(ResponseStatus.Error,"系统异常，请勿付款",false);
		  }
	  }else{
		  System.out.println(rtnmap.toString());
		  return new Response(ResponseStatus.Error,"微信侧异常",false);
	  }
  }
  
  /**
   * 
   * <p>方法名称：wxPayQuantity</p>
   * <p>方法描述：微信支付历史次数查询</p>
   * @param request
   * @param response
   * @param session
   * @return
   * @author 常瑞
   * @since  2017年9月27日
   * <p> history 2017年9月27日 常瑞  创建   </p>
   */
  @RequestMapping(value="/wxPayQuantity", method= RequestMethod.GET)
  public Response wxPayQuantity(HttpServletRequest request, HttpServletResponse response, HttpSession session){
	  PersonPower personPower = new PersonPower();
	  personPower.setUserid((String)session.getAttribute("userid"));
	  personPower.setPayname("微信");
	  personPower = personuserServiceImpl.selectPersonPowerCiShu(personPower);
	  return new Response(ResponseStatus.Success,personPower,true);
  }
  
  /**
   * 
   * <p>方法名称：wxPayInquire</p>
   * <p>方法描述：验证微信订单支付状态</p>
   * @param personPayHistoty
   * @param request
   * @param response
   * @param session
   * @return
   * @author 常瑞
 * @throws ParseException 
   * @since  2017年9月27日
   * <p> history 2017年9月27日 常瑞  创建   </p>
   */
  @RequestMapping(value="/wxPayInquire", method= RequestMethod.GET)
  public Response wxPayInquire(PersonPayHistoty personPayHistoty, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ParseException{
	  HashMap<String, String> data = new HashMap<String, String>();
      data.put("appid", "wxbbf9259beddfa2f7");
      data.put("mch_id", "1484590662");
      data.put("nonce_str", WXPayUtil.generateNonceStr());
      data.put("out_trade_no", personPayHistoty.getOrdernumber());
      try {
      	String sign = WXPayUtil.generateSignature(data, "adldkfifkr8fiujnhxUdjehgudjeuyyF");
      	System.out.println(sign);
			data.put("sign", sign);
		} catch (Exception e) {
			e.printStackTrace();
		}
      Map<String, String> rtnmap = new HashMap<String, String>();
		try {
			rtnmap = WXPayUtil.xmlToMap(SendPost.jsonPost("https://api.mch.weixin.qq.com/pay/orderquery", data));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if("SUCCESS".equals(rtnmap.get("result_code"))){
			if("NOTPAY".equals(rtnmap.get("trade_state"))){
				return new Response(ResponseStatus.Error,"订单未支付",false);
			}else if("CLOSED".equals(rtnmap.get("trade_state"))){
				return new Response(ResponseStatus.Error,"已关闭",false);
			}else if("REVOKED".equals(rtnmap.get("trade_state"))){
				return new Response(ResponseStatus.Error,"已撤销（刷卡支付）",false);
			}else if("USERPAYING".equals(rtnmap.get("trade_state"))){
				return new Response(ResponseStatus.Error,"用户支付中",false);
			}else if("PAYERROR".equals(rtnmap.get("trade_state"))){
				return new Response(ResponseStatus.Error,"支付失败(其他原因，如银行返回失败)",false);
			}else if("REFUND".equals(rtnmap.get("trade_state"))){
				return new Response(ResponseStatus.Error,"转入退款",false);
			}else if("SUCCESS".equals(rtnmap.get("trade_state"))){
				PersonPayHistoty pphz = personuserServiceImpl.selectPayHistotyByOrderNumber(personPayHistoty);
				if(pphz.getStatus()==1){
					return new Response(ResponseStatus.Success,"已支付",true);
				}else{
					if(rtnmap.get("total_fee").equals(""+(int)PrecisionIsTheOnlyStandard.doubleCalculation(pphz.getReceipt_amount(), "*", 100))){
						pphz.setStatus(1);
						pphz.setCosttype("1");
						pphz.setCostnumber(rtnmap.get("openid"));
						SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");   
						pphz.setCostTime(bartDateFormat.parse(rtnmap.get("time_end")));
						pphz.setPaynumber(rtnmap.get("transaction_id"));
						personuserServiceImpl.updatePayHistoty(pphz);
						int yfc = 0;
						if(pphz.getViptype().equals("1mo")){
							yfc = 1;
						}else if(pphz.getViptype().equals("3mo")){
							yfc = 3;
						}else if(pphz.getViptype().equals("6mo")){
							yfc = 6;
						}else if(pphz.getViptype().equals("12mo")){
							yfc = 12;
						}
						if(yfc==0){
							return new Response(ResponseStatus.Error,"会员时长异常，请联系管理员",false);
						}
						Date dqsj = null;
						PersonUser personUser = personuserServiceImpl.selectByPrimaryKey(pphz.getUserid());
						if(personUser.getVipexpiredtime()==null||personUser.getVipexpiredtime().getTime()<new Date().getTime()){
							PersonUser personUserxg = new PersonUser();
							personUserxg.setId(pphz.getUserid());
							personUserxg.setVipexpiredtime(datautil(yfc));
							dqsj = personUserxg.getVipexpiredtime();
							personuserServiceImpl.updatePersonUser(personUserxg);
						}else{
							PersonUser personUserxg = new PersonUser();
							personUserxg.setId(pphz.getUserid());
							personUserxg.setVipexpiredtime(datautil1(yfc,personUser.getVipexpiredtime()));
							dqsj = personUserxg.getVipexpiredtime();
							personuserServiceImpl.updatePersonUser(personUserxg);
						}
						PersonPower personPower = new PersonPower();
						personPower.setId(UuidUtil.getUUID());
						personPower.setUserid(pphz.getUserid());
						personPower.setPersonid(null);
						personPower.setPaytypeid(pphz.getViptype());
						personPower.setPayname("微信");
						personPower.setPaymoney(pphz.getReceipt_amount());
						personPower.setExpirytime(dqsj);
						personuserServiceImpl.insertPersonPower(personPower);
						return new Response(ResponseStatus.Success,"已支付",true);
					}else{
						return new Response(ResponseStatus.Error,"到账金额异常，请联系管理员",false);
					}
					
				}
			}else{
				return new Response(ResponseStatus.Error,"微信侧异常",false);
			}
		}else{
			return new Response(ResponseStatus.Error,"微信侧异常",false);
		}
  }
  
  public static Date datautil(int yfc){
	  Date date = new Date();
	  Calendar calendar = Calendar.getInstance();
	  calendar.setTime(date);
	  calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + yfc);
	  date = calendar.getTime();
	  return date;
  }
  
  public static Date datautil1(int yfc,Date dqsj){
	  Calendar calendar = Calendar.getInstance();
	  calendar.setTime(dqsj);
	  calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + yfc);
	  dqsj = calendar.getTime();
	  return dqsj;
  }
  
  /**
   * 
   * <p>方法名称：wxPayAutoRtn</p>
   * <p>方法描述：获取微信订单状态</p>
   * @param session
   * @param request
   * @return
   * @author 常瑞
   * @since  2017年9月27日
   * <p> history 2017年9月27日 常瑞  创建   </p>
   */
  @RequestMapping(value="/wxPayAutoRtn", method=RequestMethod.GET)
  public Response wxPayAutoRtn(PersonPayHistoty personPayHistoty, HttpSession session, HttpServletRequest request){
	  PersonPayHistoty pphz = personuserServiceImpl.selectPayHistotyByOrderNumber(personPayHistoty);
	  if(null!=pphz){
		  return new Response(ResponseStatus.Success,pphz,true);
	  }else{
		  return new Response(ResponseStatus.Error,"无查询结果",false);
	  }
  }
  
  /**
	 * 
	 * <p>方法名称：wxPayNotice</p>
	 * <p>方法描述：微信通知接口（由微信服务端调用）</p>
	 * @param request
	 * @param response
	 * @author 常瑞
	 * @since  2017年9月26日
	 * <p> history 2017年9月26日 常瑞  创建   </p>
	 */
	@RequestMapping(value="/wxPayNotice", method=RequestMethod.GET)
	public void wxPayNotice(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/xml");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			String buffer = null;
			StringBuffer xml = new StringBuffer();
			while ((buffer = br.readLine()) != null) {
				xml.append(buffer);
			}
			Document document = null;
			SAXReader reader = new SAXReader();
			ByteArrayInputStream inputStream = new ByteArrayInputStream(xml
			        .toString().getBytes());
			InputStreamReader ir = new InputStreamReader(inputStream);
			document = reader.read(ir);
			String documentStr = document.asXML();
			Map<String, String> noticeMap = WXPayUtil.xmlToMap(documentStr);
			if("SUCCESS".equals(noticeMap.get("return_code"))){
//				Recharge rcz = userServiceImpl.selectTradeBytradenum(noticeMap.get("out_trade_no"));
				PersonPayHistoty personPayHistoty = new PersonPayHistoty();
				personPayHistoty.setOrdernumber(noticeMap.get("out_trade_no"));
				PersonPayHistoty pphz = personuserServiceImpl.selectPayHistotyByOrderNumber(personPayHistoty);
				if(null!=pphz){
					if(noticeMap.get("total_fee").equals(""+(int)PrecisionIsTheOnlyStandard.doubleCalculation(pphz.getReceipt_amount(), "*", 100))){
						if(1==pphz.getStatus()){
							HashMap<String, String> data = new HashMap<String, String>();
							data.put("return_code", "SUCCESS");
							data.put("return_msg", "OK");
							byte[] xmlData = WXPayUtil.mapToXml(data).getBytes();
							response.setContentLength(xmlData.length);
							ServletOutputStream os = response.getOutputStream();
							os.write(xmlData);
							os.flush();
							os.close();
						}else{
							pphz.setStatus(1);
							pphz.setCosttype("1");
							pphz.setCostnumber(noticeMap.get("openid"));
							SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");   
							pphz.setCostTime(bartDateFormat.parse(noticeMap.get("time_end")));
							pphz.setPaynumber(noticeMap.get("transaction_id"));
							personuserServiceImpl.updatePayHistoty(pphz);
							
							int yfc = 0;
							if(pphz.getViptype().equals("1mo")){
								yfc = 1;
							}else if(pphz.getViptype().equals("3mo")){
								yfc = 3;
							}else if(pphz.getViptype().equals("6mo")){
								yfc = 6;
							}else if(pphz.getViptype().equals("12mo")){
								yfc = 12;
							}
							Date dqsj = null;
							PersonUser personUser = personuserServiceImpl.selectByPrimaryKey(pphz.getUserid());
							if(personUser.getVipexpiredtime()==null||personUser.getVipexpiredtime().getTime()<new Date().getTime()){
								PersonUser personUserxg = new PersonUser();
								personUserxg.setId(pphz.getUserid());
								personUserxg.setVipexpiredtime(datautil(yfc));
								dqsj = personUserxg.getVipexpiredtime();
								personuserServiceImpl.updatePersonUser(personUserxg);
							}else{
								PersonUser personUserxg = new PersonUser();
								personUserxg.setId(pphz.getUserid());
								personUserxg.setVipexpiredtime(datautil1(yfc,personUser.getVipexpiredtime()));
								dqsj = personUserxg.getVipexpiredtime();
								personuserServiceImpl.updatePersonUser(personUserxg);
							}
							PersonPower personPower = new PersonPower();
							personPower.setId(UuidUtil.getUUID());
							personPower.setUserid(pphz.getUserid());
							personPower.setPersonid(null);
							personPower.setPaytypeid(pphz.getViptype());
							personPower.setPayname("微信");
							personPower.setPaymoney(pphz.getReceipt_amount());
							personPower.setExpirytime(dqsj);
							personuserServiceImpl.insertPersonPower(personPower);
							
							HashMap<String, String> data = new HashMap<String, String>();
							data.put("return_code", "SUCCESS");
							data.put("return_msg", "OK");
							byte[] xmlData = WXPayUtil.mapToXml(data).getBytes();
							response.setContentLength(xmlData.length);
							ServletOutputStream os = response.getOutputStream();
							os.write(xmlData);
							os.flush();
							os.close();
						}
					}else{
						HashMap<String, String> data = new HashMap<String, String>();
						data.put("return_code", "SUCCESS");
						data.put("return_msg", "OK");
						byte[] xmlData = WXPayUtil.mapToXml(data).getBytes();
						response.setContentLength(xmlData.length);
						ServletOutputStream os = response.getOutputStream();
						os.write(xmlData);
						os.flush();
						os.close();
					}
				}else{
					HashMap<String, String> data = new HashMap<String, String>();
					data.put("return_code", "SUCCESS");
					data.put("return_msg", "OK");
					byte[] xmlData = WXPayUtil.mapToXml(data).getBytes();
					response.setContentLength(xmlData.length);
					ServletOutputStream os = response.getOutputStream();
					os.write(xmlData);
					os.flush();
					os.close();
				}
			}else{
				HashMap<String, String> data = new HashMap<String, String>();
				data.put("return_code", "SUCCESS");
				data.put("return_msg", "OK");
				byte[] xmlData = WXPayUtil.mapToXml(data).getBytes();
				response.setContentLength(xmlData.length);
				ServletOutputStream os = response.getOutputStream();
				os.write(xmlData);
				os.flush();
				os.close();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * <p>方法名称：membershipOpenSystemOrder</p>
	 * <p>方法描述：开通会员系统下单（仅下单，支付宝支付可调用）</p>
	 * @param personPayHistoty
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @author 常瑞
	 * @since  2017年9月28日
	 * <p> history 2017年9月28日 常瑞  创建   </p>
	 */
	@RequestMapping(value="/membershipOpenSystemOrder", method= RequestMethod.GET)
	public Response membershipOpenSystemOrder(PersonPayHistoty personPayHistoty, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		if(null==(String)session.getAttribute("userid")||"".equals((String)session.getAttribute("userid"))){
			return new Response(ResponseStatus.Error,"您未登录",false);
		}
		double payables = 0.0;
		if("1mo".equals(personPayHistoty.getViptype())){
			payables = 22.0;
		}else if("3mo".equals(personPayHistoty.getViptype())){
			payables = 60.0;
		}else if("6mo".equals(personPayHistoty.getViptype())){
			payables = 110.0;
		}else if("12mo".equals(personPayHistoty.getViptype())){
			payables = 210.0;
		}
		if(payables==0.0){
			return new Response(ResponseStatus.Error,"支付金额异常",false);
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date1 = new Date();
		String str = simpleDateFormat.format(date1);
		Random random = new Random();
		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
		String ordernum = str+rannum;
		personPayHistoty.setId(UuidUtil.getUUID());
		personPayHistoty.setUserid((String)session.getAttribute("userid"));
		personPayHistoty.setReceipt_amount(payables);
		personPayHistoty.setCostnumber(null);
//		personPayHistoty.setCosttype("1");
		personPayHistoty.setRequestip(LoginIp.getIpAddr(request));
		personPayHistoty.setStatus(0);
		personPayHistoty.setRefundTime(null);
		personPayHistoty.setRefund_fee(null);
		personPayHistoty.setCostTime(null);
		personPayHistoty.setPersonid(null);
		personPayHistoty.setOrdernumber(ordernum);
		int i = personuserServiceImpl.insertPayHistoty(personPayHistoty);
		if(i>0){
			return new Response(ResponseStatus.Success,personPayHistoty,true);
		}else{
			return new Response(ResponseStatus.Error,"系统异常，订单作废",false);
		}
	}
	
	/**
	 * 
	 * <p>方法名称：aliPayStartPay</p>
	 * <p>方法描述：支付宝付款开始支付（唤起alipay支付页面）</p>
	 * @param personPayHistoty
	 * @param request
	 * @param response
	 * @author 常瑞
	 * @since  2017年9月29日
	 * <p> history 2017年9月29日 常瑞  创建   </p>
	 */
	@RequestMapping(value="/aliPayStartPay", method= RequestMethod.GET)
	public void aliPayStartPay(PersonPayHistoty personPayHistoty, HttpServletRequest request, HttpServletResponse response){
		PersonPayHistoty pphz = personuserServiceImpl.selectPayHistotyByOrderNumber(personPayHistoty);
		if(pphz==null){
			return;
		}
		if(pphz.getStatus()==1){
			return;
		}
		String out_trade_no = pphz.getOrdernumber();
		String subject = new String("开通会员");
		String total_amount = ""+pphz.getReceipt_amount();
		String body = "开通个人舆情会员";
		String timeout_express="2m";
		String product_code="QUICK_WAP_PAY";
		AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
	    AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
	    
	    AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
	    model.setOutTradeNo(out_trade_no);
	    model.setSubject(subject);
	    model.setTotalAmount(total_amount);
	    model.setBody(body);
	    model.setTimeoutExpress(timeout_express);
	    model.setProductCode(product_code);
	    alipay_request.setBizModel(model);
	    alipay_request.setNotifyUrl(AlipayConfig.notify_url);
	    alipay_request.setReturnUrl(AlipayConfig.return_url);   
	    String form = "";
	    try {
			form = client.pageExecute(alipay_request).getBody();
			response.setContentType("text/html;charset=" + AlipayConfig.CHARSET); 
		    response.getWriter().write(form);
		    response.getWriter().flush(); 
		    response.getWriter().close();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * <p>方法名称：aliPayNotice</p>
	 * <p>方法描述：支付宝支付异步通知接口（由alipay服务端调用）</p>
	 * @param request
	 * @param response
	 * @author 常瑞
	 * @throws AlipayApiException 
	 * @throws IOException 
	 * @throws ParseException 
	 * @since  2017年9月28日
	 * <p> history 2017年9月28日 常瑞  创建   </p>
	 */
	@RequestMapping(value="/aliPayNotice", method= RequestMethod.POST)
	public void aliPayNotice(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, IOException, ParseException{
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");
		System.out.println(params);
		if(verify_result){
			if(trade_status.equals("TRADE_FINISHED")||trade_status.equals("TRADE_SUCCESS")){
				PersonPayHistoty personPayHistoty = new PersonPayHistoty();
				personPayHistoty.setOrdernumber(out_trade_no);
				PersonPayHistoty pphz = personuserServiceImpl.selectPayHistotyByOrderNumber(personPayHistoty);
				if(pphz==null){
					return;
				}
				if(pphz.getStatus()==1){
					return;
				}
				pphz.setStatus(1);
				pphz.setCosttype("2");
				pphz.setCostnumber(params.get("buyer_id"));
				pphz.setCostTime(DateFormatUtil.stringFormatDateTime(params.get("gmt_payment")));
				pphz.setPaynumber(params.get("trade_no"));
				personuserServiceImpl.updatePayHistoty(pphz);
				int yfc = 0;
				if(pphz.getViptype().equals("1mo")){
					yfc = 1;
				}else if(pphz.getViptype().equals("3mo")){
					yfc = 3;
				}else if(pphz.getViptype().equals("6mo")){
					yfc = 6;
				}else if(pphz.getViptype().equals("12mo")){
					yfc = 12;
				}
				if(yfc==0){
				}
				Date dqsj = null;
				PersonUser personUser = personuserServiceImpl.selectByPrimaryKey(pphz.getUserid());
				if(personUser.getVipexpiredtime()==null||personUser.getVipexpiredtime().getTime()<new Date().getTime()){
					PersonUser personUserxg = new PersonUser();
					personUserxg.setId(pphz.getUserid());
					personUserxg.setVipexpiredtime(datautil(yfc));
					dqsj = personUserxg.getVipexpiredtime();
					personuserServiceImpl.updatePersonUser(personUserxg);
				}else{
					PersonUser personUserxg = new PersonUser();
					personUserxg.setId(pphz.getUserid());
					personUserxg.setVipexpiredtime(datautil1(yfc,personUser.getVipexpiredtime()));
					dqsj = personUserxg.getVipexpiredtime();
					personuserServiceImpl.updatePersonUser(personUserxg);
				}
				PersonPower personPower = new PersonPower();
				personPower.setId(UuidUtil.getUUID());
				personPower.setUserid(pphz.getUserid());
				personPower.setPersonid(null);
				personPower.setPaytypeid(pphz.getViptype());
				personPower.setPayname("支付宝");
				personPower.setPaymoney(pphz.getReceipt_amount());
				personPower.setExpirytime(dqsj);
				personuserServiceImpl.insertPersonPower(personPower);
			}
		}else{
		}
		response.getWriter().println("success");
	}
	
	/**
	 * 
	 * <p>方法名称：aliPayInquire</p>
	 * <p>方法描述：支付宝支付结果查询接口（向alipay服务端发起订单查询）</p>
	 * @param request
	 * @param response
	 * @author 常瑞
	 * @since  2017年9月28日
	 * <p> history 2017年9月28日 常瑞  创建   </p>
	 */
	@RequestMapping(value="/aliPayInquire", method= RequestMethod.GET)
	public Response aliPayInquire(PersonPayHistoty personPayHistoty, HttpServletRequest request, HttpServletResponse response){
		PersonPayHistoty pphz = personuserServiceImpl.selectPayHistotyByOrderNumber(personPayHistoty);
		if(pphz==null){
			return new Response(ResponseStatus.Error,"无效的订单号",false);
		}
		if(pphz.getStatus()==1){
			return new Response(ResponseStatus.Success,"已支付",true);
		}else{
			String out_trade_no = personPayHistoty.getOrdernumber();
			AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
			AlipayTradeQueryRequest alipay_request = new AlipayTradeQueryRequest();
			 
			AlipayTradeQueryModel model=new AlipayTradeQueryModel();
		    model.setOutTradeNo(out_trade_no);
		    alipay_request.setBizModel(model);
		     
		    AlipayTradeQueryResponse alipay_response = null;
			try {
				alipay_response = client.execute(alipay_request);
			} catch (AlipayApiException e) {
				e.printStackTrace();
			}
			JSONObject myJsonObject = null;
			JSONObject myJsonObjectResponse = null;
			try {
				myJsonObject = new JSONObject(alipay_response.getBody());
				myJsonObjectResponse = new JSONObject(myJsonObject.getString("alipay_trade_query_response"));
				if("10000".equals(myJsonObjectResponse.getString("code"))){
					if(myJsonObjectResponse.getString("total_amount").equals(""+NumberFormat.getResult(pphz.getReceipt_amount()))){
						pphz.setStatus(1);
						pphz.setCosttype("2");
						pphz.setCostnumber(myJsonObjectResponse.getString("buyer_user_id"));
						pphz.setCostTime(DateFormatUtil.stringFormatDateTime(myJsonObjectResponse.getString("send_pay_date")));
						pphz.setPaynumber(myJsonObjectResponse.getString("trade_no"));
						personuserServiceImpl.updatePayHistoty(pphz);
						int yfc = 0;
						if(pphz.getViptype().equals("1mo")){
							yfc = 1;
						}else if(pphz.getViptype().equals("3mo")){
							yfc = 3;
						}else if(pphz.getViptype().equals("6mo")){
							yfc = 6;
						}else if(pphz.getViptype().equals("12mo")){
							yfc = 12;
						}
						if(yfc==0){
							return new Response(ResponseStatus.Error,"会员时长异常，请联系管理员",false);
						}
						Date dqsj = null;
						PersonUser personUser = personuserServiceImpl.selectByPrimaryKey(pphz.getUserid());
						if(personUser.getVipexpiredtime()==null||personUser.getVipexpiredtime().getTime()<new Date().getTime()){
							PersonUser personUserxg = new PersonUser();
							personUserxg.setId(pphz.getUserid());
							personUserxg.setVipexpiredtime(datautil(yfc));
							dqsj = personUserxg.getVipexpiredtime();
							personuserServiceImpl.updatePersonUser(personUserxg);
						}else{
							PersonUser personUserxg = new PersonUser();
							personUserxg.setId(pphz.getUserid());
							personUserxg.setVipexpiredtime(datautil1(yfc,personUser.getVipexpiredtime()));
							dqsj = personUserxg.getVipexpiredtime();
							personuserServiceImpl.updatePersonUser(personUserxg);
						}
						PersonPower personPower = new PersonPower();
						personPower.setId(UuidUtil.getUUID());
						personPower.setUserid(pphz.getUserid());
						personPower.setPersonid(null);
						personPower.setPaytypeid(pphz.getViptype());
						personPower.setPayname("支付宝");
						personPower.setPaymoney(pphz.getReceipt_amount());
						personPower.setExpirytime(dqsj);
						personuserServiceImpl.insertPersonPower(personPower);
						return new Response(ResponseStatus.Success,"已支付",true);
					}else{
						return new Response(ResponseStatus.Error,"到账金额异常，请联系管理员",false);
					}
				}else{
					return new Response(ResponseStatus.Error,"支付宝校验异常",false);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		    System.out.println(alipay_response.getBody());
		    return new Response(ResponseStatus.Error,"系统异常",false);
		}
	}
	
	/**
	 * 
	 * <p>方法名称：aliPaySynNotice</p>
	 * <p>方法描述：支付宝支付同步通知接口（支付成功后自动跳转）</p>
	 * @param request
	 * @param response
	 * @author 常瑞
	 * @throws UnsupportedEncodingException 
	 * @throws AlipayApiException 
	 * @since  2017年9月29日
	 * <p> history 2017年9月29日 常瑞  创建   </p>
	 */
	@RequestMapping(value="/aliPaySynNotice", method= RequestMethod.GET)
	public void aliPaySynNotice(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, AlipayApiException{
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		String out_trade_nofx = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");
		if(verify_result){
			PersonPayHistoty personPayHistoty = new PersonPayHistoty();
			personPayHistoty.setOrdernumber(out_trade_nofx);
			PersonPayHistoty pphz = personuserServiceImpl.selectPayHistotyByOrderNumber(personPayHistoty);
			if(pphz==null){
//				response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
				// TODO
//				response.setHeader("Location", "错误页面URL（无效的订单号）");
			}
			if(pphz.getStatus()==1){
//				response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
				// TODO
//				response.setHeader("Location", "支付成功页面");
			}else{
				String out_trade_no = personPayHistoty.getOrdernumber();
				AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
				AlipayTradeQueryRequest alipay_request = new AlipayTradeQueryRequest();
				 
				AlipayTradeQueryModel model=new AlipayTradeQueryModel();
			    model.setOutTradeNo(out_trade_no);
			    alipay_request.setBizModel(model);
			     
			    AlipayTradeQueryResponse alipay_response = null;
				try {
					alipay_response = client.execute(alipay_request);
				} catch (AlipayApiException e) {
					e.printStackTrace();
				}
				JSONObject myJsonObject = null;
				JSONObject myJsonObjectResponse = null;
				try {
					myJsonObject = new JSONObject(alipay_response.getBody());
					myJsonObjectResponse = new JSONObject(myJsonObject.getString("alipay_trade_query_response"));
					if("10000".equals(myJsonObjectResponse.getString("code"))){
						if(myJsonObjectResponse.getString("total_amount").equals(""+NumberFormat.getResult(pphz.getReceipt_amount()))){
							pphz.setStatus(1);
							pphz.setCosttype("2");
							pphz.setCostnumber(myJsonObjectResponse.getString("buyer_user_id"));
							pphz.setCostTime(DateFormatUtil.stringFormatDateTime(myJsonObjectResponse.getString("send_pay_date")));
							pphz.setPaynumber(myJsonObjectResponse.getString("trade_no"));
							personuserServiceImpl.updatePayHistoty(pphz);
							int yfc = 0;
							if(pphz.getViptype().equals("1mo")){
								yfc = 1;
							}else if(pphz.getViptype().equals("3mo")){
								yfc = 3;
							}else if(pphz.getViptype().equals("6mo")){
								yfc = 6;
							}else if(pphz.getViptype().equals("12mo")){
								yfc = 12;
							}
							if(yfc==0){
//								response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
								// TODO
//								response.setHeader("Location", "错误页面URL（会员时长异常）");
							}
							Date dqsj = null;
							PersonUser personUser = personuserServiceImpl.selectByPrimaryKey(pphz.getUserid());
							if(personUser.getVipexpiredtime()==null||personUser.getVipexpiredtime().getTime()<new Date().getTime()){
								PersonUser personUserxg = new PersonUser();
								personUserxg.setId(pphz.getUserid());
								personUserxg.setVipexpiredtime(datautil(yfc));
								dqsj = personUserxg.getVipexpiredtime();
								personuserServiceImpl.updatePersonUser(personUserxg);
							}else{
								PersonUser personUserxg = new PersonUser();
								personUserxg.setId(pphz.getUserid());
								personUserxg.setVipexpiredtime(datautil1(yfc,personUser.getVipexpiredtime()));
								dqsj = personUserxg.getVipexpiredtime();
								personuserServiceImpl.updatePersonUser(personUserxg);
							}
							PersonPower personPower = new PersonPower();
							personPower.setId(UuidUtil.getUUID());
							personPower.setUserid(pphz.getUserid());
							personPower.setPersonid(null);
							personPower.setPaytypeid(pphz.getViptype());
							personPower.setPayname("支付宝");
							personPower.setPaymoney(pphz.getReceipt_amount());
							personPower.setExpirytime(dqsj);
							personuserServiceImpl.insertPersonPower(personPower);
//							response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
							// TODO
//							response.setHeader("Location", "支付成功页面");
						}else{
//							response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
							// TODO
//							response.setHeader("Location", "错误页面URL（到账金额异常）");
						}
					}else{
//						response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
						// TODO
//						response.setHeader("Location", "错误页面URL（支付宝校验异常）");
					}
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			    System.out.println(alipay_response.getBody());
//			    response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
				// TODO
//				response.setHeader("Location", "错误页面URL（系统异常）");
			}
		}else{
			
		}
	}
}
