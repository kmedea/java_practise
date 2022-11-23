CREATE TABLE IF NOT EXISTS games
(
    id           int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name         varchar(250),
    release_year int,
    category_id  int,
    finished     boolean,

    CONSTRAINT fk_categories_games FOREIGN KEY (category_id) REFERENCES categories (id)
);