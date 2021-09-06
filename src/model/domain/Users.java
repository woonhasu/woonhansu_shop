package model.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Users {
	
	@Id
	private String id;
	
	@NonNull
	private String pw;
	
	@NonNull
	private String name;
	
	@NonNull
	private String address;
	
	@NonNull
	private String phone;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="users")
	private List<Orders> orderList;
	
	// mappedBy 같아도 되는지 확인
	@OneToMany(fetch=FetchType.LAZY, mappedBy="users")
	private List<Cart> cartList;
	
	@Override
	public String toString() {
		return "Users [id=" + id + ", pw=" + pw + ", name=" + name + ", address=" + address + ", phone=" + phone + "]";
	}
}
