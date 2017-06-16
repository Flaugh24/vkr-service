package org.barmaley.parsingStudents;
/**
 * Created by impolun on 14.06.17.
 */
public class Student {
    int id;
    int card;
    String dep;
    int group;
    int form_edu;
    boolean contract;
    int spec;
    int course;
    String in_date;
    int state;
    boolean purpose;
    String purpose_org;
    String edit_date;
    String recordbook;
    String login;

    //------------------



    public String getIn_date() {
        return in_date;
    }

    public void setIn_date(String in_date) {
        this.in_date = in_date;
    }

    public boolean isPurpose() {
        return purpose;
    }

    public void setPurpose(boolean purpose) {
        this.purpose = purpose;
    }

    public String getPurpose_org() {
        return purpose_org;
    }

    public void setPurpose_org(String purpose_org) {
        this.purpose_org = purpose_org;
    }

    public String getEdit_date() {
        return edit_date;
    }

    public void setEdit_date(String edit_date) {
        this.edit_date = edit_date;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCard() {
        return card;
    }

    public void setCard(int card) {
        this.card = card;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getForm_edu() {
        return form_edu;
    }

    public void setForm_edu(int form_edu) {
        this.form_edu = form_edu;
    }

    public boolean isContract() {
        return contract;
    }

    public void setContract(boolean contract) {
        this.contract = contract;
    }

    public int getSpec() {
        return spec;
    }

    public void setSpec(int spec) {
        this.spec = spec;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRecordbook() {
        return recordbook;
    }

    public void setRecordbook(String recordbook) {
        this.recordbook = recordbook;
    }
}
