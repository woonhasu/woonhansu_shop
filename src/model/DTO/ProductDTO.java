package model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ProductDTO {
	
	@Data
	@AllArgsConstructor
	public static class Create {
		private String name;
		private String category;
		private Integer price;
		private String color;
		private String psize;
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

}
