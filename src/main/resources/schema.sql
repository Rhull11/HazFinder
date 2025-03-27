CREATE TABLE IF NOT EXISTS Item (
    id INT NOT NULL,
    asin varchar(250) NOT NULL,
    name varchar(250) NOT NULL,
    ingredients varchar(250) NOT NULL,
    version INT,
    PRIMARY KEY (id)
);