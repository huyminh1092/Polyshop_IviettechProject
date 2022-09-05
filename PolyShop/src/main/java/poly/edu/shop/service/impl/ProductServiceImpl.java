package poly.edu.shop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import poly.edu.shop.domain.Product;
import poly.edu.shop.repository.ProductRepository;
import poly.edu.shop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> findByNameAndCategory(String keywords, Long categoryId) {
		return productRepository.findByNameAndCategory(keywords, categoryId);
	}

	@Override
	public List<Product> findByKeyWords(String keywords) {
		return productRepository.findByKeyWords(keywords);
	}

	@Override
	public Page<Product> findMobile(Pageable pageable) {
		return productRepository.findMobile(pageable);
	}

	@Override
	public Page<Product> findLaptop(Pageable pageable) {
		return productRepository.findLaptop(pageable);
	}

	@Override
	public List<Product> findTop8Mobile() {
		return productRepository.findTop8Mobile();
	}

	@Override
	public List<Product> findTop8Laptop() {
		return productRepository.findTop8Laptop();
	}

	@Override
	public List<Product> findTop4SmartWatch() {
		return productRepository.findTop4SmartWatch();
	}

	@Override
	public List<Product> findTop8Discount() {
		return productRepository.findTop8Discount();
	}

	@Override
	public Page<Product> findByNameContaining(String name, Pageable pageable) {
		return productRepository.findByNameContaining(name, pageable);
	}

	@Override
	public <S extends Product> S save(S entity) {
		return productRepository.save(entity);
	}

	@Override
	public <S extends Product> Optional<S> findOne(Example<S> example) {
		return productRepository.findOne(example);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public List<Product> findAll(Sort sort) {
		return productRepository.findAll(sort);
	}

	@Override
	public List<Product> findAllById(Iterable<Long> ids) {
		return productRepository.findAllById(ids);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return productRepository.existsById(id);
	}

	@Override
	public long count() {
		return productRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public void delete(Product entity) {
		productRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		productRepository.deleteAll();
	}

	@Override
	public Product getById(Long id) {
		return productRepository.getById(id);
	}
	
	
}
