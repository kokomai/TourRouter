package com.tourrouter.biz.member.dto;

import java.sql.Date;

import com.tourrouter.jpa.entity.member.AuthType;

import lombok.Data;

@Data
public class MemberDTO {
	private int id;
	private String name;
	private String memberId;
	private String password;
	private AuthType auth;
	private Date createdDate;
}
