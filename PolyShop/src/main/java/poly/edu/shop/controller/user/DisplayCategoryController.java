package poly.edu.shop.controller.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.shop.domain.Product;
import poly.edu.shop.service.ProductService;
import poly.edu.shop.service.SessionService;


@Controller
public class DisplayCategoryController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("laptop")
	public String laptop(ModelMap model,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		sessionService.setAttribute("checkCategory", request.getRequestURI());
		
		int currentPage = page.orElse(1);
		Integer pageSize = size.orElse(8);
		
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by(Direction.DESC, "discount"));
		Page<Product> resultPage = productService.findLaptop(pageable);
		
		long totalProducts = productService.findLaptop(pageable).getTotalElements();
		
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
		
		model.addAttribute("totalProducts", totalProducts);
		model.addAttribute("laptopPage", resultPage);
		
		return "site/products/laptop";
	}
	
	@GetMapping("mobile")
	public String mobile(ModelMap model,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		sessionService.setAttribute("checkCategory", request.getRequestURI());
		
		int currentPage = page.orElse(1);
		Integer pageSize = size.orElse(8);
		
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by(Direction.DESC, "discount"));
		Page<Product> resultPage = productService.findMobile(pageable);
		
		long totalProducts = productService.findMobile(pageable).getTotalElements();
		
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
		
		model.addAttribute("totalProducts", totalProducts);
		model.addAttribute("mobilePage", resultPage);
		
		return "site/products/mobile";
	}
	
	@GetMapping("search")
	public String searchByName(Model model, 
			@RequestParam("keyword") String key) {
		List<Product> list = productService.findByKeyWords("%"+key+"%");
		if (list.isEmpty()) {
			model.addAttribute("errorSearch", key);
			return "site/products/findByName";
		}
		model.addAttribute("successSearch", key);
		model.addAttribute("totalResult", list.size());
		model.addAttribute("listSearch", list);
		return "site/products/findByName";
	}
	
	@GetMapping("brand/{name}/{category}")
	public String brand(Model model, 
			@PathVariable("name") String productName,
			@PathVariable("category") Long categoryId) {
		List<Product> list = productService.findByNameAndCategory("%"+productName+"%", categoryId);
		String uri = (String) sessionService.getAttribute("checkCategory");
		
		if (uri.contains("laptop")) {
			model.addAttribute("categoryName", "Laptop");
		}else if (uri.contains("mobile")) {
			model.addAttribute("categoryName", "Điện thoại");
		}
		model.addAttribute("productName", productName);
		model.addAttribute("totalProducts", list.size());
		model.addAttribute("listBrand", list);
		
		return "site/products/brand";
	}
	
}
