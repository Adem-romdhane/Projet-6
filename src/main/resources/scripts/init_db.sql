-- Création de la base de données
DROP DATABASE IF EXISTS paymybuddy;
CREATE DATABASE paymybuddy;

-- Utilisation de la base de données
USE paymybuddy;

-- Création de la table User
CREATE TABLE Client (
    id INT PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    mail VARCHAR(100),
    password VARCHAR(100)
);

-- Création de la table Account
CREATE TABLE Account (
    id INT PRIMARY KEY,
    numberAccount INT,
    balance INT,
    user_id INT,
    FOREIGN KEY (client_id) REFERENCES User(id)
);

-- Création de la table Transaction
CREATE TABLE Transaction (
    id INT PRIMARY KEY,
    operationDate DATE,
    operationDescription VARCHAR(255),
    account_id INT,
    FOREIGN KEY (account_id) REFERENCES Account(id)
);

-- Requête pour verser de l'argent dans le compte
INSERT INTO Transaction (operationDate, operationDescription, account_id)
VALUES (NOW(), 'Dépôt initial', 1); -- Remplacez 1 par l'ID du compte concerné

UPDATE Account
SET balance = balance + Montant, -- Remplacez Montant par la somme à verser
WHERE id = 1; -- Remplacez 1 par l'ID du compte concerné
