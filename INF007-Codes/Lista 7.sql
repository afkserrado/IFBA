DROP TABLE IF EXISTS sales;
DROP TABLE IF EXISTS titleauthor;
DROP TABLE IF EXISTS titles;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS stores;
DROP TABLE IF EXISTS publishers;
DROP TABLE IF EXISTS jobs;

/*Table structure for table "authors" */

CREATE TABLE "authors" (
  "au_id" char(11) NOT NULL,
  "au_lname" varchar(40) NOT NULL,
  "au_fname" varchar(20) NOT NULL,
  "phone" char(12) NOT NULL,
  "address" varchar(40) default NULL,
  "city" varchar(20) default NULL,
  "state" char(2) default NULL,
  "zip" char(5) default NULL,
  "contract" bit(1) NOT NULL,
  "consecutivo" int default NULL,
  PRIMARY KEY  ("au_id")
);


/*Data for the table "authors" */

insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('172-32-1176','White','Johnson','408 496-7223','10932 Bigge Rd.','Menlo Park','CA','94025','0',18);
insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('213-46-8915','Green','Marjorie','415 986-7020','309 63rd St. #411','Oakland','CA','94618','0',2);
insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('238-95-7766','Carson','Cheryl','415 548-7723','589 Darwin Ln.','Berkeley','CA','94705','0',3);
insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('267-41-2394','Leary','Michael','408 286-2428','22 Cleveland Av. #14','San Jose','CA','95128','0',15);
insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('274-80-9391','Straight','Dean','415 834-2919','5420 College Av.','Oakland','CA','94609','0',10);
insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('341-22-1782','Smith','Meander','913 843-0462','10 Mississippi Dr.','Lawrence','KS','66044','1',23);
insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('409-56-7008','Bennet','Abraham','415 658-9932','6223 Bateman St.','Berkeley','CA','94705','0',1);
insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('427-17-2319','Dull','Ann','415 836-7128','3410 Blonde St.','Palo Alto','CA','94301','0',13);
insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('472-27-2349','Gringlesby','Burt','707 938-6445','PO Box 792','Covelo','CA','95428','0',16);
insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('486-29-1786','Locksley','Charlene','415 585-4620','18 Broadway Av.','San Francisco','CA','94130','0',21);
insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('527-72-3246','Greene','Morningstar','615 297-2723','22 Graybar House Rd.','Nashville','TN','37215','1',17);
insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('648-92-1872','Blotchet-Halls','Reginald','503 745-6402','55 Hillsdale Bl.','Corvallis','OR','97330','0',22);
insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('672-71-3249','Yokomoto','Akiko','415 935-4228','3 Silver Ct.','Walnut Creek','CA','94595','0',14);
insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('712-45-1867','del Castillo','Innes','615 996-8275','2286 Cram Pl. #86','Ann Arbor','MI','48105','0',19);
insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('722-51-5454','DeFrance','Michel','219 547-9982','3 Balding Pl.','Gary','IN','46403','0',6);
insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('724-08-9931','Stringer','Dirk','415 843-2991','5420 Telegraph Av.','Oakland','CA','94609','1',9);
insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('724-80-9391','MacFeather','Stearns','415 354-7128','44 Upland Hts.','Oakland','CA','94612','0',12);
insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('756-30-7391','Karsen','Livia','415 534-9219','5720 McAuley St.','Oakland','CA','94609','0',11);
insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('807-91-6654','Panteley','Sylvia','301 946-8853','1956 Arlington Pl.','Rockville','MD','20853','0',5);
insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('846-92-7186','Hunter','Sheryl','415 836-7128','3410 Blonde St.','Palo Alto','CA','94301','0',20);
insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('893-72-1158','McBadden','Heather','707 448-4982','301 Putnam','Vacaville','CA','95688','1',8);
insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('899-46-2035','Ringer','Anne','801 826-0752','67 Seventh Av.','Salt Lake City','UT','84152','0',7);
insert into authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract,consecutivo) values ('998-72-3567','Ringer','Albert','801 826-0752','67 Seventh Av.','Salt Lake City','UT','84152','0',4);


/*Table structure for table 'employee" */

CREATE TABLE "employee" (
  "emp_id" int NOT NULL,
  "active" int default NULL,
  "fname" varchar(20) NOT NULL,
  "minit" char(1) default NULL,
  "lname" varchar(30) NOT NULL,
  "job_id" smallint NOT NULL default '1',
  "job_lvl" int NOT NULL default '10',
  "pub_id" char(4) NOT NULL default '9952',
  "birth_date" date default NULL,
  "hire_date" timestamp NOT NULL default CURRENT_TIMESTAMP,
  "consecutivo" int default NULL,
  "photo" char(80) default NULL,
  "salary" float default NULL,
  "days" int default NULL,
  "total_salary" float default NULL,
  "afiliation_date" int default NULL,
  "email" varchar(100) default NULL,
  PRIMARY KEY  ("emp_id")
);


/*Data for the table "employee" */

insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (1,1,'Carlos','','Cruz',10,87,'1389','1977-12-24','2000-01-02 00:30:02',33,'',18000,0,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (2,0,'Annette','','Roulet',6,127,'9999','1976-01-01','2000-01-02 00:30:02',38,NULL,25000,12,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (3,1,'Annie','K','Devon',3,127,'9952','2008-05-12','2000-01-02 00:30:02',5,'image1.jpg',14000,15,7000,1009688400,'annie@hotmail.com');
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (4,0,'Anabela','R','Domingues',8,100,'0877','1976-03-19','2000-01-02 00:30:02',13,NULL,30000,15,15000,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (5,1,'Carlos','F','Hernandez',5,127,'9999','1978-01-12','2000-01-02 00:30:02',10,NULL,5000,15,2500,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (6,0,'Carines','G','Schmitt',13,64,'1389','1981-01-01','2000-01-02 00:30:02',36,NULL,20000,12,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (7,1,'Daniela','B','Tonini',11,75,'0877','1975-09-24','2000-01-02 00:30:02',16,NULL,5000,10,1666.67,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (8,0,'Diego','W','Roel',6,127,'1389','1979-02-25','2000-01-02 00:30:02',29,NULL,30000,20,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (9,0,'Elizabeth','N','Lincoln',14,35,'0877','1945-11-20','2000-01-02 00:30:02',19,NULL,15000,15,7500,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (10,0,'Carlos','x','Chang',4,127,'9952','1980-02-20','2000-01-02 00:30:02',6,'images.jpg',50000,10,16666.7,1009688400,'changuito@hongkong.com');
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (12,1,'Gary','H','Thomas',9,127,'0736',NULL,'2000-01-02 00:30:02',23,NULL,20000,10,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (13,1,'Helen','','Bennett',12,35,'0877',NULL,'2000-01-02 00:30:02',17,NULL,8000,15,4000,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (14,0,'Helvetius','A','Nagy',7,120,'9999',NULL,'2000-01-02 00:30:02',39,NULL,18000,12,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (15,0,'Howard','A','Snyder',12,100,'0736',NULL,'2000-01-02 00:30:02',26,NULL,20000,10,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (16,0,'Janine','Y','Labrune',5,127,'9901',NULL,'2000-01-02 00:30:02',9,NULL,10000,15,5000,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (17,0,'Karin','F','Josephs',14,100,'0736',NULL,'2000-01-02 00:30:02',28,NULL,15000,10,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (18,0,'Karla','J','Jablonski',9,127,'9999',NULL,'2000-01-02 00:30:02',41,NULL,23000,12,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (19,0,'Lesleyz','','Brown',7,120,'0877',NULL,'2000-01-02 00:30:02',12,NULL,15000,15,7500,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (20,1,'Laurence','A','Lebihan',5,127,'0736','1974-02-02','2000-01-02 00:30:02',2,NULL,50000,15,25000,1009688400,'lal@lebihan.com');
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (21,0,'Maria','','Larsson',7,127,'1389',NULL,'2000-01-02 00:30:02',30,NULL,12000,2,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (22,0,'Manuel','','Pereira',8,101,'9999',NULL,'2000-01-02 00:30:02',40,NULL,16000,2,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (23,1,'Martine','','Rance',9,75,'0877',NULL,'2000-01-02 00:30:02',14,NULL,5000,15,2500,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (24,0,'Miguel','A','Paolino',11,112,'1389',NULL,'2000-01-02 00:30:02',34,NULL,17000,20,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (25,0,'Margaret','A','Smith',9,78,'1389',NULL,'2000-01-02 00:30:02',32,NULL,19000,1,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (26,0,'Martin','F','Sommer',10,127,'0736',NULL,'2000-01-02 00:30:02',24,NULL,12000,10,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (27,0,'Matti','G','Karttunen',6,127,'0736',NULL,'2000-01-02 00:30:02',20,NULL,25000,15,12500,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (28,1,'Carlos','J','Pontes',5,127,'1756',NULL,'2000-01-02 00:30:02',8,NULL,5000,30,5000,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (29,0,'Mary','M','Saveley',8,127,'0736',NULL,'2000-01-02 00:30:02',22,NULL,12000,10,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (30,0,'Patricia','C','McKenna',11,127,'9999',NULL,'2000-01-02 00:30:02',43,NULL,14000,21,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (31,0,'Palle','D','Ibsen',7,127,'0736',NULL,'2000-01-02 00:30:02',21,NULL,15000,10,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (32,1,'Peter','H','Franken',10,75,'0877',NULL,'2000-01-02 00:30:02',15,NULL,3000,30,3000,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (33,0,'Paolo','M','Accorti',13,35,'0877',NULL,'2000-01-02 00:30:02',18,NULL,15000,10,5000,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (34,0,'Pirkko','O','Koskitalo',10,80,'9999',NULL,'2000-01-02 00:30:02',42,NULL,15000,12,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (35,0,'Pedro','S','Afonso',14,89,'1389',NULL,'2000-01-02 00:30:02',37,NULL,13000,10,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (36,0,'Yoshi','','Latimer',12,32,'1389',NULL,'2000-01-02 00:30:02',35,NULL,18000,2,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (37,1,'Carlos','T','Smither',2,127,'9952','1980-09-10','2000-01-02 00:30:02',9,'images.jpg',40000,45,60000,1007960400,'ccramer@strident.com');
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (39,0,'Roland','','Mendel',11,127,'0736',NULL,'2000-01-02 00:30:02',25,NULL,15000,10,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (40,0,'Rita','B','Muller',5,127,'1622',NULL,'2000-01-02 00:30:02',7,NULL,15000,15,7500,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (41,0,'Paula','S','Parente',8,125,'1389',NULL,'2000-01-02 00:30:02',31,NULL,11000,1,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (42,0,'Miryan','a','Alvarez',1,10,'9952','2008-04-17','2000-01-02 00:30:02',44,NULL,20000,21,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (43,1,'Svendy','K','Ottlieb',5,127,'1389','1987-01-01','2000-01-02 00:30:02',3,NULL,30000,20,10000,1009688400,'ottsvendy@hotmail.com');
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (44,0,'Timothy','P','Rourke',13,100,'0736',NULL,'2000-01-02 00:30:02',27,NULL,18000,10,NULL,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (45,0,'Victoria ','P','Ashworth',0,127,'0877',NULL,'2000-01-02 00:30:02',11,NULL,25000,10,8333.33,1009688400,NULL);
insert into employee (emp_id,active,fname,minit,lname,job_id,job_lvl,pub_id,birth_date,hire_date,consecutivo,photo,salary,days,total_salary,afiliation_date,email) values (91,0,'Gustavo','A','Arcila',1,10,'9952','1975-09-24','2000-01-02 00:30:02',46,NULL,13500,NULL,NULL,1009688400,NULL);

/*Table structure for table jobs */

CREATE TABLE "jobs" (
  "job_id" smallint NOT NULL,
  "job_desc" varchar(50) NOT NULL default 'New Position - title not formalized yet',
  "min_lvl" int NOT NULL,
  "max_lvl" int NOT NULL,
  PRIMARY KEY  ("job_id")
);

/*Data for the table "jobs" */

insert into jobs (job_id,job_desc,min_lvl,max_lvl) values (0,'Nothing',10,10);
insert into jobs (job_id,job_desc,min_lvl,max_lvl) values (1,'New Hire - Job not specified',10,10);
insert into jobs (job_id,job_desc,min_lvl,max_lvl)  values (2,'Chief Executive Officer',127,127);
insert into jobs (job_id,job_desc,min_lvl,max_lvl)  values (3,'Business Operations Manager',127,127);
insert into jobs (job_id,job_desc,min_lvl,max_lvl)  values (4,'Chief Financial Officier',127,127);
insert into jobs (job_id,job_desc,min_lvl,max_lvl)  values (5,'Publisher',127,127);
insert into jobs (job_id,job_desc,min_lvl,max_lvl)  values (6,'Managing Editor',127,127);
insert into jobs (job_id,job_desc,min_lvl,max_lvl)  values (7,'Marketing Manager',120,127);
insert into jobs (job_id,job_desc,min_lvl,max_lvl)  values (8,'Public Relations Manager',100,127);
insert into jobs (job_id,job_desc,min_lvl,max_lvl)  values (9,'Acquisitions Manager',75,127);
insert into jobs (job_id,job_desc,min_lvl,max_lvl)  values (10,'Productions Manager',75,127);
insert into jobs (job_id,job_desc,min_lvl,max_lvl)  values (11,'Operations Manager',75,127);
insert into jobs (job_id,job_desc,min_lvl,max_lvl)  values (12,'Editor',25,100);
insert into jobs (job_id,job_desc,min_lvl,max_lvl)  values (13,'Sales Representative',25,100);
insert into jobs (job_id,job_desc,min_lvl,max_lvl)  values (14,'Designer',25,100);


/*Table structure for table "publishers" */

CREATE TABLE "publishers" (
  "pub_id" char(4) NOT NULL,
  "pub_name" varchar(40) default NULL,
  "city" varchar(20) default NULL,
  "state" char(2) default NULL,
  "country" varchar(30) default NULL,
  "consecutivo" int default NULL,
  PRIMARY KEY  ("pub_id")
) ;

/*Data for the table "publishers" */

insert into publishers (pub_id,pub_name,city,state,country,consecutivo) values ('0736','New Moon Books','Boston','MA','USA',1);
insert into publishers (pub_id,pub_name,city,state,country,consecutivo) values ('0877','Binnet & Hardley','Washington','DC','USA',3);
insert into publishers (pub_id,pub_name,city,state,country,consecutivo) values ('1389','Algodata Infosystems','Berkeley','CA','USA',2);
insert into publishers (pub_id,pub_name,city,state,country,consecutivo) values ('1622','Five Lakes Publishing','Chicago','IL','USA',5);
insert into publishers (pub_id,pub_name,city,state,country,consecutivo) values ('1756','Ramona Publishers','Dallas','TX','USA',6);
insert into publishers (pub_id,pub_name,city,state,country,consecutivo) values ('9901','GGG&G','Munchen',NULL,'Germany',8);
insert into publishers (pub_id,pub_name,city,state,country,consecutivo) values ('9952','Scootney Books','New York','NY','USA',4);
insert into publishers (pub_id,pub_name,city,state,country,consecutivo) values ('9999','Lucerne Publishing','Paris',NULL,'France',7);

/*Table structure for table "sales" */

CREATE TABLE "sales" (
  "stor_id" char(4) NOT NULL,
  "ord_num" varchar(20) NOT NULL,
  "ord_date" varchar (30),
  "qty" smallint NOT NULL,
  "payterms" varchar(12) NOT NULL,
  "title_id" char(6) NOT NULL default '',
  PRIMARY KEY  ("stor_id","ord_num", "title_id")
) ;

/*Data for the table "sales" */

insert into sales (stor_id,ord_num,ord_date,qty,payterms,title_id) values ('6380','6871','0000-00-00 00:00:00',5,'Net 60','BU1032');
insert into sales (stor_id,ord_num,ord_date,qty,payterms,title_id) values ('6380','722a','0000-00-00 00:00:00',3,'Net 60','PS2091');
insert into sales (stor_id,ord_num,ord_date,qty,payterms,title_id) values ('7066','A2976','0000-00-00 00:00:00',50,'Net 30','PC8888');
insert into sales (stor_id,ord_num,ord_date,qty,payterms,title_id) values ('7066','QA7442.3','0000-00-00 00:00:00',75,'ON invoice','PS2091');
insert into sales (stor_id,ord_num,ord_date,qty,payterms,title_id) values ('7067','D4482','0000-00-00 00:00:00',10,'Net 60','PS2091');
insert into sales (stor_id,ord_num,ord_date,qty,payterms,title_id) values ('7067','P2121','0000-00-00 00:00:00',40,'Net 30','TC3218');
insert into sales (stor_id,ord_num,ord_date,qty,payterms,title_id) values ('7067','P2121','0000-00-00 00:00:00',20,'Net 30','TC4203');
insert into sales (stor_id,ord_num,ord_date,qty,payterms,title_id) values ('7067','P2121','0000-00-00 00:00:00',20,'Net 30','TC7777');
insert into sales (stor_id,ord_num,ord_date,qty,payterms,title_id) values ('7131','N914008','0000-00-00 00:00:00',20,'Net 30','PS2091');
insert into sales (stor_id,ord_num,ord_date,qty,payterms,title_id) values ('7131','N914014','0000-00-00 00:00:00',25,'Net 30','MC3021');
insert into sales (stor_id,ord_num,ord_date,qty,payterms,title_id) values ('7131','P3087a','0000-00-00 00:00:00',20,'Net 60','PS1372');
insert into sales (stor_id,ord_num,ord_date,qty,payterms,title_id) values ('7131','P3087a','0000-00-00 00:00:00',25,'Net 60','PS2106');
insert into sales (stor_id,ord_num,ord_date,qty,payterms,title_id) values ('7131','P3087a','0000-00-00 00:00:00',15,'Net 60','PS3333');
insert into sales (stor_id,ord_num,ord_date,qty,payterms,title_id) values ('7131','P3087a','0000-00-00 00:00:00',25,'Net 60','PS7777');
insert into sales (stor_id,ord_num,ord_date,qty,payterms,title_id) values ('7896','QQ2299','0000-00-00 00:00:00',15,'Net 60','BU7832');
insert into sales (stor_id,ord_num,ord_date,qty,payterms,title_id) values ('7896','TQ456','0000-00-00 00:00:00',10,'Net 60','MC2222');
insert into sales (stor_id,ord_num,ord_date,qty,payterms,title_id) values ('7896','X999','0000-00-00 00:00:00',35,'ON invoice','BU2075');
insert into sales (stor_id,ord_num,ord_date,qty,payterms,title_id) values ('8042','423LL922','0000-00-00 00:00:00',15,'ON invoice','MC3021');
insert into sales (stor_id,ord_num,ord_date,qty,payterms,title_id) values ('8042','423LL930','0000-00-00 00:00:00',10,'ON invoice','BU1032');
insert into sales (stor_id,ord_num,ord_date,qty,payterms,title_id) values ('8042','P723','0000-00-00 00:00:00',25,'Net 30','BU1111');
insert into sales (stor_id,ord_num,ord_date,qty,payterms,title_id) values ('8042','QA879.1','0000-00-00 00:00:00',30,'Net 30','PC1035');

/*Table structure for table "stores" */

CREATE TABLE "stores" (
  "stor_id" char(4) NOT NULL,
  "stor_name" varchar(40) default NULL,
  "stor_address" varchar(40) default NULL,
  "city" varchar(20) default NULL,
  "state" char(2) default NULL,
  "zip" char(5) default NULL,
  PRIMARY KEY  ("stor_id")
) ;

/*Data for the table "stores" */

insert into stores (stor_id,stor_name,stor_address,city,state,zip) values ('6380','Eric the Read Books','788 Catamaugus Ave.','Seattle','WA','98056');
insert into stores (stor_id,stor_name,stor_address,city,state,zip) values ('7066','Barnums','567 Pasadena Ave.','Tustin','CA','92789');
insert into stores (stor_id,stor_name,stor_address,city,state,zip) values ('7067','News & Brews','577 First St.','Los Gatos','CA','96745');
insert into stores (stor_id,stor_name,stor_address,city,state,zip) values ('7131','Doc-U-Mat: Quality Laundry and Books','24-A Avogadro Way','Remulade','WA','98014');
insert into stores (stor_id,stor_name,stor_address,city,state,zip) values ('7896','Fricative Bookshop','89 Madison St.','Fremont','CA','90019');
insert into stores (stor_id,stor_name,stor_address,city,state,zip) values ('8042','Bookbeat','679 Carson St.','Portland','OR','89076');

/*Table structure for table "titleauthor" */

CREATE TABLE "titleauthor" (
  "au_id" char(11) NOT NULL default '',
  "title_id" char(6) NOT NULL default '',
  "au_ord" int default NULL,
  "royaltyper" int default NULL,
  PRIMARY KEY  ("au_id","title_id")
);

/*Data for the table "titleauthor" */

insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('172-32-1176','PS3333',1,100);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('213-46-8915','BU1032',2,40);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('213-46-8915','BU2075',1,100);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('238-95-7766','PC1035',1,100);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('267-41-2394','BU1111',2,40);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('267-41-2394','TC7777',2,30);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('274-80-9391','BU7832',1,100);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('409-56-7008','BU1032',1,60);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('427-17-2319','PC8888',1,50);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('472-27-2349','TC7777',3,30);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('486-29-1786','PC9999',1,100);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('486-29-1786','PS7777',1,100);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('648-92-1872','TC4203',1,100);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('672-71-3249','TC7777',1,40);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('712-45-1867','MC2222',1,100);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('722-51-5454','MC3021',1,75);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('724-80-9391','BU1111',1,60);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('724-80-9391','PS1372',2,25);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('756-30-7391','PS1372',1,75);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('807-91-6654','TC3218',1,100);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('846-92-7186','PC8888',2,50);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('899-46-2035','MC3021',2,25);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('899-46-2035','PS2091',2,50);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('998-72-3567','PS2091',1,50);
 insert into  titleauthor  ( au_id , title_id , au_ord , royaltyper ) values ('998-72-3567','PS2106',1,100);

/*Table structure for table "titles" */

CREATE TABLE "titles" (
  "title_id" char(6) NOT NULL default '',
  "title" varchar(80) NOT NULL,
  "type" char(12) NOT NULL default 'UNDECIDED',
  "pub_id" char(4) default NULL,
  "price" int default NULL,
  "advance" int default NULL,
  "royalty" int default NULL,
  "ytd_sales" int default NULL,
  "notes" varchar(200) default NULL,
  "pubdate" varchar (30),
  PRIMARY KEY  ("title_id")
);

/*Data for the table "titles" */

insert into  titles  ( title_id , title , type , pub_id , price , advance , royalty , ytd_sales , notes , pubdate ) values ('BU1032','The Busy Executive Database Guide','business','1389',20,5000,10,4095,'An overview of available database systems with emphasis on common business applications. Illustrated.','0000-00-00 00:00:00');
 insert into  titles  ( title_id , title , type , pub_id , price , advance , royalty , ytd_sales , notes , pubdate ) values ('BU1111','Cooking with Computers: Surreptitious Balance Sheets','business','1389',12,5000,10,3876,'Helpful hints on how to use your electronic resources to the best advantage.','0000-00-00 00:00:00');
 insert into  titles  ( title_id , title , type , pub_id , price , advance , royalty , ytd_sales , notes , pubdate ) values ('BU2075','You Can Combat Computer Stress!','business','0736',3,10125,24,18722,'The latest medical and psychological techniques for living with the electronic office. Easy-to-understand explanations.','0000-00-00 00:00:00');
 insert into  titles  ( title_id , title , type , pub_id , price , advance , royalty , ytd_sales , notes , pubdate ) values ('BU7832','Straight Talk About Computers','business','1389',20,5000,10,4095,'Annotated analysis of what computers can do for you: a no- hype guide for the critical user.','0000-00-00 00:00:00');
 insert into  titles  ( title_id , title , type , pub_id , price , advance , royalty , ytd_sales , notes , pubdate ) values ('MC2222','Silicon Valley Gastronomic Treats','mod_cook','0877',20,0,12,2032,'Favorite recipes for quick, easy, and elegant meals.','0000-00-00 00:00:00');
 insert into  titles  ( title_id , title , type , pub_id , price , advance , royalty , ytd_sales , notes , pubdate ) values ('MC3021','The Gourmet Microwave','mod_cook','0877',3,15000,24,22246,'Traditional French gourmet recipes adapted for modern microwave cooking.','0000-00-00 00:00:00');
 insert into  titles  ( title_id , title , type , pub_id , price , advance , royalty , ytd_sales , notes , pubdate ) values ('MC3026','The Psychology of Computer Cooking','UNDECIDED','0877',NULL,NULL,NULL,NULL,NULL,'2008-03-20 08:21:58');
 insert into  titles  ( title_id , title , type , pub_id , price , advance , royalty , ytd_sales , notes , pubdate ) values ('PC1035','But Is It User Friendly?','popular_comp','1389',23,7000,16,8780,'A survey of software for the naive user, focusing on the friendliness of each.','0000-00-00 00:00:00');
 insert into  titles  ( title_id , title , type , pub_id , price , advance , royalty , ytd_sales , notes , pubdate ) values ('PC8888','Secrets of Silicon Valley','popular_comp','1389',20,8000,10,4095,'Muckraking reporting on the world largest computer hardware and software manufacturers.','0000-00-00 00:00:00');
 insert into  titles  ( title_id , title , type , pub_id , price , advance , royalty , ytd_sales , notes , pubdate ) values ('PC9999','Net Etiquette','popular_comp','1389',NULL,NULL,NULL,NULL,'A must-read for computer conferencing.','2008-03-20 08:21:58');
 insert into  titles  ( title_id , title , type , pub_id , price , advance , royalty , ytd_sales , notes , pubdate ) values ('PS1372','Computer Phobic AND Non-Phobic Individuals: Behavior Variations','psychology','0877',22,7000,10,375,'A must for the specialist, this book examines the difference between those who hate and fear computers and those who dont.','0000-00-00 00:00:00');
 insert into  titles  ( title_id , title , type , pub_id , price , advance , royalty , ytd_sales , notes , pubdate ) values ('PS2091','Is Anger the Enemy?','psychology','0736',11,2275,12,2045,'Carefully researched study of the effects of strong emotions on the body. Metabolic charts included.','0000-00-00 00:00:00');
 insert into  titles  ( title_id , title , type , pub_id , price , advance , royalty , ytd_sales , notes , pubdate ) values ('PS2106','Life Without Fear','psychology','0736',7,6000,10,111,'New exercise, meditation, and nutritional techniques that can reduce the shock of daily interactions. Popular audience. Sample menus included, exercise video available separately.','0000-00-00 00:00:00');
 insert into  titles  ( title_id , title , type , pub_id , price , advance , royalty , ytd_sales , notes , pubdate ) values ('PS3333','Prolonged Data Deprivation: Four Case Studies','psychology','0736',20,2000,10,4072,'What happens when the data runs dry?  Searching evaluations of information-shortage effects.','0000-00-00 00:00:00');
 insert into  titles  ( title_id , title , type , pub_id , price , advance , royalty , ytd_sales , notes , pubdate ) values ('PS7777','Emotional Security: A New Algorithm','psychology','0736',8,4000,10,3336,'Protecting yourself and your loved ones from undue emotional stress in the modern world. Use of computer and nutritional aids emphasized.','0000-00-00 00:00:00');
 insert into  titles  ( title_id , title , type , pub_id , price , advance , royalty , ytd_sales , notes , pubdate ) values ('TC3218','Onions, Leeks, and Garlic: Cooking Secrets of the Mediterranean','trad_cook','0877',21,7000,10,375,'Profusely illustrated in color, this makes a wonderful gift book for a cuisine-oriented friend.','0000-00-00 00:00:00');
 insert into  titles  ( title_id , title , type , pub_id , price , advance , royalty , ytd_sales , notes , pubdate ) values ('TC4203','Fifty Years in Buckingham Palace Kitchens','trad_cook','0877',12,4000,14,15096,'More anecdotes from the Queen favorite cook describing life among English royalty. Recipes, techniques, tender vignettes.','0000-00-00 00:00:00');
 insert into  titles  ( title_id , title , type , pub_id , price , advance , royalty , ytd_sales , notes , pubdate ) values ('TC7777','Sushi, Anyone?','trad_cook','0877',15,8000,10,4095,'Detailed instructions on how to make authentic Japanese sushi in your spare time.','0000-00-00 00:00:00');

ALTER TABLE Employee
ADD FOREIGN KEY (pub_id) REFERENCES publishers (pub_id);

ALTER TABLE Employee
ADD FOREIGN KEY (job_id) REFERENCES jobs (job_id);

ALTER TABLE titles
ADD FOREIGN KEY (pub_id) REFERENCES publishers (pub_id);

ALTER TABLE titleauthor
ADD FOREIGN KEY (title_id) REFERENCES  titles(title_id);

ALTER TABLE titleauthor
ADD FOREIGN KEY (au_id) REFERENCES  authors(au_id);

ALTER TABLE sales
ADD FOREIGN KEY (title_id) REFERENCES  titles(title_id);

ALTER TABLE sales
ADD FOREIGN KEY (stor_id) REFERENCES  stores(stor_id);

-- Consulting data

-- a) Selecionar todas as informações sobre todos os autores (authors).
SELECT *
FROM authors AS au;

-- b) Selecionar o código, primeiro nome e último nome de todos os autores (authors) em ordem alfabética pelo último nome (au_lname).
SELECT au_id, au_fname, au_lname
FROM authors
ORDER BY au_lname;

-- c) Selecionar todas as informações sobre todos os autores (authors) da cidade "Oakland".
SELECT *
FROM authors
WHERE city = 'Oakland';


-- d) Selecionar o código, título e preço dos livros (titles) em ordem decrescente de preço.
SELECT title_id, title, price
FROM titles
ORDER BY price DESC;

-- e) Selecionar o código, título e preço dos livros (titles) cujo preço é superior a $15.
SELECT title_id, title, price
FROM titles
WHERE price > 15;

-- f) Selecionar o código e título dos livros (titles), primeiro e último nome dos seus respectivos autores (authors).
SELECT T.title_id, T.title, A.au_fname, A.au_lname
FROM 
	titleauthor AS TA INNER JOIN titles AS T
	ON TA.title_id = T.title_id
	INNER JOIN authors AS A
	ON TA.au_id = A.au_id;

-- g) Selecionar o código e título dos livros e quantidade de autores de cada livro (titles).
SELECT T.title_id, T.title, COUNT(A.au_id)
FROM 
	titleauthor AS TA INNER JOIN titles AS T
	ON TA.title_id = T.title_id
	INNER JOIN authors AS A
	ON TA.au_id = A.au_id
	
GROUP BY T.title_id, T.title;

-- h) Selecionar o código e título dos livros (titles) que foram escritos por mais de 2 autores.
SELECT T.title_id, T.title
FROM titleauthor AS TA 
INNER JOIN titles AS T
	ON TA.title_id = T.title_id
INNER JOIN authors AS A
	ON TA.au_id = A.au_id
	
GROUP BY T.title_id, T.title
HAVING COUNT(*) > 2;

-- Solução 2
SELECT T.title_id, T.title
FROM titles T
WHERE (
    SELECT COUNT(*)
    FROM titleauthor TA
    WHERE TA.title_id = T.title_id
) > 2;

-- i) Selecionar o primeiro nome (fname), inicial do nome do meio (minit) e o último nome (lname) dos empregados (employee).
SELECT fname, minit, lname
FROM employee;

-- j) Selecionar o código e a descrição dos cargos (jobs).
SELECT job_id, job_desc
FROM jobs;

-- k) Selecionar o primeiro nome, inicial do nome do meio, ultimo nome dos empregados (employee) e a descrição do seu respectivo cargo (jobs).
SELECT E.fname, E.minit, E.lname, J.job_desc
FROM 
	employee AS E INNER JOIN jobs AS J
	ON E.job_id = J.job_id;

-- l) Selecionar o código, nome e a cidade das editoras (publishers), ordenado pelo nome.
SELECT pub_id, pub_name, city
FROM publishers
ORDER BY pub_name;

-- m) Selecionar o código e título do livro (titles), código e nome da respectiva editora (publishers).
SELECT T.title_id, T.title, P.pub_id, P.pub_name
FROM 
	titles AS T INNER JOIN publishers AS P
	ON T.pub_id = P.pub_id;

-- n) Selecionar o código e título do livro (titles), código e nome da respectiva editora (publishers), cuja editora se localiza na cidade de “Boston”.
SELECT T.title_id, T.title, P.pub_id, P.pub_name
FROM titles AS T
INNER JOIN publishers AS P
	ON T.pub_id = P.pub_id
WHERE P.city = 'Boston';

-- o) Selecionar o nome, endereço e cidade das lojas (stores) do estado "CA".
SELECT stor_name, stor_address, city
FROM stores
WHERE state = 'CA';

-- p) Selecionar código livro, titulo e a quantidade total de livros vendidos, em ordem decrescente da quantidade total de livros vendidos.
SELECT T.title_id, T.title, SUM(S.qty) AS soma
FROM titles AS T
INNER JOIN sales AS S
	ON T.title_id = S.title_id
	
GROUP BY T.title_id, T.title
ORDER BY soma DESC;

-- q) Selecionar código livro, titulo do livro, código e nome da loja (stores) e a quantidade total de livros vendidos pela loja (nas diversa vendas realizadas), em ordem decrescente da quantidade de livros vendidos.
SELECT T.title_id, T.title, ST.stor_id, ST.stor_name, SUM(S.qty) AS soma
FROM titles AS T
INNER JOIN sales AS S
	ON T.title_id = S.title_id
INNER JOIN stores AS ST
	ON S.stor_id = ST.stor_id

GROUP BY T.title_id, T.title, ST.stor_id, ST.stor_name
ORDER BY soma DESC;

-- r) Selecionar o código e nome da loja (stores), e a sua respectiva média de livros vendidos.
SELECT ST.stor_id, ST.stor_name, AVG(S.qty)
FROM stores AS ST
INNER JOIN sales AS S
	ON ST.stor_id = S.stor_id
	
GROUP BY ST.stor_id, ST.stor_name;

-- s) Selecionar os livros (titles) que nunca foram vendidos.
SELECT T.title, SUM(S.qty) AS soma
FROM titles AS T
LEFT OUTER JOIN sales AS S
	ON T.title_id = S.title_id

GROUP BY T.title
--ORDER BY soma DESC
HAVING SUM(S.qty) <= 0 OR SUM(S.qty) IS NULL;

-- Solução 2
SELECT T.title
FROM titles AS T
WHERE NOT EXISTS (
	SELECT 1
	FROM sales AS S
	WHERE T.title_id = S.title_id
);

-- t) Selecionar as editoras (publishers) que não editaram nenhum livro.
SELECT P.pub_name--, COUNT(T.title_id)
FROM publishers AS P
LEFT OUTER JOIN titles AS T
	ON P.pub_id = T.pub_id

GROUP BY P.pub_name
HAVING COUNT(T.title_id) = 0
ORDER BY P.pub_name;

-- Solução 2
SELECT P.pub_name
FROM publishers AS P
WHERE NOT EXISTS (
	SELECT 1
	FROM titles AS T
	WHERE P.pub_id = T.pub_id
)
ORDER BY P.pub_name;