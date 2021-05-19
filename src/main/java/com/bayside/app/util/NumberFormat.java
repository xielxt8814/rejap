package com.bayside.app.util;

public class NumberFormat {
	public static String getResult(double d){
		java.text.NumberFormat nf = java.text.NumberFormat.getInstance();   
		nf.setGroupingUsed(false);
		nf.setMaximumFractionDigits(2);
		String rtn = "";
		int xsws = nf.format(d).length()-nf.format(d).indexOf(".")-1;
		if(xsws == nf.format(d).length()){
			rtn = nf.format(d)+".00";
		}else if(xsws==1){
			rtn = nf.format(d)+"0";
		}else{
			rtn = nf.format(d);
		}
		return rtn;
	}
}
