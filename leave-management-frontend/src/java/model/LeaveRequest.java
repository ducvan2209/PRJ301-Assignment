package model;

import java.sql.Date;

public class LeaveRequest {
    private int requestID;
    private int userID;
    private Date startDate,endDate;
    private String Reason;
    private String status;
    private Integer approverID; // nullable

    public LeaveRequest() {
    }

    public LeaveRequest(int requestID, int userID, Date startDate, Date endDate, String reason, String status, Integer approverID) {
        this.requestID = requestID;
        this.userID = userID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.Reason = reason;
        this.status = status;
        this.approverID = approverID;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        this.Reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getApproverID() {
        return approverID;
    }

    public void setApproverID(Integer approverID) {
        this.approverID = approverID;
    }

    @Override
    public String toString() {
        return "LeaveRequest{" +
                "requestID=" + requestID +
                ", userID=" + userID +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                ", approverID=" + approverID +
                '}';
    }
}
