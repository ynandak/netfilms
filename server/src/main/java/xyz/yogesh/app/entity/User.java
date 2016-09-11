package xyz.yogesh.app.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQueries({
		@NamedQuery(name = "User.findAll", query = "SELECT u from User u ORDER BY u.name"),
		@NamedQuery(name = "User.findByEmail", query = "SELECT u from User u where u.email=:pEmail")
})
public class User {

	@Id
	private String id;

	@Column(unique=true)
	private String email;
	private String password;
	private String name;
	private String userType;

	public User() {
		id = UUID.randomUUID().toString();
		userType = "User";
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}