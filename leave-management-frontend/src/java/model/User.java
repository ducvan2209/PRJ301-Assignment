package model;

public class User {
    private int userID;
    private String employeeCode;
    private String firstName;
    private String lastName;
    private int departmentID;
    private Integer managerID; // nullable
    private String email;
    private String passwordHash;

    public User() {
    }

    public User(int userID, String employeeCode, String firstName, String lastName, int departmentID, Integer managerID, String email, String passwordHash) {
        this.userID = userID;
        this.employeeCode = employeeCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentID = departmentID;
        this.managerID = managerID;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public Integer getManagerID() {
        return managerID;
    }

    public void setManagerID(Integer managerID) {
        this.managerID = managerID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", employeeCode='" + employeeCode + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", departmentID=" + departmentID +
                ", managerID=" + managerID +
                ", email='" + email + '\'' +
                '}';
    }
}
