package global.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import domain.member.dto.MemberInfo;

public class LoginCheckInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		
		if(loginInfo != null) {
			return true;
		}
		
		response.sendRedirect(request.getContextPath()+"/view/login");
		
		return false;
	}

	
}
