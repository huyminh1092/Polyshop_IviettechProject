package poly.edu.shop.controller.user;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.shop.domain.Account;
import poly.edu.shop.domain.Address;
import poly.edu.shop.domain.Cart;
import poly.edu.shop.domain.CartItem;
import poly.edu.shop.domain.Product;
import poly.edu.shop.service.ProductService;
import poly.edu.shop.service.SessionService;
import poly.edu.shop.service.ShoppingCartService;

@Controller
@RequestMapping("cart")
public class ShoppingCartController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@GetMapping("")
	public String list(Model model) {
		Account account = (Account) sessionService.getAttribute("username");
		
		if (account == null) {
			return "redirect:/login";
		}
		
//		Collection<CartItem> cartItems = shoppingCartService.getCartItems();
//		Collection<CartItem> cartItems = (Collection<CartItem>) sessionService.getAttribute("cart");
		Cart cart = (Cart) sessionService.getAttribute("cart");
		if (cart == null) {
			model.addAttribute("cartItemsEmpty", "Bạn chưa chọn sản phẩm nào !");
			return "site/carts/shoppingCart";
		}
//		model.addAttribute("cartItemsEmpty", "Bạn chưa chọn sản phẩm nào!");
		model.addAttribute("cartItems", cart.getList());
		model.addAttribute("total", shoppingCartService.getAmount());
		model.addAttribute("noOfItems", shoppingCartService.getCount());
		
		return "site/carts/shoppingCart";
	}
	
	@GetMapping("add/{productId}")
	public String add(@PathVariable("productId") Long productId) {
		Account account = (Account) sessionService.getAttribute("username");
		
		if (account == null) {
			return "redirect:/login";
		}
		
		Optional<Product> optProduct = productService.findById(productId);
		
		if (optProduct.isPresent()) {
			Product product = optProduct.get();
			
			CartItem item = new CartItem();
			Cart cart = new Cart();
			BeanUtils.copyProperties(product, item);
			
			item.setQuantity(1);
			shoppingCartService.add(item);
			cart.setCustomer(account);
			cart.setList(shoppingCartService.getCartItems().stream().toList());
			cart.setAmount(0);
			sessionService.setAttribute("cart",cart);
			
		}
		
		return "redirect:/cart";
	}
	
	@GetMapping("remove/{productId}") 
	public String remove(@PathVariable("productId") Long productId) {
		shoppingCartService.remove(productId);
		
		return "redirect:/cart";
	}
	
	@PostMapping("update")
	public String update(@RequestParam("productId") Long productId, 
			@RequestParam("quantity") Integer quantity) {
		
		shoppingCartService.update(productId, quantity);
		
		return "redirect:/cart";
	}
	
	@GetMapping("clear")
	public String clear() {
		shoppingCartService.clear();
		return "redirect:/cart";
	}
	
	@GetMapping("checkout")
	public String checkout(Model model) {
		Collection<CartItem> cartItems = (Collection<CartItem>) sessionService.getAttribute("cart");

		model.addAttribute("checkouts", cartItems);
		model.addAttribute("totalCheckout", shoppingCartService.getAmount());
		
		return "site/carts/invoice";
	}
}
