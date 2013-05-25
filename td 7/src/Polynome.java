//
// IUT de Nice / Departement informatique / Module APO Java
// Annee 2011_2012 - DUT/S2T
//
// Classe Polynome - A valeur dans le corps C des nombres complexes
//
// Edition A        : TD_6
//
//    + Version 0.0.0	: version initiale
//
// Auteur : B. gauci
//
import java.util.*;

public class Polynome  {
	
	private LinkedList list; // on va la remplire par des objets monome ( vu qu'un polynom = somme de monome )

	// ---                                             			Constructeur par defaut

   public Polynome() {
	   	list = new LinkedList();
   	
   	list.add(new Monome());
   }
    
   // ---                                         				Clone
    
   public Object clone() {
    	return null;
    }
    
   // ---                                         				Equals
    
   public boolean equals(Object o) {
    	return false;
    }
    
   // ---                                         				Accesseur

	public int degre(){return ((Monome)list.get(0)).getExpo();}  // retourne de l'expo  .get(0) pour aller au monome du poind fort 
	public int arite(){return list.size();}  //retourne le nombre de monome dans le polynom

   // ---                                         				toString
    
   public String toString() {
   	String s = new String();
   	int i = 0;
   	
		s = ((Monome)list.get(0)).toString();	
				
	   if(list.size() > 1){
	   	for(i = 1; i < list.size(); i++){
				 s += " + " + ((Monome)list.get(i)).toString(); // une chaine de monome 
			}	
	   }	
	   return s;
   }
   
   // ---																	add
   
   public Monome add(Monome m){
   	
   	int i = 0;
   	Boolean C = true;
   	
   	if(((Monome)list.get(0)).equals(new Monome())) // si le 1re monome de notre polynom est null alors
   	{
   		list = new LinkedList(); // une liste vide = polynome vide 
   	
   		list.add(m); // une lui ajoute le monom => on a un polynome avec un seule mnom "qui est  m"
   	}
   	else
   	{
   		while(C)
   		{
   			int ik = m.getExpo() ;  // l'expo du monome � ajouter
   			
   			
   			for ( int j=0;j<list.size();j++){
   			//if (ik =((Monome)list.get(j)).getExpo())  {
   				// l'expo du monome dans la linkedlist
   		//	return (Monome)list.get(i).add(m);
   			
   			}
   				
   				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   				
   			}
				if (m.getExpo() > ((Monome)list.get(i)).getExpo()) // si le monome � ajouter a une expo plus grande on l'ajoute devant
				{
					list.add(i, m);
					C = false;
				}
				
				if (C == true && ((Monome)list.get(i)).getExpo() == m.getExpo()) // si les 2 mmonome on le m�me exposant, on les ajoutes
				{
					try{
						list.set(i, m.add((Monome)list.get(i)));
						C = false;
					}
					catch(Exception e){System.out.print("\n\nErreur dans la class polynome, tentative d'addition entre 2 monome qui a �chou�");}
				}

				
				i++;
				if(C == true && i >= list.size()) {C = false;list.add(m);}
			}
	return m;

   	}
   }

