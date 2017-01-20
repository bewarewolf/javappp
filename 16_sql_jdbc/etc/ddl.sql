CREATE TABLE grade (
  grade_id INT IDENTITY,
  description VARCHAR(100) NOT NULL,
  value INT UNIQUE NOT NULL
);

CREATE TABLE person (
  person_id INT IDENTITY,
  first_name VARCHAR(100) NOT NULL,
  middle_name VARCHAR(100),
  last_name VARCHAR(100) NOT NULL,
  birthday DATE NOT NULL,
  date_start DATE NOT NULL,
  person_type_id INT NOT NULL,
  person_status_id INT NOT NULL,
  login VARCHAR(100) UNIQUE,
  password VARCHAR(200)
);

CREATE TABLE subject (
  subject_id INT IDENTITY,
  subject_name VARCHAR(100) NOT NULL UNIQUE,
  teacher_id INT,
  semester_id INT NOT NULL
);

CREATE TABLE semester (
  semester_id INT IDENTITY,
  semester_name VARCHAR(20) NOT NULL UNIQUE,
  semester_date_start DATE NOT NULL,
  semester_date_end DATE NOT NULL
);

CREATE TABLE journal (
  record_id INT IDENTITY,
  person_id INT NOT NULL,
  subject_id INT NOT NULL,
  grade_id INT NOT NULL,
  grade_date TIMESTAMP NOT NULL,
  FOREIGN KEY (grade_id) REFERENCES grade (grade_id) ON DELETE CASCADE,
  FOREIGN KEY (person_id) REFERENCES person (person_id) ON DELETE CASCADE,
  FOREIGN KEY (subject_id) REFERENCES subject (subject_id) ON DELETE CASCADE,
);

CREATE TABLE phone_number (
  phone_number_id INT IDENTITY,
  phone_number VARCHAR(20) NOT NULL UNIQUE,
  person_id INT NOT NULL,
  FOREIGN KEY (person_id) REFERENCES person (person_id) ON DELETE CASCADE
);

CREATE TABLE person_type (
  person_type_id INT IDENTITY,
  description VARCHAR(100),
  value VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE person_status (
  person_status_id INT IDENTITY,
  description VARCHAR(100),
  value VARCHAR(20) NOT NULL UNIQUE
);

ALTER TABLE person
ADD FOREIGN KEY (person_type_id) REFERENCES person_type (person_type_id);

ALTER TABLE person
ADD FOREIGN KEY (person_status_id) REFERENCES person_status (person_status_id);

ALTER TABLE subject
ADD FOREIGN KEY (teacher_id) REFERENCES person (person_id) ON DELETE CASCADE;

ALTER TABLE subject
ADD FOREIGN KEY (semester_id) REFERENCES semester (semester_id) ON DELETE CASCADE;