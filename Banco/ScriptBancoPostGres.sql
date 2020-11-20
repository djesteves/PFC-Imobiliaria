DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE Endereco(
id_endereco SERIAL PRIMARY KEY,
logradouro VARCHAR(255) NOT NULL,
complemento VARCHAR(255),
numero INTEGER NOT NULL,
cidade VARCHAR(50) NOT NULL,
cep VARCHAR(10) NOT NULL,
bairro VARCHAR(50) NOT NULL,
estado VARCHAR(50) NOT NULL
);

CREATE TABLE Usuario(
id_usuario SERIAL PRIMARY KEY,
nome VARCHAR (255) NOT NULL,
data_cadastro TIMESTAMP NOT NULL,
tel_celular VARCHAR(15) NOT NULL,
tel_residencial VARCHAR(20),
cpf_cnpj VARCHAR(20) NOT NULL,
rg VARCHAR(20),
tipo_pessoa CHAR NOT NULL,
id_endereco INTEGER,
email VARCHAR(255) NOT NULL,
senha VARCHAR(255) NOT NULL,
nivel_acesso varchar(20) NOT NULL,
situacao VARCHAR(50) NOT NULL,
CONSTRAINT fk_usuario_endereco FOREIGN KEY (id_endereco) REFERENCES ENDERECO(id_endereco)
);

CREATE TABLE Imovel(
id_imovel SERIAL PRIMARY KEY,
titulo VARCHAR(255) NOT NULL,
descricao VARCHAR(255),
status VARCHAR(255) NOT NULL,
situacao VARCHAR(255) NOT NULL,
valor NUMERIC(24,2) NOT NULL,
area_total NUMERIC(16,2) NOT NULL,
area_edificada NUMERIC(16,2) NOT NULL,
comodos INTEGER NOT NULL,
vagas_garagem INTEGER NOT NULL,
banheiros INTEGER NOT NULL,
data_cadastro TIMESTAMP NOT NULL,
diretorio_imagem VARCHAR(255) NOT NULL,
tipo_imovel VARCHAR(255) NOT NULL,
id_usuario INTEGER,
id_endereco INTEGER,
modalidade_imovel varchar(255) NOT NULL,
iptu NUMERIC(16,2),
condominio NUMERIC (16,2),
obs VARCHAR(1000),
data_validacao TIMESTAMP,
CONSTRAINT fk_imovel_endereco FOREIGN KEY (id_endereco) REFERENCES Endereco (id_endereco),
CONSTRAINT fk_imovel_usuario FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario)
);

CREATE TABLE Agendamento(
id_agendamento SERIAL PRIMARY KEY,
dataAgendamento TIMESTAMP NOT NULL,
dataSolicitacao TIMESTAMP NOT NULL,
status VARCHAR(255) NOT NULL, 
situacao VARCHAR(20) NOT NULL,
id_usuario INTEGER,
id_imovel INTEGER,
id_corretor INTEGER,
CONSTRAINT fk_agendamento_usuario FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario),
CONSTRAINT fk_agendamento_usuarioCorretor FOREIGN KEY (id_corretor) REFERENCES Usuario (id_usuario),
CONSTRAINT fk_agendamento_imovel FOREIGN KEY (id_imovel) REFERENCES Imovel (id_imovel)
);


-- INSERT ADMINISTRADOR
insert into endereco values (DEFAULT, 'Praça dos Imigrantes', null, '13', 'Mogi das Cruzes','08735-080', 'Jardim Avenida','SP');
insert into usuario values (DEFAULT, 'Administrador', NOW(), '11912345678','1143211234','774.341.551-16', '29.767.686-6', 'F', 1, 'admin@email.com', '202CB962AC59075B964B07152D234B70', 'ADMINISTRADOR', 'Ativo');

-- INSERT CORRETOR 1
insert into endereco values (DEFAULT, 'Praça dos Imigrantes', null, '13', 'Mogi das Cruzes','08735-080', 'Jardim Avenida','SP');
insert into usuario values (DEFAULT, 'Corretor 1 - Diego', NOW(), '11912345678','1143211234','024.939.380-89', '29.767.686-6', 'F', 1, 'corretor1@email.com', '202CB962AC59075B964B07152D234B70', 'CORRETOR', 'Ativo');

-- INSERT CORRETOR 2
insert into endereco values (DEFAULT, 'Praça dos Imigrantes', null, '13', 'Mogi das Cruzes','08735-080', 'Jardim Avenida','SP');
insert into usuario values (DEFAULT, 'Consultor 2 - Danilo', NOW(), '11912345678','1143211234','047.653.890-49', '29.767.686-6', 'F', 1, 'corretor2@email.com', '202CB962AC59075B964B07152D234B70', 'CORRETOR', 'Ativo');

-- INSERT USUARIO Vendedor
insert into endereco values (DEFAULT, 'Praça dos Imigrantes', null, '13', 'Mogi das Cruzes','08735-080', 'Jardim Avenida','SP');
insert into usuario values (DEFAULT, 'Usuario Vendedor', NOW(), '11912345678','1143211234','668.962.280-08', '29.767.686-6', 'F', 1, 'usuario@email.com', '202CB962AC59075B964B07152D234B70', 'USUARIO', 'Ativo');

-- INSERT USUARIO Solicitante
insert into endereco values (DEFAULT, 'Praça dos Imigrantes', null, '13', 'Mogi das Cruzes','08735-080', 'Jardim Avenida','SP');
insert into usuario values (DEFAULT, 'Usuario Solicitante', NOW(), '11912345678','1143211234','479.229.558-04', '29.767.686-6', 'F', 1, 'diegoguedespereira@gmail.com', '202CB962AC59075B964B07152D234B70', 'USUARIO', 'Ativo');