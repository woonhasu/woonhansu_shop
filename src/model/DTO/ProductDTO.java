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
			return Product.builder().category(category).name(name).price(price).color(color).psize(psize).build();
		}
    }
	
	@Data
	@AllArgsConstructor
	public static class Update {
		private String category;
		private String name;
		private Integer price;
		private String color;
		private String psize;
    }
	
	@Data
	public static class Get {
		private Long idx;
		private String category;
		private String name;
		private Integer price;
		private String color;
		private String psize;
	}

}
