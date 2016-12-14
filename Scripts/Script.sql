select * from department;
select * from employee;
select * from title;
/*

drop table department;
drop table employee;
drop table title;
*/

-- MyERP
DROP SCHEMA IF EXISTS `erp`;

-- MyERP
CREATE SCHEMA `erp`;

-- 부서
CREATE TABLE `erp`.`department` (
	`deptno`   INTEGER     NOT NULL COMMENT '부서번호', -- 부서번호
	`deptname` VARCHAR(20) NOT NULL COMMENT '부서명', -- 부서명
	`floor`    INTEGER     NULL     COMMENT '위치', -- 위치
	primary key (deptno)
)
COMMENT '부서';

-- 사원
CREATE TABLE `erp`.`employee` (
	`empno`   INTEGER     NOT NULL COMMENT '사원번호', -- 사원번호
	`empname` VARCHAR(20) NOT NULL COMMENT '사원명', -- 사원명
	`title`   INTEGER     NULL     COMMENT '직책', -- 직책
	`manager` INTEGER     NULL     COMMENT '직속상사', -- 직속상사
	`salary`  INTEGER     NULL     COMMENT '급여', -- 급여
	`dno`     INTEGER     NULL     COMMENT '부서번호', -- 부서번호
	primary key (empno)
)
COMMENT '사원';

-- 직책
CREATE TABLE `erp`.`title` (
	`no`        INTEGER     NOT NULL COMMENT '직책번호', -- 직책번호
	`titlename` VARCHAR(10) NOT NULL COMMENT '직책명', -- 직책명
	PRIMARY KEY (
			`no` -- 직책번호
		)
)
COMMENT '직책';


-- 사원
ALTER TABLE `erp`.`employee`
	ADD CONSTRAINT `FK_title_TO_employee` -- 직책 -> 사원
		FOREIGN KEY (`title`) REFERENCES `erp`.`title` (`no`);

-- 사원
ALTER TABLE `erp`.`employee`
	ADD CONSTRAINT `FK_employee_TO_employee` -- 사원 -> 사원
		FOREIGN KEY (`manager`) REFERENCES `erp`.`employee` (`empno`);

-- 사원
ALTER TABLE `erp`.`employee`
	ADD CONSTRAINT `FK_department_TO_employee` -- 부서 -> 사원
		FOREIGN KEY (`dno`) REFERENCES `erp`.`department` (`deptno`);
		

-- 데이터 삽입 ----------------------------------------------------------------

insert into title values(1,'사장'),(2,'부장'),(3,'과장'),(4,'대리'),(5,'사원');

insert into department values (1,'영업',8),(2,'기획',10),(3,'개발',9),(4,'총무',7);

insert into employee values
(4377,'이성래',1,null,5000000,2),
(3426,'박영권',3,4377,3000000,1),
(3011,'이수민',2,4377,4000000,3),
(1003,'조민희',3,4377,3000000,2),
(3427,'최종철',5,3011,1500000,3),
(1365,'김상원',5,3426,1500000,1),
(2106,'김창섭',4,1003,2500000,2);

update employee
set empname = '김상원'
where empno = 1365;

update employee
set empno = 2106
where empno = 2103;

insert into title values(6,'인턴');
update title set titlename ='막내' where no =6;
delete from title where no =6;

select * from department;
insert into department values(5, '마케팅',100);

update department set deptname ='인사', floor = 60
where deptno =6;
delete from department where deptno =5;

select * from employee;


select e.empno,e.empname,t.titlename,manager,salary,d.deptname 
from employee e,title t, department d
where e.title =t.`no` and e.dno=d.deptno;

insert into employee values(1004,'천사', 5, 2106, 1500000, 3);
update employee set empname='악마', dno=2 where empno=1004;
delete from employee where empno =1004;

select dno, avg(salary)
from employee
where avg(salary) >= 2500000
group by dno;

select empname
from employee
where dno = (select deptno from department where deptname in ('영업','개발'));

select  empname, salary,
		(select count(*)+1 from employee e where salary > e.salary) as rank
from employee
order by rank;

create view v_plan as
select empname, title, salary
from employee
where dno = (select deptno from department where deptname ='기획');

select * from title;

select empname, deptname ,title
from employee inner join department 
on dno =deptno;

select empname, deptname
from employee, department
where dno = deptno;


select max(no)+1 from title;
select * from employee;

select empno, empname,(select titlename from title where title.`no`=e.title)
from employee e, title t
where e.title = t.no;