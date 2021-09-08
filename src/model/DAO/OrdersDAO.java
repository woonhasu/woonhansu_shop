package model.DAO;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import model.DTO.OrdersDTO;
import model.domain.Orders;
import util.DBUtil;

public class OrdersDAO {

	private static OrdersDAO instance = new OrdersDAO();
	public OrdersDAO() {}
	public static OrdersDAO getInstance() {
		return instance;
	}

	/** 주문내역 조회 >> 한나 */
	public static List<OrdersDTO> getAllOrders() throws SQLException {
		EntityManager em = DBUtil.getEntityManager();

		List<OrdersDTO> all = null;
		try {
			all = (List<OrdersDTO>) em.createNativeQuery("select * from Orders", Orders.class).getResultList();
		} finally {
			em.close();
			em = null;
		}
		
		System.out.println("+++ " + all);
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
