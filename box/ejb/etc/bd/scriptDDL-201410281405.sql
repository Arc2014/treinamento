CREATE DATABASE `box` /*!40100 DEFAULT CHARACTER SET latin1 */;

create table tb_grupo(
	id bigint auto_increment primary key,
    nome varchar(200),
    descricao varchar(1000)
);



create table tb_usuario(
	id bigint auto_increment primary key,
    login varchar(200),
    senha varchar(200),
    habilitado boolean,
    pessoa bigint
);
alter table tb_usuario add foreign key fk_pessoa(pessoa) 
	references pessoa(id) on delete no action on update no action;

	
	
create table tb_usuario_grupo(
	usuario bigint not null,
    grupo bigint not null,
    primary key(usuario, grupo)
);
alter table tb_usuario_grupo add foreign key fk_usuario(usuario) 
	references tb_usuario(id) on delete no action on update no action;
alter table tb_usuario_grupo add foreign key fk_grupo(grupo) 
	references tb_grupo(id) on delete no action on update no action;

	
	
create table tb_tipo (
	id bigint primary key auto_increment,
    nome varchar(200),
    cor varchar(50)
);



create table tb_recurso (
	id bigint primary key auto_increment,
    descricao varchar(200),
    num_patrimonio varchar(50),
    tipo bigint
);
alter table tb_recurso add foreign key fk_tipo(tipo) 
	references tb_tipo(id) on delete no action on update no action;
ALTER TABLE `box`.`tb_recurso` 
	ADD COLUMN `ativo` TINYINT(1) NULL DEFAULT NULL AFTER `tipo`;
	
	
create table tb_agenda(
	id bigint primary key auto_increment,
    titulo_evento varchar(200),
    desc_evento varchar(1000),
    data_inicio datetime,
    data_fim datetime,
    data_notificacao datetime,
    recurso bigint,
    usuario bigint,
    pessoa bigint
);
alter table tb_agenda add foreign key fk_usuario_agenda(usuario) 
	references tb_usuario(id) on delete no action;    
alter table tb_agenda add foreign key fk_pessoa_agenda(pessoa) 
	references pessoa(id) on delete no action;
alter table tb_agenda add foreign key fk_recurso_agenda(recurso) 
	references tb_recurso(id) on delete no action on update no action;  
	
create table tb_notificacao(
	id bigint primary key auto_increment,
    data_notificar date,
    data_envio date,
    remetente varchar(400),
    destinatario varchar(400),
    corpo varchar(3000),
    tipo varchar(200),
    titulo varchar(200)
);

ALTER TABLE `box`.`tb_notificacao` 
CHANGE COLUMN `data_notificar` `data_notificar` TIMESTAMP NULL DEFAULT NULL ;


ALTER TABLE tb_recurso 
ADD COLUMN descricao_imagem VARCHAR(200) ;

CREATE  TABLE `box`.`pessoa` (

  `id_pessoa` INT NOT NULL AUTO_INCREMENT ,
  
  `nome`  VARCHAR(45) NOT NULL ,

  `data_nascimento` DATE NULL ,

  `email` VARCHAR(45) NULL ,

  PRIMARY KEY (`id_pessoa`) );
  
    CREATE  TABLE `box`.`experiencia_profissional` (

  `id_experiencia_profissional` INT NOT NULL AUTO_INCREMENT ,

  `empresa` VARCHAR(45) NOT NULL ,

  `atividade` VARCHAR(45) NOT NULL ,

  `id_pessoa` INT NOT NULL ,

  PRIMARY KEY (`id_experiencia_profissional`) ,

  INDEX `id_pessoa` (`id_pessoa` ASC) ,

  CONSTRAINT `id_pessoa`

    FOREIGN KEY (`id_pessoa` )

    REFERENCES `box`.`pessoa` (`id_pessoa` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);
    
     ALTER TABLE `box`.`pessoa` CHANGE COLUMN `id_pessoa` `id` INT(11) NOT NULL  ;
     
     ALTER TABLE `box`.`pessoa` ADD COLUMN `nomeArquivo` VARCHAR(45) NULL  AFTER `NOME` ;
    
    ALTER TABLE `box`.`pessoa` DROP COLUMN `EMAIL` , ADD COLUMN `cpf` VARCHAR(45) NULL  AFTER `nomeArquivo` , ADD COLUMN `rg` VARCHAR(45) NULL  AFTER `cpf` , ADD COLUMN `sexo` CHAR NULL  AFTER `rg` , ADD COLUMN `estadoCivil` VARCHAR(45) NULL  AFTER `sexo` , ADD COLUMN `nacionalidade` VARCHAR(45) NULL  AFTER `estadoCivil` , ADD COLUMN `naturalidade` VARCHAR(45) NULL  AFTER `nacionalidade` , CHANGE COLUMN `data_nascimento` `dataNascimento` DATE NULL DEFAULT NULL  , CHANGE COLUMN `NOME` `nome` VARCHAR(255) NULL DEFAULT NULL  ;
    
    ALTER TABLE pessoa ADD CONSTRAINT chk_sexo CHECK (sexo='M'OR sexo='F');
    
    CREATE  TABLE `box`.`endereco` (

  `id` INT NOT NULL AUTO_INCREMENT ,

  `logradouro` VARCHAR(100) NOT NULL ,

  `numero` VARCHAR(45) NOT NULL ,

  `complemento` VARCHAR(45) NULL ,

  `cep` VARCHAR(45) NOT NULL ,

  `cidade` VARCHAR(45) NOT NULL ,

  `estado` VARCHAR(45) NOT NULL ,

  `pais` VARCHAR(45) NOT NULL ,

  `enderecocol` VARCHAR(45) NOT NULL ,
  
  `id_pessoa` INT NOT NULL,

  PRIMARY KEY (`id`) );
  
  CREATE  TABLE `box`.`cidade` (

  `id` INT NOT NULL AUTO_INCREMENT ,

  `nome` VARCHAR(45) NOT NULL ,

  `uf` VARCHAR(2) NOT NULL ,

  PRIMARY KEY (`id`) );


     CREATE TABLE `box`.`email`(
 		`id` int NOT NULL,
    		`email` VARCHAR(45),
        		`id_pessoa` int NOT NULL,
					PRIMARY KEY (`id`),
						FOREIGN KEY (`id_pessoa`) REFERENCES `box`.`pessoa` (`id` ));
     
      ALTER TABLE `box`.`experiencia_profissional` ADD COLUMN `cargo` VARCHAR(45) NULL  AFTER `nomeEmpresa` , ADD COLUMN `localidade` VARCHAR(45) NULL  AFTER `descricao` , ADD COLUMN `dataInicio` DATE NOT NULL  AFTER `localidade` , ADD COLUMN `dataFim` DATE NOT NULL  AFTER `dataInicio` , CHANGE COLUMN `empresa` `nomeEmpresa` VARCHAR(45) NOT NULL  , CHANGE COLUMN `atividade` `descricao` VARCHAR(45) NOT NULL  ;

ALTER TABLE `box`.`endereco` 

  ADD CONSTRAINT `fk_pessoa`

  FOREIGN KEY (`id_pessoa` )

  REFERENCES `box`.`tb_pessoa` (`id` )

  ON DELETE NO ACTION

  ON UPDATE NO ACTION

, ADD INDEX `fk_pessoa` (`id_pessoa` ASC) ;