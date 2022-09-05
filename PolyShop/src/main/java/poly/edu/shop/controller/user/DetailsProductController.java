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
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.shop.domain.Comments;
import poly.edu.shop.domain.Product;
import poly.edu.shop.service.CommentService;
import poly.edu.shop.service.ProductService;
import poly.edu.shop.service.SessionService;

@Controller
public class DetailsProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	CommentService commentService;
	
	@GetMapping("product/findall")
	public String search(ModelMap model,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		int currentPage = page.orElse(1);
		Integer pageSize = size.orElse(16);
		
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("productId"));
		Page<Product> resultPage = null;
			
			resultPage = productService.findAll(pageable);
		
		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages); 
			
			if (totalPages > 16) {
				if (end == totalPages){
					start = end - 16;
				}else if (start == 1) {
					end = start + 16;
				}
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
					.boxed()
					.collect(Collectors.toList());
			
			model.addAttribute("pageNumbers", pageNumbers);
		}
		
		model.addAttribute("productPage", resultPage);
		
		return "site/products/findAll";
	}
	
	
	
	@GetMapping("detail/{productId}")
	public String detail(@PathVariable("productId") Long productId, Model model) {
		Optional<Product> product = productService.findById(productId);
		if (!product.isPresent()) {
			model.addAttribute("error", "Sản phẩm không tồn tại");
			System.out.println("Not Found");
		}
		List<Comments> listcm=commentService.findProductComment(product.get().getProductId());
		listcm.forEach(n->System.out.println(n.getContent()));
		model.addAttribute("lscomment", listcm);
		sessionService.setAttribute("product_comment", product.get());
		model.addAttribute("product", product.get());
		
		return "site/products/detail";
	}

}
