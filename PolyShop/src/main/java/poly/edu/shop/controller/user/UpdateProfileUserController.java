package poly.edu.shop.controller.user;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import poly.edu.shop.domain.Account;
import poly.edu.shop.model.AccountDTO;
import poly.edu.shop.service.AccountService;
import poly.edu.shop.service.SessionService;

@Controller
public class UpdateProfileUserController {
	
	@Autowired
	AccountService accountService;

	@Autowired
	SessionService sessionService;
	
	@GetMapping("profile")
	public String profile(Model model) {
		Account user = (Account) sessionService.getAttribute("username");
		if (user == null) {
			return "redirect:/login";
		}
	
		AccountDTO dto = new AccountDTO();
		BeanUtils.copyProperties(user, dto);
		
		
		model.addAttribute("profile", dto);
		
		return "site/accounts/updateProfile";
		
	}
	
	@PostMapping("profile")
	public ModelAndView update(ModelMap model,
			@Valid @ModelAttribute("profile") AccountDTO dto, 
			BindingResult result) throws IOException {
		
		if (result.hasErrors()) {
			return new ModelAndView("site/accounts/updateProfile", model);
		}
		
		System.out.println("Post: " +dto.isRole());
		Account entity = new Account();
		BeanUtils.copyProperties(dto, entity);
		
		accountService.save(entity);
		
		model.addAttribute("message", "Thông tin đã được cập nhật");
		
		return new ModelAndView("site/accounts/updateProfile", model);
	}
}
