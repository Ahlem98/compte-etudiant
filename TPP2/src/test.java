import java.util.List;

import model.GroupeModule;
import model.Matiere;
import dao.PlanEtudesImp;


public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PlanEtudesImp plan =new PlanEtudesImp();
		List<Matiere> matieres = plan.getAllMatieres();
		    for(Matiere m : matieres){
			System.out.println(m.getNom());
		     }
		GroupeModule gp = new GroupeModule(2,"jee",1);
		plan.addMatiere(new Matiere("lo",1,1,1,gp));
		//plan.addMatiere(new Matiere("comm",1,3,1,gp));
		


	}

}
