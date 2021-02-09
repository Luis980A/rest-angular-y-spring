package com.umg;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.umg.dao.UserInfoJpaRepository;
import com.umg.entidad.UserInfo;

@SpringBootTest
class UserApplicationTests {
	
	@Autowired
	private  UserInfoJpaRepository userDao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Test
	void crearUsuario() {
		/*UserInfo user = new UserInfo();
		user.setEnabled(true);
		user.setPassword(encoder.encode("root"));
		user.setRole("ADMIN");
		user.setUsername("luis");
		UserInfo retorno = userDao.save(user);
		assertTrue(retorno.getUsername().equalsIgnoreCase(user.getUsername()));*/
	}

}
