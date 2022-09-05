package poly.edu.shop.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.edu.shop.domain.Account;
import poly.edu.shop.model.AccountDTO;
import poly.edu.shop.service.AccountService;

@Controller
@RequestMapping("admin/account")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	
	@GetMapping("add")
	public String add(Model model) {
		AccountDTO dto = new AccountDTO();
		dto.setCheckEdit(false);
		
		model.addAttribute("account", dto);
		
		return "admin/accounts/addOrEdit";
	}
	
	@GetMapping("edit/{username}")
	public ModelAndView edit(@PathVariable("username") String username,
		ModelMap model) {
		Optional<Account> opt = accountService.findById(username);
		AccountDTO dto = new AccountDTO();
		
		if (opt.isPresent()) {
			Account entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setCheckEdit(true);
			
			model.addAttribute("account", dto);
			return new ModelAndView("admin/accounts/addOrEdit", model);
		}
		
		model.addAttribute("error", "Người dùng không tồn tại");
		return new ModelAndView("forward:/admin/account", model); 
	}
	
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,
			@Valid @ModelAttribute("account") AccountDTO dto, 
			BindingResult result) throws IOException {
		if (result.hasErrors()) {
			return new ModelAndView("admin/accounts/addOrEdit", model);
		}
		
		Account entity = new Account();
		BeanUtils.copyProperties(dto, entity);
		
		accountService.save(entity);
		
		model.addAttribute("message", "Lưu thành công");
		
		return new ModelAndView("redirect:/admin/account", model);
	}
	
	@GetMapping("delete/{username}")
	public ModelAndView delete(@PathVariable("username") String username, 
			ModelMap model) {
		
		Optional<Account> opt = accountService.findById(username);
		if (opt.isPresent()) {
			accountService.delete(opt.get());
			model.addAttribute("message", "Xóa thành công");
		}else {
			model.addAttribute("error", "Người dùng không tồn tại. Xóa thất bại!");
		}
		
		return new ModelAndView("forward:/admin/account", model); 
	}
	
	
	@GetMapping("")
	public String search(ModelMap model,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		int currentPage = page.orElse(1);
		Integer pageSize = size.orElse(5);
		
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("username"));
		Page<Account> resultPage = null;
		
		
		if (StringUtils.hasText(name)) {
			resultPage = accountService.findByNameContaining(name, pageable);
			model.addAttribute("name", name);
		}else {
			resultPage = accountService.findAll(pageable);
		}
		
		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages); 
			
			if (totalPages > 5) {
				if (end == totalPages){
					start = end - 5;
				}else if (start == 1) {
					end = start + 5;
				}
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
					.boxed()
					.collect(Collectors.toList());
			
			model.addAttribute("pageNumbers", pageNumbers);
		}
		
		model.addAttribute("accountPage", resultPage);
		
		return "admin/accounts/list";
	}
	
	
}
