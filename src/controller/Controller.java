package controller;

import javax.persistence.EntityManager;

import model.domain.Cart;
import model.domain.Orders;
import model.domain.Product;
import model.domain.Users;
import util.DBUtil;

public class Controller {

	public static void main(String[] args) {
		EntityManager em = DBUtil.getEntityManager();
		
		System.out.println(em.find(Product.class, 1L));
		System.out.println(em.find(Cart.class, 1L));
		System.out.println(em.find(Users.class, "noowah"));
		System.out.println(em.find(Orders.class, 1L));
		
	}

}
