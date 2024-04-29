INSERT INTO papel (nome)
VALUES ('ADM'),
       ('PEDAGOGICO'),
       ('RECRUITER'),
       ('PROFESSOR'),
       ('ALUNO');

INSERT INTO usuario (login, senha, papel_id)
VALUES ('admin', '$2a$12$HqWiU1W7PadL8Naz9T6oAuzRB2ds7cDrpqw14CWsKnWEikeQ.Jzlq', 1),
       ('cesar', '$2a$12$HqWiU1W7PadL8Naz9T6oAuzRB2ds7cDrpqw14CWsKnWEikeQ.Jzlq', 2),
       ('alessandra', '$2a$12$HqWiU1W7PadL8Naz9T6oAuzRB2ds7cDrpqw14CWsKnWEikeQ.Jzlq', 3),
       ('andre', '$2a$12$HqWiU1W7PadL8Naz9T6oAuzRB2ds7cDrpqw14CWsKnWEikeQ.Jzlq', 4),
       ('gabriel', '$2a$12$HqWiU1W7PadL8Naz9T6oAuzRB2ds7cDrpqw14CWsKnWEikeQ.Jzlq', 4),
       ('joao', '$2a$12$HqWiU1W7PadL8Naz9T6oAuzRB2ds7cDrpqw14CWsKnWEikeQ.Jzlq', 5),
       ('pamela', '$2a$12$HqWiU1W7PadL8Naz9T6oAuzRB2ds7cDrpqw14CWsKnWEikeQ.Jzlq', 5),
       ('leonardo', '$2a$12$HqWiU1W7PadL8Naz9T6oAuzRB2ds7cDrpqw14CWsKnWEikeQ.Jzlq', 5),
       ('leandro', '$2a$12$HqWiU1W7PadL8Naz9T6oAuzRB2ds7cDrpqw14CWsKnWEikeQ.Jzlq', 5),
       ('breno', '$2a$12$HqWiU1W7PadL8Naz9T6oAuzRB2ds7cDrpqw14CWsKnWEikeQ.Jzlq', 5),
       ('pedro', '$2a$12$HqWiU1W7PadL8Naz9T6oAuzRB2ds7cDrpqw14CWsKnWEikeQ.Jzlq', 5),
       ('anaisa', '$2a$12$HqWiU1W7PadL8Naz9T6oAuzRB2ds7cDrpqw14CWsKnWEikeQ.Jzlq', 5);

INSERT INTO docente (nome, usuario_id, data_entrada)
VALUES ('Administrador', 1, '2024-04-28'),
       ('César Abascal', 2, '2024-04-28'),
       ('Alessandra Ribeiro', 3, '2024-04-28'),
       ('André Nunes', 4, '2024-04-28'),
       ('Gabriel Augustin', 5, '2024-04-28');

INSERT INTO curso (nome)
VALUES ('Desenvolvimento Web - Fullstack'),
       ('Desenvolvimento Web - FuturoDEV');

INSERT INTO materia (nome, curso_id)
VALUES ('Java', 1),
       ('Spring', 1),
       ('Angular', 1),
       ('React', 2),
       ('Node.js', 2);

INSERT INTO turma (nome, docente_id, curso_id)
VALUES ('Fullstack Education', 4, 1),
       ('Fullstack Health', 5, 1),
       ('FuturoDEV Eco', 4, 2),
       ('FuturoDEV Fitness', 5, 2),
       ('FuturoDEV Nature', 5, 2);

INSERT INTO aluno (nome, usuario_id, turma_id, data_nascimento)
VALUES ('João Victor Olivo', 6, 1, '2007-08-09'),
       ('Pâmela Silva', 7, 1, '1989-02-14'),
       ('Leonardo Madeira', 8, 1, '1998-06-21'),
       ('Leandro Dias', 9, 2, '1985-11-08'),
       ('Breno Rippel', 10, 2, '1994-06-02'),
       ('Pedro Santos', 11, 3, '2004-03-02'),
       ('Anaísa Teodoro', 12, 5, '1987-06-05');

INSERT INTO nota (aluno_id, docente_id, materia_id, valor, data_entrada)
VALUES (1, 4, 1, 9.75, '2024-04-28'),
       (1, 4, 2, 7.4, '2024-04-28'),
       (1, 4, 2, 6, '2024-04-28'),
       (2, 4, 3, 8, '2024-04-28'),
       (4, 4, 3, 9.2, '2024-04-28'),
       (7, 5, 5, 7.9, '2024-04-28');