package poly.edu.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import poly.edu.shop.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	@Query(value = "Select max(order_id) from polyshop.orders", 
			nativeQuery = true)
	Long findMaxId();
	
	@Query(value="Select o from Order o where o.account.username=?1")
	List<Order> findPurchasedOrder(String username);
	
	@Query(value = "select sum(od.quantity * od.unit_price) from polyshop.orders o join polyshop.orderdetails od "
			+ "on o.order_id = od.order_id "
			+ "where month(o.order_date) = ?1", nativeQuery = true)
	Double doanhThuTheoThang(Integer month);
	
}
