package com.mycompany.myapp.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mycompany.myapp.mapper.UserAuthorityMapper;
import com.mycompany.myapp.mapper.UserMapper;
import com.mycompany.myapp.model.User;
import com.mycompany.myapp.model.UserAuthority;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public void create(User user) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			UserAuthorityMapper userAuthorityMapper = sqlSession.getMapper(UserAuthorityMapper.class);
			userMapper.insert(user);
			user.getAuthorities().forEach(userAuthorityMapper::insert);
		}
	}
	
	public User read(Integer id) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			UserAuthorityMapper userAuthorityMapper = sqlSession.getMapper(UserAuthorityMapper.class);
			User user = userMapper.selectById(id);
			if (user != null) {
				List<UserAuthority> authorities = userAuthorityMapper.selectByUserId(user.getId());
				user.setAuthorities(authorities);
			}
			return user;
		}
	}
	
	public User read(String username) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			UserAuthorityMapper userAuthorityMapper = sqlSession.getMapper(UserAuthorityMapper.class);
			User user = userMapper.selectByUsername(username);
			if (user != null) {
				List<UserAuthority> authorities = userAuthorityMapper.selectByUserId(user.getId());
				user.setAuthorities(authorities);
			}
			return user;
		}
	}
	
	public void update(User user) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			UserAuthorityMapper userAuthorityMapper = sqlSession.getMapper(UserAuthorityMapper.class);
			userMapper.update(user);
			userAuthorityMapper.deleteByUserId(user.getId());
			user.getAuthorities().forEach(userAuthorityMapper::insert);
		}
	}
	
	public void delete(Integer id) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.deleteById(id);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = read(username);
		if (user == null) {
			throw new UsernameNotFoundException(null);
		} else {
			return user;
		}
	}
	
}
