package org.barmaley.controller;


import org.barmaley.domain.EducProgram;
import org.barmaley.domain.EmployeeCopy;
import org.barmaley.domain.StudentCopy;
import org.barmaley.parsingEmployee.Otdel;
import org.barmaley.parsingEmployee.Person;
import org.barmaley.parsingEmployee.ReadXML;
import org.barmaley.parsingEmployee.StudentParsingTxt;
import org.barmaley.parsingStudents.Auth;
import org.barmaley.service.EducProgramService;
import org.barmaley.service.EmployeeCopyService;
import org.barmaley.service.StudentCopyService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;


@Component
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger("controller");

    @Resource(name = "employeeCopyService")
    private EmployeeCopyService employeeCopyService;

    @Resource(name = "studentCopyService")
    private StudentCopyService studentCopyService;

    @Resource(name = "educProgramService")
    private EducProgramService educProgramService;

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
  //  @Scheduled(fixedRate = 5000)
    public void testParsingTxt() throws IOException {

//        List<String> listParsing = parsingTxt.parseTxtFile3();
//        StudentCopy studentCopy = new StudentCopy();
//        studentCopy.setUsername(s+1);
//        studentCopy.setSurname(listParsing.get(0));
//        studentCopy.setFirstName(listParsing.get(1));
//        studentCopy.setSecondName(listParsing.get(2));
//        studentCopyService.add(studentCopy);


    }
    ArrayList<Person> allPerson; //Список всех сотрудников.
    ArrayList<Otdel> allOtdel;   //Список всех отделов.

    //@Scheduled(fixedRate = 500000000)
    public void testParsingXml(){
        logger.info("Я зашел");
        //allPerson = ReadXML.readAllPerson("/home/impolun/git/service/VKR-service/kadry.xml"); //Чтение списка сотрудников.
        //allOtdel = ReadXML.readAllOtdel("/home/impolun/git/service/VKR-service/otdel.xml");   //Чтение списка отделов.
        allPerson = ReadXML.readAllPerson("/home/impolun/github/VKR-service/kadry.xml"); //Чтение списка сотрудников.
        allOtdel = ReadXML.readAllOtdel("/home/impolun/github/VKR-service/otdel.xml");   //Чтение списка отделов.
        for (Person person : allPerson) {
            //Получаем номер отдела сотрудника.
            int number = person.getNumberOtdel();
            //Цикл по отделам.
            for (Otdel otdel : allOtdel) {
                //Если номер отдела и сотрудника совпал, то...
                if (number == otdel.getNumber()) {
                    //Заменяем номер отдела на его название.
                    person.setField("OTDEL", otdel.getName());
                    //Выходим из цикла, так как нашли нужнный отдел.
                    break;
                }
            }
        }
        for(int i=0; i<allPerson.size();i++){
            Person person = allPerson.get(i);
            EmployeeCopy employeeCopy = new EmployeeCopy();
            employeeCopy.setUsername(person.getId());
            employeeCopy.setSurname(person.getSurname());
            String s = person.getFirstSecondname();
            String[]s1 = s.split(" ");
            System.out.println("sizeArrayS1= "+s1.length);

            if(s1.length==2){
                System.out.println("xe= "+s1[0]);
                System.out.println("xe1= "+s1[1]);
                employeeCopy.setFirstName(s1[0]);
                employeeCopy.setSecondName(s1[1]);
            }
            else{
                System.out.println("xe= "+s1[0]);
                employeeCopy.setFirstName(s1[0]);
            }

            employeeCopy.setPosition(person.getPosition());
            employeeCopyService.add(employeeCopy);
        }
        // List<Person> list = parsingTxt.parseXMLFile();
       // logger.info(String.valueOf(list.get(0).getNumberOtdel()));
        logger.info("Я вышел");
    }

    @Scheduled(fixedRate = 500000000)
    public void test() throws Exception {
        Auth auth = new Auth();
       // Map< StudentCopy, Set<EducProgram >> staff= auth.searchRecordXML();
        List<StudentCopy> staff = auth.searchRecordXML();
        //Set<Map.Entry< StudentCopy, Set<EducProgram>>> set = staff.entrySet();
        for(int i=0; i<staff.size();i++){
            studentCopyService.add(staff.get(i));
        }
//        for (Map.Entry< StudentCopy, Set<EducProgram>> me : set) {
//            StudentCopy studentCopy = (StudentCopy)me.getKey();
//            studentCopyService.add(studentCopy);
//            Set<EducProgram> educPrograms = (Set<EducProgram>)me.getValue();
//            //for(EducProgram educProgram : educPrograms){
//               // educProgramService.add(educPrograms);
//            //}
//            studentCopy.setEducPrograms(educPrograms);
////            studentCopyService.edit(studentCopy);
//        }
    }
}
