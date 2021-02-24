select user(),database();

show tables;

desc title;
desc department;
desc employee;

select empno,empname,title,manager,salary,dept from employee;
select deptno,deptname,floor from department;
select tno,tname from title;
select tno,tname from title  where tno = 3;
insert into title values(6,"인턴");
delete from title where tno = 6;
update title set tname = "계약직" where tno = 6;

select * from title;
desc department ;
select deptno,deptname,floor from department;

select deptno,deptname from department where deptno = 4;

insert into department values (5,'인사',6);
delete from department where deptno = 5;
update department set deptname = '관리' where deptno = 5;
-- 
insert into employee values (1002,"수지",5,3426,1500000,2);
select empno,empname,title,manager,salary,dept from employee;
desc employee;

delete from employee where empno = 1002;
select empno,empname,title,manager,salary,dept from employee where empno = 1365;

update employee set manager = 4377 where empno = 1002;


-- 
CREATE OR REPLACE VIEW VW_EMPLOYEE
(EMP_NO, EMP_NAME, TNO, TITLE_NAME, MANAGER, MGR_NAME, SALARY, DNO, DEPT_NAME, FLOOR)



select * from title;

select * from employee;

create or replace view vw_employee
(empno,empname,title,tname,manager,mgrName,salary,dept,deptName,floor)
as
select e.empno, e.empname, t.tno, t.tname, m.empno ,m.empname ,e.salary,d.deptno ,d.deptname,d.floor 
  from employee e join title t on e.title = t.tno
  left join employee m on e.manager = m.empno
  join department d on e.dept =d.deptno;
  

select empno,empname,title,tname,manager,mgrName,salary,dept,deptName,floor from vw_employee;
select * from vw_employee;
drop view vw_employee ;


select * from employee;
select * from department;
select * from title;

-- join
create or replace view vw_full_employee 
as
select e.empno,e.empname
       ,t.tno as title_no
       ,t.tname as title_name
       ,e.manager as manager_no
       , m.empname as manager_name
       ,e.salary
       ,d.deptNo,d.deptname
       ,floor
  from employee e join title t on e.title = t.tno
  	   left join employee m on e.manager = m.empno
  	   join department d on e.dept = d.deptno;

select empno,empname,title_no,title_name,manager_no,manager_name,salary,deptNo,deptname,floor from vw_full_employee;

select empno,empname,title as title_no,manager as manager_no,salary,dept as deptno from employee;

select * from employee;
 
select * from department;

delete  from department where deptno = 45;

desc employee;



 
-- 
select tno,tname from title;
select deptno,deptname,floor from department;

delete from title where tno = 6;


delete from department where deptno = 5;





-- 부서가 1인 사원정보를 출력
select empno,empname,title as title_no ,manager as manager_no ,salary,deptno 
  from department d join employee e on d.deptno = e.dept
 where deptno = 1;



select empno,empname,title as title_no ,manager as manager_no ,salary,dept as deptno from employee where dept = (select deptNo from department where deptNo = 1)

 
select * from vw_deptemp;

drop view vw_deptemp;














