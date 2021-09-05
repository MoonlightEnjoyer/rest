package com.rest.ApplicationFiles;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

@Component
public class DAO {
    public Response findById(String word){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Response.class,word);
    }

    public void Save(Response response){
        Session session=HibernateSessionFactoryUtil.getSessionFactory().openSession();
        //Transaction tx1=session.beginTransaction();
        session.beginTransaction();
        session.save(response);
        session.getTransaction().commit();
        //tx1.commit();
        session.close();
    }


}
