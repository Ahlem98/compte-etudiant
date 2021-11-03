package dao;

import model.Compte;
import model.GroupeModule;
import model.Matiere;

import java.util.List;

public interface IPlanEtudes {


    List<GroupeModule> getAllGroupesModules();
    List<Matiere> getAllMatieres();
    List<Matiere> getAllMatieresByGM(int id);
    GroupeModule getGroupeModuleByID(int id);
    void addMatiere(Matiere matiere);
    String verif(Compte compte);
    List<Matiere> rechercheMc(String mc);


}

