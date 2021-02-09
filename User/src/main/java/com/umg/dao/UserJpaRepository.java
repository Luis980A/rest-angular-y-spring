package com.umg.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umg.entidad.*;

@Repository
public interface UserJpaRepository extends JpaRepository<UsersDTO, Long>{
	Optional<UsersDTO> findById(Long id);
	UsersDTO findByName(String name);
}
