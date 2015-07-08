  
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

    
    CREATE TABLE `box`.`telefone`(
 		`id` int NOT NULL,
    		`telefone` VARCHAR(45),
    		`tipo` VARCHAR(45),
        		`id_pessoa` int NOT NULL,
					PRIMARY KEY (`id`),
						FOREIGN KEY (`id_pessoa`) REFERENCES `box`.`pessoa` (`id` ));
						
   
	CREATE TABLE `box`.`formacao_academica`(
 		`id` int NOT NULL,
                `insituicao` VARCHAR(60),
                    `curso` VARCHAR(25),
                        `dt_inicio` DATE null,
                            `dt_fim` DATE null,
                                `situacao` VARCHAR(25),
                                    `tipo` VARCHAR(25),
                                        `id_pessoa` int NOT NULL,
                                            PRIMARY KEY (`id`),
                                                FOREIGN KEY (`id_pessoa`) REFERENCES `box`.`pessoa` (`id` ));

	

    

