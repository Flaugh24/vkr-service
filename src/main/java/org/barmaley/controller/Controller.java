package org.barmaley.controller;


import org.barmaley.domain.EmployeeCopy;
import org.barmaley.service.EmployeeCopyService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;


@Component
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger("controller");

    @Resource(name = "employeeCopyService")
    private EmployeeCopyService employeeCopyService;

    @Scheduled(fixedRate = 5000)
    public void getEmployee(){
        EmployeeCopy employeeCopy  = employeeCopyService.get("vladimir");

        logger.info(employeeCopy.getSurname() + " " + employeeCopy.getFirstName() + " " + employeeCopy.getSecondName());
    }
}
