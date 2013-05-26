package Exo1;

public class Analyse {
	private Object ob ;
	private Class c ;
	
	
	
	
	
	public Analyse(){
	    this.ob = null;
	    this.c = null;
	    System.out.println("Instanciation !");
	  }
	

	
	
	public void analyserClass(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		
		Class<?> cc = Class.forName("java.awt.Dimension");

		
		Object o = cc.newInstance();
		
		
		//System.out.println("La Nom de la classe :" +  cc.getClass().getName().toString() + " est : " + cc.getClass().getSuperclass().toString());
	//	System.out.println("La superclasse de la classe " + String.class.getName() + " est : " + String.class.getSuperclass());
		
		
		Package p = c.getPackage();
		System.out.println(p.getName());
		
		// getInterfaces retourne un tableau de Class
		Class[] interfaces = cc.getInterfaces();
		for(int i=0;i<interfaces.length;++i)
		{
		   System.out.println(interfaces[i].getName());
		}
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		Analyse an = new Analyse();
		an.analyserClass("java.awt.Dimension");
	}


}
