package poly.edu.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import poly.edu.shop.domain.Account;

public interface AccountService {

	Account getById(String id);

	void deleteAll();

	Account getOne(String id);

	void delete(Account entity);

	void deleteById(String id);

	long count();

	boolean existsById(String id);

	Optional<Account> findById(String id);

	List<Account> findAllById(Iterable<String> ids);

	List<Account> findAll(Sort sort);

	Page<Account> findAll(Pageable pageable);

	List<Account> findAll();

	<S extends Account> Optional<S> findOne(Example<S> example);

	<S extends Account> S save(S entity);

	Page<Account> findByNameContaining(String name, Pageable pageable);

	Account login(String username, String password);

	Account forgot(String username, String email);

}
