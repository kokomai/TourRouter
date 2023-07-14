package com.tourrouter.jpa.entity.member;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.tourrouter.jpa.converter.PasswordConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name="member")
@Getter
@Builder
public class Member {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		@Column(name="name")
		private String name;
		
		@Column(name="memberId")
		private String memberId;
		
		@Convert(converter = PasswordConverter.class)
		private String password;
		
		@Enumerated(EnumType.STRING)
		private AuthType auth;
		
		@CreationTimestamp
		@Column(name="createdDate", updatable = false)
		@Temporal(TemporalType.TIMESTAMP)
		private Date createdDate;
}
