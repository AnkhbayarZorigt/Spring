ALTER TABLE Book change name title varchar(255) null;

ALTER TABLE Book ADD COLUMN author varchar (255);
ALTER TABLE Book ADD COLUMN country varchar (255);
ALTER TABLE Book ADD COLUMN language varchar (255);
ALTER TABLE Book ADD COLUMN pages int;
ALTER TABLE Book ADD COLUMN year int;