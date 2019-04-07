DROP DATABASE IF EXISTS dao;
CREATE DATABASE  IF NOT EXISTS `dao` DEFAULT CHARACTER SET utf8mb4;
USE dao;
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
                      `deptno` int(11) NOT NULL,
                      `dname` varchar(200) NOT NULL,
                      `location` varchar(200) NOT NULL,
                      PRIMARY KEY (`deptno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `dept` WRITE;
ALTER TABLE `dept` DISABLE KEYS;
INSERT INTO `dept` VALUES (10,'Accounting','New York'),(20,'Research','Dallas'),(30,'Sales','Chicago'),(40,'Operations','Boston');
ALTER TABLE `dept` ENABLE KEYS;
UNLOCK TABLES;

DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
                     `empno` int(11) NOT NULL,
                     `ename` varchar(200) NOT NULL,
                     `job` varchar(200) NOT NULL,
                     `manager` int(11) DEFAULT NULL,
                     `hiredate` date DEFAULT NULL,
                     `salary` float DEFAULT NULL,
                     `commision` float DEFAULT NULL,
                     `deptno` int(11) NOT NULL,
                     PRIMARY KEY (`empno`),
                     KEY `emp_dept_fk_idx` (`deptno`),
                     CONSTRAINT `emp_dept_fk` FOREIGN KEY (`deptno`) REFERENCES `dept` (`deptno`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `emp` WRITE;
ALTER TABLE `emp` DISABLE KEYS;
INSERT INTO `emp` VALUES (7369,'SMITH','CLERK',7902,'1993-06-13',800,0,20),(7499,'ALLEN','SALESMAN',7698,'1998-08-15',1600,300,30),(7521,'WARD','SALESMAN',7698,'1996-03-26',1250,500,30),(7566,'JONES','MANAGER',7839,'1995-10-31',2975,NULL,20),(7654,'MARTIN','SALESMAN',7698,'1998-12-05',1250,1400,30),(7698,'BLAKE','MANAGER',7839,'1992-06-11',2850,NULL,30),(7782,'CLARK','MANAGER',7839,'1993-05-14',2450,NULL,10),(7788,'SCOTT','ANALYST',7566,'1996-03-05',3000,NULL,20),(7839,'KING','PRESIDENT',NULL,'1990-06-09',5000,0,10),(7844,'TURNER','SALESMAN',7698,'1995-06-04',1500,0,30),(7876,'ADAMS','CLERK',7788,'1999-06-04',1100,NULL,20),(7900,'JAMES','CLERK',7698,'2000-06-23',950,NULL,30),(7902,'FORD','ANALYST',7566,'1997-12-05',3000,NULL,20),(7934,'MILLER','CLERK',7782,'2000-01-21',1300,NULL,10);
ALTER TABLE `emp` ENABLE KEYS;
UNLOCK TABLES;

DROP PROCEDURE IF EXISTS `calculate_salary_by_dept`;

CREATE PROCEDURE `calculate_salary_by_dept`(i INT, OUT r float)
BEGIN
  SET r = (SELECT SUM(e.salary) FROM emp e WHERE e.deptno = i);
END
