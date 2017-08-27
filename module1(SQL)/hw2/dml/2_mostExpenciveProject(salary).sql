SELECT 
    proj_id, proj_name, max_salary_sum
FROM
    goit.projects,
    (SELECT 
        projects_id, MAX(salary_sum) AS 'max_salary_sum'
    FROM
        (SELECT 
        projects_id, SUM(salary) AS 'salary_sum'
    FROM
        goit.projects_has_developers, goit.developers
    WHERE
        dev_id = developers_id
    GROUP BY projects_id
    ORDER BY salary_sum DESC) AS temp) AS temp1
WHERE
    projects_id = proj_id;