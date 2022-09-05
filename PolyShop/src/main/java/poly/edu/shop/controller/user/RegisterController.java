package poly.edu.shop.controller.user;

import java.io.IOException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.edu.shop.domain.Account;
import poly.edu.shop.model.AccountDTO;
import poly.edu.shop.service.AccountService;

@Controller
public class RegisterController {
	
	@Autowired
	AccountService accountService;
	
	@GetMapping("register")
	public String register(Model model) {
		
		AccountDTO dto = new AccountDTO();
		model.addAttribute("account", dto);
		
		return "site/accounts/register";
	}
	
	@PostMapping("register")
	public String registerPost(Model model, RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute("account") AccountDTO dto, 
			BindingResult result) throws IOException {
		try {
			
		
		if (result.hasErrors()) {
			return "site/accounts/register";
		}
		
		Optional<Account> opt = accountService.findById(dto.getUsername());
		if (opt.isPresent()) {
			model.addAttribute("errorUsername", "Tên đăng nhập đã tồn tại!");
			return "site/accounts/register";
		}
		
		Account entity = new Account();
		BeanUtils.copyProperties(dto, entity);
		System.out.println(entity.getName()+"-"+entity.getUsername()+"-"+entity.getEmail()+"---------------------------------------");
		accountService.save(entity);
		
		
		redirectAttributes.addFlashAttribute("message", "Đăng ký thành công. Vui lòng đăng nhập để tiếp tục!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/login";
	}
}
