-- 5. Найти клиента (customer), которая приносит меньше всего прибыли компании (company) для каждой из компаний .
-- select temp1.company_id as company_id, temp1.proj_id as project_id, temp1.cost as project_cost, temp2.customers_id as customers_id from (select company_id, proj_id, cost from goit.companies, goit.companies_has_projects, goit.projects where projects_id = proj_id and company_id = companies_id) as temp1, (select proj_id, customers_id from goit.projects, goit.customers_has_projects, goit.customers where proj_id = projects_id and cust_id = customers_id) as temp2 where temp1.proj_id = temp2.proj_id;
SELECT 
    temp1.company_id AS company_id,
    temp1.proj_id AS project_id,
    MIN(temp1.cost) AS project_cost,
    temp2.customers_id AS customers_id
FROM
    (SELECT 
        company_id, proj_id, cost
    FROM
        goit.companies, goit.companies_has_projects, goit.projects
    WHERE
        projects_id = proj_id
            AND company_id = companies_id) AS temp1,
    (SELECT 
        proj_id, customers_id
    FROM
        goit.projects, goit.customers_has_projects, goit.customers
    WHERE
        proj_id = projects_id
            AND cust_id = customers_id) AS temp2
WHERE
    temp1.proj_id = temp2.proj_id
GROUP BY temp1.company_id;