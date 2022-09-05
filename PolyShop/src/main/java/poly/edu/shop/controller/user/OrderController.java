package poly.edu.shop.controller.user;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.edu.shop.domain.Account;
import poly.edu.shop.domain.Address;
import poly.edu.shop.domain.Cart;
import poly.edu.shop.domain.CartItem;
import poly.edu.shop.domain.Order;
import poly.edu.shop.domain.OrderDetail;
import poly.edu.shop.domain.Product;
import poly.edu.shop.model.OrderDTO;
import poly.edu.shop.model.OrderDetailDTO;
import poly.edu.shop.service.AddressService;
import poly.edu.shop.service.OrderDetailService;
import poly.edu.shop.service.OrderService;
import poly.edu.shop.service.SessionService;
import poly.edu.shop.service.ShoppingCartService;

@Controller
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDetailService orderDetaiService;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	AddressService addressService;
	
	@GetMapping("customer")
	public String listOrder(Model model) {
		Account account = (Account) sessionService.getAttribute("username");
		if (account == null) {
			return "redirect:/login";
		}
		List<OrderDetailDTO> orders = orderDetaiService.findByAccount(account.getUsername(), Sort.by(Direction.DESC, "order"));
//		
//		
		model.addAttribute("listOrdersCustomer", orders);
//		
		return "site/ordersCustomer/list";
	}
	
	@GetMapping("customer/edit/{orderId}")
	public String edit(Model model, 
			@PathVariable("orderId") Long orderId) {
		Optional<Order> opt = orderService.findById(orderId);
		List<OrderDetail> listOrderDetail = orderDetaiService.findByOrderId(orderId);
		if (opt.isPresent() && !listOrderDetail.isEmpty()) {
			Order order = opt.get();
			Double total = listOrderDetail.stream()
					.mapToDouble(tt -> tt.getUnitPrice() * tt.getQuantity()).sum();
			
			model.addAttribute("order", order);
			model.addAttribute("listOrderDetails", listOrderDetail);
			model.addAttribute("total", total);
		}
		
		return "site/ordersCustomer/detail";
	}
	
	
	@PostMapping("checkout")
	public String save(Model model, RedirectAttributes redirectAttributes,
			@RequestParam("address") String address,@RequestParam("city") String city,
			@RequestParam("province") String province,@RequestParam("phone") String phone, 
			@RequestParam("note") String note) {
		Account user = (Account) sessionService.getAttribute("username");
		
		if (user == null) {
			return "redirect:/login";
		}
		if (address.isEmpty()) {
			redirectAttributes.addFlashAttribute("errorAddress", "Vui lòng nhập địa chỉ để giao hàng!");
			return "redirect:/cart";
		}
		Address add=new Address();
		add.setAccount(user);
		add.setCity(city);
		add.setDistrict(address);
		add.setProvince(province);
		add.setPhone(phone);
		addressService.save(add);
		
		Order order = new Order();
		Account account = new Account();
		account.setUsername(user.getUsername());

		order.setAccount(account);
		order.setOrderDate(new Date());
		order.setName(user.getName());
		order.setPhone(user.getPhone());
		order.setNote(note);
		order.setAddress(add);
		
		
		if (orderService.addOrder(order) > 0) {
			Long orderId = orderService.findMaxId();
			Cart cart = (Cart)sessionService.getAttribute("cart");
			List<CartItem> cartItems = cart.getList();
//			Collection<CartItem> cartItems = shoppingCartService.getCartItems();
			
			for (CartItem item : cartItems) {
				OrderDetail orderDetail = new OrderDetail();
				Order order2 = new Order();
				order2.setOrderId(orderId);
				Product product = new Product();
				product.setProductId(item.getProductId());
				orderDetail.setOrder(order2);
				orderDetail.setProduct(product);
				orderDetail.setQuantity(item.getQuantity());
				orderDetail.setUnitPrice(item.getUnitPrice());
				
				orderDetaiService.save(orderDetail);
				
			}
			model.addAttribute("inforOrder", order);
			model.addAttribute("cartItemsInvoice", cartItems);
			model.addAttribute("totalAmount", shoppingCartService.getAmount());
		}
		
		return "site/carts/invoice";
	}
}
