CREATE TABLE IF NOT EXISTS address (
    id BINARY(16) PRIMARY KEY, 
    street VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(50) NOT NULL
);