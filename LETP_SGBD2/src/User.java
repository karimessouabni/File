import java.io.Serializable;

public class User implements Serializable {
 
    private String nom;
    private String prenom;
    private int age;
    private int id ;
    private String  mp ;
 
    public User() {
        nom = "";
        prenom = "";
        age = 0;
        id = 0;
        mp = "";
    }
 
    public User(String nom, String prenom, int age, int id, String mp) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.mp = mp;
        this.id = id;
    }
 
    public void setNom(String nom) {
        this.nom = nom;
    }
 
    public String getNom() {
        return nom;
    }
 
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
 
    public String getPrenom() {
        return prenom;
    }
 
    public void setAge(int age) {
        this.age = age;
    }
 
    public int getAge() {
        return age;
    }

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the mp
	 */
	public String getMp() {
		return mp;
	}

	/**
	 * @param mp the mp to set
	 */
	public void setMp(String mp) {
		this.mp = mp;
	}
    }