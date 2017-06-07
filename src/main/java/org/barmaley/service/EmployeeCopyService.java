package org.barmaley.service;

import org.barmaley.domain.EmployeeCopy;
import org.barmaley.domain.StudentCopy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Service("employeeCopyService")
@Transactional
public class EmployeeCopyService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeCopyService.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    SessionFactory sessionFactory;

    public EmployeeCopy get(String username) {
        Session session = sessionFactory.getCurrentSession();
        EmployeeCopy employeeCopy = (EmployeeCopy) session.get(EmployeeCopy.class, username);

        return employeeCopy;
    }

    //Добавление данных координатора в базу
    public void add(EmployeeCopy employeeCopy) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employeeCopy);
        session.flush();
    }


}