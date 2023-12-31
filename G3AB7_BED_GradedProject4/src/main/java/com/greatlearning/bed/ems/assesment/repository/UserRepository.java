package com.greatlearning.bed.ems.assesment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.bed.ems.assesment.model.NewEmployeeRegister;

@Repository
public interface UserRepository extends JpaRepository<NewEmployeeRegister, Long> {

	NewEmployeeRegister findByEmail(String email);
	
	public List<NewEmployeeRegister> findByFirstNameContaining(String query);
}
