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
	
	/** 회원 정보 수정 ** request, update 나눠서  >> 지수
	 * 
	 */

}
