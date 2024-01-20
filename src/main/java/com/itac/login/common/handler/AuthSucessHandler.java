package com.itac.login.common.handler;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.itac.login.entity.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class AuthSucessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private final MemberRepository memberRepository;
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        
        memberRepository.updateMemberLastLogin(authentication.getName(), LocalDateTime.now());
        System.out.println("로그인성공 정보갱신");

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
