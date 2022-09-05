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
import org.springframework.stereotype.Service;

import poly.edu.shop.domain.Address;
import poly.edu.shop.repository.AddressRepository;
import poly.edu.shop.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
    
	@Autowired
	AddressRepository addressRepository;

	@Override
	public <S extends Address> S save(S entity) {
		return addressRepository.save(entity);
	}

	@Override
	public <S extends Address> Optional<S> findOne(Example<S> example) {
		return addressRepository.findOne(example);
	}

	@Override
	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	@Override
	public Page<Address> findAll(Pageable pageable) {
		return addressRepository.findAll(pageable);
	}

	@Override
	public List<Address> findAll(Sort sort) {
		return addressRepository.findAll(sort);
	}

	@Override
	public List<Address> findAllById(Iterable<Integer> ids) {
		return addressRepository.findAllById(ids);
	}

	@Override
	public Optional<Address> findById(Integer id) {
		return addressRepository.findById(id);
	}

	@Override
	public <S extends Address> List<S> saveAll(Iterable<S> entities) {
		return addressRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		addressRepository.flush();
	}

	@Override
	public <S extends Address> S saveAndFlush(S entity) {
		return addressRepository.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return addressRepository.existsById(id);
	}

	@Override
	public <S extends Address> List<S> saveAllAndFlush(Iterable<S> entities) {
		return addressRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Address> Page<S> findAll(Example<S> example, Pageable pageable) {
		return addressRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Address> entities) {
		addressRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Address> long count(Example<S> example) {
		return addressRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Address> entities) {
		addressRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return addressRepository.count();
	}

	@Override
	public <S extends Address> boolean exists(Example<S> example) {
		return addressRepository.exists(example);
	}

	@Override
	public void deleteById(Integer id) {
		addressRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		addressRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Address entity) {
		addressRepository.delete(entity);
	}

	@Override
	public <S extends Address, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return addressRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		addressRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		addressRepository.deleteAllInBatch();
	}

	@Override
	public Address getOne(Integer id) {
		return addressRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Address> entities) {
		addressRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		addressRepository.deleteAll();
	}

	@Override
	public Address getById(Integer id) {
		return addressRepository.getById(id);
	}

	@Override
	public <S extends Address> List<S> findAll(Example<S> example) {
		return addressRepository.findAll(example);
	}

	@Override
	public <S extends Address> List<S> findAll(Example<S> example, Sort sort) {
		return addressRepository.findAll(example, sort);
	}
	
	
}
