package poly.edu.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import poly.edu.shop.domain.OrderDetail;
import poly.edu.shop.model.OrderDetailDTO;
import poly.edu.shop.model.SellingDTO;

public interface OrderDetailService {

	void deleteAll();

	void delete(OrderDetail entity);

	void deleteById(Long id);

	long count();

	boolean existsById(Long id);

	Optional<OrderDetail> findById(Long id);

	List<OrderDetail> findAll(Sort sort);

	Page<OrderDetail> findAll(Pageable pageable);

	List<OrderDetail> findAll();

	<S extends OrderDetail> S save(S entity);

	Page<OrderDetailDTO> findByNameCustomer(String name, Pageable pageable);

	Page<OrderDetailDTO> findAllOrder(Pageable pageable);

	List<OrderDetail> findByOrderId(Long orderId);

	List<SellingDTO> sanPhamBanChay();

	List<OrderDetailDTO> findByAccount(String name, Sort sort);

}
