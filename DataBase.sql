CREATE DATABASE IF NOT EXISTS 3chan;

CREATE DATABASE IF NOT EXISTS 3chan_test;

USE 3chan;

-- Table for authors
CREATE TABLE IF NOT EXISTS author (
    id bigint(20) AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    birth_day DATE NOT NULL,
    role ENUM('CONTRIBUTOR', 'EDITOR') NOT NULL
);

-- Table for articles
CREATE TABLE IF NOT EXISTS article (
    id bigint(20) AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    creation_date DATETIME NOT NULL,
    publication_date DATETIME,
    article_statut ENUM('DRAFT', 'PUBLISHED') NOT NULL DEFAULT 'DRAFT',
    author_id bigint(20),
    CONSTRAINT fk_article_author FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE SET NULL
);

-- Table for comments
CREATE TABLE IF NOT EXISTS comment (
    id bigint(20) AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    creation_date DATETIME NOT NULL,
    comment_status ENUM('APPROVED', 'REJECTED'),
    author_id bigint(20),
    article_id bigint(20),
    CONSTRAINT fk_comment_author FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE SET NULL,
    CONSTRAINT fk_comment_article FOREIGN KEY (article_id) REFERENCES article(id) ON DELETE CASCADE
);

CREATE USER 'dbadmin'@'localhost' IDENTIFIED BY 'azerty';

GRANT ALL PRIVILEGES ON 3chan.* TO 'dbadmin'@'localhost';
