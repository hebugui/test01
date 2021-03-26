package register.control;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import register.dao.UserDAO;
import register.dao.UserDAOImpl;
import register.model.User;

public class RegisterServlet extends HttpServlet {
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		//模型对象生成
		User userBean = new User();
		UserDAO userDAO = new UserDAOImpl();
		//从视图register.jsp获取参数，并存放在实体模型中
		userBean.setUserName(request.getParameter("userName"));
		userBean.setPassword(request.getParameter("password"));
		userBean.setGender(request.getParameter("gender"));
		userBean.setEmail(request.getParameter("email"));
		userBean.setAge(Integer.parseInt(request.getParameter("age")));
		userBean.setRole("普通用户");
		//调用业务模型，保存注册用户
		userDAO.saveUser(userBean);
		//将用户模型userBean存入request对象中，以便在视图文件regsuccess.jsp中展示注册信息
		request.setAttribute("userBean", userBean);
		//转发请求至注册成功提示页regsuccess.jsp中展示注册信息
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("regsuccess.jsp");
		requestDispatcher.forward(request,response);
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doPost(request,response);
	}
}
