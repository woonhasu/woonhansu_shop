package model.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.DTO.UsersDTO;
import model.domain.Users;
import util.DBUtil;

public class UsersDAO {
	
	private static UsersDAO instance = new UsersDAO();
	
	private UsersDAO() {}
	
	public static UsersDAO getInstance() {
		return instance;
	}
	
	/** 회원가입 >> 지수
	 * 
	 */
	public boolean addUser(UsersDTO.Create user) {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		
		tx.begin();
		
		try {
			em.persist(user.toEntity());
			
			tx.commit();
			result = true;
			
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		return result;
	}

	public UsersDTO.Get login(String userId, String pw) {
		EntityManager em = DBUtil.getEntityManager();
		Users check = null;
		
		UsersDTO.Get user = null;
		
		try {
			check = em.find(Users.class, userId);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		if(check.getId().equals(userId) && check.getPw().equals(pw)) {
			user = getUser(userId);
		}
		return user;
	}
	
	/** 회원 정보 단일 조회 >> 지수
	 * 
	 */
	public UsersDTO.Get getUser(String userId) {
		EntityManager em = DBUtil.getEntityManager();
		UsersDTO.Get user = null;
		
		try {
			Users u = em.find(Users.class, userId);
			user = new UsersDTO.Get(u.getId(), u.getPw(), u.getAdmin(), u.getName(), u.getAddress(), u.getPhone());
		}catch(Exception e) {
			//e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return user;
	}  
	
	/** 회원 정보 수정 ** request, update 나눠서  >> 지수
	 * 
	 */
	public boolean updateUser(String userId, UsersDTO.Update newUser) {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		
		tx.begin();
		
		try {
			Users user = em.find(Users.class, userId);
			
			if(user != null) {
				user.setPw(newUser.getPw());
				user.setAddress(newUser.getAddress());
				user.setPhone(newUser.getPhone());
				
				tx.commit();
				result = true;
			}
		
		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
			em = null;
		}
		
		return result;
	}
	
	/** 회원 탈퇴 >> 지수
	 * 
	 */
	public boolean deleteUser(String userId) {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		
		tx.begin();
		
		try {
			Users user = em.find(Users.class, userId);
			
			if(user != null) {
				em.remove(user);

				tx.commit();
				result = true;
			}
			
		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
			em = null;
		}
		
		return result;
	}

}
