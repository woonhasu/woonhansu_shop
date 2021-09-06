package model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name="orders_seq", sequenceName="orders_seq", initialValue=1, allocationSize=1)
public class Orders {
	
	@Id
	@Column(name="order_idx")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="orders_seq")
	private Long idx;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private Users users;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_idx")
	private Product product;
	
	@Column(name="order_date")
	private Date date;

	@Override
	public String toString() {
		return "Orders [idx=" + idx + ", userId=" + users.getId() + ", productIdx=" + product.getIdx() + ", date=" + date + "]";
	}

}
