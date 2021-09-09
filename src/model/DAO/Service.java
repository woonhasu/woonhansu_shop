package model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.NotExistException;
import model.DTO.CartDTO;
import model.DTO.OrdersDTO;
import model.DTO.ProductDTO;
import model.DTO.ProductDTO.Create;
import model.DTO.ProductDTO.Get;
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
	
	
	/** 회원가입 >> 지수
	 * 
	 */
	public static boolean addUser(UsersDTO.Create user) {
		return usersDAO.addUser(user);
	}
	
	/** 회원 정보 단일 조회 >> 지수
	 * @throws NotExistException 
	 * 
	 */
	public static UsersDTO.Get getUser(String userId) throws NotExistException {
		UsersDTO.Get user = usersDAO.getUser(userId);
		if(user == null) {
			throw new NotExistException("해당 ID 고객 정보가 존재하지 않습니다.");
		}
		
		return user;
	}
	
	/** 회원 정보 수정 ** request, update 나눠서  >> 지수
	 * 
	 */
	public static boolean updateUser(String userId, UsersDTO.Update newUser) {
		return usersDAO.updateUser(userId, newUser);
	}
	
	/** 회원 탈퇴 >> 지수
	 * 
	 */
	public static boolean deleteUser(String userId) {
		return usersDAO.deleteUser(userId);
	}
	
	/** 제품 다중(전체) 조회 >> 지수
	 * 
	 */
	public static ArrayList<ProductDTO.Get> getProductAll() throws NotExistException {
		ArrayList<ProductDTO.Get> all = productDAO.getProductAll();
		if(all == null) {
			throw new NotExistException("제품 정보를 찾을 수 없습니다.");
		}
		if(all.size() == 0) {
			throw new NotExistException("제품 정보가 존재하지 않습니다.");
		}
		return all;
	}
	
	/** 제품 이름으로 다중 조회 >> 지수
	 * 
	 */
	public static ArrayList<ProductDTO.Get> getProductByName(String name) throws NotExistException {
		ArrayList<ProductDTO.Get> nAll = productDAO.getProductByName(name);
		if(nAll == null) {
			throw new NotExistException("해당 이름 제품 정보를 찾을 수 없습니다.");
		}
		if(nAll.size() == 0) {
			throw new NotExistException("해당 이름 제품 정보가 존재하지 않습니다.");
		}
		
		return nAll;
	}
	
	/** 제품 카테고리별로 조회 >> 지수
	 * 
	 */
	public static ArrayList<ProductDTO.Get> getProductByCategory(String category) throws NotExistException {
		ArrayList<ProductDTO.Get> cAll = productDAO.getProductByCategory(category);
		if(cAll == null) {
			throw new NotExistException("해당 카테고리 제품 정보를 찾을 수 없습니다.");
		}
		if(cAll.size() == 0) {
			throw new NotExistException("해당 카테고리 제품 정보가 존재하지 않습니다.");
		}
		
		return cAll;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		// Cart CRUD
		public static List<CartDTO.Get> getUserCartAll(UsersDTO.Get user) throws NotExistException {
			List<CartDTO.Get> cart = cartDAO.getUserCartAll(user);
			if(cart == null) {
				throw new NotExistException("장바구니가 비어있습니다");
			} else {
				return cart;
			}
		}
		
		public static boolean addCart(String userId, Long productIdx) {
			return cartDAO.addCart(userId, productIdx);
		}
		
		public static boolean deleteCart(Long idx) {
			return cartDAO.deleteCart(idx);
		}
				
		// idx 로 product 가져오기
		public ProductDTO.Get getProductByIdx(Long idx) {
			return productDAO.getProductByIdx(idx);
		}

		public UsersDTO.Get login(String id, String pw) throws NotExistException {
			UsersDTO.Get user = usersDAO.login(id, pw);
			if(user == null) {
				throw new NotExistException("아이디 혹은 비밀번호가 일치하지 않습니다.");
			} else {
				return user;
			}
		}
		
		//	주문 추가
		public static boolean addOrdersFromCart(String userId, Long idx, Long cartIdx) throws SQLException {
			boolean result = ordersDAO.addOrders(userId, idx);
			if(result) {
				cartDAO.deleteCart(cartIdx);
			}
			return result;
		}
		
		//	주문 추가
		public static boolean addOrders(String userId, Long idx) throws SQLException {
			return ordersDAO.addOrders(userId, idx);
		}
	
		//	전체 장바구니 주문
		public static boolean cartToOrdersAll(List<CartDTO.Get> cart) throws SQLException {
			for(CartDTO.Get c : cart) {
				if(ordersDAO.addOrders(c.getUsers().getId(), c.getProduct().getIdx())) {
					cartDAO.deleteCart(c.getIdx());
				} else {
					return false;
				}
			}
			return true;
		}
	
	
	

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//	주문 조회
		public static List<OrdersDTO.Get> getOrdersAll() throws SQLException, NotExistException {
			List<OrdersDTO.Get> all = ordersDAO.getOrdersAll();
			if(all == null) {
				throw new NotExistException("주문 정보를 찾을 수 없습니다.");
			}
			if(all.size() == 0) {
				throw new NotExistException("주문 정보가 존재하지 않습니다.");
			} else {
				return all;
			}
		}
		
		public List<OrdersDTO.Get> getUserOrdersAll(UsersDTO.Get user) throws NotExistException {
			List<OrdersDTO.Get> all = ordersDAO.getUserOrdersAll(user);
			if(all == null) {
				throw new NotExistException("주문 정보를 찾을 수 없습니다.");
			}
			if(all.size() == 0) {
				throw new NotExistException("주문 정보가 존재하지 않습니다.");		//이부분은 잘 모르겠음..
			} else {
				return all;
			}
		}
		
		
		
		//	주문 삭제-------------------------------------
		public static boolean deleteOrders(Long idx) throws SQLException {
			return ordersDAO.deleteOrders(idx);
		}
		
		// 확인 해봐야함. 
		// 제품 추가
		public static boolean addProduct(Create idx) throws SQLException {
			return productDAO.addProduct(idx);
		}
		
		//제품 삭제
		public static boolean deleteProduct(Long idx) throws SQLException {
			return productDAO.deleteProduct(idx);
		}
		
		//제품 수정
		public static boolean updateProduct(Long idx, ProductDTO.Update newProduct) {
			return productDAO.updateProduct(idx, newProduct);
		}

		public List<String> getCategory(List<ProductDTO.Get> all) {
			List<String> category = new ArrayList<String>();;
			return category;
		}

	



}
