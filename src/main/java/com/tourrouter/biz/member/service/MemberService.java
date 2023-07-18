package com.tourrouter.biz.member.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tourrouter.biz.member.dto.MemberDTO;
import com.tourrouter.jpa.entity.member.AuthType;
import com.tourrouter.jpa.entity.member.Member;
import com.tourrouter.jpa.exception.MemberNotFoundException;
import com.tourrouter.jpa.repository.member.MemberRepository;
import com.tourrouter.jpa.specification.member.MemberSpecification;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService {
	@Autowired
	MemberRepository memberRepository;
	
	public List<Member> getAllMember() {
		return memberRepository.findAll();
	}
	
	public List<Member> findMembers(
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
			spec = spec.and(MemberSpecification.greaterThanOrEqualToCreatedDate(startDate));
		}
		
		if(endDate != null) {
			spec = spec.and(MemberSpecification.lessThanOrEqualToCreatedDate(endDate));
		}
		
		return memberRepository.findAll(spec);
	}
	
	public Member createMember(MemberDTO memberDto) {
		log.info("들어 오긴 왔다..");
		log.info("이름 : " + memberDto.getName());
		Member member = Member.builder()
						.auth(memberDto.getAuth())
						.memberId(memberDto.getMemberId())
						.password(memberDto.getPassword())
						.name(memberDto.getName()).build();
		
		return memberRepository.save(member);
	}
	
	public ResponseEntity<HttpStatus> deleteMember(String memberId) {
		List<Member> members = findMembers(memberId, null, null, null, null, null);
		if(members.size() != 0) {
			Long targetId = members.get(0).getId();
			memberRepository.delete(memberRepository.findById(targetId).orElseThrow(()-> new MemberNotFoundException("멤버가 없다")));
			members = findMembers(memberId, null, null, null, null, null);
			
			if(members.size() == 0) {
				// 삭제 완료된 경우
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				// 아직 남아있다..?(그럴 리는 없겠지만)
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	

	
}
