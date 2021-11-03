package dao;

import model.Compte;
import model.GroupeModule;
import model.Matiere;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanEtudesImp implements IPlanEtudes{

    Connection connection;

    public PlanEtudesImp() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/tp2?serverTimezone=UTC";
            String user = "Ahlem";
            String password = "ivanhoe";

            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<GroupeModule> getAllGroupesModules() {
        List<GroupeModule> groupeModules = new ArrayList<GroupeModule>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from groupemod");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("idgm");
                String nom = resultSet.getString("nom");
                int niveau = resultSet.getInt("niveau");

                groupeModules.add(new GroupeModule(id, nom, niveau ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return groupeModules;
    }

    @Override
    public List<Matiere> getAllMatieres() {
        List<Matiere> matieres = new ArrayList<Matiere>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from matiere");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("idmat");
                String nom = resultSet.getString("nom");
                int nbHeuresCours = resultSet.getInt("nbhCours");
                int getNbHeuresTp = resultSet.getInt("nbhTp");
                double coefficient = resultSet.getInt("coef");
                GroupeModule groupeModule = getGroupeModuleByID (resultSet.getInt("idgm"));

                matieres.add(new Matiere(id, nom, nbHeuresCours, getNbHeuresTp, coefficient , groupeModule ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return matieres;
    }
    @Override
    public List<Matiere> getAllMatieresByGM(int idgm) {
        List<Matiere> matieres = new ArrayList<Matiere>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM matiere WHERE idgm = ?;");
            preparedStatement.setInt(1, idgm);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("idmat");
                String nom = resultSet.getString("nom");
                int nbHeuresCours = resultSet.getInt("nbhCours");
                int getNbHeuresTp = resultSet.getInt("nbhTp");
                double coefficient = resultSet.getInt("coef");
                GroupeModule groupeModule = getGroupeModuleByID (resultSet.getInt("idgm"));

                matieres.add(new Matiere(id, nom, nbHeuresCours, getNbHeuresTp, coefficient , groupeModule ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return matieres;
    }

    @Override
    public GroupeModule getGroupeModuleByID(int idgm) {
        GroupeModule groupeModule = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from groupemod WHERE idgm = ? ;");
            preparedStatement.setInt(1, idgm);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("idgm");
                String nom = resultSet.getString("nom");
                int niveau = resultSet.getInt("niveau");
                groupeModule = new GroupeModule(id, nom, niveau);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return groupeModule ;
    }

    public GroupeModule getGroupeModuleByNomNiveau(String nom ,int niveau) {
        GroupeModule groupeModule = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from groupemod WHERE nom = ? and niveau = ? ;");
            preparedStatement.setString(1, nom);
            preparedStatement.setInt(2,niveau);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("idgm");
                String nomg = resultSet.getString("nom");
                int niveaug = resultSet.getInt("niveau");
                groupeModule = new GroupeModule(id, nomg, niveaug);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return groupeModule ;
    }

    @Override
    public void addMatiere(Matiere matiere) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO matiere (idgm,nom,nbhCours,nbhTp,coef) VALUES  ( ?, ?, ?, ?, ?);");
            //preparedStatement.setInt(1, matiere.getId());

            preparedStatement.setInt(1, matiere.getGroupeModule().getId());

            preparedStatement.setString(2, matiere.getNom());
            preparedStatement.setInt(3, matiere.getNbHeuresCours());
            preparedStatement.setInt(4, matiere.getGetNbHeuresTp());
            preparedStatement.setDouble(5, matiere.getCoefficient());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String verif(Compte compte) {
        String role = null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("SELECT email , password ,role FROM user WHERE email=? and password=? ;");
            preparedStatement.setString(1, compte.getLogin());
            preparedStatement.setString(2, compte.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                role = resultSet.getString("role");
                return role;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "invalid user";
    }

    @Override
    public List<Matiere> rechercheMc(String mc) {
        List<Matiere> matieres = new ArrayList<Matiere>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM matiere WHERE nom Like ? ;");
            preparedStatement.setString(1, "%"+mc+"%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("idmat");
                String nom = resultSet.getString("nom");
                int nbHeuresCours = resultSet.getInt("nbhCours");
                int getNbHeuresTp = resultSet.getInt("nbhTp");
                double coefficient = resultSet.getInt("coef");
                GroupeModule groupeModule = getGroupeModuleByID (resultSet.getInt("idgm"));

                matieres.add(new Matiere(id, nom, nbHeuresCours, getNbHeuresTp, coefficient , groupeModule ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return matieres;
    }


}

