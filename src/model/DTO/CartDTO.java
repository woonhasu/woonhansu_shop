package model.DTO;

import lombok.Data;
import model.domain.Product;
import model.domain.Users;

public class CartDTO {

	@Data
	public static class Create {
		private Users users;
		private Product product;
    }
	
	@Data
	public static class Delete {
		private Long idx;
    }
}
