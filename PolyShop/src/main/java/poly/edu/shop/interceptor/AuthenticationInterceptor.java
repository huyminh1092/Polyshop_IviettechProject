package poly.edu.shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import poly.edu.shop.domain.Account;
import poly.edu.shop.service.SessionService;



@Component
public class AuthenticationInterceptor implements HandlerInterceptor{
	private boolean check;
	
	@Autowired
	SessionService sessionService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		System.out.println("pre handle of request: " +request.getRequestURI());
		if (sessionService.getAttribute("username") != null) {
			check = true;
		}
		
		String uri = request.getRequestURI();
		Account user = (Account) sessionService.getAttribute("username");
		
		String error = "";
		if (user == null) {
			error = "Vui lòng đăng nhập!";
		}
		else if (!user.isRole() && uri.contains("/admin/")) {
			error = "Vui lòng đăng nhập với vai trò Admin";
			System.out.println(user.isRole());
		}
		
		if (error.length() > 0) {
			sessionService.setAttribute("redirect-uri", uri);
			sessionService.setAttribute("errorLogin", error);
			response.sendRedirect("/polyshop/login");
			check = false;
		}
			
//		sessionService.setAttribute("redirect-uri", uri);
//		response.sendRedirect("/polyshop/login?error="+error);
//		
		return check;
	}
	
}
