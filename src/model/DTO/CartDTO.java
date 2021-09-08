package model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import model.DTO.UsersDTO.Create;
import model.domain.Product;
import model.domain.Users;

public class CartDTO {
	
	@Data
	@AllArgsConstructor
	public static class Get {
		private Long idx;
		private Users users;
		private Product product;
    }
  
}
