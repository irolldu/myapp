package com.mycompany.myapp.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mycompany.myapp.mapper.PostCategoryMapper;
import com.mycompany.myapp.mapper.PostMapper;
import com.mycompany.myapp.mapper.PostPrizeBenefitMapper;
import com.mycompany.myapp.mapper.UserAuthorityMapper;
import com.mycompany.myapp.mapper.UserMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class MyBatisConfig {
	
	@Bean
	public DataSource dataSource() throws SQLException {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDataSourceClassName("org.mariadb.jdbc.MariaDbDataSource");
		hikariConfig.addDataSourceProperty("url", "jdbc:mariadb://localhost:3306/demo");
		hikariConfig.setUsername("demo");
		hikariConfig.setPassword("password");
		return new HikariDataSource(hikariConfig);
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws SQLException {
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development", transactionFactory, dataSource());
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration(environment);
		configuration.addMapper(UserMapper.class);
		configuration.addMapper(UserAuthorityMapper.class);
		configuration.addMapper(PostMapper.class);
		configuration.addMapper(PostCategoryMapper.class);
		configuration.addMapper(PostPrizeBenefitMapper.class);
		return new SqlSessionFactoryBuilder().build(configuration);
	}
	
}
