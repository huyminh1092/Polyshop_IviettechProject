package poly.edu.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import poly.edu.shop.domain.Order;
import poly.edu.shop.model.OrderDTO;

public interface OrderService {

	Order getById(Long id);

	void deleteAll();

	Order getOne(Long id);

	void delete(Order entity);

	void deleteById(Long id);

	long count();

	boolean existsById(Long id);

	Optional<Order> findById(Long id);

	List<Order> findAllById(Iterable<Long> ids);

	List<Order> findAll(Sort sort);

	Page<Order> findAll(Pageable pageable);

	List<Order> findAll();

	<S extends Order> Optional<S> findOne(Example<S> example);

	<S extends Order> S save(S entity);

	Long findMaxId();

	int addOrder(Order order);

	Double doanhThuTheoThang(Integer month);

	List<Order> findPurchasedOrder(String username);


}
