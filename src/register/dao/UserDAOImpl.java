package register.dao;
import java.sql.*;
import java.util.*;
import register.model.User;

//UserDAO接口的实现
public class UserDAOImpl implements UserDAO {
	public String dbClassName = "com.mysql.jdbc.Driver";
	public String dbUrl ="jdbc:mysql://localhost:3306/userdb";
	private String dbUser = "root";
	private String dbPwd = "123456abc";
	public UserDAOImpl() {
	}
	//获取Connection对象
	public Connection getConnection() {
		Connection conn = null;
		try{
			Class.forName(dbClassName);
			conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	//保存用户
	public void saveUser(User user) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);// 将自动提交设为false
			stmt = con.prepareStatement("insert into myuser(userName,password,gender,role,email,age)values(?,?,?,?,?,?)");
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getGender());
			stmt.setString(4, user.getRole());
			stmt.setString(5, user.getEmail());
			stmt.setInt(6, user.getAge());
			stmt.execute();
			con.commit();// 提交
		} catch(Exception e){
			try{
				con.rollback();// 回滚
			} catch(SQLException sqlex){
				sqlex.printStackTrace();
			}
		}finally {
			try {
				stmt.close();
				con.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	//更新用户
	public void updateUser(User user){
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);// 将自动提交设为false
			stmt = con.prepareStatement("update myuser set userName=?,password=?,gender=?,role=?,email=?,age=? where id=" + user.getId() +"");
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getGender());
			stmt.setString(4, user.getRole());
			stmt.setString(5, user.getEmail());
			stmt.setInt(6, user.getAge());
			stmt.execute();
			con.commit();// 提交
		} catch(Exception e){
			try{
				con.rollback();// 回滚
			}catch(SQLException sqlex){
				sqlex.printStackTrace();
			}
		}finally {
			try {
				stmt.close();
				con.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	//查找所有用户
	public User[] listAllUser(){
		User[] users = null;
		int i;
		Connection con = null;
		// 执行两条查询语句
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = getConnection();
			stmt = con.createStatement();
			//用来执行查询记录总数
			rs = stmt.executeQuery("select count(*) from myuser");
			rs.next(); //把元素放出来使之为空
			users = new User[rs.getInt(1)]; // 定义一个长度为count(*)的User数组
			pstmt = con.prepareStatement("select * from myuser");
			rs = pstmt.executeQuery();
			i = 0;
			while(rs.next()){
				users[i] = new User();
				users[i].setId(rs.getLong(1));
				users[i].setUserName(rs.getString(2));
				users[i].setPassword(rs.getString(3));
				users[i].setGender(rs.getString(4));
				users[i].setRole(rs.getString(5));
				users[i].setEmail(rs.getString(6));
				users[i].setAge(rs.getInt(7));
				i++;
			}
			rs.close();
			stmt.close();
			con.close();
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
		}
		return users;
	}
	
	//根据用户ID查找指定用户
	public User listUserById(long userId){
		User user = new User();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			con = getConnection();
			stmt = con.prepareStatement("select * from myuser where id=" + userId +"");
			//用来执行查询记录总数
			rs = stmt.executeQuery();
			if(rs.next()){
				user.setId(rs.getLong(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setGender(rs.getString(4));
				user.setRole(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setAge(rs.getInt(7));
			}
			rs.close();
			stmt.close();
			con.close();
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
		}
		return user;
	}
	
	//删除指定ID的用户
	public void deleteUser(long userId){
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);// 将自动提交设为false
			stmt = con.prepareStatement("delete from myuser where id=?");
			stmt.setLong(1, userId);
			stmt.execute();
			con.commit();// 提交
		} catch(Exception e){
			try{
				con.rollback();// 回滚
			} catch(SQLException sqlex){
				sqlex.printStackTrace();
			}
		}finally {
			try {
				stmt.close();
				con.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	//根据用户名与口令获取用户角色信息
	public String validate(String userName, String password){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String role ="";
		try{
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from myuser where userName ='" + userName +"' and password='" +password +"'");
			if(rs.next()){
				role = rs.getString("role");
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return role;
	}
}
