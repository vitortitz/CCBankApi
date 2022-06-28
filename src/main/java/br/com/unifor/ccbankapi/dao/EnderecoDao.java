package br.com.unifor.ccbankapi.dao;

import br.com.unifor.ccbankapi.domain.Endereco;
import br.com.unifor.ccbankapi.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class EnderecoDao {

    private final Session session;

    public EnderecoDao() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public List<Endereco> findAll() {
        session.beginTransaction();
        try {
            List<Endereco> Enderecos = session.createQuery("from Endereco order by nome").list();
            session.getTransaction().commit();
            return Enderecos;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

    public Endereco findById(Integer id) {
        session.beginTransaction();
        try {
            Endereco Endereco = (Endereco) session.createQuery("from Endereco where id = " + id).uniqueResult();
            session.getTransaction().commit();
            return Endereco;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

    public Endereco insert(Endereco Endereco) {
        session.beginTransaction();
        try {
            session.save(Endereco);
            session.getTransaction().commit();
            System.out.print(Endereco.getId());
            return Endereco;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

    public Endereco update(Endereco Endereco) {
        session.beginTransaction();
        try {
            session.update(Endereco);
            session.getTransaction().commit();
            return Endereco;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return Endereco;
        }
    }

    public Endereco delete(Endereco Endereco) {
        session.beginTransaction();
        try {
            session.delete(Endereco);
            session.getTransaction().commit();
            return Endereco;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }
}
