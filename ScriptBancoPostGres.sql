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
CONSTRAINT fk_usuario_endereco FOREIGN KEY (id_endereco) REFERENCES ENDERECO(id_endereco)
);

CREATE TABLE Funcionario(
id_funcionario SERIAL PRIMARY KEY,
id_usuario INTEGER,
CONSTRAINT fk_funcionario_usuario FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario)
);

CREATE TABLE Imovel(
id_imovel SERIAL PRIMARY KEY,
titulo VARCHAR(255) NOT NULL,
descricao VARCHAR(255),
Obs VARCHAR(1000),
status VARCHAR(255) NOT NULL,
situacao VARCHAR(255) NOT NULL,
valor NUMERIC(16,2) NOT NULL,
area_total NUMERIC(8,2) NOT NULL,
area_edificada NUMERIC(8,2) NOT NULL,
comodos INTEGER NOT NULL,
vagas_garagem INTEGER NOT NULL,
banheiros INTEGER NOT NULL,
data_cadastro TIMESTAMP NOT NULL,
diretorio_imagem VARCHAR(255) NOT NULL,
tipo_imovel VARCHAR(255) NOT NULL,
id_usuario INTEGER,
id_endereco INTEGER,
CONSTRAINT fk_imovel_endereco FOREIGN KEY (id_endereco) REFERENCES Endereco (id_endereco),
CONSTRAINT fk_imovel_usuario FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario)
);

CREATE TABLE Login(
email VARCHAR(255) NOT NULL,
senha VARCHAR(255) NOT NULL,
nivel_acesso varchar(20) NOT NULL,
situacao VARCHAR(50) NOT NULL,
id_usuario integer,
CONSTRAINT fk_login_usuario FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Contrato(
id_contrato SERIAL PRIMARY KEY,
valor_fechado NUMERIC(16,2) NOT NULL,
data_contrato DATE,
id_imovel INTEGER,
id_usuario INTEGER,
id_funcionario INTEGER,
CONSTRAINT fk_contrato_imovel FOREIGN KEY (id_imovel) REFERENCES Imovel (id_imovel),
CONSTRAINT fk_contrato_usuario FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario),
CONSTRAINT fk_contrato_funcionario FOREIGN KEY (id_funcionario) REFERENCES Funcionario (id_funcionario)
);

CREATE TABLE Agendamento(
id_agendamento SERIAL PRIMARY KEY,
data_inicio DATE NOT NULL,
data_fim DATE NOT NULL,
status VARCHAR(255) NOT NULL, 
id_usuario INTEGER,
id_funcionario INTEGER,
id_imovel INTEGER,
CONSTRAINT fk_agendamento_usuario FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario),
CONSTRAINT fk_agendamento_imovel FOREIGN KEY (id_imovel) REFERENCES Imovel (id_imovel),
CONSTRAINT fk_agendamento_funcionario FOREIGN KEY (id_funcionario) REFERENCES Funcionario (id_funcionario)
);