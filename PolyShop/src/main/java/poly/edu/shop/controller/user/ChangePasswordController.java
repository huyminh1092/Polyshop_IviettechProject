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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.edu.shop.domain.Account;
import poly.edu.shop.model.ChangeDTO;
import poly.edu.shop.service.AccountService;
import poly.edu.shop.service.SessionService;

@Controller
public class ChangePasswordController {
	@Autowired
	AccountService accountService;
	
	@Autowired
	SessionService sessionService;
	
	@GetMapping("change")
	public String change1(ModelMap model) {
		Account account = (Account) sessionService.getAttribute("username");
		if (account == null) {
			return "redirect:/login";
		}
		ChangeDTO dto  = new ChangeDTO();
		dto.setUsername(account.getUsername());
		
		model.addAttribute("accountChange", dto);
		return "site/accounts/change";
	}
	
	@PostMapping("change")
	public String change(ModelMap model,
			@Valid @ModelAttribute("accountChange") ChangeDTO dto, 
			BindingResult result) {
		if (result.hasErrors()) {
			return "site/accounts/change";
		}
		Account account = accountService.login(dto.getUsername(), dto.getCurrentPassword());
		
		if (account == null) {
			model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
			return "site/accounts/change";
//			return new ModelAndView("site/accounts/change", model);
		}
		
		if (!dto.getConfirmPassword().equals(dto.getNewPassword())) {
			model.addAttribute("error", "Xác nhận mật khẩu không đúng!");
			return "site/accounts/change";
//			return new ModelAndView("site/accounts/change", model);
		}
		
		account.setPassword(dto.getNewPassword());
		accountService.save(account);
		System.out.println(account.getPassword());
		
		model.addAttribute("message", "Đổi mật khẩu thành công!");
		
//		return new ModelAndView("forward:/logout", model);
		return "site/accounts/change";
	}
}
