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
		} else if(command.equals("getCartAll")) {
			getCartAll(request, response);
		} else if(command.equals("addOrder")) {
			//아직 없다!!
		}
	}
	
	//	로그인
//	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String url = "showError.jsp";
//		String id = request.getParameter("id");
//		String pw = request.getParameter("pw");
//		
//		try {
//			UsersDTO.LogIn user = service.login(id,pw);
//			request.getSession().setAttribute("user", user);
//			url = "shop.jsp";
//		} catch (Exception s) {
//			request.setAttribute("errorMsg", s.getMessage());
//			s.printStackTrace();
//		}
//		request.getRequestDispatcher(url).forward(request, response);
//	}
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
			getProductAll(request, response);
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
	}
	
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
	
	/** �옣諛붽뎄�땲�뿉 �젣�뭹 異붽� >> �븯�슫
	 * 
	 */
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
	
	/** �옣諛붽뎄�땲 �젣�뭹 �궘�젣  >> �븯�슫
	 * 
	 */
	public void deleteCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
//			request.setAttribute("deleteCart", service.deleteCart(request.getParameter("user")));
			url = "cart.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
}
