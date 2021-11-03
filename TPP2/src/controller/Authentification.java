package controller;

import dao.PlanEtudesImp;
import model.Compte;
import model.GroupeModule;
import model.Matiere;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Authentification" , urlPatterns = "/Authentification")
public class Authentification extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PlanEtudesImp planEtudesImp = new PlanEtudesImp();
        List<Matiere> list = planEtudesImp.getAllMatieres();
        request.setAttribute("list",list);

        String user = request.getParameter("user");
        String password =request.getParameter("password");
        Compte compte = new Compte();
        compte.setLogin(user);
        compte.setPassword(password);

        String userRole =planEtudesImp.verif(compte);

        if(userRole.equals("admin")) {
            HttpSession session = request.getSession();
            
            session.setAttribute("user",user);
            session.setAttribute("role","admin");
            request.setAttribute("check", true);
         
            request.getRequestDispatcher("/accueil.jsp").forward(request, response);
        }else if(userRole.equals("etudiant")){
            HttpSession session = request.getSession();
           
            session.setAttribute("user",user);
            session.setAttribute("role","etudiant");
            request.setAttribute("check", false);
           
            request.getRequestDispatcher("/accueil.jsp").forward(request, response);
        }else{
            request.setAttribute("errorMessage","Invalid email or password. Try again ");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
