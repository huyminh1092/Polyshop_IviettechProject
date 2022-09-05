package poly.edu.shop.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import poly.edu.shop.domain.Comments;

public interface CommentService {

	<S extends Comments> List<S> findAll(Example<S> example, Sort sort);

	<S extends Comments> List<S> findAll(Example<S> example);

	Comments getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Comments> entities);

	Comments getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	<S extends Comments, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Comments entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	<S extends Comments> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Comments> entities);

	<S extends Comments> long count(Example<S> example);

	void deleteInBatch(Iterable<Comments> entities);

	<S extends Comments> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Comments> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(Integer id);

	<S extends Comments> S saveAndFlush(S entity);

	void flush();

	<S extends Comments> List<S> saveAll(Iterable<S> entities);

	Optional<Comments> findById(Integer id);

	List<Comments> findAllById(Iterable<Integer> ids);

	List<Comments> findAll(Sort sort);

	Page<Comments> findAll(Pageable pageable);

	List<Comments> findAll();

	<S extends Comments> Optional<S> findOne(Example<S> example);

	<S extends Comments> S save(S entity);

	List<Comments> findProductComment(Long productId);

	

}
