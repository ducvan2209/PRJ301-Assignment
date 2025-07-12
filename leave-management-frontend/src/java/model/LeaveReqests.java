/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Dell
 */
public class LeaveReqests {
    private int RequistID, UserID;
    private Date StaDate,EndDate;
    private String Reason;
    private String Status;
    private int ApproverID;

    public LeaveReqests(){
        
    }
    public LeaveReqests(int RequistID, int UserID, Date StaDate, Date EndDate, String Reason, String Status, int ApproverID) {
        this.RequistID = RequistID;
        this.UserID = UserID;
        this.StaDate = StaDate;
        this.EndDate = EndDate;
        this.Reason = Reason;
        this.Status = Status;
        this.ApproverID = ApproverID;
    }

    public int getRequistID() {
        return RequistID;
    }

    public void setRequistID(int RequistID) {
        this.RequistID = RequistID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public Date getStaDate() {
        return StaDate;
    }

    public void setStaDate(Date StaDate) {
        this.StaDate = StaDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date EndDate) {
        this.EndDate = EndDate;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public int getApproverID() {
        return ApproverID;
    }

    public void setApproverID(int ApproverID) {
        this.ApproverID = ApproverID;
    }

    @Override
    public String toString() {
        return "LeaveReqests{" + "RequistID=" + RequistID + ", UserID=" + UserID + ", StaDate=" + StaDate + ", EndDate=" + EndDate + ", Reason=" + Reason + ", Status=" + Status + ", ApproverID=" + ApproverID + '}';
    }
    
} 



