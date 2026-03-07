CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    weight DECIMAL(5,2), -- e.g., 1.20 kg
    length_cm DECIMAL(5,2),
    width_cm  DECIMAL(5,2),
    height_cm DECIMAL(5,2),
    price DECIMAL(10,2) NOT NULL,
    supplier_id INT NOT NULL REFERENCES supplier(id),
    category_id INT NOT NULL REFERENCES category(id),
    brand_id INT NOT NULL REFERENCES brand(id)
);

CREATE TABLE variant (
    id SERIAL PRIMARY KEY,
    product_id INT NOT NULL REFERENCES product(id),
    color VARCHAR(50),
    size VARCHAR(10),
    sku VARCHAR(50) UNIQUE
);

CREATE TABLE rating (
    id SERIAL PRIMARY KEY,
    variant_id INT NOT NULL REFERENCES variant(id),
    user_id INT NOT NULL,
    rating_value INT NOT NULL,
    review_text TEXT
);

ALTER TABLE rating
ADD CONSTRAINT rating_value_check CHECK (rating_value >= 1 AND rating_value <= 5);
