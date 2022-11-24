package com.sist.vo;

import lombok.Data;

@Data
public class MyReviewVO {
	private int reviewid;
	private String custid;
	private int ticketid;
	private int score;
	private String review_content;
	private String ticket_name;
	private String ticket_date;
}
