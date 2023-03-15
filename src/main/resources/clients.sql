CREATE DATABASE clients;

CREATE SCHEMA if NOT EXISTS clients default CHARACTER SET UTF8 COLLATE utf8_hungarian_ci; 
CREATE USER 'clients'@'localhost' IDENTIFIED BY 'clients';
GRANT ALL ON clients.* TO 'clients'@'localhost';

create table clients(id bigint auto_increment, client_name varchar(255), constraint pk_clients primary key (id));




GRANT ALL PRIVILEGES ON clients.* TO 'clients'@'localhost'; 

INSERT INTO clients(client_name) VALUES ('Jane Doe');