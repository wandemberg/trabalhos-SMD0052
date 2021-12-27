CREATE DATABASE db_ecommerce;

CREATE TABLE USUARIO 
( 
 id SERIAL PRIMARY KEY,  
 administrador BOOLEAN NOT NULL,  
 endereco VARCHAR(200) NOT NULL,  
 nome VARCHAR(100) NOT NULL,  
 email VARCHAR(50) NOT NULL,  
 login VARCHAR(50) NOT NULL,  
 senha VARCHAR(20) NOT NULL,
 CONSTRAINT usuario_login_ukey UNIQUE (login)
); 

CREATE TABLE VENDA 
( 
 id Integer PRIMARY KEY,  
 data_hora TIMESTAMP NOT NULL,  
 id_usuario INT NOT NULL  
); 

CREATE TABLE CATEGORIA 
( 
 descricao VARCHAR(100) NOT NULL,  
 id Integer PRIMARY KEY  
); 

CREATE TABLE PRODUTO 
( 
 id Integer PRIMARY KEY,  
 quantidade Integer NOT NULL,  
 foto VARCHAR(200),  
 preco FLOAT NOT NULL,  
 descricao VARCHAR(100) NOT NULL  
); 

CREATE TABLE VENDA_PRODUTO 
( 
 quantidade Integer NOT NULL,  
 id_venda Integer,  
 id_produto Integer,  
 PRIMARY KEY(id_venda, id_produto)
); 

CREATE TABLE PRODUTO_CATEGORIA 
( 
 id_produto Integer,  
 id_categoria Integer,  
  PRIMARY KEY(id_produto, id_categoria)
); 

ALTER TABLE VENDA ADD FOREIGN KEY(id_usuario) REFERENCES USUARIO (id);
ALTER TABLE VENDA_PRODUTO ADD FOREIGN KEY(id_venda) REFERENCES PRODUTO (id);
ALTER TABLE VENDA_PRODUTO ADD FOREIGN KEY(id_produto) REFERENCES VENDA (id);
ALTER TABLE PRODUTO_CATEGORIA ADD FOREIGN KEY(id_produto) REFERENCES PRODUTO (id);
ALTER TABLE PRODUTO_CATEGORIA ADD FOREIGN KEY(id_categoria) REFERENCES CATEGORIA (id);

insert into USUARIO values (1, true, 'Rua X, 111', 'wandemberg gomes', 
'wandemberg.rodrigues@gmail.com', 'wandemberg', 'qwer1234');

 insert into produto(id, quantidade, preco, descricao) 
 values (1, 5, 1000, 'play 1');
 insert into produto(id, quantidade, preco, descricao) 
 values (2, 10, 2000, 'play 2');
 insert into produto(id, quantidade, preco, descricao) 
 values (3, 34, 3000, 'play 3');
 insert into produto(id, quantidade, preco, descricao) 
 values (4, 51, 4000, 'play 4');
 insert into produto(id, quantidade, preco, descricao) 
 values (5, 87, 5000, 'play 5');
