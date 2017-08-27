use goit;

# Fill skills table
insert into skills (skill_skill) values ('JAVA');
insert into skills (skill_skill) values ('C#');
insert into skills (skill_skill) values ('C++');
insert into skills (skill_skill) values ('DELPHY');
insert into skills (skill_skill) values ('PHP');
insert into skills (skill_skill) values ('JAVASCRIPT');
insert into skills (skill_skill) values ('NODE.JS');
insert into skills (skill_skill) values ('HTML/CSS');
insert into skills (skill_skill) values ('ASP');
insert into skills (skill_skill) values ('OBJECTIVE C');

#FILL DEVELOPERS TABLE
insert into developers (dev_name, dev_lastname, dev_email)
values ('name1' , 'lasname1' , 'address1@mail.com');
insert into developers (dev_name, dev_lastname, dev_email)
values ('name2' , 'lasname2' , 'address2@mail.com');
insert into developers (dev_name, dev_lastname, dev_email)
values ('name3' , 'lasname3' , 'address3@mail.com');
insert into developers (dev_name, dev_lastname, dev_email)
values ('name4' , 'lasname4' , 'address4@mail.com');
insert into developers (dev_name, dev_lastname, dev_email)
values ('name5' , 'lasname5' , 'address5@mail.com');
insert into developers (dev_name, dev_lastname, dev_email)
values ('name6' , 'lasname6' , 'address6@mail.com');
insert into developers (dev_name, dev_lastname, dev_email)
values ('name7' , 'lasname7' , 'address7@mail.com');
insert into developers (dev_name, dev_lastname, dev_email)
values ('name8' , 'lasname8' , 'address8@mail.com');
insert into developers (dev_name, dev_lastname, dev_email)
values ('name9' , 'lasname9' , 'address9@mail.com');
insert into developers (dev_name, dev_lastname, dev_email)
values ('name10' , 'lasname10' , 'address10@mail.com');

#FILL THE COMPANIES TABLE
insert into companies (company_name, company_address)  values ('company1', 'address1');
insert into companies (company_name, company_address)  values ('company2', 'address2');
insert into companies (company_name, company_address)  values ('company3', 'address3');

#FILL THE CUSTOMERS TABLE
insert into customers (cust_name, cust_lastname) values ('cust1', 'custLastName1');
insert into customers (cust_name, cust_lastname) values ('cust2', 'custLastName2');
insert into customers (cust_name, cust_lastname) values ('cust3', 'custLastName3');
insert into customers (cust_name, cust_lastname) values ('cust4', 'custLastName4');

#FILL THE PROJECTS TABLE
insert into projects (proj_name) values ('proj1');
insert into projects (proj_name) values ('proj2');
insert into projects (proj_name) values ('proj3');
insert into projects (proj_name) values ('proj4');

#FILL THE DEV_HAS_SKILLS
insert into developers_has_skills (developers_id, skills_id) values (1, 1);
insert into developers_has_skills (developers_id, skills_id) values (1, 3);
insert into developers_has_skills (developers_id, skills_id) values (1, 8);
insert into developers_has_skills (developers_id, skills_id) values (2, 10);
insert into developers_has_skills (developers_id, skills_id) values (2, 1);
insert into developers_has_skills (developers_id, skills_id) values (2, 5);
insert into developers_has_skills (developers_id, skills_id) values (3, 6);
insert into developers_has_skills (developers_id, skills_id) values (3, 7);
insert into developers_has_skills (developers_id, skills_id) values (3, 8);
insert into developers_has_skills (developers_id, skills_id) values (4, 1);
insert into developers_has_skills (developers_id, skills_id) values (4, 2);
insert into developers_has_skills (developers_id, skills_id) values (5, 4);
insert into developers_has_skills (developers_id, skills_id) values (5, 5);
insert into developers_has_skills (developers_id, skills_id) values (6, 9);
insert into developers_has_skills (developers_id, skills_id) values (6, 8);
insert into developers_has_skills (developers_id, skills_id) values (6, 7);
insert into developers_has_skills (developers_id, skills_id) values (7, 1);
insert into developers_has_skills (developers_id, skills_id) values (7, 3);
insert into developers_has_skills (developers_id, skills_id) values (7, 8);
insert into developers_has_skills (developers_id, skills_id) values (8, 1);
insert into developers_has_skills (developers_id, skills_id) values (8, 2);
insert into developers_has_skills (developers_id, skills_id) values (9, 4);
insert into developers_has_skills (developers_id, skills_id) values (9, 7);
insert into developers_has_skills (developers_id, skills_id) values (10, 1);
insert into developers_has_skills (developers_id, skills_id) values (10, 4);

#FILL THE COMPANIES_HAS_DEV
insert into companies_has_developers (companies_id, developers_id) values (1, 1);
insert into companies_has_developers (companies_id, developers_id) values (1, 2);
insert into companies_has_developers (companies_id, developers_id) values (1, 3);
insert into companies_has_developers (companies_id, developers_id) values (1, 4);
insert into companies_has_developers (companies_id, developers_id) values (2, 5);
insert into companies_has_developers (companies_id, developers_id) values (2, 6);
insert into companies_has_developers (companies_id, developers_id) values (2, 7);
insert into companies_has_developers (companies_id, developers_id) values (3, 8);
insert into companies_has_developers (companies_id, developers_id) values (3, 9);
insert into companies_has_developers (companies_id, developers_id) values (3, 10);

#FILL THE COMPANIES_HAS_PROJECTS
insert into companies_has_projects (companies_id, projects_id) values (1, 1);
insert into companies_has_projects (companies_id, projects_id) values (1, 4);
insert into companies_has_projects (companies_id, projects_id) values (2, 2);
insert into companies_has_projects (companies_id, projects_id) values (3, 3);
insert into companies_has_projects (companies_id, projects_id) values (3, 4);

#FILL THE CUSTOMERS_HAS_PROJECTS
insert into customers_has_projects (customers_id, projects_id) values (1, 1);
insert into customers_has_projects (customers_id, projects_id) values (2, 2);
insert into customers_has_projects (customers_id, projects_id) values (3, 3);
insert into customers_has_projects (customers_id, projects_id) values (4, 4);
#insert into customers_has_projects (customers_id, projects_id) values (1, 4);
#insert into customers_has_projects (customers_id, projects_id) values (2, 3);
#insert into customers_has_projects (customers_id, projects_id) values (3, 2);
#insert into customers_has_projects (customers_id, projects_id) values (4, 1);

#FILL THE PROJECTS_HAS_DEVELOPERS
insert into projects_has_developers (projects_id, developers_id) values (1, 1);
insert into projects_has_developers (projects_id, developers_id) values (1, 2);
insert into projects_has_developers (projects_id, developers_id) values (1, 3);
insert into projects_has_developers (projects_id, developers_id) values (2, 4);
insert into projects_has_developers (projects_id, developers_id) values (2, 5);
insert into projects_has_developers (projects_id, developers_id) values (3, 6);
insert into projects_has_developers (projects_id, developers_id) values (3, 7);
insert into projects_has_developers (projects_id, developers_id) values (3, 8);
insert into projects_has_developers (projects_id, developers_id) values (3, 9);
insert into projects_has_developers (projects_id, developers_id) values (4, 10);
insert into projects_has_developers (projects_id, developers_id) values (4, 1);
insert into projects_has_developers (projects_id, developers_id) values (4, 5);