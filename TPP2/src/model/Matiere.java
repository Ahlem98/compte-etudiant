package model;

public class Matiere {
    private int id;
    private String nom;
    private int nbHeuresCours;
    private int getNbHeuresTp;
    private double coefficient;
    private GroupeModule groupeModule ;

    public Matiere() {
    }

    public Matiere(String nom, int nbHeuresCours, int getNbHeuresTp, double coefficient, GroupeModule groupeModule) {
        this.nom = nom;
        this.nbHeuresCours = nbHeuresCours;
        this.getNbHeuresTp = getNbHeuresTp;
        this.coefficient = coefficient;
        this.groupeModule = groupeModule;
    }

    public Matiere(int id, String nom, int nbHeuresCours, int getNbHeuresTp, double coefficient, GroupeModule groupeModule) {
        this.id = id;
        this.nom = nom;
        this.nbHeuresCours = nbHeuresCours;
        this.getNbHeuresTp = getNbHeuresTp;
        this.coefficient = coefficient;
        this.groupeModule = groupeModule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbHeuresCours() {
        return nbHeuresCours;
    }

    public void setNbHeuresCours(int nbHeuresCours) {
        this.nbHeuresCours = nbHeuresCours;
    }

    public int getGetNbHeuresTp() {
        return getNbHeuresTp;
    }

    public void setGetNbHeuresTp(int getNbHeuresTp) {
        this.getNbHeuresTp = getNbHeuresTp;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public GroupeModule getGroupeModule() {
        return groupeModule;
    }

    public void setGroupeModule(GroupeModule groupeModule) {
        this.groupeModule = groupeModule;
    }
}
