package model.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name="product_seq", sequenceName="product_seq", initialValue=1, allocationSize=1)
public class Product {
	
	@Id
	@Column(name="product_idx")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="product_seq")
	private Long idx;
	
	@NonNull
	@Column(name="product_name")
	private String name;
	
	@NonNull
	private Integer price;
	
	private String color;
	
	private String psize;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="product")
	private List<Orders> orderList;

	// mappedBy 같아도 되는지 확인
	@OneToMany(fetch=FetchType.LAZY, mappedBy="product")
	private List<Cart> cartList;

	@Override
	public String toString() {
		return "Product [idx=" + idx + ", name=" + name + ", price=" + price + ", color=" + color + ", psize=" + psize
				+ "]";
	}
	
}
