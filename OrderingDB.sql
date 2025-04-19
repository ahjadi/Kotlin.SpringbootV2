CREATE TABLE users (
                       id serial PRIMARY KEY,
                       name VARCHAR(255),
                       email VARCHAR(255),
                       username VARCHAR(255) unique,
                       password VARCHAR(255)
);

CREATE TABLE orders (
                        id serial PRIMARY KEY,
                        user_id BIGINT,
                        restaurant VARCHAR(255),
                        FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE items (
                       id serial PRIMARY KEY,
                       name VARCHAR(255),
                       quantity INT,
                       price DECIMAL(9, 3),
                       order_id BIGINT,
                       FOREIGN KEY (order_id) REFERENCES orders(id)
);
-- added menu too