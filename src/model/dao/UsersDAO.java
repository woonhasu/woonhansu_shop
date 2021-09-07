package model.dao;

import java.util.List;

import model.domain.Users;

public class UsersDAO {
	
	private static UsersDAO instance = new UsersDAO();
	
	private UsersDAO() {};
	
	public static UsersDAO getInstance() {
		return instance;
	}

//	public List<Users> getUserAll(){
//		
//	}
}
