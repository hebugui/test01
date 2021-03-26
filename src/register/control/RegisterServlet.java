package register.control;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import register.dao.UserDAO;
import register.dao.UserDAOImpl;
import register.model.User;

public class RegisterServlet extends HttpServlet {
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		//ģ�Ͷ�������
		User userBean = new User();
		UserDAO userDAO = new UserDAOImpl();
		//����ͼregister.jsp��ȡ�������������ʵ��ģ����
		userBean.setUserName(request.getParameter("userName"));
		userBean.setPassword(request.getParameter("password"));
		userBean.setGender(request.getParameter("gender"));
		userBean.setEmail(request.getParameter("email"));
		userBean.setAge(Integer.parseInt(request.getParameter("age")));
		userBean.setRole("��ͨ�û�");
		//����ҵ��ģ�ͣ�����ע���û�
		userDAO.saveUser(userBean);
		//���û�ģ��userBean����request�����У��Ա�����ͼ�ļ�regsuccess.jsp��չʾע����Ϣ
		request.setAttribute("userBean", userBean);
		//ת��������ע��ɹ���ʾҳregsuccess.jsp��չʾע����Ϣ
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("regsuccess.jsp");
		requestDispatcher.forward(request,response);
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doPost(request,response);
	}
}
