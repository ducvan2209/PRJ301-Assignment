-- Insert Departments
INSERT INTO Departments (DepartmentName) VALUES ('IT'), ('QA'), ('Sales');

-- Insert Roles
INSERT INTO Roles (RoleName) VALUES ('Division Leader'), ('Team Lead'), ('Employee');

-- Insert Features
INSERT INTO Features (FeatureName) VALUES ('/request/create', '/request/modify', '/request/review', '/request/list', '/home');

-- Example User Data (Replace with actual data)
INSERT INTO Users (EmployeeCode, FirstName, LastName, DepartmentID, ManagerID, Email)
VALUES
('EMP001', 'Mr. A', 'Division Leader', 1, NULL, 'a@example.com'),
('EMP002', 'Mr. B', 'Team Lead', 1, 1, 'b@example.com'),
('EMP003', 'Mr. C', 'Employee', 1, 2, 'c@example.com');

-- Assign Roles to Users (Example)
INSERT INTO UserRoles (UserID, RoleID) VALUES (1, 1); -- Mr. A is a Division Leader
INSERT INTO UserRoles (UserID, RoleID) VALUES (2, 2); -- Mr. B is a Team Lead
INSERT INTO UserRoles (UserID, RoleID) VALUES (3, 3); -- Mr. C is an Employee

-- Assign Features to Roles (Example - Division Leader has all features)
INSERT INTO RoleFeatures (RoleID, FeatureID)
SELECT 1, FeatureID FROM Features;  -- All features for RoleID = 1 (Division Leader)
