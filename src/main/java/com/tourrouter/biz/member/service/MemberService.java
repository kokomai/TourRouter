package com.tourrouter.biz.member.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tourrouter.biz.member.dto.MemberDTO;
import com.tourrouter.jpa.entity.member.AuthType;
import com.tourrouter.jpa.entity.member.Member;
import com.tourrouter.jpa.repository.member.MemberRepository;
import com.tourrouter.jpa.specification.member.MemberSpecification;

@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepository;
	
	public List<Member> getAllMember() {
		return memberRepository.findAll();
	}
	
	public List<Member> findMember(
			String memberId, 
			String password,
			List<AuthType> auth,
			String name, 
			Date startDate,
			Date endDate
	) {
		Specification<Member> spec = (root, query, criterialBuilder) -> null;
		
		if(StringUtils.hasText(memberId)) {
			spec = spec.and(MemberSpecification.equalMemberId(memberId));
		}
		
		if(StringUtils.hasText(password)) {
			spec = spec.and(MemberSpecification.equalPassword(password));
		}
		
		if(StringUtils.hasText(name)) {
			spec = spec.and(MemberSpecification.equalName(name));
		}
		
		if(auth != null && !auth.isEmpty()) {
			spec = spec.and(MemberSpecification.equalAuth(auth));
		}
		
		if(startDate != null) {
			spec = spec.and(MemberSpecification.equalAuth(auth));
		}
		
		return memberRepository.findAll(spec);
	}
	
	public Member inserMember(MemberDTO memberDto) {
		Member member = Member.builder()
						.auth(memberDto.getAuth())
						.memberId(memberDto.getMemberId())
						.password(memberDto.getPassword())
						.name(memberDto.getName()).build();
		
		return memberRepository.save(member);
	}
	
}
