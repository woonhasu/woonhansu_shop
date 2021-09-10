package model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.NotExistException;
import model.DTO.CartDTO;
import model.DTO.OrdersDTO;
import model.DTO.ProductDTO;
import model.DTO.ProductDTO.Create;
import model.DTO.UsersDTO;

public class Service {
	
	private static Service instance = new Service();
	
	private Service() {}
	
	public static Service getInstance() {
		return instance;
	}
	
	private static UsersDAO usersDAO = UsersDAO.getInstance();
	private static ProductDAO productDAO = ProductDAO.getInstance();
	private static CartDAO cartDAO = CartDAO.getInstance();
	private static OrdersDAO ordersDAO = OrdersDAO.getInstance();
	
	//login
	public UsersDTO.Get login(String id, String pw) throws NotExistException {
		UsersDTO.Get user = usersDAO.login(id, pw);
		if (user == null) {
			throw new NotExistException("아이디 혹은 비밀번호가 일치하지 않습니다.");
		} else {
			return user;
		}
	}
	
	//UsersDAO
	//addUser
	public boolean addUser(UsersDTO.Create user) throws SQLException {
		return usersDAO.addUser(user);
	}
	
	//getUsersAll
	public ArrayList<UsersDTO.Get> getUsersAll() throws NotExistException {
		ArrayList<UsersDTO.Get> all = usersDAO.getUsersAll();
		if(all == null) {
			throw new NotExistException("고객 정보를 찾을 수 없습니다.");
		} else if(all.size() == 0) {
			throw new NotExistException("고객 정보가 존재하지 않습니다.");
		} else {
			return all;
		}
	}
	
	//getUser
	public UsersDTO.Get getUser(String userId) throws NotExistException {
		UsersDTO.Get user = usersDAO.getUser(userId);
		if(user == null) {
			throw new NotExistException("해당 ID 고객 정보가 존재하지 않습니다.");
		} else {
			return user;
		}
	}
	
	//updateUser
	public boolean updateUser(String userId, UsersDTO.Update newUser) throws SQLException {
		return usersDAO.updateUser(userId, newUser);
	}
	
	//deleteUser
	public boolean deleteUser(String userId) throws SQLException {
		return usersDAO.deleteUser(userId);
	}
	
	//ProductDAO
	//addProduct
	public boolean addProduct(Create idx) throws SQLException {
		return productDAO.addProduct(idx);
	}
	
	//getProductAll
	public List<ProductDTO.Get> getProductAll() throws NotExistException {
		List<ProductDTO.Get> all = productDAO.getProductAll();
		if(all == null) {
			throw new NotExistException("제품 정보를 찾을 수 없습니다.");
		} else if(all.size() == 0) {
			throw new NotExistException("제품 정보가 존재하지 않습니다.");
		} else {
			return all;
		}
	}
	
	//getProductByCategory
	public List<ProductDTO.Get> getProductByCategory(String category) throws NotExistException {
		List<ProductDTO.Get> cAll = productDAO.getProductByCategory(category);
		if(cAll == null) {
			throw new NotExistException("해당 카테고리 제품 정보를 찾을 수 없습니다.");
		} else if(cAll.size() == 0) {
			throw new NotExistException("해당 카테고리 제품 정보가 존재하지 않습니다.");
		} else {
			return cAll;
		}
	}
	
	//getProductByName
	public List<ProductDTO.Get> getProductByName(String name) throws NotExistException {
		List<ProductDTO.Get> nAll = productDAO.getProductByName(name);
		if(nAll == null) {
			throw new NotExistException("해당 이름 제품 정보를 찾을 수 없습니다.");
		} else if(nAll.size() == 0) {
			throw new NotExistException("해당 이름 제품 정보가 존재하지 않습니다.");
		}
		return nAll;
	}
	
	//getProductByIdx
	public ProductDTO.Get getProductByIdx(Long idx) throws SQLException {
		return productDAO.getProductByIdx(idx);
	}
	
	//updateProduct
	public boolean updateProduct(Long idx, ProductDTO.Update newProduct) throws SQLException {
		return productDAO.updateProduct(idx, newProduct);
	}
	
	//deletProduct
	public boolean deleteProduct(Long idx) throws SQLException {
		return productDAO.deleteProduct(idx);
	}
	
	//CartDAO
	//addCart
	public boolean addCart(CartDTO.Create cart) throws SQLException {
		return cartDAO.addCart(cart);
	}
	
	//getUserCartAll
	public List<CartDTO.Get> getUserCartAll(UsersDTO.Get user) throws NotExistException {
		List<CartDTO.Get> cart = cartDAO.getUserCartAll(user);
		if (cart == null) {
			throw new NotExistException("장바구니가 비어있습니다");
		} else {
			return cart;
		}
	}

	//deleteCart
	public boolean deleteCart(Long idx) throws SQLException {
		return cartDAO.deleteCart(idx);
	}

	//cartToOrdersAll
	public boolean cartToOrdersAll(List<CartDTO.Get> cart) throws SQLException {
		for(CartDTO.Get c : cart) {
			if (ordersDAO.addOrders(c.getUsers().getId(), c.getProduct().getIdx())) {
				cartDAO.deleteCart(c.getIdx());
			} else {
				return false;
			}
		}
		return true;
	}
	
	//OrdersDAO	
	//addOrders
	public boolean addOrders(String userId, Long idx) throws SQLException {
		return ordersDAO.addOrders(userId, idx);
	}
	
	//addOrdersFromCart
	public boolean addOrdersFromCart(String userId, Long idx, Long cartIdx) throws SQLException {
		boolean result = ordersDAO.addOrders(userId, idx);
		if(result) {
			cartDAO.deleteCart(cartIdx);
		}
		return result;
	}
	
	//getOrdersAll
	public List<OrdersDTO.Get> getOrdersAll() throws SQLException, NotExistException {
		List<OrdersDTO.Get> all = ordersDAO.getOrdersAll();
		if(all == null) {
			throw new NotExistException("주문 정보를 찾을 수 없습니다.");
		} else if(all.size() == 0) {
			throw new NotExistException("주문 정보가 존재하지 않습니다.");
		} else {
			return all;
		}
	}
	
	//getUserOrdersAll
	public List<OrdersDTO.Get> getUserOrdersAll(UsersDTO.Get user) throws NotExistException {
		return ordersDAO.getUserOrdersAll(user);
//		if(all == null) {
//			throw new NotExistException("주문 정보를 찾을 수 없습니다.");
//		} else if(all.size() == 0) {
//			throw new NotExistException("주문 정보가 존재하지 않습니다.");		//이부분은 잘 모르겠음..
//		} else {
//			return all;
//		}
	}
	
	//updateOrders
	public boolean updateOrders(Long idx, Long productIdx) throws SQLException {
		return ordersDAO.updateOrders(idx, productIdx);
	}
	
	//deleteOrders
	public boolean deleteOrders(Long idx) throws SQLException {
		return ordersDAO.deleteOrders(idx);
	}
}
