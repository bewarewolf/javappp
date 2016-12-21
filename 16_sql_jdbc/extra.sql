-- index

CREATE INDEX IF NOT EXISTS idx_phone ON phone_number(phone_number);
CREATE INDEX IF NOT EXISTS idx_name ON person(first_name, last_name);

-- /index

-- function

CREATE ALIAS IF NOT EXISTS INITIALS AS $$
String initials(String fName, String mName, String lName) {
  StringBuilder sb = new StringBuilder();
  
  if (fName != null && fName.length() > 0) {
    sb.append(fName.toUpperCase().substring(0, 1));  
    sb.append(". ");
  }

  if (mName != null && mName.length() > 0) {
    sb.append(mName.toUpperCase().substring(0, 1));  
    sb.append(". ");
  }
  
  if (lName != null && lName.length() > 0) {
    sb.append(lName.toUpperCase().substring(0, 1));  
    sb.append(". ");
  }

  return sb.toString();
}
$$;

SELECT first_name, middle_name, last_name, INITIALS(first_name, middle_name, last_name) as initials from person;

-- /function

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

CREATE SEQUENCE IF NOT EXISTS seq1;

CREATE SEQUENCE IF NOT EXISTS seq1000 START WITH 1000 INCREMENT BY 10;

CREATE TABLE IF NOT EXISTS seq_test (
  seq1 LONG,
  seq1000 LONG
);

INSERT INTO seq_test 
VALUES (seq1.NEXTVAL, seq1000.NEXTVAL);

INSERT INTO seq_test 
VALUES (seq1.NEXTVAL, seq1000.NEXTVAL);

INSERT INTO seq_test 
VALUES (seq1.NEXTVAL, seq1000.NEXTVAL);

SELECT * FROM seq_test;


CREATE SEQUENCE IF NOT EXISTS seq10 START WITH 10 INCREMENT BY 5;

CREATE TABLE IF NOT EXISTS test_10 (
  id BIGINT DEFAULT seq10.nextval PRIMARY KEY,
  field VARCHAR(10)
);

INSERT INTO test_10 (field)
VALUES ('abc');
INSERT INTO test_10 (field)
VALUES ('def');
INSERT INTO test_10 (field)
VALUES ('ghi');

SELECT * from test_10;

-- /sequences
