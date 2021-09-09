package model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.DTO.ProductDTO;
import model.domain.Product;
import util.DBUtil;

public class ProductDAO {
	
	private static ProductDAO instance = new ProductDAO();
	
	private ProductDAO() {}

	public static ProductDAO getInstance() {
		return instance;
	}
	
	/** 제품 다중(전체) 조회 >> 지수
	 * 
	 */
	public ArrayList<ProductDTO.Get> getProductAll() {
		EntityManager em =  DBUtil.getEntityManager();
		ArrayList<ProductDTO.Get> all = null;
		
		try {
			all = (ArrayList<ProductDTO.Get>)em.createNativeQuery("SELECT * FROM product", Product.class).getResultList();
		} catch(Exception e) {
			//e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		
		return all;
	}
	
	/** 제품 이름으로 다중 조회 >> 지수
	 * 
	 */
	public ArrayList<ProductDTO.Get> getProductByName(String name) {
		EntityManager em = DBUtil.getEntityManager();
		ArrayList<ProductDTO.Get> nAll = null;
		
		try {
			Query query = em.createNativeQuery("SELECT * FROM product WHERE product_name LIKE ?||'%'", Product.class);
			query.setParameter(1, name.substring(0,2));
			nAll = (ArrayList<ProductDTO.Get>)query.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		
		return nAll;
	}
	
	/** 제품 카테고리별로 조회 >> 지수
	 * 
	 */
	public ArrayList<ProductDTO.Get> getProductByCategory(String category) {
		EntityManager em = DBUtil.getEntityManager();
		ArrayList<ProductDTO.Get> cAll = null;
		
		try {
			Query query = em.createNativeQuery("SELECT * FROM product WHERE category=?", Product.class);
			query.setParameter(1, category);
			cAll = (ArrayList<ProductDTO.Get>)query.getResultList();
		} catch(Exception e) {
			//e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		
		return cAll;
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

	public static ProductDTO.Get getProductByIdx(Long idx) {
		EntityManager em = DBUtil.getEntityManager();
		
		ProductDTO.Get product = null;
		
		Product p = em.find(Product.class, idx);
		
		System.out.println(p);
		
		try {
			product = new ProductDTO.Get();
			product.setIdx(p.getIdx());
			product.setCategory(p.getCategory());
			product.setName(p.getName());
			product.setPrice(p.getPrice());
			product.setColor(p.getColor());
			product.setPsize(p.getPsize());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return product;
	}
	
	/** 제품 수정
	 * 
	 */
	public boolean updateProduct(Long idx, ProductDTO.Update newProduct) {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		
		tx.begin();
		
		try {
			Product product = em.find(Product.class, idx);
			
			if(product != null) {
				product.setCategory(newProduct.getCategory());
				product.setName(newProduct.getName());
				product.setPrice(newProduct.getPrice());
				product.setColor(newProduct.getColor());
				product.setPsize(newProduct.getPsize());
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
