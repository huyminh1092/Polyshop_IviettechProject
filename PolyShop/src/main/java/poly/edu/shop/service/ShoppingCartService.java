package poly.edu.shop.service;

import java.util.Collection;

import poly.edu.shop.domain.CartItem;

public interface ShoppingCartService {

	int getCount();

	double getAmount();

	void clear();

	Collection<CartItem> getCartItems();

	void remove(Long productId);

	void update(Long productId, int quantity);

	void add(CartItem item);

}
