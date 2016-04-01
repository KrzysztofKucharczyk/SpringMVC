package pl.spring.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import pl.spring.demo.enumerations.Role;

@Entity
@Table(name = "USER_AUTHORIZATION")
public class UserAuthorizationEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long role_id;
	
	@Column(nullable = false)
	private Long user_id;
	
	@Enumerated(EnumType.STRING)
	private Role role;

	// for hibernate
	protected UserAuthorizationEntity() {
	}

	public UserAuthorizationEntity(Long id, Long user, Role role) {
		this.role_id = id;
		this.user_id = user;
		this.role = role;
	}

	public Long getRoleId() {
		return role_id;
	}

	public void setRoleId(Long id) {
		this.role_id = id;
	}

	public Long getUserId() {
		return user_id;
	}

	public void setUserId(Long user_id) {
		this.user_id = user_id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
