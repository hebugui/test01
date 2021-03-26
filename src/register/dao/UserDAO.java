package register.dao;
import register.model.User;

//用户操作接口定义
public interface UserDAO {
		public void saveUser(User user);
		public void updateUser(User user);
		public User[] listAllUser();
		public User listUserById(long userId);
		public void deleteUser(long userId);
		public String validate(String userName, String password);
}
