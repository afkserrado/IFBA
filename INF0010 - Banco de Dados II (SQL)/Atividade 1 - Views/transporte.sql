DROP TABLE IF EXISTS Calendario_Excecao CASCADE;
DROP TABLE IF EXISTS Programacao CASCADE;
DROP TABLE IF EXISTS Calendario CASCADE;
DROP TABLE IF EXISTS Viagem CASCADE;
DROP TABLE IF EXISTS Itinerario_Ponto CASCADE;
DROP TABLE IF EXISTS Itinerario CASCADE;
DROP TABLE IF EXISTS Ponto CASCADE;
DROP TABLE IF EXISTS Endereco CASCADE;
DROP TABLE IF EXISTS Rua CASCADE;
DROP TABLE IF EXISTS Bairro CASCADE;
DROP TABLE IF EXISTS Linha CASCADE;
DROP TABLE IF EXISTS Passageiro CASCADE;
DROP TABLE IF EXISTS Cobrador CASCADE;
DROP TABLE IF EXISTS Motorista CASCADE;
DROP TABLE IF EXISTS Veiculo CASCADE;
DROP TABLE IF EXISTS Consorcio_Empresa CASCADE;
DROP TABLE IF EXISTS Empresa CASCADE;
DROP TABLE IF EXISTS Consorcio CASCADE;

CREATE TABLE Consorcio (
	cnpj VARCHAR(14) NOT NULL,
	nome VARCHAR NOT NULL,

	CONSTRAINT pk_consorcio PRIMARY KEY (cnpj)
);

CREATE TABLE Empresa (
	cnpj VARCHAR(14) NOT NULL,
	nome VARCHAR NOT NULL,
	cnpj_consorcio VARCHAR(14),

	CONSTRAINT pk_empresa PRIMARY KEY (cnpj),
	
	CONSTRAINT fk_empresa_consorcio FOREIGN KEY (cnpj_consorcio)
		REFERENCES Consorcio (cnpj)
);

CREATE TABLE Consorcio_Empresa (
	cnpj_consorcio VARCHAR(14) NOT NULL,
	cnpj_empresa VARCHAR(14) NOT NULL,

	CONSTRAINT pk_consorcio_empresa PRIMARY KEY (cnpj_consorcio, cnpj_empresa),
	
	CONSTRAINT fk_consorcio_empresa_cnpj_consorcio_consorcio FOREIGN KEY (cnpj_consorcio)
		REFERENCES Consorcio (cnpj),
	
	CONSTRAINT fk_consorcio_empresa_cnpj_empresa_empresa FOREIGN KEY (cnpj_empresa)
		REFERENCES Empresa (cnpj)
);

CREATE TABLE Veiculo (
	placa VARCHAR(7) NOT NULL,
	cnpj VARCHAR(14) NOT NULL,
	num_veiculo VARCHAR NOT NULL,
	modelo VARCHAR NOT NULL,

	CONSTRAINT pk_veiculo PRIMARY KEY (placa),

	CONSTRAINT fk_veiculo_empresa FOREIGN KEY (cnpj)
		REFERENCES Empresa (cnpj),

	CONSTRAINT uq_veiculo_num_veiculo UNIQUE (num_veiculo)
);

CREATE TABLE Motorista (
	registro VARCHAR NOT NULL,
	nome VARCHAR NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	data_nascimento DATE NOT NULL,

	CONSTRAINT pk_motorista PRIMARY KEY (registro),

	CONSTRAINT uq_motorista_cpf UNIQUE (cpf)
);

CREATE TABLE Cobrador (
	registro VARCHAR NOT NULL,
	nome VARCHAR NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	data_nascimento DATE NOT NULL,

	CONSTRAINT pk_cobrador PRIMARY KEY (registro),

	CONSTRAINT uq_cobrador_cpf UNIQUE (cpf)
);

CREATE TABLE Passageiro (
	num_cartao VARCHAR NOT NULL,
	nome VARCHAR NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	data_nascimento DATE NOT NULL,

	CONSTRAINT pk_passageiro PRIMARY KEY (num_cartao),

	CONSTRAINT uq_passageiro_cpf UNIQUE (cpf)
);

CREATE TABLE Linha (
	numero VARCHAR NOT NULL,
	descricao VARCHAR NOT NULL,

	CONSTRAINT pk_linha PRIMARY KEY (numero)
);

CREATE TABLE Rua (
	codigo VARCHAR NOT NULL,
	cep VARCHAR(8) NOT NULL,
	nome VARCHAR NOT NULL,

	CONSTRAINT pk_rua PRIMARY KEY (codigo)
);

CREATE TABLE Bairro (
	codigo VARCHAR NOT NULL,
	nome VARCHAR NOT NULL,

	CONSTRAINT pk_bairro PRIMARY KEY (codigo)
);

CREATE TABLE Endereco (
	id_endereco INTEGER NOT NULL,
	cod_rua VARCHAR NOT NULL,
	cod_bairro VARCHAR NOT NULL,

	CONSTRAINT pk_endereco PRIMARY KEY (id_endereco),

	CONSTRAINT fk_endereco_rua FOREIGN KEY (cod_rua)
		REFERENCES Rua (codigo),

	CONSTRAINT fk_endereco_bairro FOREIGN KEY (cod_bairro)
		REFERENCES Bairro (codigo)
);

CREATE TABLE Ponto (
	id_ponto INTEGER NOT NULL,
	id_endereco INTEGER NOT NULL,
	num_ponto VARCHAR NOT NULL,

	CONSTRAINT pk_ponto PRIMARY KEY (id_ponto),

	CONSTRAINT fk_ponto_endereco FOREIGN KEY (id_endereco)
		REFERENCES Endereco (id_endereco)
);

CREATE TABLE Itinerario (
	numero VARCHAR NOT NULL,
	sentido VARCHAR NOT NULL,
	descricao VARCHAR NOT NULL,
	vigencia_inicio DATE NOT NULL,
	vigencia_fim DATE NOT NULL,
	status BOOLEAN NOT NULL,

	CONSTRAINT pk_itinerario PRIMARY KEY (numero)
);

CREATE TABLE Calendario (
	id_calendario INTEGER NOT NULL,
	segunda BOOLEAN NOT NULL,
	terca BOOLEAN NOT NULL,
	quarta BOOLEAN NOT NULL,
	quinta BOOLEAN NOT NULL,
	sexta BOOLEAN NOT NULL,
	sabado BOOLEAN NOT NULL,
	domingo BOOLEAN NOT NULL,
	vigencia_inicio DATE NOT NULL,
	vigencia_fim DATE NOT NULL,
	descricao VARCHAR NOT NULL,

	CONSTRAINT pk_calendario PRIMARY KEY (id_calendario)
);

CREATE TABLE Programacao (
	id_programacao INTEGER NOT NULL,
	num_linha VARCHAR NOT NULL,
	num_itinerario VARCHAR NOT NULL,
	id_calendario INTEGER NOT NULL,
	horario_partida TIME NOT NULL,

	CONSTRAINT pk_programacao PRIMARY KEY (id_programacao),

	CONSTRAINT fk_programacao_linha FOREIGN KEY (num_linha)
		REFERENCES Linha (numero),

	CONSTRAINT fk_programacao_itinerario FOREIGN KEY (num_itinerario)
		REFERENCES Itinerario (numero),

	CONSTRAINT fk_programacao_calendario FOREIGN KEY (id_calendario)
		REFERENCES Calendario (id_calendario)
);

CREATE TABLE Calendario_Excecao (
	id_excecao INTEGER NOT NULL,
	id_calendario INTEGER NOT NULL,
	data_excecao DATE NOT NULL,
	tipo BOOLEAN NOT NULL,
	descricao VARCHAR NOT NULL,

	CONSTRAINT pk_calendario_excecao PRIMARY KEY (id_excecao),

	CONSTRAINT fk_calendario_excecao_calendario FOREIGN KEY (id_calendario)
		REFERENCES Calendario (id_calendario)
);

CREATE TABLE Itinerario_Ponto (
	id_itinerario_ponto INTEGER NOT NULL,
	num_itinerario VARCHAR NOT NULL,
	id_ponto INTEGER NOT NULL,
	sequencia INTEGER NOT NULL,

	CONSTRAINT pk_itinerario_ponto PRIMARY KEY (id_itinerario_ponto),

	CONSTRAINT fk_itinerario_ponto_itinerario FOREIGN KEY (num_itinerario)
		REFERENCES Itinerario (numero),

	CONSTRAINT fk_itinerario_ponto_ponto FOREIGN KEY (id_ponto)
		REFERENCES Ponto (id_ponto)
);

CREATE TABLE Viagem (
	id_viagem INTEGER NOT NULL,
	num_cartao VARCHAR,
	num_linha VARCHAR NOT NULL,
	registro_motorista VARCHAR NOT NULL,
	registro_cobrador VARCHAR NOT NULL,
	placa VARCHAR(7) NOT NULL,
	embarque_id_ponto INTEGER NOT NULL,
	desembarque_id_ponto INTEGER NOT NULL,
	data_entrada DATE NOT NULL,
	data_saida DATE NOT NULL,
	horario_entrada TIME NOT NULL,
	horario_saida TIME NOT NULL,
	tarifa DECIMAL(10,2) NOT NULL,

	CONSTRAINT pk_viagem PRIMARY KEY (id_viagem),

	CONSTRAINT fk_viagem_passageiro FOREIGN KEY (num_cartao)
		REFERENCES Passageiro (num_cartao),

	CONSTRAINT fk_viagem_linha FOREIGN KEY (num_linha)
		REFERENCES Linha (numero),

	CONSTRAINT fk_viagem_motorista FOREIGN KEY (registro_motorista)
		REFERENCES Motorista (registro),

	CONSTRAINT fk_viagem_cobrador FOREIGN KEY (registro_cobrador)
		REFERENCES Cobrador (registro),

	CONSTRAINT fk_viagem_veiculo FOREIGN KEY (placa)
		REFERENCES Veiculo (placa),

	CONSTRAINT fk_viagem_ponto_embarque_id_ponto FOREIGN KEY (embarque_id_ponto)
		REFERENCES Ponto (id_ponto),

	CONSTRAINT fk_viagem_ponto_desembarque_id_ponto FOREIGN KEY (desembarque_id_ponto)
		REFERENCES Ponto (id_ponto)	
);