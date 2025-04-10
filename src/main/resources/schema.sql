CREATE TABLE IF NOT EXISTS Item (
    id INT GENERATED ALWAYS AS IDENTITY,
    asin varchar(250) NOT NULL,
    name varchar(250) NOT NULL,
    ingredients varchar(250) NOT NULL,
    PRIMARY KEY (id)
);