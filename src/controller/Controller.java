package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.Service;
import model.DTO.ProductDTO;
import model.DTO.UsersDTO;

@WebServlet("/controller")
public class Controller extends HttpServlet {
	
	private static Service service = Service.getInstance();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		
		if(command.equals("login")){
			login(request, response);
		} else if(command.equals("logout")) {
			logout(request, response);
		} else if(command.equals("getProductAll")) {
			getProductAll(request, response);
		} else if(command.equals("getCartAll")) {
			getCartAll(request, response);
		} else if(command.equals("addOrder")) {
			//아직 없다!!
		} else if(command.equals("deleteCart")) {
			deleteCart(request, response);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	//	로그인
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
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
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
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	// 전체 장바구니조회 (유저 장바구니 조회로 바꿔야 함)
	public void getCartAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("cartAll", service.getCartAll());
			url = "cart.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	// 장바구니 추가 (미완)
	public void insertCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			Long idx = (long)Integer.parseInt(request.getParameter("idx"));
			ProductDTO.Get product = service.getProductByIdx(idx);
//			request.setAttribute("insertCart", service.insertCart(request.getSession().getAttribute("user"), product);
			url = "cart.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);	
	}
	
	// 장바구니 삭제 (수정 중)
	public void deleteCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			Long idx = (long)Integer.parseInt(request.getParameter("idx"));
			request.setAttribute("deleteCart", service.deleteCart(idx));
			url = "cart.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
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
				request.setAttribute("user", user);
				request.setAttribute("successMsg", "회원 가입 완료");
				url = "users/usersDetail.jsp";
			}else {
				request.setAttribute("errorMsg", "회원 가입 실패");
			}
		}catch(Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	/** 회원 정보 수정 요청  >> 지수
	 * 
	 */
	public void updateUserReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		
		try {
			request.setAttribute("user", service.getUser(request.getParameter("userId")));
			url = "users/usersUpdate.jsp";
		}catch(Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			//e.printStackTrace();
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
			boolean result = service.updateUser(request.getParameter("userId"), newUser);
			if(result) {
				request.setAttribute("user", service.getUser(request.getParameter("userId")));
				url = "users/usersDetail.jsp";
			}else {
				request.setAttribute("errorMsg", "수정 실패");
			}
		}catch(Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			//e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	/** 제품 이름으로 단일 조회 >> 지수
	 * 
	 */
	public void getProductByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		
		try {
			request.setAttribute("product", service.getProductByName(request.getParameter("productName")));
			url = "product/productDetail.jsp";
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
		
		try {
			request.setAttribute("productCategory", service.getProductByCategory(request.getParameter("category")));
		}catch(Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			//e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
}
