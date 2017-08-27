#3. Вычислить общую ЗП всех Java разработчиков.
SELECT 
    SUM(salary)
FROM
    goit.developers
WHERE
    dev_id IN (SELECT 
            developers_id
        FROM
            goit.developers_has_skills
        WHERE
            skills_id = (SELECT 
                    skill_id
                FROM
                    goit.skills
                WHERE
                    skill_skill LIKE 'JAVA')
    );