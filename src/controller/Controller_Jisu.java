package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.Service;
import model.DTO.UsersDTO;

@WebServlet("/Controller_Jisu")
public class Controller_Jisu extends HttpServlet {
       
	private static Service service = Service.getInstance();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String command = request.getParameter("command");
		
		//command 값에 따라서 매핑
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
	public void userUpdateReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	public void userUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	
	/** 제품 다중(전체) 조회 >> 지수
	 * 
	 */
	public void productAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		
		try {
			request.setAttribute("productAll", service.getAllProducts());
			url = "productAllList.jsp";
		}catch(Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			//e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	/** 제품 이름으로 단일 조회 >> 지수
	 * 
	 */
	public void productName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		
		try {
			request.setAttribute("product", service.getProduct(request.getParameter("productName")));
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
	public void productCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		
		try {
			request.setAttribute("productCategory", service.getCategoryProduct(request.getParameter("category")));
		}catch(Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			//e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
