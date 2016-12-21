-- view

CREATE OR REPLACE VIEW IF NOT EXISTS average_grades AS (
SELECT p.first_name, p.last_name, s.subject_name, AVG(g.value) as grade FROM person p
JOIN journal j ON p.person_id = j.person_id
JOIN subject s ON s.subject_id = j.subject_id
JOIN grade g ON j.grade_id = g.grade_id
GROUP BY p.first_name, p.last_name, s.subject_name
ORDER BY p.last_name, s.subject_name);

SELECT * FROM average_grades;

-- /view

-- sequences

CREATE SEQUENCE seq1;

CREATE SEQUENCE seq1000 START WITH 1000 INCREMENT BY 10;

CREATE TABLE seq_test (
  seq1 LONG,
  seq1000 LONG
);

INSERT INTO seq_test 
VALUES (seq1.NEXTVAL, seq1000.NEXTVAL);

SELECT * FROM seq_test;

-- /sequences