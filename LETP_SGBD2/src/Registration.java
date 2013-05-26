import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.tomcat.dbcp.pool.impl.GenericKeyedObjectPool.Config;

public class Registration extends HttpServlet {
  
	
	private UserDAO userDAO;
	

	 
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {
        String nom = request.getParameter("name");
        String prenom = request.getParameter("familyname");
        String ages = request.getParameter("familyname");

 
    	/*User user = null ;	
        String function = request.getParameter("function");
        if(function.equals("getRegistration")){
        	String nom = request.getParameter("familyname");
        	String prenom = request.getParameter("name");
        	String logs = request.getParameter("login");
        	int login = Integer.parseInt(logs);
        	String password = request.getParameter("password");
        	//String ages = request.getParameter("age");
        	user = new User(nom, prenom, 22, login, password);
        }
       // int age = Integer.parseInt(ages);
        //enregistrement à partir du formulaire
        
        String passwd = UserDAO.getHash(request.getParameter("password"));

		String verifPasswd = UserDAO.getHash(request.getParameter("cfpassword"));

  
        //enregistrer l'utilisateur dans une session

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        
        //afficher la réponse dans Affiche.jsp

        */
        String url = "/Accueil.jsp";
        String message = "OOps!!! Invalid Username/Password";
        request.setAttribute("message", message);
        
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
       

    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
        
}
    }