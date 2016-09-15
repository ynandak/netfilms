package xyz.yogesh.app.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
@NamedQueries({
		@NamedQuery(name = "User.findAll", query = "SELECT u from User u ORDER BY u.name"),
		@NamedQuery(name = "User.findByEmail", query = "SELECT u from User u where u.email=:pEmail")
})
public class User {
	
	private enum UserType {
		ADMIN, USER
	}

	@Id
	private String id;


	@NotNull
	@Column(unique=true)
	@Pattern(regexp="/.+@.+\\..+/i")
	private String email;
	
	//At least 1 alphabet and 1 digit, 8-20 characters
	@NotNull
	@Pattern(regexp="/^(?:[0-9]+[a-z]|[a-z]+[0-9])[a-z0-9]*$/i")
	@Size(min=8, max=20)
	private String password;
	
	@NotNull
	@Size(min=2, max=30)
	private String name;
	
	@NotNull
	@JsonIgnore
	@Enumerated(EnumType.STRING)
	@Column(name = "USERTYPE")
	private UserType userType;

	public User() {
		id = UUID.randomUUID().toString();
		userType = UserType.USER;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", userType=" + userType + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String hashedPassword) {
		this.password = hashedPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}