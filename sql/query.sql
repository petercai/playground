-- select * from employee

 -- EmployeeDetails

-- EmpId	FullName		ManagerId	DateOfJoining
-- 121		John Snow		321			01/31/2014
-- 321		Walter White	986			01/30/2015
-- 421		Kuldeep Rana	876			27/11/2016


-- EmployeeSalary
-- EmpId	Project	Salary
-- 121		P1		8000
-- 321		P2		1000
-- 421		P1		12000



 -- fetch the count of employees working in project ‘P1’.
 select count(*) from EmployeeSalary where Project = 'P1'
 
 -- o fetch employee names having a salary greater than or equal to 5000 and less than or equal 10000
 select FullName from EmployeeDetails d join EmployeeSalary s ON d.EmpId = s.EmpId where s.Salary BETWEEN 5000 AND 10000
 
 -- second highest salary employee
 select 
	MAX(Salary) -- 2nd high
 from Employee 
 WHERE 
	Salary NOT IN (	
		select MAX(Salary) from Employee -- 1st hight
		); 
 
  SELECT DeptName, MAX(Salary) FROM Employee e RIGHT JOIN Department d ON e.DeptId = d.DeptID GROUP BY DeptName;


select SYSDATA FROM DUAL;
select SYSTIMESTAMP FROM dual;
select to_char(sysdata, 'YYYY-MON-DD HH:MI:SS') FROM DUAL;

-- QUERY employee and their manager
SELECT e.name, m.name FROM Employee e, Employee m WHERE e.mgr_id = m.emp_id;

-- find the 3rd highest salary
SELECT Salary
FROM EmployeeSalary Emp1
WHERE 2 = (
                SELECT COUNT( DISTINCT ( Emp2.Salary ) )
                FROM EmployeeSalary Emp2
                WHERE Emp2.Salary > Emp1.Salary
            )