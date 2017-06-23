package org.barmaley.parsingStudents;
//

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//

import com.fasterxml.jackson.databind.ObjectMapper;
import org.barmaley.domain.EducProgram;
import org.barmaley.domain.StudentCopy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by impolun on 14.06.17.
 */
public class Auth {
    public static final String WEATHER_URL ="http://docker-svc.spbstu.ru:3007/api/v2/asu/groups";
    private static final Logger log = Logger.getLogger("RESTTest");
    private static final HttpHost targetHost = new HttpHost(
            "docker-svc.spbstu.ru", 3007);
    private static final String targetURL = "http://docker-svc.spbstu.ru:3007/api/v2/asu/specs";
    private static final UsernamePasswordCredentials creds = new UsernamePasswordCredentials(
            "libusr", ":Lkdm$034MdsgaY@");
    private static final RequestConfig globalConfig = RequestConfig.custom()
            .setCookieSpec(CookieSpecs.NETSCAPE).build();
    private static final CloseableHttpClient httpClient = HttpClients.custom()
            .setDefaultRequestConfig(globalConfig).build();
    private static final HttpClientContext localContext = HttpClientContext
            .create();

    public void testAuth() throws Exception {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(targetHost.getHostName(),
                targetHost.getPort()), creds);

        AuthCache authCache = new BasicAuthCache();
        AuthScheme basicAuth = new BasicScheme();
        authCache.put(targetHost, basicAuth);

        localContext.setCredentialsProvider(credsProvider);
        localContext.setAuthCache(authCache);

        HttpGet httpget = new HttpGet(targetURL);
        HttpResponse response = httpClient.execute(httpget, localContext);
        HttpEntity entity = response.getEntity();
        EntityUtils.consumeQuietly(entity);
        log.info("здесь");
        log.info(String.valueOf((response.getStatusLine().getStatusCode())));
        assertTrue(response.getStatusLine().getStatusCode() >= 200
                && response.getStatusLine().getStatusCode() < 300);
        System.out.println("13");
    }
    public String getrequest(String url) throws IOException {
        log.info("Geting request.");
        HttpGet httpget = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpget, localContext);
        assertTrue(response.getStatusLine().getStatusCode() >= 200
                && response.getStatusLine().getStatusCode() < 300);
        HttpEntity entity = response.getEntity();

        return toString(entity);
    }

    public List<StudentCopy>/*Map< StudentCopy, Set<EducProgram >>*/searchRecordXML() throws Exception {

        StudentCopy studentCopy1 = null;
        //Map staff = new HashMap< StudentCopy, Set<EducProgram>>();
        List staff = new ArrayList();
        testAuth();
        String resultJson = getrequest("http://docker-svc.spbstu.ru:3007/api/v2/asu/groups");
        System.out.println("groupsAll= "+resultJson);

        ObjectMapper mapper = new ObjectMapper();
        ListGroups[] listGroups = mapper.readValue(resultJson, ListGroups[].class);
        System.out.println("number= "+listGroups[7826].getNumber());
        for(int i=7825; i<=7826;i++) {
            log.info("i= "+i);
            System.out.println("LENGTH: "+listGroups.length);
            resultJson = getrequest("http://docker-svc.spbstu.ru:3007/api/v2/asu/groups/" + listGroups[i].getId() + "/students");
            System.out.println("groupGet= "+resultJson);
            mapper = new ObjectMapper();
            ListStudentsGroup[] listStudentsGroup = mapper.readValue(resultJson, ListStudentsGroup[].class);


            for(int j=0;j<listStudentsGroup.length;j++) {
                log.info("j= "+j);

                resultJson = getrequest("http://docker-svc.spbstu.ru:3007/api/v2/asu/students/" + listStudentsGroup[j].getStudent());
                System.out.println("studentsAllGroup= "+resultJson);
                mapper = new ObjectMapper();
                Student student = mapper.readValue(resultJson, Student.class);

                resultJson = getrequest("http://docker-svc.spbstu.ru:3007/api/v2/asu/specs/" + student.getSpec());
                System.out.println("studentSpec= "+resultJson);
                mapper = new ObjectMapper();
                Speciality speciality = mapper.readValue(resultJson, Speciality.class);

                if (student.getLogin() != null && (student.getCourse() == 4 && speciality.getLevel_edu().equals("Бакалавр")
                        ||
                        student.getCourse() == 5 && speciality.getLevel_edu().equals("Специалист")
                        ||
                        student.getCourse() == 6 && speciality.getLevel_edu().equals("Магистр"))) {

                    resultJson = getrequest("http://docker-svc.spbstu.ru:3007/api/v2/asu/cards/" + student.getCard());
                    System.out.println("cardStudent= "+resultJson);
                    mapper = new ObjectMapper();
                    StudentCard studentCard = mapper.readValue(resultJson, StudentCard.class);

                    StudentCopy studentCopy = new StudentCopy();
                    Set<EducProgram> educProgramsStudent = new HashSet<>();

                    studentCopy.setUsername((String.valueOf(studentCard.getId())));
                    studentCopy.setSurname(studentCard.getLast_name());
                    studentCopy.setFirstName(studentCard.getFirst_name());
                    studentCopy.setSecondName(studentCard.getMiddle_name());
                    studentCopy.setPassword("123");

                    EducProgram educProgram = new EducProgram();
//            educProgram.setInstitute();
//            educProgram.setDepartment();
                    educProgram.setId(student.getId());
                    educProgram.setStudent(studentCopy);
                    educProgram.setDirection(speciality.getName());
                    educProgram.setDirectionCode(speciality.getCode());
                    educProgram.setDegree(speciality.getLevel_edu());
                    educProgram.setGroupNum(listGroups[i].getNumber());
                    // educProgram.setId(6);
                    educProgramsStudent.add(educProgram);
                    studentCopy.setEducPrograms(educProgramsStudent);
                    staff.add(studentCopy);
                    //staff.put(studentCopy, educProgramsStudent);
                    //studentCopy.setEducPrograms(educProgramsStudent);
                    //System.out.println("sizeEducPrograms= "+studentCopy.getEducPrograms().size());
                }
            }
        }

        return staff;
    }

    public void test(ListGroups[] obj) throws Exception {
        testAuth();

        HttpGet httpget = new HttpGet(
                "http://docker-svc.spbstu.ru:3007/api/v2/asu/groups/"+obj[0].getId()+"/students");
        HttpResponse response = httpClient.execute(httpget, localContext);

        assertTrue(response.getStatusLine().getStatusCode() >= 200
                && response.getStatusLine().getStatusCode() < 300);

        HttpEntity entity = response.getEntity();
        String entity1 = response.getEntity().toString();
        log.info("Response:");
        String resultJson = toString(entity);
        //log.info("result JSON= "+resultJson);
        System.out.println("result JSON= "+resultJson);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "{'name' : 'mkyong'}";
        System.out.println("xaaxaxaxaxa");
        ListStudentsGroup[] obj1 = mapper.readValue(resultJson, ListStudentsGroup[].class);

        System.out.println("objSize= "+obj.length);
        System.out.println(obj[2].getNumber());
        List<ListGroups> list = new ArrayList();



        //list.add(obj[i]);

    }

    public static String toString(HttpEntity entity)
            throws UnsupportedEncodingException, IllegalStateException,
            IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(
                entity.getContent(), "UTF-8"));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null)
            result.append(line);

        return result.toString();
    }
}
