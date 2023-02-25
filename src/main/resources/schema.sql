DROP TABLE IF EXISTS users;

CREATE TABLE users (
    userId varchar(255)  PRIMARY KEY, 
    username varchar(255), 
    email varchar(25), img varchar(255), 
    gender varchar(20), 
    age int, lfr varchar(20), 
    password varchar(16)
);

-- SESSION CREATION TABLES

CREATE TABLE SPRING_SESSION (
    PRIMARY_ID CHAR(36) NOT NULL,
    SESSION_ID CHAR(36) NOT NULL,
    CREATION_TIME BIGINT NOT NULL,
    LAST_ACCESS_TIME BIGINT NOT NULL,
    MAX_INACTIVE_INTERVAL INT NOT NULL,
    EXPIRY_TIME BIGINT NOT NULL,
    PRINCIPAL_NAME VARCHAR(100),
    CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
) ENGINE=InnoDB;


CREATE TABLE spring_session_attributes (
  session_id VARCHAR(255) NOT NULL,
  attribute_name VARCHAR(255) NOT NULL,
  attribute_bytes BLOB NOT NULL,
  PRIMARY KEY (session_id, attribute_name),
  CONSTRAINT spring_session_attributes_fk FOREIGN KEY (session_id) REFERENCES spring_session (session_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
