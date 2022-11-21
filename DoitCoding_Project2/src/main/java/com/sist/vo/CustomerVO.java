package com.sist.vo;

import lombok.Data;

@Data
public class CustomerVO {
	private String custid;	
	private String pwd;
	private String name;
	private String birth;
	private String email;
	private String phone;
	private String gender;
	private int cateid;
}
