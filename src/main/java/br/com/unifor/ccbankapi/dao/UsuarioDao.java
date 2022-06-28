package br.com.unifor.ccbankapi.dao;

import br.com.unifor.ccbankapi.domain.Usuario;
import br.com.unifor.ccbankapi.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class UsuarioDao {

    private final Session session;

    public UsuarioDao() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

   

    public List<Usuario> findAll() {
        session.beginTransaction();
        try {
            List<Usuario> usuarios = session.createQuery("from Usuario order by id").list();
            session.getTransaction().commit();
            return usuarios;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

    public Usuario findById(Integer id) {
        session.beginTransaction();
        try {
            Usuario usuario = (Usuario) session.createQuery("from Usuario where id = " + id).uniqueResult();
            session.getTransaction().commit();
            return usuario;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

    public Usuario findByLoginAndPasswd(String login, String senha) {
        session.beginTransaction();
        try {
            Query query = session.createQuery("from Usuario where login=:login and senha=:senha");
            query.setParameter("login", login);
            query.setParameter("senha", senha);
            Usuario usuario=(Usuario)query.uniqueResult();
            session.getTransaction().commit();
            return usuario;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

    public Usuario insert(Usuario usuario) {
        session.beginTransaction();
        try {
            session.save(usuario);
            session.getTransaction().commit();
            return usuario;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

    public Usuario update(Usuario usuario) {
        session.beginTransaction();
        try {
            session.update(usuario);
            session.getTransaction().commit();
            return usuario;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

    public Usuario delete(Usuario usuario) {
        session.beginTransaction();
        try {
            session.delete(usuario);
            session.getTransaction().commit();
            return usuario;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

}