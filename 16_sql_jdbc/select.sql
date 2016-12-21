-- 5. simple select

SELECT * FROM grade;

SELECT first_name, last_name, birthday FROM person;

SELECT DISTINCT person_id FROM phone_number;

-- 6. conditional select

SELECT * FROM person WHERE person_type_id IN (1, 2);

SELECT * FROM person WHERE middle_name IS NULL;

SELECT * FROM person WHERE date_start BETWEEN TO_DATE('14-AUG-2015', 'DD-MON-YYYY') AND TO_DATE('21-DEC-2015', 'DD-MON-YYYY');

SELECT * FROM semester WHERE semester_date_end < CURRENT_DATE();

SELECT * FROM person WHERE first_name LIKE 'Ma%';

SELECT * FROM journal WHERE grade_id BETWEEN 2 AND 4;

SELECT * FROM subject WHERE semester_id = 1;

-- 7. nested select

SELECT phone_number FROM phone_number WHERE person_id IN (
  SELECT person_id FROM person WHERE person_type_id = 1);

SELECT subject_id, grade_id, grade_date FROM journal WHERE person_id = (
  SELECT person_id FROM person WHERE first_name = 'Yvo' AND last_name = 'Barnet');

-- 8. order by
SELECT * FROM person
ORDER BY date_start;

-- 9. union 

SELECT * FROM person WHERE person_id IN (
  SELECT person_id FROM grade where GRADE_ID = 1)
UNION
SELECT * FROM person WHERE person_id IN (
  SELECT person_id FROM grade where GRADE_ID = 5);

-- 10. inner join

SELECT p.first_name, p.last_name, pt.value, ps.value 
FROM person p
JOIN person_type pt ON p.person_type_id = pt.person_type_id
JOIN person_status ps ON p.person_status_id = ps.person_status_id;

SELECT p.first_name, p.last_name, s.subject_name, g.value, j.grade_date 
FROM journal j
JOIN person p ON j.person_id = p.person_id
JOIN subject s ON j.subject_id = s.subject_id AND s.subject_name = 'IO'
JOIN grade g ON j.grade_id = g.grade_id;

SELECT sem.semester_name, sub.subject_name 
FROM semester sem
JOIN subject sub on sem.semester_id = sub.semester_id;

-- 11. outer join

SELECT p.first_name, p.last_name, pn.phone_number 
FROM person p
LEFT JOIN phone_number pn ON p.person_id = pn.person_id ;

SELECT p.first_name, p.last_name, ps.value 
FROM person p
RIGHT JOIN person_status ps ON p.person_status_id = ps.person_status_id;

-- 12. numeric functions

-- 13. string functions

SELECT CONCAT(first_name, ' ', last_name)  as name, birthday FROM person;

-- 14. calendar functions

SELECT CONCAT(first_name, ' ', last_name)  as name, DATEDIFF('YEAR', birthday, CURRENT_DATE()) as age FROM person;

-- 15. aggregate functions

-- shows average grades 
SELECT p.first_name, p.last_name, s.subject_name, AVG(g.value) as grade FROM person p
JOIN journal j ON p.person_id = j.person_id
JOIN subject s ON s.subject_id = j.subject_id
JOIN grade g ON j.grade_id = g.grade_id
GROUP BY p.first_name, p.last_name, s.subject_name
ORDER BY p.last_name, s.subject_name;

SELECT CONCAT(p.first_name, ' ', p.last_name) as name, COUNT(s.subject_id) as subjects FROM person p
JOIN subject s on p.person_id = s.teacher_id
GROUP BY name
HAVING subjects > 2;
