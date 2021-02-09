package com.umg.dao;
import com.umg.entidad.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoJpaRepository extends JpaRepository<UserInfo, Long>{
	public UserInfo findByUsername(String username);
}
