package poly.edu.shop.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import poly.edu.shop.domain.Address;

public interface AddressService {

	<S extends Address> List<S> findAll(Example<S> example, Sort sort);

	<S extends Address> List<S> findAll(Example<S> example);

	Address getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Address> entities);

	Address getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	<S extends Address, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Address entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	<S extends Address> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Address> entities);

	<S extends Address> long count(Example<S> example);

	void deleteInBatch(Iterable<Address> entities);

	<S extends Address> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Address> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(Integer id);

	<S extends Address> S saveAndFlush(S entity);

	void flush();

	<S extends Address> List<S> saveAll(Iterable<S> entities);

	Optional<Address> findById(Integer id);

	List<Address> findAllById(Iterable<Integer> ids);

	List<Address> findAll(Sort sort);

	Page<Address> findAll(Pageable pageable);

	List<Address> findAll();

	<S extends Address> Optional<S> findOne(Example<S> example);

	<S extends Address> S save(S entity);

}
