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

@WebServlet(name = "RechercheMc")
public class RechercheMc extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanEtudesImp planEtudesImp = new PlanEtudesImp();
        String mc = request.getParameter("motCle");
        List<Matiere> list = planEtudesImp.rechercheMc(mc);
        request.setAttribute("list",list);

        HttpSession session = request.getSession();
        String role = (String)session.getAttribute("role");
        if(role.equals("admin")) {
            request.setAttribute("check", true);
            
        }else if(role.equals("etudiant")){
            request.setAttribute("check", false);
           
        }
        request.getRequestDispatcher("accueil.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
