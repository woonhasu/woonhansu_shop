package model.DAO;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import model.DTO.ProductDTO;
import model.DTO.UsersDTO;
import model.domain.Product;
import util.DBUtil;


public class ProductDAO2 {
	private static ProductDAO2 instance = new ProductDAO2();
	public ProductDAO2() {}
	public static ProductDAO2 getInstance() {
		return instance;
	}
	
	/** 제품 추가 >> 한나 */
	public static boolean addProduct(ProductDTO.Create Product) throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		em.getTransaction().begin();
		boolean result = false;
		try {
			em.persist(Product.toEntity());
			em.getTransaction().commit();
			
			result = true;
		}catch(Exception e) {
			em.getTransaction().rollback();
		}finally {
			em.close();
			em = null;
		}
		return result;
	}
	
	/** 제품 삭제 >> 한나 */
	public static boolean deleteProduct(Long idx) throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		em.getTransaction().begin();
		boolean result = false;
		
		try {
			em.remove(em.find(Product.class, idx));
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
