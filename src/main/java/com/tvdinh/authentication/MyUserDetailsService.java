package com.tvdinh.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tvdinh.dao.IUserDao;
import com.tvdinh.entity.UserEntity;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private IUserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user=userDao.findOneUsername(username);
		
		if(user==null)
		{
			throw new UsernameNotFoundException("User not found");
		}
		
		List<GrantedAuthority> authorities= new ArrayList<GrantedAuthority>();
		//for(RoleEntity role: user.getRoles()) {
		//	authorities.add(new SimpleGrantedAuthority(role.getCode()));
		//}
		authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole().getCode()));
		
		// User của spring security đã được custom qua MyUser và do User implements
		// UserDetails nên ta có thể return myUser
		MyUser myUser = new MyUser(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
		myUser.setFullName(user.getFull_name());

		return myUser;
	}

}
