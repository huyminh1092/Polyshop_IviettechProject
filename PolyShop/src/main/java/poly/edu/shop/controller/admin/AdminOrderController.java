package poly.edu.shop.controller.admin;

import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.edu.shop.domain.Order;
import poly.edu.shop.domain.OrderDetail;
import poly.edu.shop.model.OrderDTO;
import poly.edu.shop.model.OrderDetailDTO;
import poly.edu.shop.service.OrderDetailService;
import poly.edu.shop.service.OrderService;

@Controller
@RequestMapping("admin/order")
public class AdminOrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@GetMapping("edit/{orderId}")
	public String edit(Model model, 
			@PathVariable("orderId") Long orderId) {
		Optional<Order> opt = orderService.findById(orderId);
		List<OrderDetail> listOrderDetail = orderDetailService.findByOrderId(orderId);
		if (opt.isPresent() && !listOrderDetail.isEmpty()) {
			Order order = opt.get();
			Double total = listOrderDetail.stream()
					.mapToDouble(tt -> tt.getUnitPrice() * tt.getQuantity()).sum();
			
			model.addAttribute("order", order);
			model.addAttribute("listOrderDetails", listOrderDetail);
			model.addAttribute("total", total);
		}
		
		return "admin/orders/addOrEdit";
	}
	
	@PostMapping("update")
	public String update(RedirectAttributes redirectAttributes, 
			@ModelAttribute("order") Order order) {
		Optional<Order> opt = orderService.findById(order.getOrderId());
		Order temp = opt.get();
		order.setOrderDate(temp.getOrderDate());
		order.setAccount(temp.getAccount());
		
		orderService.save(order);
		redirectAttributes.addFlashAttribute("message", "Cập nhật thành công!");
		return "redirect:/admin/order";
	}
	
	@GetMapping("delete/{orderId}")
	public String delete(@PathVariable("orderId") Long orderId, 
			Model model) {
		orderService.deleteById(orderId);
		model.addAttribute("message", "Xóa thành công");
		
		return "forward:/admin/order";
	}
	
	
	
	@GetMapping("")
	public String list(Model model, 
			@RequestParam(name = "name", required = false) String name, 
			@RequestParam("page") Optional<Integer> page, 
			@RequestParam("size") Optional<Integer> size) {
		
		int currentPage = page.orElse(1);
		Integer pageSize = size.orElse(5);
		
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("order"));
		Page<OrderDetailDTO> resultPage = null;
		
		if (StringUtils.hasText(name)) {
			resultPage = orderDetailService.findByNameCustomer("%"+ name +"%", pageable);
			model.addAttribute("name", name);
		}else {
			resultPage = orderDetailService.findAllOrder(pageable);
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
		model.addAttribute("orderPage", resultPage);
		
		return "admin/orders/list";
	}
	
	
}
