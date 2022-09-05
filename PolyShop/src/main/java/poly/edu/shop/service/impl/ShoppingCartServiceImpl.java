package poly.edu.shop.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import poly.edu.shop.domain.CartItem;
import poly.edu.shop.service.SessionService;
import poly.edu.shop.service.ShoppingCartService;

@Service
@SessionScope
public class ShoppingCartServiceImpl implements ShoppingCartService{
	
	@Autowired
	SessionService sessionService;
	
	private Map<Long, CartItem> map = new HashMap<>();
	
//	private Map<Long, CartItem> maps = sessionService.get
	
	@Override
	public void add(CartItem item) {
		CartItem existItem = map.get(item.getProductId());
		
		if (existItem != null) {
			existItem.setQuantity(item.getQuantity() + existItem.getQuantity());
			existItem.setIntoMoney(existItem.getQuantity() * item.getUnitPrice());
		}else {
			item.setIntoMoney(item.getQuantity() * item.getUnitPrice());
			map.put(item.getProductId(), item);
		}
	}
	
	
	
	@Override
	public void update(Long productId, int quantity) {
		CartItem item = map.get(productId);
		
		item.setQuantity(quantity);
		item.setIntoMoney(quantity * item.getUnitPrice());
	}
	
	@Override
	public void remove(Long productId) {
		map.remove(productId);
	}
	
	@Override
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	
	
	
	@Override
	public void clear() {
		map.clear();
	}
	
	@Override
	public double getAmount() {
		return map.values().stream().
				mapToDouble(item->item.getIntoMoney()).sum();
	}
	
	@Override
	public int getCount() {
		if (map.isEmpty()) {
			return 0;
		}
		
		return map.values().size();
	}
 }
