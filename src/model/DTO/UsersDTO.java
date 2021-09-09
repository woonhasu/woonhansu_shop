package model.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import model.domain.Orders;
import model.domain.Users;

public class UsersDTO {
	
	@Data
	@AllArgsConstructor
	public static class Create {
		private String id;
		private String pw;
		private Integer admin;
		private String name;
		private String address;
		private String phone;
		
		public Users toEntity() {
			return Users.builder().id(id).pw(pw).admin(admin).name(name).address(address).phone(phone).build();
		}
    }
	
	@Data
	@AllArgsConstructor
	public static class Update {
		private String pw;
		private String address;
		private String phone;
    }
	
	@Data
	public static class Delete {
		private String id;
    }
	
	@Data
	@AllArgsConstructor
	public static class Get {
		private String id;
		private String pw;
		private Integer admin;
		private String name;
		private String address;
		private String phone;
	}
	
	@Data
	public static class LogIn {
		private String id;
		private String pw;
    }
	
	@Data
	public static class Order {
		private String id;
		private List<Orders> orderList;
    }
	
	@Data
	public static class Cart {
		private String id;
		private List<Cart> cartList;
    }

}
