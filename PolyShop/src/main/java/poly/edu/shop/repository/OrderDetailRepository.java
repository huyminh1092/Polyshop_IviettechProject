package poly.edu.shop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import poly.edu.shop.domain.OrderDetail;
import poly.edu.shop.model.OrderDetailDTO;
import poly.edu.shop.model.SellingDTO;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
	
	@Query("Select new OrderDetailDTO(od.order, sum(od.unitPrice * od.quantity)) "
			+ "From OrderDetail od "
			+ "Group By od.order")
	Page<OrderDetailDTO> findAllOrder(Pageable pageable);
	
	@Query("Select new OrderDetailDTO(od.order, sum(od.unitPrice * od.quantity)) "
			+ "From OrderDetail od " 
			+ "Where od.order.name like ?1 "
			+ "Group By od.order")
	Page<OrderDetailDTO> findByNameCustomer(String name, Pageable pageable);

	@Query("Select od From OrderDetail od where od.order.orderId = ?1")
	List<OrderDetail> findByOrderId(Long orderId);
	
	@Query("Select new OrderDetailDTO(od.order, sum(od.unitPrice * od.quantity)) "
			+ "From OrderDetail od " 
			+ "Where od.order.account.username = ?1 "
			+ "Group By od.order")
	List<OrderDetailDTO> findByAccount(String name, Sort sort);
	
	@Query("Select new SellingDTO(od.product, count(od.product)) From OrderDetail od "
			+ "Group By od.product Order By count(od.product) DESC")
	List<SellingDTO> sanPhamBanChay();
	
}
