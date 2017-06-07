package org.barmaley.domain;

import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Set;


@Entity
@Table(name="STUDENT_COPY")
@EnableScheduling
public class StudentCopy implements Serializable {

    @Id
    @Column(name ="USERNAME")
    private String username;

    @Column(name ="PASSWORD")
    private String password;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "SECOND_NAME")
    private String secondName;

    @ManyToMany
    @JoinTable(name = "STUDENT_EDUC_PROGRAMS", joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "EDUC_PROGRAM_ID"))
    private Set<EducProgram> educPrograms;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Set<EducProgram> getEducPrograms() {
        return educPrograms;
    }

    public void setEducPrograms(Set<EducProgram> educPrograms) {
        this.educPrograms = educPrograms;
    }
}
