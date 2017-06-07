package org.barmaley.service;

import org.apache.log4j.Logger;

import org.barmaley.domain.StudentCopy;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.barmaley.domain.EducProgram;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by gagar on 28.04.2017.
 */

@Service("educProgramService")
@Transactional
public class EducProgramService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EmployeeCopyService.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    SessionFactory sessionFactory;

    public List<EducProgram> getAll(String username){
        Session session = sessionFactory.getCurrentSession();
        logger.debug("Get users by institute");
        EducProgram educProgram = new EducProgram();
        SQLQuery query = session.createSQLQuery("SELECT * FROM EDUC_PROGRAM " +
                "WHERE ID IN(SELECT EDUC_PROGRAM_ID FROM STUDENT_EDUC_PROGRAMS WHERE STUDENT_ID='"+username+"')");
        query.addEntity(EducProgram.class);
        return query.list();
    }

    public EducProgram get(Integer id){
        Session session = sessionFactory.getCurrentSession();

        EducProgram educProgram = (EducProgram) session.get(EducProgram.class, id);

        return educProgram;
    }


    public EducProgram getByGroupNum(String groupNum){
        Session session = sessionFactory.getCurrentSession();


        Query query = session.createQuery("FROM EducProgram AS EP WHERE groupNum= '"+groupNum + "'");

        return (EducProgram) query.uniqueResult();
    }

    //Добавление данных
    public void add(StudentCopy studentCopy) {
        Session session = sessionFactory.getCurrentSession();
        session.save(studentCopy);
        session.flush();
    }
}
