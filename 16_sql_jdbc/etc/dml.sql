INSERT INTO grade (description, value) VALUES ('Fail', 1);
INSERT INTO grade (description, value) VALUES ('Pass', 2);
INSERT INTO grade (description, value) VALUES ('Average', 3);
INSERT INTO grade (description, value) VALUES ('Good', 4);
INSERT INTO grade (description, value) VALUES ('Very good', 5);

INSERT INTO person_type (description, value) VALUES ('Administration', 'Dean');
INSERT INTO person_type (description, value) VALUES ('Teaching and grading', 'Teacher');
INSERT INTO person_type (description, value) VALUES ('Studying', 'Student');

INSERT INTO person_status (description, value) VALUES ('Default', 'Active');
INSERT INTO person_status (description, value) VALUES ('Absent', 'Sick leave');
INSERT INTO person_status (description, value) VALUES ('Activity is suspended', 'Academic leave');
INSERT INTO person_status (description, value) VALUES ('Activity is terminated', 'Expelled');

INSERT INTO semester (semester_name, semester_date_start, semester_date_end) VALUES ('1st', TO_DATE('1-SEP-2015', 'DD-MON-YYYY'), TO_DATE( '31-DEC-2015', 'DD-MON-YYYY'));
INSERT INTO semester (semester_name, semester_date_start, semester_date_end) VALUES ('2nd', TO_DATE('1-JAN-2016', 'DD-MON-YYYY'), TO_DATE( '31-MAY-2016', 'DD-MON-YYYY'));
INSERT INTO semester (semester_name, semester_date_start, semester_date_end) VALUES ('3rd', TO_DATE('1-SEP-2016', 'DD-MON-YYYY'), TO_DATE( '31-DEC-2016', 'DD-MON-YYYY'));
INSERT INTO semester (semester_name, semester_date_start, semester_date_end) VALUES ('4th', TO_DATE('1-JAN-2017', 'DD-MON-YYYY'), TO_DATE( '31-MAY-2017', 'DD-MON-YYYY'));

INSERT INTO person (first_name, middle_name, last_name, birthday, date_start, person_type_id, person_status_id)
VALUES ('Averill', 'Humphrey', 'Samuelson', TO_DATE('25-SEP-1916', 'DD-MON-YYYY'), TO_DATE('25-JUN-2006', 'DD-MON-YYYY'),
    select person_type_id from person_type where value = 'Dean',
    select person_status_id from person_status where value = 'Active');

INSERT INTO person (first_name, middle_name, last_name, birthday, date_start, person_type_id, person_status_id)
VALUES ('Aden', 'Giffard', 'Lyon', TO_DATE('20-MAR-1975', 'DD-MON-YYYY'), TO_DATE('15-AUG-2012', 'DD-MON-YYYY'),
    (select person_type_id from person_type where value = 'Dean'),
    (select person_status_id from person_status where value = 'Active'));

UPDATE person 
SET person_type_id = (select person_type_id from person_type where value = 'Teacher')
WHERE first_name = 'Aden' and last_name = 'Lyon';

INSERT INTO person (first_name, middle_name, last_name, birthday, date_start, person_type_id, person_status_id)
VALUES ('Matt', 'Sullivan', 'Hochberg', TO_DATE('13-FEB-1963', 'DD-MON-YYYY'), TO_DATE('15-AUG-2012', 'DD-MON-YYYY'),
    (select person_type_id from person_type where value = 'Teacher'),
    (select person_status_id from person_status where value = 'Active'));

INSERT INTO person (first_name, middle_name, last_name, birthday, date_start, person_type_id, person_status_id)
VALUES ('Jarod', NULL, 'Fry', TO_DATE('15-MAR-1993', 'DD-MON-YYYY'), TO_DATE('15-AUG-2015', 'DD-MON-YYYY'),
    (select person_type_id from person_type where value = 'Student'),
    (select person_status_id from person_status where value = 'Active'));

INSERT INTO person (first_name, middle_name, last_name, birthday, date_start, person_type_id, person_status_id)
VALUES ('Yvo', 'Vern', 'Barnet', TO_DATE('1-JUN-1990', 'DD-MON-YYYY'), TO_DATE('13-AUG-2015', 'DD-MON-YYYY'),
    (select person_type_id from person_type where value = 'Student'),
    (select person_status_id from person_status where value = 'Active'));

INSERT INTO person (first_name, middle_name, last_name, birthday, date_start, person_type_id, person_status_id)
VALUES ('Mark', 'Nathan', 'Konstantinov', TO_DATE('12-JUN-1992', 'DD-MON-YYYY'), TO_DATE('25-DEC-2015', 'DD-MON-YYYY'),
    (select person_type_id from person_type where value = 'Student'),
    (select person_status_id from person_status where value = 'Sick leave'));

INSERT INTO person (first_name, middle_name, last_name, birthday, date_start, person_type_id, person_status_id)
VALUES ('Dana', 'Osmond', 'Savage', TO_DATE('14-JUN-1992', 'DD-MON-YYYY'), TO_DATE('20-DEC-2015', 'DD-MON-YYYY'),
    (select person_type_id from person_type where value = 'Student'),
    (select person_status_id from person_status where value = 'Academic leave'));

INSERT INTO person (first_name, middle_name, last_name, birthday, date_start, person_type_id, person_status_id)
VALUES ('Darnell', 'Tim', 'Sandford', TO_DATE('14-JUN-1992', 'DD-MON-YYYY'), TO_DATE('20-DEC-2015', 'DD-MON-YYYY'),
    (select person_type_id from person_type where value = 'Student'),
    (select person_status_id from person_status where value = 'Academic leave'));

INSERT INTO phone_number (person_id, phone_number) VALUES (1, '202-555-0195');
INSERT INTO phone_number (person_id, phone_number) VALUES (2, '+1-202-555-0167');
INSERT INTO phone_number (person_id, phone_number) VALUES (2, '+1-850-974-8113');
INSERT INTO phone_number (person_id, phone_number) VALUES (3, '+1-262-709-3127');
INSERT INTO phone_number (person_id, phone_number) VALUES (4, '917-471-0430');
INSERT INTO phone_number (person_id, phone_number) VALUES (5, '304-565-6075');
INSERT INTO phone_number (person_id, phone_number) VALUES (5, '+1-939-307-9646');
INSERT INTO phone_number (person_id, phone_number) VALUES (6, '+1-602-276-7168');
INSERT INTO phone_number (person_id, phone_number) VALUES (7, '+1-520-408-5102');
INSERT INTO phone_number (person_id, phone_number) VALUES (7, '928-612-9331');
INSERT INTO phone_number (person_id, phone_number) VALUES (8, '+1-928-293-6658');
INSERT INTO phone_number (person_id, phone_number) VALUES (8, '602-369-7307');
INSERT INTO phone_number (person_id, phone_number) VALUES (8, '602-468-7493');

DELETE FROM phone_number 
WHERE person_id = 7;

INSERT INTO subject (subject_name, teacher_id, semester_id) VALUES ('JUnit', 2, 1);
INSERT INTO subject (subject_name, teacher_id, semester_id) VALUES ('Reflection', 3, 1);
INSERT INTO subject (subject_name, teacher_id, semester_id) VALUES ('SQL', 2, 2);
INSERT INTO subject (subject_name, teacher_id, semester_id) VALUES ('IO', 3, 3);
INSERT INTO subject (subject_name, teacher_id, semester_id) VALUES ('JDBC', 3, 3);
INSERT INTO subject (subject_name, teacher_id, semester_id) VALUES ('TMP', 3, 3);

DELETE FROM subject 
WHERE subject_name = 'TMP';

INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (4, 1, 5, TIMESTAMPADD('DAY', -1, CURRENT_TIMESTAMP()));
INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (4, 1, 3, TIMESTAMPADD('DAY', -3, CURRENT_TIMESTAMP()));
INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (4, 2, 3, TIMESTAMPADD('DAY', -2, CURRENT_TIMESTAMP()));
INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (4, 2, 4, TIMESTAMPADD('DAY', -10, CURRENT_TIMESTAMP()));
INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (4, 2, 5, TIMESTAMPADD('DAY', -1, CURRENT_TIMESTAMP()));

INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (5, 2, 5, TIMESTAMPADD('DAY', -2, CURRENT_TIMESTAMP()));
INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (5, 2, 4, TIMESTAMPADD('DAY', -3, CURRENT_TIMESTAMP()));
INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (5, 2, 3, TIMESTAMPADD('DAY', -4, CURRENT_TIMESTAMP()));
INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (5, 3, 4, TIMESTAMPADD('DAY', -6, CURRENT_TIMESTAMP()));
INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (5, 3, 5, TIMESTAMPADD('DAY', -1, CURRENT_TIMESTAMP()));

INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (6, 2, 5, TIMESTAMPADD('DAY', -2, CURRENT_TIMESTAMP()));
INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (6, 1, 4, TIMESTAMPADD('DAY', -3, CURRENT_TIMESTAMP()));
INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (6, 3, 3, TIMESTAMPADD('DAY', -7, CURRENT_TIMESTAMP()));
INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (6, 4, 4, TIMESTAMPADD('DAY', -6, CURRENT_TIMESTAMP()));
INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (6, 4, 5, TIMESTAMPADD('DAY', -4, CURRENT_TIMESTAMP()));

INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (7, 3, 3, TIMESTAMPADD('DAY', -2, CURRENT_TIMESTAMP()));
INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (7, 3, 4, TIMESTAMPADD('DAY', -2, CURRENT_TIMESTAMP()));
INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (7, 4, 3, TIMESTAMPADD('DAY', -4, CURRENT_TIMESTAMP()));
INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (7, 4, 4, TIMESTAMPADD('DAY', -3, CURRENT_TIMESTAMP()));
INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (7, 5, 1, TIMESTAMPADD('DAY', -8, CURRENT_TIMESTAMP()));

INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (8, 3, 4, TIMESTAMPADD('DAY', -1, CURRENT_TIMESTAMP()));
INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (8, 3, 5, TIMESTAMPADD('DAY', -7, CURRENT_TIMESTAMP()));
INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (8, 4, 4, TIMESTAMPADD('DAY', -6, CURRENT_TIMESTAMP()));
INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (8, 5, 5, TIMESTAMPADD('DAY', -4, CURRENT_TIMESTAMP()));
INSERT INTO journal (person_id, subject_id, grade_id, grade_date) VALUES (8, 5, 4, TIMESTAMPADD('DAY', -5, CURRENT_TIMESTAMP()));