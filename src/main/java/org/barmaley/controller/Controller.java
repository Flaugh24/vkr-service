package org.barmaley.controller;


import org.barmaley.domain.EmployeeCopy;
import org.barmaley.domain.StudentCopy;
import org.barmaley.parsing.StudentParsingTxt;
import org.barmaley.service.EmployeeCopyService;
import org.barmaley.service.StudentCopyService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;


@Component
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger("controller");

    @Resource(name = "employeeCopyService")
    private EmployeeCopyService employeeCopyService;

    @Resource(name = "studentCopyService")
    private StudentCopyService studentCopyService;

    String s = "";

    StudentParsingTxt parsingTxt = new StudentParsingTxt();

    //@Scheduled(fixedRate = 5000)
    public void getEmployee(){
        EmployeeCopy employeeCopy  = employeeCopyService.get("vladimir");

        logger.info(employeeCopy.getSurname() + " " + employeeCopy.getFirstName() + " " + employeeCopy.getSecondName());
    }

   // @Scheduled(fixedRate = 5000)
    public void getStudent(){
        StudentCopy studentCopy = studentCopyService.get("gagarkin");

        logger.info(studentCopy.getSurname() + " " + studentCopy.getFirstName() + " " + studentCopy.getSecondName());
    }
    @Scheduled(fixedRate = 5000)
    public void testParsingTxt() throws IOException {
        List<String> listParsing = parsingTxt.parseTxtFile3();
        StudentCopy studentCopy = new StudentCopy();
        studentCopy.setUsername(s+1);
        studentCopy.setSurname(listParsing.get(0));
        studentCopy.setFirstName(listParsing.get(1));
        studentCopy.setSecondName(listParsing.get(2));
        studentCopyService.add(studentCopy);

    }

}
