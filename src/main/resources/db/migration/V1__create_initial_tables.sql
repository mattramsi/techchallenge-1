-- Criação da tabela Categoria
CREATE TABLE IF NOT EXISTS categoria (
    categoria_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

-- Inserção dos itens padrão na tabela Categoria
INSERT INTO categoria (categoria_id, nome) VALUES
(1, 'Lanche'),
(2, 'Acompanhamento'),
(3, 'Bebida'),
(4, 'Sobremesa')
ON DUPLICATE KEY UPDATE nome = VALUES(nome);

-- Criação da tabela Status do Pedido
CREATE TABLE IF NOT EXISTS status_pedido (
    status_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

INSERT INTO status_pedido (status_id, nome) VALUES
(1, 'Recebido'),
(2, 'Em preparação'),
(3, 'Pronto'),
(4, 'Finalizado')
ON DUPLICATE KEY UPDATE nome = VALUES(nome);

-- Criação da tabela Cliente
CREATE TABLE IF NOT EXISTS cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(255)
);

-- Criação da tabela Produto
CREATE TABLE IF NOT EXISTS produto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    categoria_id BIGINT,
    FOREIGN KEY (categoria_id) REFERENCES categoria(categoria_id)
);

-- Criação da tabela Pedido
CREATE TABLE IF NOT EXISTS pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT DEFAULT NULL,  -- Cliente ID é opcional
    total DECIMAL(10, 2) NOT NULL,
    status_id BIGINT DEFAULT 1,  -- Status padrão é 1 (Recebido)
    FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    FOREIGN KEY (status_id) REFERENCES status_pedido(status_id)
);

-- Tabela de relacionamento entre Pedido e Produto (para a relação muitos-para-muitos)
CREATE TABLE pedido_produto (
    pedido_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    PRIMARY KEY (pedido_id, produto_id),
    FOREIGN KEY (pedido_id) REFERENCES pedido(id),
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);