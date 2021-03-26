package register.control;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import register.dao.UserDAO;
import register.dao.UserDAOImpl;

public class LoginServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
	//从视图index.jsp读取请求参数
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String role = "";
		String goForward;
		
	//调用模型 userDAO，获取用户角色
		UserDAO userDAO = new UserDAOImpl(); //向上向下转型
		role = userDAO.validate(userName, password);
		
	//根据角色，转发请求至相应的视图
		//空指针
		if("普通用户".equals(role)){
			goForward = "userPage.jsp";
		} else if("管理员".equals(role)){
			goForward = "manageUser.jsp";
		} else{
			goForward = "index.jsp";
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(goForward);
		requestDispatcher.forward(request,response);
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doPost(request,response);
	}
}
