package com.gtmc.carapp.service.workorder.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class MyDateUtil {
	
	private static final long FORMAT_DAY = 24 * 60 * 60 * 1000;
	
	private static final long FORMAT_HOUR = 60 * 60 * 1000;

	private static final long FORMAT_MIN = 60 * 1000;
	
	
    public static String getDistanceTimeFormat(Date startTime, Date endTime) {
        String showTags = "";
        if(startTime != null && endTime != null) {
	        long time1 = startTime.getTime();
	        long time2 = endTime.getTime();
	
	        long diff;
	        if (time1 < time2) {
	            diff = time2 - time1;
	        } else {
	            diff = time1 - time2;
	        }
	        if((diff / FORMAT_MIN) < 1) {//不到1分钟
	        	showTags = "不到1分钟";
	        }else if((diff / FORMAT_DAY) < 1) {
	        	int hourTags = (int) (diff / FORMAT_HOUR) ;
	        	int minTags = 0 ;
	        	if(hourTags < 1) {
	        		minTags = (int) (diff / FORMAT_MIN) ;
	        		showTags = minTags + "分钟";
	        	}else {
	        		minTags = (int) ((diff % FORMAT_HOUR) / FORMAT_MIN) ;
	        		showTags = hourTags + "小时" + minTags + "分钟";
	        	}
	        }else{
	        	int dayTags = (int) (diff / FORMAT_DAY) ;
	        	int hourTags = (int) ((diff % FORMAT_DAY) / FORMAT_HOUR) ;
	    		showTags = dayTags + "天" + hourTags + "小时";
	        }
        }
        return showTags;
    }

    public static String formatDate(Date time, String pattern) {
        String showDate = "";
        if(time != null) {
        	if(StringUtils.isBlank(pattern)) {
        		pattern = "yyyy-MM-dd HH:mm:ss";
        	}
        	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        	showDate = sdf.format(time);
        }
        return showDate;
    }
    
    public static Date parseDate(String time, String pattern) throws ParseException {
    	Date showDate = null;
    	if(StringUtils.isNotBlank(time)) {
	    	if(StringUtils.isBlank(pattern)) {
	    		pattern = "yyyy-MM-dd HH:mm:ss";
	    	}
	    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	    	showDate = sdf.parse(time);
    	}
    	return showDate;
    }

	/**
	 * 验证时间字符串格式输入是否正确
	 * @param timeStr
	 * @return
	 */
	public static boolean valiDateTimeWithLongFormat(String timeStr) {
		String format = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) "
				+ "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
		Pattern pattern = Pattern.compile(format);
		Matcher matcher = pattern.matcher(timeStr);
		if (matcher.matches()) {
			pattern = Pattern.compile("(\\d{4})-(\\d+)-(\\d+).*");
			matcher = pattern.matcher(timeStr);
			if (matcher.matches()) {
				int y = Integer.valueOf(matcher.group(1));
				int m = Integer.valueOf(matcher.group(2));
				int d = Integer.valueOf(matcher.group(3));
				if (d > 28) {
					Calendar c = Calendar.getInstance();
					c.set(y, m-1, 1);
					int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
					return (lastDay >= d);
				}
			}
			return true;
		}
		return false;
	}

	public static void main(String[] args){
		System.out.println(MyDateUtil.valiDateTimeWithLongFormat("2016-5-2 08:02:02"));//true
		System.out.println(MyDateUtil.valiDateTimeWithLongFormat("2016-02-29 08:02:02"));//true
		System.out.println(MyDateUtil.valiDateTimeWithLongFormat("2015-02-29 08:02:02"));//false
		System.out.println(MyDateUtil.valiDateTimeWithLongFormat("2016-02-02 082:02"));//false
	}

}
