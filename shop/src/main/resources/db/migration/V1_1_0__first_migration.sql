CREATE TABLE IF NOT EXISTS customer (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name varchar(30),
    last_name varchar(30),
    username varchar(30),
    password varchar(30),
    email_address varchar(50)
);

CREATE TABLE IF NOT EXISTS location (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(30),
    address_country varchar(30),
    address_city varchar(30),
    address_county varchar(30),
    address_street_address varchar(30)
);

CREATE TABLE IF NOT EXISTS customer_order (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customer int NOT NULL REFERENCES customer (id),
    shipped_from int NOT NULL REFERENCES location (id),
    created_at timestamp,
    address_country varchar(30),
    address_city varchar(30),
    address_county varchar(30),
    address_street_address varchar(30)
);

CREATE TABLE IF NOT EXISTS revenue (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    location int NOT NULL REFERENCES location (id),
    revenue_date date,
    revenue_sum decimal
);

CREATE TABLE IF NOT EXISTS product_category (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(30),
    description varchar(100)
);

CREATE TABLE IF NOT EXISTS supplier (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(30)
);

CREATE TABLE IF NOT EXISTS product (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    category int NOT NULL REFERENCES product_category (id),
    supplier int NOT NULL REFERENCES supplier (id),
    name varchar(30),
    description varchar(100),
    price decimal,
    weight double,
    image_url varchar(100)
);

CREATE TABLE IF NOT EXISTS stock (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product int NOT NULL REFERENCES product (id),
    location int NOT NULL REFERENCES location (id),
    quantity int
);

CREATE TABLE IF NOT EXISTS order_detail (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customer_order int NOT NULL REFERENCES customer_order (id),
    product int NOT NULL REFERENCES product (id),
    quantity int
);


