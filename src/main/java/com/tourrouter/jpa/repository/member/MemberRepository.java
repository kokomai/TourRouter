package com.tourrouter.jpa.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tourrouter.jpa.entity.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member>{
}
