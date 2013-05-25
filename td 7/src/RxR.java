

	//
	// IUT de Nice / Departement informatique / Module APO Java
	// Annee 2011_2012 - DUT/S2T
	//
	// Classe Classe - Modelisation du corps C des nombres complexes
	
	// Edition "Draft"  : le point d'entree simule les tests unitaires
	//
//	    + Version 0.0.0	: TP_1
// + Version 0.1.0	: TP_2
	//
	// Auteur : A. Thuaire
	//
 import java.lang.Math ;

public class RxR
		{



	

	protected Fraction m_abscisse;
		protected Fraction m_ordonnee;
		//private final double EPSILON= 1./10000;
		private final double PI= 3.141592654;




	   // ---                                               Constructeur par defaut



  public RxR() {

	      // Fixer les deux attributs du complexe (element neutre)


	  m_abscisse = new Fraction () ;


	  m_ordonnee = new Fraction () ;


	   }

	   // ---                                            Premier constructeur normal



	   public RxR(final Fraction x,final Fraction y) { 


	      // Fixer les deux attributs du nombre complexe
	      //


		   	  m_abscisse =x;

			  m_ordonnee = y;


	   }

	   // ---                                            Premier"Double" constructeur normal 
	   
	   public RxR ( final double x, final double y) {
		   // on construit d'abord une fractoin a l'aide d'un double , pour cela on cree un nouveau constructeur de fraction a partire des double 
		   // dans la classe Fraction 
		
		   m_abscisse = new Fraction(x) ; 
		   m_ordonnee = new Fraction(y) ; 
		   
		   
		   
	   }
	  
	   // ---                                            Second constructeur normal

	   public RxR(final Fraction x) { 

	      // Fixer les deux attributs du nombre complexe
	      //
		      m_abscisse = x;

			  m_ordonnee = new Fraction();
	   }

	   // ---                                            Second constructeur normal
	   
	   public RxR (final double x) { 
		   
		   //Fraction n = new Fraction (x) ;  // on appelle dans la classe Fraction le constructeur d'une fraction avec double en parametre 
		
		
		 
		
		 
		   m_abscisse   = new Fraction (x) ; 
		   m_ordonnee = new Fraction();                  // faut pas oublier cella --'
	   }
	   
	   
	   // ---                                            Accesseurs de consultation






	   public double getArgument() {

	      // Traiter le cas particulier de l'axe des ordonnees
	      //

	      if (m_abscisse.getNumerateur() == 0.0)
	      {
	    	  if (m_ordonnee.getDenominateur() > 0.0 && m_ordonnee.getNumerateur() > 0.0 ) return PI/2; //l'ongle ici vaut a 90 degres
	    	  else if (m_ordonnee.getNumerateur() == 0.0)  return 0 ;

	      else return -PI/2;

	      }
	      // Traiter le cas general
	      //
	      double laval=0.0 ;
	      Fraction fra ;

	      fra = ( m_ordonnee.div(m_abscisse)) ;

	      double dou ;
	      dou  = fra.getdouble()  ;




	      double teta_1= Math.atan(dou);
	      // tan (teta_1) = b/a  // math ne supporte que les double
	      // et tan (teta_1) = y =>  arctan (y) = teta_1
	      // ici c est b/a c-Âˆ-d  m_ordonnee/m_abscisse

	      if (m_abscisse.getNumerateur() >0 && m_abscisse.getDenominateur() > 0)


	    	  return teta_1;

	      if (m_abscisse.getDenominateur() < 0.0 && m_abscisse.getNumerateur()>0 || m_abscisse.getDenominateur() > 0.0 && m_abscisse.getNumerateur()<0 )  //car tan (teta) = tan (teta + Pi )


	    		  laval = teta_1+PI;
	      return laval ;


	   }

	   //*****************************************//

	   public double getReel () // faut laisser des doubles
	   {
		   double dou = m_abscisse.getdouble() ;

		   return dou ;
	   }


	   //*****************************************//

	   public double getImag()
	   {
		   double dou =m_ordonnee.getdouble() ;

		   return dou ;
	   }

	   //*****************************************//

	   public  double  getModule()
	{
		Fraction a, b  ;
		a= m_abscisse.mul(m_abscisse) ;
		b = m_ordonnee.mul(m_ordonnee );
		double dou ;
		dou = (a.add(b)).getdouble() ;

		return Math.sqrt (dou)  ;


	}

	   // ---                                                      Methode toString

	   public String toString() {


	      // Traiter le cas particulier de l'origine
	      //


		   if (  m_abscisse.getNumerateur() == 0 && m_ordonnee.getNumerateur() == 0 )    
			   
			   
			   return ""+0 ;



	      // ne plus Traiter le cas particulier de l'axe reel ' traités en cas general '

	    

	      // Traiter le cas general
	      //
			   else
			   {
				   return  "("+m_abscisse.toString()+","+m_ordonnee.toString()+")" ;
	      //
		   																//m_ordonnee et m_abscisse sont des fractiondonc pour les afficher appeler le tostring de la class fraction est indispensable
			  
				  

			   }

	   }

	   // ---                                                      Methode conjugue

	   public RxR conjugue ()

	   {
		   Fraction a = new Fraction () ;

		   return new RxR( 	 m_abscisse,  a.sub(m_ordonnee))  ;



	   }






	   // ---                                                           Methode add 1 
	   

	   
	   
	   public RxR add( double d) 
	   {
		   
	   
			return new RxR (m_abscisse.getdouble()+d , m_ordonnee.getdouble() )  ; 
			
	   
	   } 
	   

	   // ---                                                           Methode add 2 

	   public RxR add( final RxR v)
	   {


			return new RxR (m_abscisse.add(v.m_abscisse)  ,m_ordonnee.add(v.m_ordonnee)  )  ;


	   }

	   // ---                                                           Methode sub 1
	   public RxR sub ( double v) 
	   {
		   
	   
			return new RxR (m_abscisse.getdouble()- v , m_ordonnee.getdouble() )  ; 
			
	   
	   } 
	   



	   // ---                                                           Methode sub 2
	   public RxR sub ( final RxR v)
	   {


			return new RxR (m_abscisse.sub(v.m_abscisse)  ,m_ordonnee.sub(v.m_ordonnee)  )  ;


	   }




		 
	   // ---                                                           Methode mul 1

  public RxR mul( double v  ) 
	   
	   {return new RxR (m_abscisse.getdouble()*v ,  m_ordonnee.getdouble() *  v ) ;}
	   
	   
	   
	   // ---                                                           Methode mul 2

  public RxR mul(final RxR v  )

	   {
	Fraction a =(      (      m_abscisse.mul( v.m_abscisse)     ).sub( m_ordonnee.mul( v.m_ordonnee ) )       ) ;
	Fraction b = 	((m_abscisse.mul(v.m_ordonnee)).add( v.m_abscisse.mul(m_ordonnee) )           )      ;


		return new RxR (a,b) ;
	   }

  // ---                                                           Methode div 1
  
  
  
  public RxR div(final double v  ) 

  {return new RxR (m_abscisse.getdouble()/v ,  m_ordonnee.getdouble() /  v ) ;}
  
  

  
  
  
  // ---                                                           Methode div 2



  public RxR div(final RxR v  ) throws Exception

	   {

	  // les abscisse et ordonnee de 1/v


	  Fraction a,b,c ,d , ac , bd , aa , bb , ad , bc, adMbc, acPbd , absc, ordon ,aaPbb ;

	 a =  m_abscisse   ;
	b =   m_ordonnee  ;
	c  =  v.m_abscisse;
	 d =  v.m_ordonnee ;

	  if  ( c.getNumerateur() == 0 && d.getNumerateur()==0 )
	  throw new Exception();






	  else
	  {







	  ac =   a.mul(c) ;  //ac
	  bd =	 b.mul(d) ; //bd
	  aa =   a.mul(a) ; //a²
	  bb =	 b.mul(b) ;// b²
	  ad =	 a.mul(d) ; //ad
	  bc =	 b.mul(c) ; //bc


	 acPbd= ac.add(bd); // ac +bd
	  adMbc=  ad.sub(bc) ; // ad - bc
		aaPbb = aa.add(bb) ;  // a²+b²
	absc = acPbd.div(aaPbb); // abscice (reel )
	ordon = adMbc.div(aaPbb); // ordonnee (img )

	RxR var = new RxR(absc,ordon) ;
	return var ;


	  }

	   }
  public boolean equals(Object op) {
	  RxR of = (RxR)op ; 
	  
	  Fraction abs=  of.m_abscisse; 
	  Fraction ord=  of.m_ordonnee; 
	  
	  
      return m_abscisse.equals(abs) && m_ordonnee.equals(ord);
   }
  
  public Object clone() {
  	return new RxR( m_abscisse, m_ordonnee);}
  
 
		}
