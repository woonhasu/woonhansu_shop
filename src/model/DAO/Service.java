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
import model.domain.Product;
import model.domain.Users;

public class Service {
	
	private static Service instance = new Service();
	
	private Service() {}
	
	public static Service getInstance() {
		return instance;
	}
	
	private static UsersDAO usersDAO = UsersDAO.getInstance();
	private static ProductDAO productDAO = ProductDAO.getInstance();
	private static CartDAO cartDAO = CartDAO.getInstance();
	
	
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
	
//	public static void main(String[] args) throws NotExistException {
////		UsersDTO.Create user = new UsersDTO.Create("jisu20", "pw", 0, "배지수", "집", "010");
////		System.out.println(addUser(user));
//		
//		System.out.println(getAllProducts());
//		
//		System.out.println(getProduct("조거팬츠"));
//		
//		System.out.println(getCategoryProduct("상의"));
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
		
		// idx 로 product 가져오기
		public ProductDTO.Get getProductByIdx(Long idx) {
			return productDAO.getProductByIdx(idx);
		}

		public UsersDTO.Get login(String id, String pw) throws NotExistException {
			UsersDTO.Get user = usersDAO.login(id, pw);
			if(user == null) {
				throw new NotExistException("해당 카테고리 제품 정보를 찾을 수 없습니다.");
			} else {
				return user;
			}
		}
	
	
	
	

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//주문 조회
		public static List<OrdersDTO> getAllOrders() throws SQLException {
			List<OrdersDTO> all = OrdersDAO.getAllOrders();
			if(all == null) {
				System.out.println("주문 정보를 찾을 수 없습니다.");
//				throw new NotExistException("제품 정보를 찾을 수 없습니다.");
			}
			if(all.size() == 0) {
				System.out.println("주문 정보가 존재하지 않습니다.");
//				throw new NotExistException("제품 정보가 존재하지 않습니다.");		//이부분은 잘 모르겠음..
			}
			return all;
		}
		
		
		//주문 삭제
		public static boolean deleteOrders(Long idx) throws SQLException {
		return OrdersDAO.deleteOrders(idx);
		}
		
		// 확인 해봐야함. 
		// 제품 추가
		public static boolean addProduct(Create idx) throws SQLException {
			return ProductDAO.addProduct(idx);
		}
		
		//제품 삭제
		public static boolean deleteProduct(Long idx) throws SQLException {
			return ProductDAO.deleteProduct(idx);
		}
		
		//제품 수정
		public static boolean updateProduct(Long idx, ProductDTO.Update newProduct) {
			return productDAO.updateProduct(idx, newProduct);
		}
		
}
