package com.tourrouter.jpa.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tourrouter.jpa.entity.member.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{
	
}
