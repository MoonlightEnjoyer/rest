package com.rest.ApplicationFiles;

import com.rest.exceptions.InternalException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class HibernateSessionFactoryUtil {
    static Logger logger;

    static {
        try(FileInputStream inputStream=new FileInputStream("C:\\Users\\artio\\IdeaProjects\\rest\\src\\main\\java\\com\\rest\\logger\\logger.properties")){
            //try (FileInputStream inputStream = new FileInputStream("C:\\Users\\Artem\\Desktop\\rest\\src\\main\\java\\com\\rest\\logger\\logger.properties")) {
            LogManager.getLogManager().readConfiguration(inputStream);
            logger = Logger.getLogger(Controller.class.getName());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    private static SessionFactory sessionFactory;
    private HibernateSessionFactoryUtil(){}

    public static SessionFactory getSessionFactory(){
        if(sessionFactory==null){
            try{
                Configuration configuration=new Configuration().configure();
                configuration.addAnnotatedClass(Response.class);
                StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory=configuration.buildSessionFactory(builder.build());
            }
            catch (Exception exception){
                logger.log(Level.SEVERE,exception.getMessage());
                throw new InternalException("Internal exception occurred(Exception). Details: "+exception.getMessage());
            }
        }
        return sessionFactory;
    }
}
