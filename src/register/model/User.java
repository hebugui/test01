package register.model;

public class User {
	private long id;
	private String userName;
	private String password;
	private String role;
	private String gender;
	private String email;
	private int age;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	//���ö����HTML�����ʽ
	public String toString(){
		return "�û���" + getUserName() +"<br>���룺"+getPassword()+"<br>��ɫ��"+getRole()+"<br>�Ա�"+getGender()+"<br>���䣺"+getEmail()+"<br>���䣺"+getAge();
	}

}
