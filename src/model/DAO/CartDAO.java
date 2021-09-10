package model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import model.DTO.CartDTO;
import model.DTO.UsersDTO;
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
	public static List<CartDTO.Get> getCartAll() {
		EntityManager em = DBUtil.getEntityManager();
		
		List<Cart> list = null;

		ArrayList<CartDTO.Get> alist = new ArrayList<>();
		
		list = (List<Cart>) em.createNativeQuery("select * from cart", Cart.class).getResultList();
		
		list.forEach(v -> {
			alist.add(new CartDTO.Get(v.getIdx(), v.getUsers(), v.getProduct()));
		});
		return alist;
	}
	
	//	회원 장바구니 조회 (회원)
	public static List<CartDTO.Get> getUserCartAll(UsersDTO.Get user){
		EntityManager em = DBUtil.getEntityManager();
		List<Cart> list = null;

		ArrayList<CartDTO.Get> alist = new ArrayList<>();
		
		list = (List<Cart>) em.createNativeQuery("select * from cart where user_id = ?", Cart.class).setParameter(1, user.getId()).getResultList();
		
		list.forEach(v -> {
			alist.add(new CartDTO.Get(v.getIdx(), v.getUsers(), v.getProduct()));
		});
		return alist;
	}
	
	// 장바구니 내역 추가
	public static boolean addCart(CartDTO.Create cart) {
		EntityManager em = DBUtil.getEntityManager();
		em.getTransaction().begin();
		
		boolean result = false;
		Cart newCart = null;
		
		try {
			newCart = new Cart();
			newCart.setUsers(em.find(Users.class, cart.getUserIdx()));
			newCart.setProduct(em.find(Product.class, cart.getProductIdx()));
			
			em.persist(newCart);
			em.getTransaction().commit();
			
			result = true;
		} catch(Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return result;
	}
	
	//	장바구니 내역 삭제
	public static boolean deleteCart(Long cartIdx) {
		EntityManager em = DBUtil.getEntityManager();
		em.getTransaction().begin();
		
		boolean result = false;
		Cart cart = null;
		
		try {
			cart = em.find(Cart.class, cartIdx);
			
			em.remove(cart);
			em.getTransaction().commit();
			
			result = true;
		} catch(Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return result;
	}
}