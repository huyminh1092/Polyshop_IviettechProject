package poly.edu.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import poly.edu.shop.domain.CartItem;
import poly.edu.shop.domain.OrderDetail;
import poly.edu.shop.model.OrderDetailDTO;
import poly.edu.shop.model.SellingDTO;
import poly.edu.shop.repository.OrderDetailRepository;
import poly.edu.shop.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{

	@Autowired
	OrderDetailRepository orderDetailRepository;
	
//	public addOrderDetail(HashMap<Long, CartItem> cart) {
//		
//	}
	
	

	@Override
	public <S extends OrderDetail> S save(S entity) {
		return orderDetailRepository.save(entity);
	}

	@Override
	public List<OrderDetailDTO> findByAccount(String name, Sort sort) {
	return orderDetailRepository.findByAccount(name, sort);
}

	@Override
	public List<SellingDTO> sanPhamBanChay() {
		return orderDetailRepository.sanPhamBanChay();
	}

	@Override
	public List<OrderDetail> findByOrderId(Long orderId) {
		return orderDetailRepository.findByOrderId(orderId);
	}

	@Override
	public Page<OrderDetailDTO> findAllOrder(Pageable pageable) {
		return orderDetailRepository.findAllOrder(pageable);
	}

	@Override
	public Page<OrderDetailDTO> findByNameCustomer(String name, Pageable pageable) {
		return orderDetailRepository.findByNameCustomer(name, pageable);
	}

	@Override
	public List<OrderDetail> findAll() {
		return orderDetailRepository.findAll();
	}

	@Override
	public Page<OrderDetail> findAll(Pageable pageable) {
		return orderDetailRepository.findAll(pageable);
	}

	@Override
	public List<OrderDetail> findAll(Sort sort) {
		return orderDetailRepository.findAll(sort);
	}

	@Override
	public Optional<OrderDetail> findById(Long id) {
		return orderDetailRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return orderDetailRepository.existsById(id);
	}

	@Override
	public long count() {
		return orderDetailRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		orderDetailRepository.deleteById(id);
	}

	@Override
	public void delete(OrderDetail entity) {
		orderDetailRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		orderDetailRepository.deleteAll();
	}
	
	
}
