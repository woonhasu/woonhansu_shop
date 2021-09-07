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
public class Controller_hawoon extends HttpServlet {
	
	private static Service service = Service.getInstance();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		
		if(command.equals("login")){
			login(request, response);
		} else if(command.equals("logout")) {
			logout(request, response);
		} else if(command.equals("getProductAll")) {
			getProductAll(request, response);
		}
	}
	
	//	로그인
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		try {
			UsersDTO.LogIn user = service.login(id,pw);
			request.getSession().setAttribute("user", user);
			url = "shop.jsp";
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
			response.sendRedirect("shop.jsp");
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
	}
	
	private void getProductAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			ArrayList<ProductDTO.Get> all = service.getAllProducts();
			request.setAttribute("productAll", all);
			url = "shop.jsp";
		} catch (Exception s) {
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}



//	public static void main(String[] args) {
//		EntityManager em = DBUtil.getEntityManager();
//		
//		System.out.println(em.find(Product.class, 1L));
//		System.out.println(em.find(Cart.class, 1L));
//		System.out.println(em.find(Users.class, "noowah"));
//		System.out.println(em.find(Orders.class, 1L));
//		
//	}

	// 하운
	// 한나
	// 지수
	
	/** 회원가입 >> 지수 O
	 *
	 */
	
	/** 로그인(세션에 데이터 저장 / 관리자 식별)
	 *
	 */
	
	/** 로그아웃(세션에 있는 데이터 삭제)
	 *
	 */
	
	// 고객
	/** 회원 정보 수정 ** request, update 나눠서  >> 지수
	 *
	 */
	
	/** 제품 다중(전체) 조회 >> 지수
	 *
	 */
	
	/** 제품 이름으로 단일 조회 >> 지수
	 *
	 */
	
	public void getCartAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("getCartAll", service.getCartAll());
			url = "cartList.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	/** �옣諛붽뎄�땲�뿉 �젣�뭹 異붽� >> �븯�슫
	 * 
	 */
	public void insertCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			Long idx = (long)Integer.parseInt(request.getParameter("idx"));
			ProductDTO.Get product = service.getProductByIdx(idx);
//			request.setAttribute("insertCart", service.insertCart(request.getSession().getAttribute("user"), product);
			url = "cartList.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);	
	}
	
	/** �옣諛붽뎄�땲 �젣�뭹 �궘�젣  >> �븯�슫
	 * 
	 */
	public void deleteCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
//			request.setAttribute("deleteCart", service.deleteCart(request.getParameter("user")));
			url = "cartList.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	/** 장바구니에 제품 추가 >> 하운O
	 *
	 */
	
	/** 장바구니 제품 삭제  >> 하운
	 *
	 */
	
	/**
	 * 바로 주문하기(장바구니 안타고 바로 주문내역으로 옮기기) >> 하운
	 */
	
	/** 주문(장바구니에서 주문내역으로 옮기기) >> 하운
	 *
	 */
	
	/** 주문내역 조회 >> 한나O
	 *
	 */
	
	/** 주문 취소(주문 내역 삭제) >> 한나O
	 *
	 */
	
	//관리자
	/** 제품 추가 >> 한나
	 *
	 */
	
	/** 제품 수정 ** request, update 나눠서(보류)
	 *
	 */
	
	/** 제품 삭제 >> 한나
	 *
	 */
	
	/** 전체 주문 조회 >> 한나
	 *
	 */
	/** (고객과 동일) 제품 다중(전체) 조회 -> 만들기 X
	 *
	 */
	
	/** (고객과 동일) 제품 단일 조회 -> 만들기 X
	 *
	 */
	
	/** (고객과 동일) 주문 취소 -> 만들기 X
	 *
	 */
}
