package com.bayside.app.util;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


/**
 * 发送验证码
 * @author netease
 * lyy
 * 3355315CD86A2BC5B0A6F2114DC4
 */
public class SendCode {
	private static final Logger log = Logger.getLogger(SendCode.class);
	static String requestUrl="http://api.febook.cn/SmsService/Template";
	static String sendUrl="http://api.febook.cn/SmsService/Send";
	public static void main(String[] args) {
		System.out.println("Hello World!");
		try {
			  List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			   formparams.add(new BasicNameValuePair("Account","lyy"));
			   formparams.add(new BasicNameValuePair("Pwd","3355315CD86A2BC5B0A6F2114DC4"));
			   formparams.add(new BasicNameValuePair("Content","ssql"+"||"+"3"+"||"+"huolandata.com/a.html?id=1234"));
			   formparams.add(new BasicNameValuePair("Mobile","15969714581"));
			   formparams.add(new BasicNameValuePair("TemplateId","30360"));
			   formparams.add(new BasicNameValuePair("SignId","30273"));
			   Post(formparams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
	}
			public static void Post( List<NameValuePair> formparams) throws Exception {
			   CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
		        
			   httpClient.start();
			 
			   HttpPost requestPost=new HttpPost(requestUrl);
			 
		       requestPost.setEntity(new UrlEncodedFormEntity(formparams,"utf-8"));

			   httpClient.execute(requestPost, new FutureCallback<HttpResponse>() {
				
				public void failed(Exception arg0) {
					
					 System.out.println("Exception: " + arg0.getMessage());
					 
				}
				public void completed(HttpResponse arg0) {
					  System.out.println("Response: " + arg0.getStatusLine());
					
				try {
					
					InputStream stram= arg0.getEntity().getContent();
					BufferedReader reader = new BufferedReader(new InputStreamReader(stram));
					System.out.println(	reader.readLine());
				} catch (UnsupportedOperationException e) {
					e.printStackTrace();
					log.error(e.getMessage(), e);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
							
				}
				public void cancelled() {
					// TODO Auto-generated method stub
					
				}
			}).get();
			   httpClient.close();
			 }
}

