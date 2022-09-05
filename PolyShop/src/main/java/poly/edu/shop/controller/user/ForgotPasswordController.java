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
import poly.edu.shop.model.ForgotDTO;
import poly.edu.shop.service.AccountService;
import poly.edu.shop.service.MailerService;

@Controller
public class ForgotPasswordController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private MailerService mailerService;
	
	@GetMapping("forgot")
	public String forgot(ModelMap model) {
		model.addAttribute("account", new ForgotDTO());
		return "site/accounts/forgot";
	}
	
	@PostMapping("forgot")
	public ModelAndView forgot(ModelMap model,
			@Valid @ModelAttribute("account") ForgotDTO dto, 
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("site/accounts/forgot", model);
		}
		
		Account account = accountService.forgot(dto.getUsername(), dto.getEmail());
		
		if (account == null) {
			model.addAttribute("error", "Tên đăng nhập hoặc email không đúng!");
			return new ModelAndView("site/accounts/forgot", model);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("Dear ").append(account.getUsername()).append("<br>");
		sb.append("Bạn đã sử dụng chức năng quên mật khẩu của PolyShop. <br>");
		sb.append("Mật khẩu của bạn là: <b>").append(account.getPassword()).append("</b>").append("<br><br>");
		sb.append("Best Regards <br>");
		sb.append("Adminstrator");
		
		mailerService.queue("mhuy13660@gmail.com", 
				dto.getEmail(), 
				"Chức năng quên mật khẩu PolyShop", 
				sb.toString());
		model.addAttribute("message", "Lấy mật khẩu thành công. Vui lòng kiểm tra email của bạn để nhận mật khẩu");
	
		return new ModelAndView("site/accounts/forgot", model);
	}
	
	
}
