

-- Table: Users (Employees)
CREATE TABLE Users (
    UserID INT PRIMARY KEY IDENTITY(1,1), -- Auto-incrementing ID
    EmployeeCode VARCHAR(50) UNIQUE NOT NULL,  -- Unique identifier for the employee
    FirstName NVARCHAR(50) NOT NULL,
    LastName NVARCHAR(50) NOT NULL,
    DepartmentID INT NOT NULL, -- Foreign key to Departments table
    ManagerID INT NULL,       -- Self-referencing foreign key (reports to another user)
    Email VARCHAR(100) UNIQUE,
    PasswordHash VARCHAR(255),  -- Store hashed passwords if using SQL Authentication.  Otherwise, this would be null.
    CONSTRAINT FK_Users_Departments FOREIGN KEY (DepartmentID) REFERENCES Departments(DepartmentID)
);


-- Table: Departments
CREATE TABLE Departments (
    DepartmentID INT PRIMARY KEY IDENTITY(1,1),
    DepartmentName NVARCHAR(50) NOT NULL
);

-- Table: Roles
CREATE TABLE Roles (
    RoleID INT PRIMARY KEY IDENTITY(1,1),
    RoleName NVARCHAR(50) UNIQUE NOT NULL -- e.g., 'Division Leader', 'Team Lead', 'Employee'
);

-- Table: UserRoles (Many-to-many relationship between Users and Roles)
CREATE TABLE UserRoles (
    UserID INT NOT NULL,
    RoleID INT NOT NULL,
    PRIMARY KEY (UserID, RoleID),
    CONSTRAINT FK_UserRoles_Users FOREIGN KEY (UserID) REFERENCES Users(UserID),
    CONSTRAINT FK_UserRoles_Roles FOREIGN KEY (RoleID) REFERENCES Roles(RoleID)
);

-- Table: Features
CREATE TABLE Features (
    FeatureID INT PRIMARY KEY IDENTITY(1,1),
    FeatureName NVARCHAR(50) UNIQUE NOT NULL -- e.g., '/request/create', '/request/modify'
);

-- Table: RoleFeatures (Many-to-many relationship between Roles and Features)
CREATE TABLE RoleFeatures (
    RoleID INT NOT NULL,
    FeatureID INT NOT NULL,
    PRIMARY KEY (RoleID, FeatureID),
    CONSTRAINT FK_RoleFeatures_Roles FOREIGN KEY (RoleID) REFERENCES Roles(RoleID),
    CONSTRAINT FK_RoleFeatures_Features FOREIGN KEY (FeatureID) REFERENCES Features(FeatureID)
);

-- Table: LeaveRequests
CREATE TABLE LeaveRequests (
    RequestID INT PRIMARY KEY IDENTITY(1,1),
    UserID INT NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    Reason NVARCHAR(255) NULL,
    Status VARCHAR(50) NOT NULL DEFAULT 'InProgress', -- e.g., 'InProgress', 'Approved', 'Rejected'
    ApproverID INT NULL,  -- Foreign key to User who approved the request
    CONSTRAINT FK_LeaveRequests_Users FOREIGN KEY (UserID) REFERENCES Users(UserID),
    CONSTRAINT FK_LeaveRequests_Users2 FOREIGN KEY (ApproverID) REFERENCES Users(UserID) -- Approver is also a user.
);

