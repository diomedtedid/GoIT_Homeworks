#6. Вычислить, среднюю ЗП программистов в проекте, который приносит наименьшую прибыль.
SELECT 
    temp2.company_id, temp2.project_id, AVG(salary)
FROM
    (SELECT 
        temp1.company_id AS company_id,
            temp1.proj_id AS project_id,
            MIN(temp1.cost) AS project_cost
    FROM
        (SELECT 
        company_id, proj_id, cost
    FROM
        goit.companies, goit.companies_has_projects, goit.projects
    WHERE
        projects_id = proj_id
            AND company_id = companies_id) AS temp1
    GROUP BY temp1.company_id) AS temp2,
    (SELECT 
        projects_id, dev_id, salary
    FROM
        goit.projects_has_developers, goit.developers
    WHERE
        dev_id = developers_id) AS temp3
WHERE
    temp2.project_id = temp3.projects_id
GROUP BY temp2.company_id;