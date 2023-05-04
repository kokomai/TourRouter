package com.tourrouter.jpa.specification.member;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.tourrouter.jpa.entity.member.AuthType;
import com.tourrouter.jpa.entity.member.Member;

public class MemberSpecification {
	public static Specification<Member> equalMemberId(String memberId) {
		return (root, query, criterialBuilder) -> criterialBuilder.equal(root.get("memberId"), memberId);
	}
	
	public static Specification<Member> equalPassword(String password) {
		return (root, query, criterialBuilder) -> criterialBuilder.equal(root.get("password"), password);
	}
	
	public static Specification<Member> equalName(String name) {
		return (root, query, criterialBuilder) -> criterialBuilder.equal(root.get("name"), name);
	}
	
	public static Specification<Member> equalAuth(List<AuthType> auth) {
		return (root, query, criterialBuilder) -> root.get("auth").in(auth);
	}
	
	public static Specification<Member> lessThanOrEqualToCreatedDate(Date createdDate) {
		return (root, query, criterialBuilder) -> criterialBuilder.lessThanOrEqualTo(root.get("createdDate"), createdDate);
	}
	
	public static Specification<Member> greaterThanOrEqualToCreatedDate(Date createdDate) {
		return (root, query, criterialBuilder) -> criterialBuilder.greaterThanOrEqualTo(root.get("createdDate"), createdDate);
	}
}
