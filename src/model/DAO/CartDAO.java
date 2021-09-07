package model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import model.DTO.CartDTO;
import model.domain.Cart;
import model.domain.Product;
import model.domain.Users;
import util.DBUtil;

public class CartDAO {
	
	private static CartDAO instance = new CartDAO();
	
	private CartDAO() {};
	
	public static CartDAO getInstance() {
		return instance;
	}
	
	
	//	관리자 전체 장바구니 조회 (관리자)
	public static List<CartDTO.Get> getCartAll(){
		EntityManager em = DBUtil.getEntityManager();
		List<Cart> list = null;
		
		ArrayList<CartDTO.Get> alist = new ArrayList<>();
		
		list = (List<Cart>) em.createNativeQuery("select * from cart", Cart.class).getResultList();
		
		list.forEach(v -> {
			alist.add(new CartDTO.Get(v.getUsers(), v.getProduct()));
		});
			
		return alist;
	}
	
	//	회원 장바구니 조회 (회원)
	public static List<CartDTO.Get> getUserCartAll(Users user){
		EntityManager em = DBUtil.getEntityManager();
		List<Cart> list = null;
		
		ArrayList<CartDTO.Get> alist = new ArrayList<>();
		
		list = (List<Cart>) em.createNativeQuery("select * from cart where user_id = ?", Cart.class).setParameter(1, user.getId()).getResultList();
		
		list.forEach(v -> {
			alist.add(new CartDTO.Get(v.getUsers(), v.getProduct()));
		});
		
		return alist;
	}
	
	
	// 장바구니 내역 추가
	public static boolean insertCart(Users user, Product product) {
		EntityManager em = DBUtil.getEntityManager();
		em.getTransaction().begin();
		
		boolean result = false;
		Cart cart = null;
		
		try {
			cart = new Cart();
			cart.setUsers(user);
			cart.setProduct(product);
			
			em.persist(cart);
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
	
	public static boolean deleteCart(Long idx) {
		EntityManager em = DBUtil.getEntityManager();
		em.getTransaction().begin();
		
		boolean result = false;
		Cart cart = null;
		
		try {
			cart = em.find(Cart.class, idx);
			
			em.remove(cart);
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
	
}
