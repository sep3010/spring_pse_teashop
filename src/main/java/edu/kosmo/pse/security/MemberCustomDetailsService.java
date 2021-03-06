package edu.kosmo.pse.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.kosmo.pse.mapper.MemberMapper;
import edu.kosmo.pse.vo.MemberCustomDetails;
import edu.kosmo.pse.vo.MemberVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class MemberCustomDetailsService implements UserDetailsService  {
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		log.warn("Load Member By MemverVO user_id: " + userId);
		MemberVO memberVO = memberMapper.getMember(userId);
		
		log.warn("Queried by MemberVO mapper: " + memberVO);
		
		return memberVO == null ? null : new MemberCustomDetails(memberVO);
	}
	
	
}
