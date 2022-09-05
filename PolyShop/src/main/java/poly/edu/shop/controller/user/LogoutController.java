package poly.edu.shop.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.edu.shop.service.SessionService;

@Controller
public class LogoutController {
	
	@Autowired
	SessionService sessionService;
	
	@RequestMapping("logout")
	public String logout() {
		sessionService.removeAttribute("username");
		sessionService.removeAttribute("nameLogin");
		sessionService.removeAttribute("checkLogin");
		sessionService.removeAttribute("cart");
		return "redirect:/login";
	}
}
