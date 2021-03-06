package edu.kosmo.pse.controller;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.kosmo.pse.service.AdminService;
import edu.kosmo.pse.service.MemberService;
import edu.kosmo.pse.vo.MemberVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@AllArgsConstructor
@RestController
@RequestMapping("/user/*")
public class MemberController {

	@Inject
	private MemberService memberService;
	
	@GetMapping("/userHome") // 마이 페이지
	public ModelAndView userHome(ModelAndView view, MemberVO memberVO, Principal principal) {
		log.info("userHome..");
		String userId = principal.getName();
		view.addObject("member", memberService.getMember(userId));
		view.setViewName("user/userHome");
		return view;
	}


	
}
