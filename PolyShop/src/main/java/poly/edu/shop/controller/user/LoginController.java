package poly.edu.shop.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import poly.edu.shop.domain.Account;
import poly.edu.shop.model.LoginDTO;
import poly.edu.shop.service.AccountService;
import poly.edu.shop.service.SessionService;

@Controller
public class LoginController {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private SessionService session;
	
	@GetMapping("login")
	public String login(ModelMap model) {
		model.addAttribute("account", new LoginDTO());
		return "site/accounts/login";
	}
	
	@PostMapping("login")
	public ModelAndView login(ModelMap model, 
			@Valid @ModelAttribute("account") LoginDTO dto,
			BindingResult result) {
		
		if (result.hasErrors()) {
			return new ModelAndView("site/accounts/login", model);
		}
		
		Account account = accountService.login(dto.getUsername(), dto.getPassword());
		
		if (account == null) {
			model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
			return new ModelAndView("site/accounts/login", model);
		}
		
		session.setAttribute("username", account);
		session.setAttribute("nameLogin", account.getUsername());
		session.setAttribute("checkLogin", true);
		
		String ruri = (String) session.getAttribute("redirect-uri");
		System.out.println(ruri+"-------------------//////////--------------");
		
		if (ruri != null) {
			session.removeAttribute("redirect-uri");
			session.removeAttribute("errorLogin");
			if (account.isRole()) {
				return new ModelAndView("redirect:/admin/category");
			}
			return new ModelAndView("redirect:" +ruri.substring(9));
		}
		if (ruri == null) {
			session.removeAttribute("errorLogin");
			if (account.isRole()) {
				return new ModelAndView("redirect:/admin/category");
			}else {
				return new ModelAndView("redirect:/home");
			}
		}
		
		return new ModelAndView();
	}
}
