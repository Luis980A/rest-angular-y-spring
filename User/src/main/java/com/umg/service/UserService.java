package com.umg.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.umg.dao.UserInfoJpaRepository;
import com.umg.entidad.UserInfo;

@Service
public class UserService implements UserDetailsService{
	@Autowired
	private UserInfoJpaRepository repoUser;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserInfo user = repoUser.findByUsername(username);
		if(user.isEnabled()) {
			List<GrantedAuthority> roles = new ArrayList<>();
			roles.add(new SimpleGrantedAuthority(user.getRole()));
			UserDetails userDet = new User(user.getUsername(),user.getPassword(), roles);
			return userDet;
		}else {
			System.out.println("usuario hinabilitado");
		}
		return null;
	}

}
