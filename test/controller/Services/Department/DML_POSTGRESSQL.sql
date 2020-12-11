DELETE FROM Competence;
DELETE FROM Users_Competences;
DELETE FROM Activity_Competences;
DELETE FROM Activity_Maintainers;
DELETE FROM MaintenanceActivity;
DELETE FROM Department;
DELETE FROM Users;

insert into Users values ('Mario','Rossi', 'mrossi', 'MRossi98', 'mariorossi@gmail.it', '3339546046', 'MAINTAINER');
insert into Users values ('Luca','Bianchi', 'lbianchi', 'LBianchi*', 'lbianchi@gmail.it', '3349646278', 'PLANNER');

insert into Competence (Description) values ('PAV Certification');
insert into Competence (Description) values ('Mechanical Maintenance');
insert into Competence (Description) values ('Compressor Knowledge');
insert into Competence (Description) values ('Molding plant knowledge');

insert into Department (Area) values ('Fisciano - Molding');
insert into Department (Area) values ('Nusco - Carpentry');

insert into MaintenanceActivity(Type_Activity, Site, Description, Time_Activity, Week_Number, Assigned) values ('Eletric','Fisciano - Molding','change tube' 120, '12', 'True');