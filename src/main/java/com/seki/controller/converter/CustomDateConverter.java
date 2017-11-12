package com.seki.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class CustomDateConverter implements Converter<String, Date> {
	
	 public Date convert(String s) {
	        //ʵ�� �����ڴ�ת����������(��ʽ��yyyy-MM-dd)
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	        try {
	            //ת��ֱ�ӷ���
				return simpleDateFormat.parse(s);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }

	        //���������ʧ�ܷ���null
	        return null;
	    }
	
}
