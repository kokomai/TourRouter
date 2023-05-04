package com.tourrouter.biz.member.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tourrouter.biz.member.dto.MemberDTO;
import com.tourrouter.biz.member.service.MemberService;
import com.tourrouter.jpa.entity.member.AuthType;
import com.tourrouter.jpa.entity.member.Member;

@RestController
public class MemberController {
	
	@Autowired
	MemberService memberService = new MemberService();
	
	@GetMapping("/getAllMember")
	public List<Member> getAllMember() {
		return memberService.getAllMember();
	}
	
	@GetMapping("/findMember")
	public List<Member> findMember(
				@RequestParam(required = false) String memberId, 
				@RequestParam(required = false) String password,
				@RequestParam(required = false) List<AuthType> auth,
				@RequestParam(required = false) String name, 
				@RequestParam(required = false) Date startDate,
				@RequestParam(required = false) Date endDate
			) {
		return memberService.findMember(
				memberId, 
				password,
				auth,
				name, 
				startDate,
				endDate
				);
	}
	
	@PostMapping("/createMember")
	public Member createMember(@RequestBody MemberDTO params) {
		return memberService.inserMember(params);
	}
	
	@PutMapping("/updateMember")
	public int updateMember(@RequestBody MemberDTO params) {
		return 0;
	}
	
	@DeleteMapping("/deleteMember")
	public int deleteMember(@RequestBody MemberDTO params) {
		return 0;
	}
}
