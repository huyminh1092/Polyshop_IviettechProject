package poly.edu.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poly.edu.shop.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer>{

}
