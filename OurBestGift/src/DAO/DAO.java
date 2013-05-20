package DAO;


import java.sql.Connection;


/**
 * @author karim
 *
 * @param <T>
 */
public abstract class DAO<T> {
 protected Connection connect = ConnectionManager.getDbCon().conn;
   
 public DAO(Connection conn){
   this.connect = conn;
 }
   
 /**
 * Méthode de création
 * @param obj
 * @return boolean 
 */
 public abstract T create(T obj);

 /**
 * Méthode pour effacer
 * @param obj
 * @return boolean 
 */
 public abstract boolean delete(T obj);

 /**
 * Méthode de mise à jour
 * @param obj
 * @return boolean
 */
 public abstract T update(T obj);

 /**
 * Méthode de recherche des informations
 * @param id
 * @return T
 */
 public abstract T find(long id);
}