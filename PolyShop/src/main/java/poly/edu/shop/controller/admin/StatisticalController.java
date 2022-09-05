package poly.edu.shop.controller.admin;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.shop.domain.Category;
import poly.edu.shop.domain.Product;
import poly.edu.shop.model.SellingDTO;
import poly.edu.shop.service.OrderDetailService;
import poly.edu.shop.service.OrderService;
import poly.edu.shop.service.ProductService;

@Controller
@RequestMapping("admin")
public class StatisticalController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("statistical")
	public String doanhThu(Model model) {
		Map<Integer, Double> map = new LinkedHashMap<>();
		map.put(1, orderService.doanhThuTheoThang(1));
		map.put(2, orderService.doanhThuTheoThang(2));
		map.put(3, orderService.doanhThuTheoThang(3));
		map.put(4, orderService.doanhThuTheoThang(4));
		map.put(5, orderService.doanhThuTheoThang(5));
		map.put(6, orderService.doanhThuTheoThang(6));
		map.put(7, orderService.doanhThuTheoThang(7));
		map.put(8, orderService.doanhThuTheoThang(8));
		map.put(9, orderService.doanhThuTheoThang(9));
		map.put(10, orderService.doanhThuTheoThang(10));
		map.put(11, orderService.doanhThuTheoThang(11));
		map.put(12, orderService.doanhThuTheoThang(12));
		
		model.addAttribute("map", map);
			
		return "admin/statistical/turnover";
	}
	
	@GetMapping("selling")
	public String sanPhamBanChay(Model model) {
		
		List<SellingDTO> list = orderDetailService.sanPhamBanChay();
		model.addAttribute("listSelling", list);
		
		return "admin/statistical/selling";
	}
	
	@GetMapping("stock")
	public String stock(ModelMap model,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		int currentPage = page.orElse(1);
		Integer pageSize = size.orElse(5);
		
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by(Direction.DESC, "quantity"));
		Page<Product> resultPage = null;
		
		
		if (StringUtils.hasText(name)) {
			resultPage = productService.findByNameContaining(name, pageable);
			model.addAttribute("name", name);
		}else {
			resultPage = productService.findAll(pageable);
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
		
		model.addAttribute("productPage", resultPage);
		
		return "admin/statistical/stocks";
	}
}
