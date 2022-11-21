package com.ssafy.home.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssafy.home.member.service.JwtService;
import com.ssafy.home.member.service.MemberService;

@Component
@Aspect
public class AdminAspect {
	Logger logger = LoggerFactory.getLogger(AdminAspect.class);
	
	@Autowired
	JwtService jwtService;
	
	@Before(value = "execution(* com.ssafy.home.notice.NoticeController.writeNotice(..)) ||"
			+ "		 execution(* com.ssafy.home.notice.NoticeController.modifyNotice(..))|| "
			+ "		 execution(* com.ssafy.home.notice.NoticeController.deleteNotice(..))")
	public void isAdmin(JoinPoint jp) throws Exception {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request =requestAttributes.getRequest();
        if(!jwtService.checkToken(request.getHeader("access-token"))) {
        	throw new Exception();
        }
	}
}
