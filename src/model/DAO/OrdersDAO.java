package model.DAO;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import model.DTO.OrdersDTO;
import model.DTO.OrdersDTO.Get;
import model.DTO.UsersDTO;
import model.domain.Orders;
import util.DBUtil;

public class OrdersDAO {

	private static OrdersDAO instance = new OrdersDAO();
	public OrdersDAO() {}
	public static OrdersDAO getInstance() {
		return instance;
	}

	/** 주문내역 조회 >> 한나 */
	public static List<OrdersDTO.Get> getOrdersAll() throws SQLException {
		EntityManager em = DBUtil.getEntityManager();

		List<OrdersDTO.Get> all = null;
		try {
			all = (List<OrdersDTO.Get>) em.createNativeQuery("select * from Orders", Orders.class).getResultList();
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
			all = (List<OrdersDTO.Get>) em.createNativeQuery("select * from orders where user_id =?", Orders.class).setParameter(1, user.getId()).getResultList();
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return all;
	}

	/** 주문 취소(주문 내역 삭제) >> 한나 */
	public static boolean deleteOrders(Long idx) throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		em.getTransaction().begin();
		boolean result = false;

		try {
			em.remove(em.find(Orders.class, idx));
			em.getTransaction().commit();

			result = true;
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
			em = null;
		}
		return result;
	}

}
