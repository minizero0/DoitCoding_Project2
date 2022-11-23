package com.sist.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookByCustidVO {
	private String custid;
	private String ticket_name;
	private String img_fname;
	private int ticketid;
	private int seatid;
	private int bookid;
	private String ticket_date;
	private String loc;
	private int seatnum;
	private String seatname;
}
