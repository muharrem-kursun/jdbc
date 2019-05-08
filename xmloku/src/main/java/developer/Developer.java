package developer;

public class Developer {
    private int age ;
    private String name;
    private String surName;
    private String gender;
    private String department;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String toString()
    {
        return " name : "+this.name+"  surname : "+this.surName+" age : "+this.age+" gender : "+this.gender+ "  department : "+this.department;
    }
}
