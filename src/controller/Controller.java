package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAO.Service;
import model.DTO.ProductDTO;
import model.DTO.UsersDTO;

@WebServlet("/controller")
public class Controller extends HttpServlet {
	
	private static Service service = Service.getInstance();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String command = request.getParameter("command");
		
		if(command.equals("login")){
			login(request, response);
		} else if(command.equals("logout")) {
			logout(request, response);
		} else if(command.equals("getProductAll")) {
			getProductAll(request, response);
		} else if(command.equals("getUserCartAll")) {
			getUserCartAll(request, response);
		} else if(command.equals("addOrders")) {
			addOrders(request, response);
		} else if(command.equals("deleteCart")) {
			deleteCart(request, response);
		} else if(command.equals("register")) {
			addUser(request, response);
		} else if(command.equals("userUpdate")) {
			updateUser(request, response);
		} else if(command.equals("userDelete")) {
			deleteUser(request, response);
		} else if(command.equals("addCart")) {
			addCart(request, response);
		} else if(command.equals("getOrdersAll")) {
			getOrdersAll(request, response);
		} else if(command.equals("category")) {
			getProductByCategory(request, response);
		} else if(command.equals("search")) {
			getProductByName(request, response);
		} else if(command.equals("getUserOrdersAll")) {
			getUserOrdersAll(request, response);
		} else if(command.equals("addOrdersFromCart")) {
			addOrdersFromCart(request, response);
		} else if(command.equals("addOrders")) {
			addOrders(request, response);
		} else if(command.equals("deleteOrders")) {
			deleteOrders(request, response);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



































	//	로그인
	// 로그인 실패 메세지 출력 하도록 수정 필요ㅠ
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		try {
			UsersDTO.Get user = service.login(id,pw);
			request.getSession().setAttribute("user", user);
			url = "controller?command=getProductAll";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	// 로그아웃
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
	
	// 전체제품조회
	private void getProductAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			ArrayList<ProductDTO.Get> all = service.getProductAll();
			request.setAttribute("productAll", all);
			url = "shop.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	// 전체 장바구니조회 (유저 장바구니 조회로 바꿔야 함)
	public void getUserCartAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			UsersDTO.Get user = (UsersDTO.Get) request.getSession().getAttribute("user");
			System.out.println(user);
			request.setAttribute("cartAll", service.getUserCartAll(user));
			url = "cart.jsp";
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	// 장바구니 추가 (완)
	public void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			Long idx = (long)Integer.parseInt(request.getParameter("idx"));
			UsersDTO.Get user = (UsersDTO.Get) request.getSession().getAttribute("user");
			if(user != null) {
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
	
	// 장바구니 삭제 (수정 중)
	public void deleteCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			Long idx = (long)Integer.parseInt(request.getParameter("idx"));
			service.deleteCart(idx);
			getUserCartAll(request, response);
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/** 회원가입 >> 지수
	 * 
	 */
	public void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		Integer admin = Integer.parseInt(request.getParameter("admin"));
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		//null 값 체크는 front에서 required로 체크
		//required로 공백도 체크되는지 확인 필요
		
		UsersDTO.Create user = new UsersDTO.Create(id, pw, admin, name, address, phone);
		try {
			boolean result = service.addUser(user);
			if(result) {
				request.getSession().setAttribute("user", user);
				url = "controller?command=login";
			}else {
				request.setAttribute("errorMsg", "회원 가입 실패");
			}
		}catch(Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	/** 회원 정보 수정 - 상세 정보 확인  >> 지수
	 * 
	 */
	public void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		
		String pw = request.getParameter("pw");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		UsersDTO.Update newUser = new UsersDTO.Update(pw, address, phone);
		
		try {
			HttpSession session = request.getSession();
			UsersDTO.Get user = (UsersDTO.Get) session.getAttribute("user");
			boolean result = service.updateUser(user.getId(), newUser);
			if(result) {
				request.getSession().setAttribute("user", service.getUser(user.getId()));
				url = "myPage.jsp";
				request.setAttribute("successMsg", "수정 완료");
			}else {
				request.setAttribute("errorMsg", "수정 실패");
			}
		}catch(Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			//e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	/** 회원 탈퇴 >> 지수
	 * 
	 */
	public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		
		try {
			HttpSession session = request.getSession();
			UsersDTO.Get user = (UsersDTO.Get) session.getAttribute("user");
			boolean result = service.deleteUser(user.getId());
			if(result) {
				url = "controller?command=logout";
			}else {
				request.setAttribute("errorMsg", "삭제 실패");
			}
		}catch(Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	/** 제품 이름으로 단일 조회 >> 지수
	 * 
	 */
	public void getProductByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		String name = request.getParameter("name");
		
		try {
			ArrayList<ProductDTO.Get> all = service.getProductAll();
			request.setAttribute("productAll", all);
			request.setAttribute("productName", service.getProductByName(name));
			url = "productName.jsp";
		}catch(Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			//e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	/** 제품 카테고리별로 조회 >> 지수
	 * 
	 */
	public void getProductByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		String category = request.getParameter("category");
		

		try {
			ArrayList<ProductDTO.Get> all = service.getProductAll();
			request.setAttribute("productAll", all);
			request.setAttribute("productCategory", service.getProductByCategory(category));
			url = "productCategory.jsp";
		}catch(Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			//e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 전체 주문조회 (유저 주문 조회로 바꿔야 함)
		public void getOrdersAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String url = "showError.jsp";
			try {
				request.setAttribute("OrdersAll", service.getOrdersAll());
				url = "orders.jsp";
			} catch (Exception s) {
				request.setAttribute("errorMsg", s.getMessage());
				s.printStackTrace();
			}
			request.getRequestDispatcher(url).forward(request, response);
		}
		
		public void getUserOrdersAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String url = "showError.jsp";
			try {
				UsersDTO.Get user = (UsersDTO.Get) request.getSession().getAttribute("user");
				request.setAttribute("OrdersAll", service.getUserOrdersAll(user));
				url = "orders.jsp";
			} catch (Exception s) {
				request.setAttribute("errorMsg", s.getMessage());
				s.printStackTrace();
			}
			request.getRequestDispatcher(url).forward(request, response);
		}
		
		private void addOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String url = "showError.jsp";
			try {
				Long idx = Long.parseLong(request.getParameter("idx"));
				UsersDTO.Get user = (UsersDTO.Get) request.getSession().getAttribute("user");
				
				if(user != null) {
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
		
		private void addOrdersFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String url = "showError.jsp";
			try {
				Long idx = Long.parseLong(request.getParameter("idx"));
				UsersDTO.Get user = (UsersDTO.Get) request.getSession().getAttribute("user");
				
				Long cartIdx = Long.parseLong(request.getParameter("cart"));
				
				if(user != null) {
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
		
		// 주문 삭제
		public void deleteOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String url = "showError.jsp";
			try {
				Long idx = Long.parseLong(request.getParameter("ordersIdx"));
				service.deleteOrders(idx);
				getUserOrdersAll(request, response);
			} catch (Exception s) {
				request.setAttribute("errorMsg", s.getMessage());
				s.printStackTrace();
			}
			request.getRequestDispatcher(url).forward(request, response);
		}
		
}
