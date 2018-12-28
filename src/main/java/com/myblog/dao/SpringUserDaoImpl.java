package com.myblog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.myblog.entity.User;

public class SpringUserDaoImpl implements UserDao {
	
	private DataSource dataSource;
		
	private Connection connection;
	
	private Statement statement;


	public SpringUserDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
		
		try {
			connection = this.dataSource.getConnection();
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<User> getAllUsers() {
		String selectSql = "select * from user";
		List<User> allUsers = new ArrayList<User>();
		ResultSet rs = null;
		try {
			rs = statement.executeQuery(selectSql);
			while(rs.next()) {
				long id = rs.getLong("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				
				User user = new User(id, name, age);
				allUsers.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return allUsers;
	}

	public User getUserById(long id) {
		String sqlQuery = "select * from user where id=" + id;
		
		ResultSet rs = null;
		User user = null;
		try {
			rs = statement.executeQuery(sqlQuery);
			//todo  what if user provides the id thats not present in database;

			while(rs.next()) {
				long userId = rs.getLong("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				user = new User(id, name, age);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
		
	}

	public void addUser(String providedName, int providedAge) {
		String sqlQuery = String.format("insert into user(name, age) values ('%s', %d);", 
				providedName, providedAge);
	
		
		try {
			statement.executeUpdate(sqlQuery);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void addUser(User user) {
		this.addUser(user.getName(), user.getAge());
		
	}

	@Override
	public void deleteUser(long userId) {
		String deleteSql = "delete from user where id ="+userId +";";
		try {
			statement.executeUpdate(deleteSql);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public List<User> getUsersByName(String name) {
		String sqlQuery = "select * from user where name = ?";

		List<User> users = new ArrayList<>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, name);

			ResultSet rs = statement.executeQuery();
			//todo  what if user provides the id thats not present in database;

			while(rs.next()) {
				long userId = rs.getLong("id");
				String userName = rs.getString("name");
				int age = rs.getInt("age");
				User user = new User(userId, userName, age);
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

}
