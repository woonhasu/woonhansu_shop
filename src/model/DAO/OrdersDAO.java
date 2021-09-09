package model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.DTO.OrdersDTO;
import model.DTO.OrdersDTO.Get;
import model.DTO.UsersDTO;
import model.domain.Orders;
import model.domain.Product;
import model.domain.Users;
import util.DBUtil;

public class OrdersDAO {

	private static OrdersDAO instance = new OrdersDAO();
	public OrdersDAO() {}
	public static OrdersDAO getInstance() {
		return instance;
	}

	/** 주문내역 조회 >> 한나 */
	public static ArrayList<OrdersDTO.Get> getOrdersAll() throws SQLException {
		EntityManager em = DBUtil.getEntityManager();

		ArrayList<OrdersDTO.Get> all = null;
		try {
			all = (ArrayList<OrdersDTO.Get>) em.createNativeQuery("select * from Orders", Orders.class).getResultList();
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return all;
	}
	
	public static List<Get> getUserOrdersAll(UsersDTO.Get user) {
		EntityManager em = DBUtil.getEntityManager();

		List<OrdersDTO.Get> all = null;
		try {
			all = (List<OrdersDTO.Get>) em.createNativeQuery("select * from orders where user_id =? order by 1", Orders.class).setParameter(1, user.getId()).getResultList();
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return all;
	}

	/** 주문 취소(주문 내역 삭제) >> 한나 */
	public static boolean deleteOrders(Long idx) {
		System.out.println(idx);
		EntityManager em = DBUtil.getEntityManager();
		em.getTransaction().begin();
		
		boolean result = false;
		Orders orders = null;

		try {
			orders = em.find(Orders.class, idx);
			
			em.remove(orders);
			em.getTransaction().commit();

			result = true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
			System.out.println(result);
		}
		return result;
	}
	
	/** 주문 취소(주문 내역 삭제) >> 하운 추가! */
	public static boolean addOrders(String userId, Long idx) throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		em.getTransaction().begin();
		
		boolean result = false;
		Orders order = null;
		
		try {
			order = new Orders();
			order.setUsers(em.find(Users.class, userId));
			order.setProduct(em.find(Product.class, idx));
			order.setDate(new Date());
			
			em.persist(order);
			em.getTransaction().commit();
			
			result = true;
			System.out.println(order);
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return result;
	}
	
	/**
	 * 주문 수정 
	 */
	public boolean updateOrders(Long idx, Long productIdx) {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		
		tx.begin();
		
		try {
			Orders orders = em.find(Orders.class, idx);
			Product newProduct = em.find(Product.class, productIdx);
			
			if(orders != null) {
				orders.setProduct(newProduct);
			}
			tx.commit();
			result = true;
		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
			em = null;
		}
		return result;
	}
	
}
