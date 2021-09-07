package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.Service;
import model.DTO.ProductDTO;

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
