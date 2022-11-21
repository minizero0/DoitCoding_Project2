package com.sist.dao;

import java.time.LocalDate;

public class ClassTest {
	 public static void main(String[] args) {
		 
	        // 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
	        LocalDate now = LocalDate.now();
	 
	        // 연도, 월(문자열, 숫자), 일, 일(year 기준), 요일(문자열, 숫자)
	        int year = now.getYear();
	        
	        System.out.println(now); // 2021-06-17
	        System.out.println(year); // 2021
	 }
}
