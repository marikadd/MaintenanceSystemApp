delete from Users_Competences;
delete from Activity_Competences;
delete from Activity_Maintainers;
delete from MaintenanceActivity;
delete from Department;
delete from Material;
delete from Users;
delete from Competence;
delete from Notification_Planner;

select setval('competence_id_seq', 1, false);
select setval('maintaineractivityday_id_seq', 1, false);
select setval('maintenanceactivity_id_seq', 1, false);
select setval('material_id_seq', 1, false);

insert into Users values ('Mario','Rossi', 'mrossi', 'MRossi98', 'mariorossi@gmail.it', '3339546046', 'MAINTAINER');
insert into Users values ('Luca','Bianchi', 'lbianchi', 'LBianchi*', 'lbianchi@gmail.it', '3349646278', 'PLANNER');
insert into Users values ('Fabio','Cerruti', 'fcerruti', 'Fcerruti98@', 'fcerruti@gmail.it', '3331146046', 'MAINTAINER');
insert into Users values ('Giacomo','Coppola', 'gcoppola', 'GCoppola98@', 'gcoppola@gmail.it', '3462234556', 'SYSTEM_ADMIN');
insert into Users values ('Lucio','Fermi', 'lfermi', 'LFermi1998@', 'lfermi@gmail.it', '3331146099', 'PROD_MANAGER');

insert into Competence (Description) values ('PAV Certification');
insert into Competence (Description) values ('Mechanical Maintenance');
insert into Competence (Description) values ('Compressor Knowledge');
insert into Competence (Description) values ('Molding plant knowledge');

insert into Department (Area) values ('Fisciano - Molding');
insert into Department (Area) values ('Nusco - Carpentry');

insert into MaintenanceActivity(Type_Activity, Site, Description, Time_Activity, Week_Number, Assigned) values ('Eletric','Fisciano - Molding','change tube',120, '12', 'true');
insert into MaintenanceActivity(Type_Activity, Site, Description, Time_Activity, Week_Number, Assigned) values ('Hydraulic','Nusco - Carpentry','change sink',100, '9', 'false');

insert into Users_Competences (Username, ID_Competences) values ('mrossi',1);

insert into Activity_Competences(Competence_ID, Activity_ID) values (1,1);

insert into Activity_Maintainers(Username_Maintainer, Activity_Maintainer_ID) values ('mrossi', 1);

insert into MaintainerActivityDay(username, ma_id, week_day) values ('mrossi', 1, 1);

insert into Material(Type_Material) values ('Iron');
insert into Material(Type_Material) values ('Wood');
insert into Material(Type_Material) values ('Marble');

insert into Notification_Planner values ('Not Read 1', false);
insert into Notification_Planner values ('Not Read 2', false);
insert into Notification_Planner values ('Read', true);


