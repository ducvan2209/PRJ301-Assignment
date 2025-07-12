/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dell
 */
public class User {
   private int UserID;
   private String EmployeeCode;
   private String FirstName;
   private String LastName;
   private int DepartmentID;

   public  User(){
       
   }
    public User(int UserID, String EmployeeCode, String FirstName, String LastName, int DepartmentID, int ManagerID, String Email, String PassworkHash) {
        this.UserID = UserID;
        this.EmployeeCode = EmployeeCode;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.DepartmentID = DepartmentID;
        this.ManagerID = ManagerID;
        this.Email = Email;
        this.PassworkHash = PassworkHash;
    }
   private int ManagerID;
   private  String Email;
   private String PassworkHash;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getEmployeeCode() {
        return EmployeeCode;
    }

    public void setEmployeeCode(String EmployeeCode) {
        this.EmployeeCode = EmployeeCode;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public int getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(int DepartmentID) {
        this.DepartmentID = DepartmentID;
    }

    public int getManagerID() {
        return ManagerID;
    }

    public void setManagerID(int ManagerID) {
        this.ManagerID = ManagerID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassworkHash() {
        return PassworkHash;
    }

    public void setPassworkHash(String PassworkHash) {
        this.PassworkHash = PassworkHash;
    }

    @Override
    public String toString() {
        return "User{" + "UserID=" + UserID + ", EmployeeCode=" + EmployeeCode + ", FirstName=" + FirstName + ", LastName=" + LastName + ", DepartmentID=" + DepartmentID + ", ManagerID=" + ManagerID + ", Email=" + Email + ", PassworkHash=" + PassworkHash + '}';
    }

    public String getpasswordHash() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
}
