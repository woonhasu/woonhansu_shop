package model.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
	
	@Id
	private String id;
	
	private String pw;
	
	private Integer admin;
	
	private String name;
	
	private String address;
	
	private String phone;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="users")
	private List<Orders> orderList;
	
	// mappedBy 같아도 되는지 확인
	@OneToMany(fetch=FetchType.LAZY, mappedBy="users")
	private List<Cart> cartList;

	@Override
	public String toString() {
		return "Users [id=" + id + ", pw=" + pw + ", admin=" + admin + ", name=" + name + ", address=" + address
				+ ", phone=" + phone + "]";
	}
	
	
}
