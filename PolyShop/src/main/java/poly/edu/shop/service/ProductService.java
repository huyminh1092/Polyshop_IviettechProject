package poly.edu.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import poly.edu.shop.domain.Product;

public interface ProductService {

	Product getById(Long id);

	void deleteAll();

	void delete(Product entity);

	void deleteById(Long id);

	long count();

	boolean existsById(Long id);

	Optional<Product> findById(Long id);

	List<Product> findAllById(Iterable<Long> ids);

	List<Product> findAll(Sort sort);

	Page<Product> findAll(Pageable pageable);

	List<Product> findAll();

	<S extends Product> Optional<S> findOne(Example<S> example);

	<S extends Product> S save(S entity);

	Page<Product> findByNameContaining(String name, Pageable pageable);

	List<Product> findTop8Discount();

	List<Product> findTop4SmartWatch();

	List<Product> findTop8Laptop();

	List<Product> findTop8Mobile();

	Page<Product> findLaptop(Pageable pageable);

	Page<Product> findMobile(Pageable pageable);

	List<Product> findByKeyWords(String keywords);

	List<Product> findByNameAndCategory(String keywords, Long categoryId);

}
