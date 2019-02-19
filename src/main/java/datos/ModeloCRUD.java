package datos;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pojos.Arma;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class ModeloCRUD<T> {
    T ultimoBorrado;

    public ModeloCRUD() {
        conectar();
    }

    public void conectar() {
        HibernateUtil.buildSessionFactory();
    }

    public void desconectar() {
        HibernateUtil.closeSessionFactory();
    }

    // CREATE
    public boolean guardar(T dato){
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(dato);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    // READ
    public List<T> cogerTodo() {
        Session session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery("FROM Arma");
        ArrayList<Arma> armas = (ArrayList<Arma>) query.list() ;
        session.close();
        return (List<T>) armas;
    }
    
    public List<T> coger(String busqueda) {
        Session session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery("FROM Arma a WHERE a.nombre LIKE '%:busqueda%'");
        query.setParameter("busqueda", busqueda);
        ArrayList<Arma> armas = (ArrayList<Arma>) query.list();
        session.close();
        return (List<T>) armas;
    }

    // UPDATE
    public boolean modificar(T dato) {
        guardar(dato);
        return true;
    }

    // DELETE
    public T eliminar(T dato) {
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.delete(dato);
        session.getTransaction().commit();
        session.close();
        ultimoBorrado = dato;
        return dato;
    }
    
    public boolean eliminarTodo() {
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM Arma").executeUpdate();
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    public T deshacer() {
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(ultimoBorrado);
        session.getTransaction().commit();
        session.close();
        return ultimoBorrado;
    }
}
