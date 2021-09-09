package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAO.Service;
import model.DTO.CartDTO;
import model.DTO.ProductDTO;
import model.DTO.UsersDTO;

@WebServlet("/controller")
public class Controller extends HttpServlet {

	private static Service service = Service.getInstance();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String command = request.getParameter("command");
		
		//login,logout,register
		if (command.equals("login")) {
			login(request, response);
		} else if (command.equals("logout")) {
			logout(request, response);
		} else if (command.equals("register")) {
			addUser(request, response);
		
		//userDAO
		} else if (command.equals("getUsersList")) {
			getUsersList(request, response);
		} else if (command.equals("updateUser")) {
			updateUser(request, response);
		} else if (command.equals("deleteUser")) {
			deleteUser(request, response);
			
		//productDAO
		} else if (command.equals("addProduct")) {	
			addProduct(request, response);
		} else if (command.equals("getProductAll")) {
			getProductAll(request, response);
		} else if (command.equals("getProductList")) { //user랑 admin 나누기 전 > 지수 수정
			getProductList(request, response);
			//-----------------------------
		} else if (command.equals("category")) {
			getProductByCategory(request, response);
		} else if (command.equals("search")) {
			getProductByName(request, response);
		} else if (command.equals("updateProduct")) {
			updateProduct(request, response);
		} else if (command.equals("deleteProduct")) {
			deleteProduct(request, response);
			
		//cartDAO
		} else if (command.equals("addCart")) {
			addCart(request, response);
		} else if (command.equals("getUserCartAll")) {
			getUserCartAll(request, response);
		} else if (command.equals("deleteCart")) {
			deleteCart(request, response);
		} else if (command.equals("cartToOrdersAll")) {  // 이따 같이 확인 
			cartToOrdersAll(request, response);
		
		//ordersDAO
		} else if (command.equals("addOrders")) {
			addOrders(request, response);
		} else if (command.equals("addOrdersFromCart")) {
			addOrdersFromCart(request, response);
		} else if (command.equals("getOrdersList")) {  // admin user 구분해서 합치기
			getOrdersList(request, response);
		} else if (command.equals("getUserOrdersAll")) {
			getUserOrdersAll(request, response);
		} else if (command.equals("updateOrders")) {
			updateOrders(request, response);
		} else if (command.equals("deleteOrders")) {
			deleteOrders(request, response);
		} 
		
		else {
			System.out.println("잘못된 command 접근입니다.");
		}
			
	}

	//login
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		try {
			UsersDTO.Get user = service.login(id, pw);
			request.getSession().setAttribute("user", user);
			url = "controller?command=getProductAll";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	//logout
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getSession().removeAttribute("user");
			request.getSession().invalidate();
			getProductAll(request, response);
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
	}
	
	//register
	public void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		Integer admin = Integer.parseInt(request.getParameter("admin"));
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");

		UsersDTO.Create user = new UsersDTO.Create(id, pw, admin, name, address, phone);
		try {
			boolean result = service.addUser(user);
			if (result) {
				request.getSession().setAttribute("user", user);
				url = "controller?command=login";
			} else {
				request.setAttribute("errorMsg", "회원 가입 실패");
			}
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	//getUsersList
	public void getUsersList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("UsersAll", service.getUsersAll());
			url = "manageUsers.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	//updateUser
	public void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";

		HttpSession session = request.getSession();
		UsersDTO.Get user = (UsersDTO.Get) session.getAttribute("user");
		Integer admin = user.getAdmin();

		String pw = request.getParameter("pw");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		UsersDTO.Update newUser = new UsersDTO.Update(pw, address, phone);
		
		try {
			if(admin == 0) {
				boolean result = service.updateUser(user.getId(), newUser);
				if(result) {
					request.getSession().setAttribute("user", service.getUser(user.getId()));
					request.setAttribute("successMsg", "수정 성공");
					url = "myPage.jsp";
				}else {
					request.setAttribute("errorMsg", "수정 실패");
				}
			}else if(admin == 1) {
				String id = request.getParameter("id");
				boolean result = service.updateUser(id, newUser);
				if(result) {
					url = "controller?command=getUsersList";
				}else {
					request.setAttribute("errorMsg", "수정 실패");
				}
			}else {
				request.setAttribute("errorMsg", "계정 정보 에러, 로그인을 해주세요.");
			}
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			// e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	//deleteUser
	public void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		
		HttpSession session = request.getSession();
		UsersDTO.Get user = (UsersDTO.Get) session.getAttribute("user");
		Integer admin = user.getAdmin();
		
		String id = (String)request.getParameter("id");
		
		try {
			if(admin == 0) {
				boolean result = service.deleteUser(user.getId());
				if(result) {
					url = "controller?command=logout";
				}else {
					request.setAttribute("errorMsg", "회원 탈퇴 실패");
				}
			}else if(admin == 1) {
				boolean result = service.deleteUser(id);
				if(result) {
					url = "controller?command=getUsersList";
				}else {
					request.setAttribute("errorMsg", "회원 삭제 실패");
				}
			}else {
				request.setAttribute("errorMsg", "계정 정보 에러, 로그인을 해주세요.");
			}
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	//addProduct
	public void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";

		String category = request.getParameter("category");
		String name = request.getParameter("name");
		Integer price = Integer.parseInt(request.getParameter("price"));
		String color = request.getParameter("color");
		String psize = request.getParameter("psize");

		ProductDTO.Create user = new ProductDTO.Create(category, name, price, color, psize);
		
		try {
			boolean result = service.addProduct(user);
			if (result) {
				url = "controller?command=getProductList";
			} else {
				request.setAttribute("errorMsg", "제품 추가 실패");
			}
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	//getProductAll
	private void getProductAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			List<ProductDTO.Get> all = service.getProductAll();

			request.setAttribute("productAll", all);
			url = "shop.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	//getProductList
	private void getProductList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("productAll", service.getProductAll());
			url = "manageProduct.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	//getProductByCategory
	public void getProductByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "productCategory.jsp";
		String category = request.getParameter("category");
		
		try {
			ArrayList<ProductDTO.Get> all = service.getProductAll();
			request.setAttribute("productAll", all);
			request.setAttribute("productCategory", service.getProductByCategory(category));
		}catch(Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			// e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	//getProductByName
	public void getProductByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "productName.jsp";
		String name = request.getParameter("name");

		try {
			ArrayList<ProductDTO.Get> all = service.getProductAll();
			request.setAttribute("productAll", all);
			request.setAttribute("productName", service.getProductByName(name));
		}catch(Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			// e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	//updateProduct
	public void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";

		String category = request.getParameter("category");
		String name = request.getParameter("name");
		Integer price = Integer.parseInt(request.getParameter("price"));
		String color = request.getParameter("color");
		String psize = request.getParameter("psize");
		
		Long idx = Long.parseLong(request.getParameter("idx"));

		ProductDTO.Update newProduct = new ProductDTO.Update(category, name, price, color, psize);

		try {
			boolean result = service.updateProduct(idx, newProduct);
			if (result) {
				url = "controller?command=getProductList";
			} else {
				request.setAttribute("errorMsg", "수정 실패");
			}
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			// e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	//deleteProduct
	public void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		Long idx = Long.parseLong(request.getParameter("idx"));

		try {
			boolean result = service.deleteProduct(idx);
			if(result) {
				url = "controller?command=getProductList";				
			} else {
				request.setAttribute("errorMsg", "제품 삭제 실패");
			}
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	//addCart
	public void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			Long idx = (long) Integer.parseInt(request.getParameter("idx"));
			UsersDTO.Get user = (UsersDTO.Get) request.getSession().getAttribute("user");
			if (user != null) {
				service.addCart(user.getId(), idx);
				getUserCartAll(request, response);
			} else {
				request.setAttribute("errorMsg", "로그인을 먼저 부탁드립니다");
			}
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	//getUserCartAll
	public void getUserCartAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "cart.jsp";

		try {
			UsersDTO.Get user = (UsersDTO.Get) request.getSession().getAttribute("user");
			System.out.println(user);
			request.setAttribute("cartAll", service.getUserCartAll(user));
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	// deleteCart
	public void deleteCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			Long idx = (long) Integer.parseInt(request.getParameter("idx"));
			service.deleteCart(idx);
			getUserCartAll(request, response);
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	//cartToOrdersAll
	private void cartToOrdersAll(HttpServletRequest request, HttpServletResponse response) {
		String url = "showError.jsp";
		try {
			UsersDTO.Get user = (UsersDTO.Get) request.getSession().getAttribute("user");
			List<CartDTO.Get> cart = service.getUserCartAll(user);
			service.cartToOrdersAll(cart);
			getUserCartAll(request, response);
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
	}

	//addOrders
	private void addOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			Long idx = Long.parseLong(request.getParameter("idx"));
			UsersDTO.Get user = (UsersDTO.Get) request.getSession().getAttribute("user");

			if (user != null) {
				service.addOrders(user.getId(), idx);
			} else {
				request.setAttribute("errorMsg", "로그인을 먼저 부탁드립니다");
			}
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		response.sendRedirect("controller?command=getUserOrdersAll");
	}

	//addOrdersFromCart
	private void addOrdersFromCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			Long idx = Long.parseLong(request.getParameter("idx"));
			UsersDTO.Get user = (UsersDTO.Get) request.getSession().getAttribute("user");

			Long cartIdx = Long.parseLong(request.getParameter("cart"));

			if (user != null) {
				service.addOrdersFromCart(user.getId(), idx, cartIdx);
				getUserOrdersAll(request, response);
			} else {
				request.setAttribute("errorMsg", "로그인을 먼저 부탁드립니다");
			}
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
	}

	//getOrdersList
	public void getOrdersList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("OrdersAll", service.getOrdersAll());
			url = "manageOrders.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	//getUserOrdersAll
	public void getUserOrdersAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "orders.jsp";
		
		try {
			UsersDTO.Get user = (UsersDTO.Get) request.getSession().getAttribute("user");
			request.setAttribute("OrdersAll", service.getUserOrdersAll(user));
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	//updateOrders
	public void updateOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		
		Long idx = Long.parseLong(request.getParameter("idx"));
		Long productIdx = Long.parseLong(request.getParameter("productIdx"));
		
		try {
			boolean result = service.updateOrders(idx, productIdx);
			if (result) {
				url = "controller?command=getOrdersList";
			} else {
				request.setAttribute("errorMsg", "수정 실패");
			}
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			// e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	// deleteOrders
	public void deleteOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";

		HttpSession session = request.getSession();
		UsersDTO.Get user = (UsersDTO.Get) session.getAttribute("user");
		Integer admin = user.getAdmin();
		
		Long idx = Long.parseLong(request.getParameter("idx"));
		
		try {
			if(admin == 0) {
				boolean result = service.deleteOrders(idx);
				if(result) {
					url = "controller?command=getUserOrdersAll";
				}else {
					request.setAttribute("errorMsg", "주문 삭제 실패");
				}
			}else if(admin == 1) {
				boolean result = service.deleteOrders(idx);
				if(result) {
					url = "controller?command=getOrdersList";
				}else {
					request.setAttribute("errorMsg", "주문 삭제 실패");
				}
			}else {
				request.setAttribute("errorMsg", "계정 정보 에러, 로그인을 해주세요.");
			}
			
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
