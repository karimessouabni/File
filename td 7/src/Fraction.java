	//
	// IUT de Nice / Departement informatique / Module APO Java
	
	// Annee 2011_2012 - DUT/S2T
	//
	// Classe Fraction - Modelisation de l'ensemble ZxZ*
	//
	// V 0.1.0 : base
	// V 0.2.0 : Ajout des accesseurs de consultation
	// V 0.3.0 : Ajout d'un nouveau constructeur
	// V 0.4.0 : Introduction de la mŽthode sqrt
	//
	// Auteur : B. Gauci & K.essouabi
	//
	
	public class Fraction {
		private int m_numerateur;
		private int m_denominateur;
	
		// --- Constructeur par defaut
	
		public Fraction() {
	
			// Fixer les deux attributs de la fraction
			//
			m_numerateur = 0;
			m_denominateur = 1;
		}
	
		// --- Premier constructeur normal
	
		public Fraction(int num, int den) throws Exception {
	
			// Controler la validite du denominateur
			//
			if (den == 0)
				throw new Exception();
	
			// Fixer les deux attributs de la fraction
			//
			m_numerateur = num;
			m_denominateur = den;
	
			// Reduire la fraction resultante
			//
			reduire();
		}
	
		// --- Second constructeur normal
	
		public Fraction(int num) {
	
			// Fixer les deux attributs de la fraction
			//
			m_numerateur = num;
			m_denominateur = 1;
		}
	
		// --- Troisieme constructeur normal
	
		public Fraction(double x) {
			int puissance, den;
	
			String num;
			String s = new String("" + x);
	
			String str[] = s.split("\\.");
	
			if (str[1].length() > 7) {
				str[1] = str[1].substring(0, 5);
			}
	
			num = "" + str[0] + str[1];
	
			int numerateur = Integer.parseInt(num);
	
			puissance = str[1].length();
	
			den = (int) Math.pow(10, puissance);
	
			m_numerateur = numerateur;
			m_denominateur = den;
			reduire();
		}
	
		// Fixer les deux attributs de la fractio
		//
	
		// --- accesseurs de consultation
	
		public int getNumerateur() {
			return m_numerateur;
		}
	
		public int getDenominateur() {
			return m_denominateur;
		}
	
		// Fixer les deux attributs de la fractio
		//
	
		// Methode ajoutee
		public double getdouble() {
	
			return (double) m_numerateur / (double) m_denominateur; // attention ,
																	// il faut
																	// caster les
																	// int en double
																	// avant de
																	// faire la
																	// division
	
		}
	
		// --- Methode reduire
	
		private void reduire() {
			int signe, pgcd;
	
			// Traiter le cas particulier d'une fraction nulle
			//
			if (m_numerateur == 0) {
				m_denominateur = 1;
			}
	
			// Determiner le signe du resultat
			//
			if (m_numerateur > 0)
				if (m_denominateur > 0)
					signe = 1;
				else {
					signe = -1;
				}
			else if (m_denominateur > 0) {
				signe = -1;
			} else
				signe = 1;
	
			// Calculer le PGCD des deux membres
			//
			pgcd = Arithm.pgcd(Math.abs(m_numerateur), Math.abs(m_denominateur));
	
			// Diviser chaque membre par le PGCD
			//
			m_numerateur /= pgcd;
			m_denominateur /= pgcd;
	
			if (signe == -1) {
				m_numerateur = Math.abs(m_numerateur) * -1;
			} else {
				m_numerateur = Math.abs(m_numerateur);
			}
	
			m_denominateur = Math.abs(m_denominateur);
		}
	
		// --- Methode toString
	
		public String toString() {
	
			// Traiter le cas particulier d'une fraction nulle
			//
			if (m_numerateur == 0)
				return "0";
	
			// Traiter le cas particulier d'un entier
			//
			if (m_denominateur == 1)
				return "" + m_numerateur;
	
			// Traiter le cas general
			//
			return m_numerateur + "/" + m_denominateur;
			// double resultat = m_numerateur/m_denominateur ;
			// return ""+resultat ;
	
		}
	
		// --- Methode add
	
		public Fraction add(Fraction op2) {
			int ppcm, num, num1, num2;
			// Calculer le PPCM des deux denominateurs
			//
			ppcm = Arithm.ppcm(m_denominateur, op2.m_denominateur);
	
			// Calculer le coefficient multiplicateur de chaque numerateur
			//
			num1 = (ppcm / m_denominateur) * m_numerateur;
			num2 = (ppcm / op2.m_denominateur) * op2.m_numerateur;
	
			// Fixer le numerateur du resultat
			//
			num = num1 + num2;
	
			// Construire le resultat
			//
			Fraction resultat = null;
			try {
				resultat = new Fraction(num, ppcm);
			} catch (Exception e) {
			}
	
			// Restituer le resultat
			//
			return resultat;
		}
	
		public Fraction add(int op2) {
			int num;
			// Fixer le numerateur du resultat
			//
			num = m_numerateur + op2 * m_denominateur;
	
			// Construire le resultat
			//
			Fraction resultat = null;
			try {
				resultat = new Fraction(num, m_denominateur);
			} catch (Exception e) {
			}
	
			// Restituer le resultat
			//
			return resultat;
		}
	
		// --- Methode sub
	
		public Fraction sub(Fraction op2) {
			int ppcm, num, num1, num2;
	
			// Calculer le PPCM des deux denominateurs
			//
			ppcm = Arithm.ppcm(m_denominateur, op2.m_denominateur);
	
			// Calculer le coefficient multiplicateur de chaque numerateur
			//
			num1 = (ppcm / m_denominateur) * m_numerateur;
			num2 = (ppcm / op2.m_denominateur) * op2.m_numerateur;
	
			// Fixer le numerateur du resultat
			//
			num = num1 - num2;
	
			// Construire le resultat
			//
			Fraction resultat = null;
			try {
				resultat = new Fraction(num, ppcm);
			} catch (Exception e) {
			}
	
			// Restituer le resultat
			//
			return resultat;
		}
	
		public Fraction sub(int op2) {
			int num;
			// Fixer le numerateur du resultat
			//
			num = m_numerateur - op2 * m_denominateur;
	
			// Construire le resultat
			//
			Fraction resultat = null;
			try {
				resultat = new Fraction(num, m_denominateur);
			} catch (Exception e) {
			}
	
			// Restituer le resultat
			//
			return resultat;
		}
	
		public Fraction sub() {
	
			// Fixer le numerateur du resultat
			//
	
			// Construire le resultat
			//
			Fraction resultat = null;
			try {
				resultat = new Fraction(1, 0);
			} catch (Exception e) {
			}
	
			// Restituer le resultat
			//
			return resultat;
		}
	
		// --- Methode oppose
	
		private void oppose() {
			m_numerateur *= -1;
		}
	
		// --- Methode inverse
	
		public static Fraction inverse(Fraction op) throws Exception {
	
			// Controler le cas particulier d'une fraction nulle
			//
			if (op == null)
				throw new Exception();
	
			// Construire le resultat
			//
			Fraction resultat = null;
			try {
				resultat = new Fraction(op.m_denominateur, op.m_numerateur);
			} catch (Exception e) {
			}
	
			// Restituer le resultat
			//
			return resultat;
	
		}
	
		// --- Methode mul
	
		public Fraction mul(final Fraction op2) {
			int num, dem;
			// Fixer le numerateur du resultat
			//
			num = this.m_numerateur * op2.m_numerateur;
	
			// Fixer le denominateur du resultat
			//
			dem = this.m_denominateur * op2.m_denominateur;
	
			// Construire le resultat
			//
			Fraction resultat = null;
			try {
				resultat = new Fraction(num, dem);
			} catch (Exception e) {
			}
	
			// Restituer le resultat
			//
			return resultat;
		}
	
		public Fraction mul(final int op2) {
			int num;
			// Fixer le numerateur du resultat
			//
			num = this.m_numerateur * op2;
	
			// Construire le resultat
			//
			Fraction resultat = null;
			try {
				resultat = new Fraction(num, m_denominateur);
			} catch (Exception e) {
			}
	
			// Restituer le resultat
			//
			return resultat;
		}
	
		// --- Methode div
	
		public Fraction div(final Fraction op2) {
	
			Fraction resultat = null;
			try {
				resultat = new Fraction(op2.m_denominateur, op2.m_numerateur);
			} catch (Exception e) {
			}
	
			return this.mul(resultat);
		}
	
		public Fraction div(final int op2) {
	
			Fraction resultat = null;
			try {
				resultat = new Fraction(m_numerateur, this.m_denominateur * op2);
			} catch (Exception e) {
			}
	
			return resultat;
		}
	
		// --- Methode sqrt
	
		public Fraction sqrt() {
			Fraction A, B;
	
			A = new Fraction(Math.sqrt(m_numerateur));
			B = new Fraction(Math.sqrt(m_denominateur));
			try {
				return A.mul(Fraction.inverse(B));
			} catch (Exception e) {
			}
	
			return null;
		}
	
		public Object clone() {
			try {
				return new Fraction(m_numerateur, m_denominateur);
			} catch (Exception e) {
			}
	
			return null;
		}
		// --- Methode equals
		 public boolean equals(Object op) {
			  Fraction of = (Fraction)op ; 
			  
			
			  
			  
		      return this.m_numerateur== of.getNumerateur() && m_denominateur==of.getDenominateur() ; 
		   }
	}