//
// IUT de Nice / Departement informatique / Module APO Java
// Annee 2011_2012 - DUT/S2T
//
// Classe Point - Modelisation du plan euclidien
//
// Edition A        : TP_5
//
//    + Version 0.0.0	: version initiale
//    + Version 1.0.0	: coef devient un RxR
//    + Version 1.0.1	: expo nul géré
//
// Auteur : B. gauci & K.essouabni 
//


public class Monome {

	public RxR coef;
	public int expo;

	// ---                                             			Constructeur par defaut

    public Monome() {
    	coef = new RxR();
    	expo = 1;
    }
    
    // ---                                         				Premier constructeur normal
    
    public Monome(RxR coef, int expo) throws Exception {
    	
    	if(expo < 0) {throw new Exception ("expo non positif");}
    	
    	this.coef = (RxR) coef.clone();
    	this.expo = expo;
    }
    
    // ---                                         				Second constructeur normal
    
    public Monome(RxR coef){
    	this.coef = (RxR) coef.clone();
    	expo = 1;
    }
    
    // ---                                         				Clone
    
    protected Object clone() {
    	try{return new Monome((RxR) coef.clone(), expo);}
    	catch(Exception e){}
    	
    	return null;
    }
    
    // ---                                         				Equals
    
    public boolean equals(Object o) {
    	Monome M = (Monome)o;
    	
    	if(coef.equals(M.getCoef()) == false) {return false;}
    	
   		if(coef.equals(new RxR()) == true) {return true;} //attention equals de rxr  et pas == cas d objet 
    	
    	if(expo == M.getExpo()){return true;}
    	
    	return false;
    }
    
    // ---                                         				Accesseur
    
    public RxR getCoef(){return coef;}
    public int getExpo(){return expo;}
    
    // ---                                         				toString
    
    
    public String toString() {
    	return "(" + coef.toString() + ", " + expo + ")";
    }
    
    
    
    public String toStringz() {
    	if (coef.equals(new RxR(0, 0)))
    		return "";
    	if (expo == 0)
    	{
    		return "" + coef.toString();
    	}
    	else if (expo == 1)
    	{
    		if (coef.equals(new RxR(1, 0)))
    			return "Z";
    		return "" + coef.toString() + "Z";
    	}
    	if (coef.equals(new RxR(1, 0)))
    		return "Z" + expo;
    	return "" + coef.toString() + "Z" + expo;
    }
    
    
    
    // ---                                         				Mul
    
    public Monome mul(Monome m){
    	try{return new Monome(this.coef.mul(m.getCoef()),this.expo + m.getExpo());}
    	catch(Exception e) {}
    	
    	return null;
    }
    
    // ---														Add
    
    public Monome add(Monome m) throws Exception {
    	
    	if(coef.equals(new RxR())) {return (Monome) m.clone();} // pas besoin de clonne ici non ? et pourquoi caster
    														 // en monome alors que c'est deja monome 
    															// dans RxR la clone  n'a elle pas besoin de try catch vu que nos constructeurs en RxR ne leve
    																// aucune exception 
    																//ou bien le try catch est indisponsable dans la methode catch
    														// jai mis protected a la place de public dans le clonne vu qu il faut respecter la signature
    	// si une exception est levŽ dans un constructeur dans une classe ( monome par exemple )
    	//alors chaque fos qu'on cree un objet en utilisant ce constructeur il faut absolument gerer l'exception avec un try catch 
    	// mais dans le cas ou on a une classe qui dans une de ses methode cree un objet ( polynome par exemple ) en utilisant le constructeur dans la classe monom
    	// qui leve une exception alors faut il passer une gestion avec try catch ou pas besoin ? 
    	// le boolean dans add dans polynom sert a quoi au fait ? 
    	// dans clone on cree un objet mais clonne passe que par reference @ de l'objet l objet resultat n'existe pas alors c'est
    	//juste un copiage d'@ c'est ca ? 
    	//pas de return dans les add polynom ????
    	
    	
    	if(m.getCoef().equals(new RxR())) {return (Monome) this.clone();} //j'ai ajoute this ici !
    	
    	if(expo == m.getExpo())
    	{
    		return new Monome (coef.add(m.getCoef()) , expo);
    	}
    	else // dans le else on leve une exception 
    	{
    		throw new Exception ("expo non égaux");
    	}
    }
    
    // ---														Sub
    
    public Monome sub(Monome m) throws Exception {
    	
    	if(coef.equals(new RxR())) {return (Monome) m.clone();}
    	
    	if(m.getCoef().equals(new RxR())) {return (Monome) clone();}
    	
    	if ( expo == m.getExpo() ) 
    	//	try{
    		return new Monome (coef.sub(m.getCoef()), expo);
    	//}catch(Exception e){}
    	else
    	{
    		throw new Exception ("expo non égaux");
    	}
    }
    
    // ---														Inv
   public Monome inv() {
    	
    	try{return new Monome (new RxR(-coef.getReel(), -coef.getImag()), expo);}
    	catch(Exception e){}
    	
    	return null;
    }
}