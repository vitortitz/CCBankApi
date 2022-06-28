package br.com.unifor.ccbankapi.dao;

import br.com.unifor.ccbankapi.domain.Conta;
import br.com.unifor.ccbankapi.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class ContaDao {

    private final Session session;

    public ContaDao() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public List<Conta> findAll() {
        session.beginTransaction();
        try {
            List<Conta> Contas = session.createQuery("from Conta order by nome").list();
            session.getTransaction().commit();
            return Contas;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

    public Conta findById(Integer id) {
        session.beginTransaction();
        try {
            Conta Conta = (Conta) session.createQuery("from Conta where id = " + id).uniqueResult();
            session.getTransaction().commit();
            return Conta;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

    public Conta insert(Conta Conta) {
        session.beginTransaction();
        try {
            session.save(Conta);
            session.getTransaction().commit();
            System.out.print(Conta.getId());
            return Conta;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

    public Conta update(Conta Conta) {
        session.beginTransaction();
        try {
            session.update(Conta);
            session.getTransaction().commit();
            return Conta;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return Conta;
        }
    }

    public Conta delete(Conta Conta) {
        session.beginTransaction();
        try {
            session.delete(Conta);
            session.getTransaction().commit();
            return Conta;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }
}
