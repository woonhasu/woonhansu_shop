package model.domain;

import java.util.List;

import model.DTO.CartDTO;
import model.dao.CartDAO;

public class Service {

	private static Service instance = new Service();

	private Service() {
	}

	public static Service getInstance() {
		return instance;
	}
	
	private static CartDAO cartDAO = CartDAO.getInstance();
	

	// Car CRUD
	public static List<CartDTO.Get> getCartAll() {
		return cartDAO.getCartAll();
	}
	
	public static boolean insertCart(Users user, Product product) {
		return cartDAO.insertCart(user, product);
	}
	
	public static boolean deleteCart(Long idx) {
		return cartDAO.deleteCart(idx);
	}
	
	public static void main(String[] args) {
		List<CartDTO.Get> a = getCartAll();
		a.forEach(v -> {
			System.out.println(v.getProduct());
		});
	}
	
}
