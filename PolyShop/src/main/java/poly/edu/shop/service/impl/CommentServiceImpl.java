package poly.edu.shop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import poly.edu.shop.domain.Comments;
import poly.edu.shop.repository.CommentRepository;
import poly.edu.shop.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
    
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public <S extends Comments> S save(S entity) {
		return commentRepository.save(entity);
	}

	@Override
	public <S extends Comments> Optional<S> findOne(Example<S> example) {
		return commentRepository.findOne(example);
	}
    
	
	

	@Override
	public List<Comments> findProductComment(Long productId) {
		return commentRepository.findProductComment(productId);
	}

	@Override
	public List<Comments> findAll() {
		return commentRepository.findAll();
	}

	@Override
	public Page<Comments> findAll(Pageable pageable) {
		return commentRepository.findAll(pageable);
	}

	@Override
	public List<Comments> findAll(Sort sort) {
		return commentRepository.findAll(sort);
	}

	@Override
	public List<Comments> findAllById(Iterable<Integer> ids) {
		return commentRepository.findAllById(ids);
	}

	@Override
	public Optional<Comments> findById(Integer id) {
		return commentRepository.findById(id);
	}

	@Override
	public <S extends Comments> List<S> saveAll(Iterable<S> entities) {
		return commentRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		commentRepository.flush();
	}

	@Override
	public <S extends Comments> S saveAndFlush(S entity) {
		return commentRepository.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return commentRepository.existsById(id);
	}

	@Override
	public <S extends Comments> List<S> saveAllAndFlush(Iterable<S> entities) {
		return commentRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Comments> Page<S> findAll(Example<S> example, Pageable pageable) {
		return commentRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Comments> entities) {
		commentRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Comments> long count(Example<S> example) {
		return commentRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Comments> entities) {
		commentRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return commentRepository.count();
	}

	@Override
	public <S extends Comments> boolean exists(Example<S> example) {
		return commentRepository.exists(example);
	}

	@Override
	public void deleteById(Integer id) {
		commentRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		commentRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Comments entity) {
		commentRepository.delete(entity);
	}

	@Override
	public <S extends Comments, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return commentRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		commentRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		commentRepository.deleteAllInBatch();
	}

	@Override
	public Comments getOne(Integer id) {
		return commentRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Comments> entities) {
		commentRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		commentRepository.deleteAll();
	}

	@Override
	public Comments getById(Integer id) {
		return commentRepository.getById(id);
	}

	@Override
	public <S extends Comments> List<S> findAll(Example<S> example) {
		return commentRepository.findAll(example);
	}

	@Override
	public <S extends Comments> List<S> findAll(Example<S> example, Sort sort) {
		return commentRepository.findAll(example, sort);
	}
	
	
	
}
