package org.barmaley.service;

import org.apache.log4j.Logger;
import org.barmaley.domain.StudentCopy;
import org.barmaley.parsing.StudentParsingTxt;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by SUN_SUN on 06.05.2017.
 */
@Component
@Service("studentCopyService")
@Transactional
public class StudentCopyService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EmployeeCopyService.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    SessionFactory sessionFactory;


    public StudentCopy get(String username) {
        logger.debug("Editing existing studentCopy");

        // Retrieve session from Hibernate
        // как всегда получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing person via id
        // получаем существующую персону по id
        StudentCopy studentCopy = (StudentCopy) session.get(StudentCopy.class, username);

        // Assign updated values to this person
        // обновляем значения
        // Save updates
        // сохраняем изменения
        return studentCopy;
    }

    //Добавление данных студента в базу
    public void add(StudentCopy studentCopy) {
        Session session = sessionFactory.getCurrentSession();
        session.save(studentCopy);
        session.flush();
    }

//    public List<StudentCopy> getStudentByEducProgram(String groupNum){
//
//        Session session = sessionFactory.getCurrentSession();
//
//        Query query = session.createQuery("FROM StudentCopy as SC LEFT JOIN FETCH SC.educPrograms as EP where EP.groupNum = '"+groupNum +
//                "' AND SC.username NOT IN( SELECT extId FROM Users) ORDER BY surname");
//
//        return query.list();
//    }

}

