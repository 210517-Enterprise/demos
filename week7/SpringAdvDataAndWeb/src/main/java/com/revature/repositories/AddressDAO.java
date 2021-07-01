package com.revature.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.Address;

public interface AddressDAO extends JpaRepository<Address, Integer>{
	
	public Set<Address> findByOwnerUsername(String username);

}
