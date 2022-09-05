package poly.edu.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import poly.edu.shop.domain.Comments;


@Repository
public interface CommentRepository extends JpaRepository<Comments,Integer>{

	@Query("Select c From Comments c Where c.product.productId = ?1")
	List<Comments>findProductComment(Long productId);
}
