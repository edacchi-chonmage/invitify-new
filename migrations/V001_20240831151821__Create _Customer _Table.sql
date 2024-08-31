CREATE TABLE customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL,
    created_trace VARCHAR(255) NOT NULL,
    updated_at DATETIME NOT NULL,
    updated_trace VARCHAR(255) NOT NULL
)