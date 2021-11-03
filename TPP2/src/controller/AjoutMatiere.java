package controller;

import dao.PlanEtudesImp;
import model.GroupeModule;
import model.Matiere;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AjoutMatiere", urlPatterns = "/AjoutMatiere")
public class AjoutMatiere extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanEtudesImp planEtudesImp = new PlanEtudesImp();

        Matiere matiere = new Matiere();
        matiere.setNom(request.getParameter("NomMatiere"));
        matiere.setNbHeuresCours(Integer.parseInt(request.getParameter("nbHeuresCours")));
        matiere.setGetNbHeuresTp(Integer.parseInt(request.getParameter("nbHeuresTp")));
        matiere.setCoefficient(Double.parseDouble(request.getParameter("coefficient")));

        String nom = request.getParameter("groupe");
        int niveau = Integer.parseInt(request.getParameter("niveau"));
        GroupeModule groupeModule = planEtudesImp.getGroupeModuleByNomNiveau(nom,niveau);

        matiere.setGroupeModule(groupeModule);

        planEtudesImp.addMatiere(matiere);
        List<Matiere> list = planEtudesImp.getAllMatieres();

        request.setAttribute("list",list);
        request.setAttribute("check",true);
        request.getRequestDispatcher("accueil.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
