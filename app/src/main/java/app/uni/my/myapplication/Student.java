package app.uni.my.myapplication;

/**
 * Created by Ted on 07.03.2016.
 */
public class Student {
    private String id;
    private String user_id;
    private String name;
    private String surname;
    private String user_pass;
    private String sqesi;
    private String birth;
    private String address;
    private String fakulteti;
    private String phone;
    private String jgupi;
    private String email;
    private String photo;
    private String semester;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setId(String id) {        this.id = id;    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public void setSqesi(String sqesi) {
        this.sqesi = sqesi;
    }

    public String getJgupi() {
        return jgupi;
    }

    public void setJgupi(String jgupi) {
        this.jgupi = jgupi;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFakulteti(String fakulteti) {
        this.fakulteti = fakulteti;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public String getSqesi() {
        return sqesi;
    }

    public String getBirth() {
        return birth;
    }

    public String getAddress() {
        return address;
    }

    public String getFakulteti() {
        return fakulteti;
    }

    public String getPhone() {
        return phone;
    }
}
