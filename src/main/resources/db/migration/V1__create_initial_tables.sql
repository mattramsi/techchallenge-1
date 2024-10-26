-- Criação da tabela Categorias
CREATE TABLE IF NOT EXISTS categorias (
    categoria_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

-- Inserção dos itens padrão na tabela Categorias
INSERT INTO categorias (categoria_id, nome) VALUES
(1, 'Lanche'),
(2, 'Acompanhamento'),
(3, 'Bebida'),
(4, 'Sobremesa')
ON DUPLICATE KEY UPDATE nome = VALUES(nome);

-- Criação da tabela Status dos Pedidos
CREATE TABLE IF NOT EXISTS status_pedidos (
    status_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

INSERT INTO status_pedidos (status_id, nome) VALUES
(1, 'Recebido'),
(2, 'Em preparação'),
(3, 'Pronto'),
(4, 'Finalizado')
ON DUPLICATE KEY UPDATE nome = VALUES(nome);

-- Criação da tabela Clientes
CREATE TABLE IF NOT EXISTS clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(255)
);

-- Criação da tabela Produtos
CREATE TABLE IF NOT EXISTS produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    categoria_id BIGINT,
    FOREIGN KEY (categoria_id) REFERENCES categorias(categoria_id)
);

-- Criação da tabela Pedidos
CREATE TABLE IF NOT EXISTS pedidos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT DEFAULT NULL,  -- Cliente ID é opcional
    total DECIMAL(10, 2) NOT NULL,
    status_id BIGINT DEFAULT 1,  -- Status padrão é 1 (Recebido)
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (status_id) REFERENCES status_pedidos(status_id)
);

-- Tabela de relacionamento entre Pedidos e Produtos (para a relação muitos-para-muitos)
CREATE TABLE pedidos_produtos (
    pedido_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    PRIMARY KEY (pedido_id, produto_id),
    FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);
