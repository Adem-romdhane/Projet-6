-- Création de la table User
CREATE TABLE IF NOT EXISTS client (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
                                      first_name VARCHAR(50),
                                      last_name VARCHAR(50),
                                      mail VARCHAR(100),
                                      password VARCHAR(100),
                                      account_id BIGINT,
                                      client_id BIGINT
);

-- Création de la table Account
CREATE TABLE IF NOT EXISTS account (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
                                       number_account INT,
                                       balance INT
);

-- Création de la table Transaction
CREATE TABLE  IF NOT EXISTS transactions (
                                             id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
                                             created_at DATE,
                                             description VARCHAR(255),
                                             connexion VARCHAR(255),
                                             transaction_number VARCHAR(255),
                                             account_id BIGINT,
                                             amount DECIMAL
);

ALTER TABLE client
    ADD FOREIGN KEY (client_id) REFERENCES client (id)
        ON DELETE CASCADE;

ALTER TABLE client
    ADD FOREIGN KEY (account_id) REFERENCES client (id)
        ON DELETE CASCADE;

