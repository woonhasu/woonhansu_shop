package model.DTO;

import lombok.Data;

public class ProductDTO {
	
	@Data
	public static class Create {
		private String category;
		private String name;
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
	
	@Data
	public static class Get extends Create {
		private Long idx;
	}

}
