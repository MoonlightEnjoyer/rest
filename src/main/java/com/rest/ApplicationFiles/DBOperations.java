package com.rest.ApplicationFiles;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class DBOperations {
        public void Insert(Response response){
            try {

                String resource = "mybatis-config.xml";
                InputStream inputStream = Resources.getResourceAsStream(resource);
                SqlSessionFactory sqlSessionFactory = new
                        SqlSessionFactoryBuilder().build(inputStream);

                SqlSession session = sqlSessionFactory.openSession();


                session.insert("insertWord", response);
                session.commit();

                //List<LargeCities> list = session.selectList("selectCities");

//                for (LargeCities a : list) {
//                    System.out.println("Rank: " + a.getRank() + " Name: " + a.getName());
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
