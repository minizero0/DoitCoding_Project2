package com.sist.vo;

import lombok.Data;

@Data
public class ReviewVO {
	private int reviewid;
	private String custid;
	private int ticketid;
	private double score;
	private String review_content;
}
