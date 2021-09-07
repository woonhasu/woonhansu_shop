package controller;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import model.DAO.OrdersDAO2;
import model.DAO.ProductDAO2;
import model.DTO.OrdersDTO;
import model.DTO.ProductDTO;
import model.domain.Cart;
import model.domain.Orders;
import model.domain.Product;
import model.domain.Users;
import util.DBUtil;

public class Controller {
	//하운
	//한나
	//지수
	
	/** 회원가입 >> 지수
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
	
	/** 제품 카테고리별로 조회 >> 지수
	 * 
	 */
	
	/** 옵션) 가격별 조회
	 * 
	 */
	
	/** 장바구니 조회 >> 하운
	 * 
	 */
	
	/** 장바구니에 제품 추가 >> 하운
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
	
	/** 주문내역 조회 >> 한나
	 * 
	 */
	
	/** 주문 취소(주문 내역 삭제) >> 한나
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
