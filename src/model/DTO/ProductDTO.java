package model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.domain.Product;

public class ProductDTO {
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Create {
		private String category;
		private String name;
		private Integer price;
		private String color;
		private String psize;
		
		public Product toEntity() {
			return Product.builder().name(name).category(category).price(price).color(color).psize(psize).build();
		}
    }
	
	@Data
	public static class Update extends Delete {
		private String name;
		private Integer price;
		private String color;
    }
	
	@Data
	public static class Delete {
		private Long idx;
    }
	
	@Data
	public static class Get extends Create {
		private Long idx;
	}

}
