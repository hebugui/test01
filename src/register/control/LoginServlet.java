package register.control;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import register.dao.UserDAO;
import register.dao.UserDAOImpl;

public class LoginServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
	//����ͼindex.jsp��ȡ�������
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String role = "";
		String goForward;
		
	//����ģ�� userDAO����ȡ�û���ɫ
		UserDAO userDAO = new UserDAOImpl(); //��������ת��
		role = userDAO.validate(userName, password);
		
	//���ݽ�ɫ��ת����������Ӧ����ͼ
		//��ָ��
		if("��ͨ�û�".equals(role)){
			goForward = "userPage.jsp";
		} else if("����Ա".equals(role)){
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
