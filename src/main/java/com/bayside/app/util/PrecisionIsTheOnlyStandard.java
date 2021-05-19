package com.bayside.app.util;

import java.math.BigDecimal;

public class PrecisionIsTheOnlyStandard {
	public static double doubleCalculation(double val1,  String symbol, double val2){
		
		BigDecimal bigDecimal1 = new BigDecimal(Double.toString(val1));
		BigDecimal bigDecimal2 = new BigDecimal(Double.toString(val2));
		
		if("+".equals(symbol.trim())||"＋".equals(symbol.trim())||"加".equals(symbol.trim())||"加上".equals(symbol.trim())){
			return bigDecimal1.add(bigDecimal2).doubleValue();
		}
		
		if("-".equals(symbol.trim())||"－".equals(symbol.trim())||"减".equals(symbol.trim())||"减去".equals(symbol.trim())){
			return bigDecimal1.subtract(bigDecimal2).doubleValue();
		}
		
		if("*".equals(symbol.trim())||"×".equals(symbol.trim())||"乘".equals(symbol.trim())||"乘以".equals(symbol.trim())){
			return bigDecimal1.multiply(bigDecimal2).doubleValue();
		}
		
		if("/".equals(symbol.trim())||"÷".equals(symbol.trim())||"除".equals(symbol.trim())||"除以".equals(symbol.trim())){
			return div(val1,val2,2);
		}
		
		if("[".equals(symbol.trim())||"]".equals(symbol.trim())||"[]".equals(symbol.trim())||"四舍五入".equals(symbol.trim())){
			return round(val1, 2);
		}
		return 0.0;
	}
	
	public static double div(double v1,double v2,int scale){
		if(scale<0){  
			throw new IllegalArgumentException("请填写正整数或零");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static double round(double v,int scale){
		if(scale<0){
			throw new IllegalArgumentException("请填写正整数或零");   
		}   
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
