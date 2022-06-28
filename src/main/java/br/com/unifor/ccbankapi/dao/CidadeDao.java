package br.com.unifor.ccbankapi.dao;

import br.com.unifor.ccbankapi.domain.Cidade;
import br.com.unifor.ccbankapi.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class CidadeDao {

    private final Session session;
    
    public CidadeDao() {
        session = HibernateUtil.getSessionFactory().openSession();
    }
     

    public List<Cidade> findAll(){
        session.beginTransaction();
        try{
            List<Cidade> cidades = session.createQuery("from Cidade order by nome").list();
            session.getTransaction().commit();
            return cidades;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }
    }

    public Cidade findById(Integer id){
        session.beginTransaction();
        try{
            Cidade cidade = (Cidade)session.createQuery("from Cidade where id = " + id).uniqueResult();
            session.getTransaction().commit();
            return cidade;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }
    }

    public Cidade insert(Cidade cidade){
        session.beginTransaction();
        try{            
            session.save(cidade);
            session.getTransaction().commit();
            System.out.print(cidade.getId());
            return cidade;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }
    }

    public Cidade update(Cidade cidade){
        session.beginTransaction();
        try{
            session.update(cidade);
            session.getTransaction().commit();
            return cidade;
        }catch(Exception e){
            session.getTransaction().rollback();
            return cidade;
        }
    }

    public Cidade delete(Cidade cidade){
        session.beginTransaction();
        try{
            session.delete(cidade);
            session.getTransaction().commit();
            return cidade;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }
    }
}
