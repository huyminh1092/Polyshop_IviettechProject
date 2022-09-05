package poly.edu.shop.controller.user;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.edu.shop.domain.Category;
import poly.edu.shop.service.CategoryService;
import poly.edu.shop.service.ProductService;
import poly.edu.shop.service.SessionService;

@Controller
public class HomeController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	SessionService sessionService;
	
	@GetMapping("home")
	public String home(Model model) {
		
		model.addAttribute("listTrend", productService.findAll());
		
		model.addAttribute("topDiscount", productService.findTop8Discount());
		
		model.addAttribute("topMobile", productService.findTop8Mobile());
		
		model.addAttribute("topLaptop", productService.findTop8Laptop());
		
		model.addAttribute("topSmartWatch", productService.findTop4SmartWatch());

		sessionService.setAttributeList("listCategories", categoryService.findAll());
		
		return "site/components/home";
	}
}
