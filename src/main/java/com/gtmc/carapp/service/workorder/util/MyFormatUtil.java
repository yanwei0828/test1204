package com.gtmc.carapp.service.workorder.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;

import tk.mybatis.mapper.util.StringUtil;

public class MyFormatUtil {
	
    public static Double roundingFormat(Double d) {
		d = (double) Math.round(d * 100) / 100;
		return d;
	}
    
    public static String formatPrice(Double price) {
    	String priceStr = "0.00";
    	if (price > 0) {
    		priceStr = new DecimalFormat("###0.00").format(new Double(price));
    	}
    	return priceStr;
    }

    public static String nullToSpace(String s){
		return StringUtil.isEmpty(s)?"":s;
	}
	
	public static String parseDouble(String num){
		double f = 0D;
		if(StringUtils.isNotBlank(num)) {
			f = Double.parseDouble(num);
		}
		BigDecimal b = new BigDecimal(f);  
		BigDecimal f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP);  
		return f1.toString();
	}
	
	
	
}
