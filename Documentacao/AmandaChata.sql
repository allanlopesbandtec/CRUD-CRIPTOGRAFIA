/****** Script do comando SelectTopNRows de SSMS  ******/

SELECT * from usuario
SELECT * from perfil
SELECT * from usuario_perfis

insert into Usuario (nome,email,senha) values
('Amanda Biagi', 'amanda.biagi@gmail.com', '$2a$10$wX9az56HFQCbro1sR8XZJ.i.rkhnCYQCWLPWQVVe3Vj3AlfiWCn4e'),
('Allan Lopes', 'allan.lopes@gmail.com', '$2a$10$wX9az56HFQCbro1sR8XZJ.i.rkhnCYQCWLPWQVVe3Vj3AlfiWCn4e');


insert into perfil (tipo_perfil) values
('ADMIN'),
('Somente Leitura');

insert into usuario_perfis values
(1,1),
(2,2);

