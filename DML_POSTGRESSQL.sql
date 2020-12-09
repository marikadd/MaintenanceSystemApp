DELETE FROM Competence;
DELETE FROM Users_Competences;
DELETE FROM Activity_Competences;
DELETE FROM Activity_Maintainers;
DELETE FROM MaintenanceActivity;
DELETE FROM Department;
DELETE FROM Material;
DELETE FROM Users;



insert into Users values ('Mario','Rossi', 'mrossi', 'MRossi98', 'mariorossi@gmail.it', '3339546046', 'MAINTAINER');
insert into Users values ('Luca','Bianchi', 'lbianchi', 'LBianchi*', 'lbianchi@gmail.it', '3349646278', 'PLANNER');
insert into Users values ('Fabio','Cerruti', 'fcerruti', 'Fcerruti98@', 'fcerruti@gmail.it', '3331146046', 'MAINTAINER');


insert into Competence (Description) values ('PAV Certification');
insert into Competence (Description) values ('Mechanical Maintenance');
insert into Competence (Description) values ('Compressor Knowledge');
insert into Competence (Description) values ('Molding plant knowledge');

insert into Department (Area) values ('Fisciano - Molding');
insert into Department (Area) values ('Nusco - Carpentry');

insert into MaintenanceActivity(Type_Activity, Site, Description, Time_Activity, Week_Number, Assigned) values ('Eletric','Fisciano - Molding','change tube',120, '12', 'True');
insert into MaintenanceActivity(Type_Activity, Site, Description, Time_Activity, Week_Number, Assigned) values ('Hydraulic','Nusco - Carpentry','change sink',100, '9', 'True');

insert into Users_Competences (Username, ID_Competences) values ('mrossi',1);

insert into Material(Type_Material) values ('Iron');
insert into Material(Type_Material) values ('Wood');
insert into Material(Type_Material) values ('Marble');

ALTER SEQUENCE Competence_ID_seq RESTART WITH 1;