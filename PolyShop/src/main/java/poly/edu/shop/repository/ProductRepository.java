package poly.edu.shop.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import poly.edu.shop.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query("Select p From Product p  Where p.name Like ?1 order by p.unitPrice desc")
	List<Product>findByKeyWords(String keywords);
	
	@Query("Select p From Product p Where p.name Like ?1 and p.category.categoryId = ?2")
	List<Product>findByNameAndCategory(String keywords, Long categoryId);

	Page<Product>findByNameContaining(String name, Pageable pageable);
	
	@Query("Select p From Product p Where p.category.categoryId = '1'")
	Page<Product>findLaptop(Pageable pageable);
	
	@Query("Select p From Product p Where p.category.categoryId = '2'")
	Page<Product>findMobile(Pageable pageable);
	
	@Query(value = "Select * from polyshop.products ORDER BY discount DESC limit 8", 
			nativeQuery = true)
	List<Product>findTop8Discount();
	
	@Query(value = "Select * from polyshop.products where category_id = 2 ORDER BY unit_price DESC limit 8", 
			nativeQuery = true)
	List<Product>findTop8Mobile();
	
	@Query(value = "Select * from polyshop.products where category_id = 1 ORDER BY unit_price DESC limit 8", 
			nativeQuery = true)
	List<Product>findTop8Laptop();
	
	@Query(value = "Select * from polyshop.products where category_id = 15 ORDER BY unit_price DESC limit 4", 
			nativeQuery = true)
	List<Product>findTop4SmartWatch();
	
}
