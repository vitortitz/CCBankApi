package br.com.unifor.ccbankapi.dao;

import br.com.unifor.ccbankapi.domain.Pessoa;
import br.com.unifor.ccbankapi.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class PessoaDao {

    private final Session session;
    
    public PessoaDao() {
        session = HibernateUtil.getSessionFactory().openSession();
    }
     

 

    public Pessoa findById(Integer id){
        session.beginTransaction();
        try{
            Pessoa pessoa = (Pessoa)session.createQuery("from Pessoa where id = " + id).uniqueResult();
            session.getTransaction().commit();
            return pessoa;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }
    }
    
    

    public Pessoa insert(Pessoa pessoa){
        session.beginTransaction();
        try{
            session.save(pessoa);
            session.getTransaction().commit();
            return pessoa;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }
    }

    public Pessoa update(Pessoa pessoa){
        session.beginTransaction();
        try{
            session.update(pessoa);
            session.getTransaction().commit();
            return pessoa;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }
    }

    public Pessoa delete(Pessoa pessoa){
        session.beginTransaction();
        try{
            session.delete(pessoa);
            session.getTransaction().commit();
            return pessoa;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }
    }
}