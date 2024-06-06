CREATE DATABASE UserLoginRegister1;

USE UserLoginRegister1;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
INSERT INTO users (email, password) VALUES ('test@example.com', 'password123');

select * from users;