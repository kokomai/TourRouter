package com.tourrouter.biz.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourrouter.biz.member.controller.MemberController;
import com.tourrouter.biz.member.dto.MemberDTO;
import com.tourrouter.biz.member.service.MemberService;
import com.tourrouter.jpa.entity.member.AuthType;
import com.tourrouter.security.SecurityConfig;

@ComponentScan("src.main.java.com.tourrouter")
@WebMvcTest(MemberController.class)
@AutoConfigureRestDocs
@Import({MemberController.class, SecurityConfig.class})
public class MemberApiTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MemberService memberService;
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	ObjectMapper objectMapper;
	
	
	@Test
	@DisplayName("멤버 생성")
	public void createMember() throws Exception {
		MemberDTO dto = new MemberDTO();
		dto.setMemberId("test");
		dto.setName("test");
		dto.setPassword("1234");
		dto.setAuth(AuthType.USER);
		
		this.mockMvc
			.perform(
				MockMvcRequestBuilders
				.post("/member/createMember")
				.content(objectMapper.writeValueAsString(dto))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andDo(document("create-memeber", 
					PayloadDocumentation.requestFields(
							PayloadDocumentation.fieldWithPath("id").description("자동으로 증가하는 id값"),
							PayloadDocumentation.fieldWithPath("memberId").description("사용자 아이디"),
							PayloadDocumentation.fieldWithPath("name").description("사용자 이름"),
							PayloadDocumentation.fieldWithPath("password").description("사용자 비밀번호"),
							PayloadDocumentation.fieldWithPath("auth").description("사용자 권한"),
							PayloadDocumentation.fieldWithPath("createdDate").description("자동으로 설정되는 생성날짜")
					)
			));
					
	}
	
	@Test
	@DisplayName("전체 멤버 조회")
	public void getAllMember() throws Exception {
		this.mockMvc
		.perform(
				MockMvcRequestBuilders
				.get("/member/getAllMember")
				)
		.andDo(print())
		.andExpect(status().isOk());
//		.andDo(document("get-all-member", 
//				PayloadDocumentation.responseFields(
//					PayloadDocumentation.fieldWithPath("id").description("자동으로 증가하는 id값"),
//					PayloadDocumentation.fieldWithPath("memberId").description("사용자 아이디"),
//					PayloadDocumentation.fieldWithPath("name").description("사용자 이름"),
//					PayloadDocumentation.fieldWithPath("password").description("사용자 비밀번호"),
//					PayloadDocumentation.fieldWithPath("auth").description("사용자 권한"),
//					PayloadDocumentation.fieldWithPath("createdDate").description("자동으로 설정되는 생성날짜")
//				)
//		));
		
	}
}
