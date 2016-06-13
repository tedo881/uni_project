package app.uni.my.myapplication;

/**
 * Created by Ted on 25.04.2016.
 */
public class Score {
    String id, name,professor, group;
    int first_act,sec_act, third_act, first_ex,sec_ex,last_ex,rep_ex;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public int getFirst_act() {
        return first_act;
    }

    public void setFirst_act(int first_act) {
        this.first_act = first_act;
    }

    public int getSec_act() {
        return sec_act;
    }

    public void setSec_act(int sec_act) {
        this.sec_act = sec_act;
    }

    public int getThird_act() {
        return third_act;
    }

    public void setThird_act(int third_act) {
        this.third_act = third_act;
    }

    public int getFirst_ex() {
        return first_ex;
    }

    public void setFirst_ex(int first_ex) {
        this.first_ex = first_ex;
    }

    public int getSec_ex() {
        return sec_ex;
    }

    public void setSec_ex(int sec_ex) {
        this.sec_ex = sec_ex;
    }

    public int getLast_ex() {
        return last_ex;
    }

    public void setLast_ex(int last_ex) {
        this.last_ex = last_ex;
    }

    public int getRep_ex() {
        return rep_ex;
    }

    public void setRep_ex(int rep_ex) {
        this.rep_ex = rep_ex;
    }
}
