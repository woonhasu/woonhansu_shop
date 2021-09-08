package model.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.domain.Product;
import model.domain.Users;

public class OrdersDTO {
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Create {
		private Users users;
		private Product product;
		private Date date;
    }
	
	@Data
	public static class Delete {
		private Long idx;
    }

	
	public static void main(String [] args) {
		OrdersDTO.Create a = new OrdersDTO.Create();
		a.setUsers(new Users("1", "pw", 2, "name", "address", "phone", null, null));
		System.out.println(a.getUsers().getId());
		
	}
}
