create table users (
    id                      bigint NOT NULL AUTO_INCREMENT,PRIMARY KEY (id),
    username                varchar(30) not null unique,
    password                varchar(80) not null,
    email                   varchar(50) unique,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

create table roles (
    id                      bigint NOT NULL AUTO_INCREMENT,PRIMARY KEY (id),
    name                    varchar(50) not null unique,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

CREATE TABLE users_roles (
    user_id                 bigint not null references users (id),
    role_id                 bigint not null references roles (id),
    primary key (user_id, role_id)
);

create table products (
    id                      bigint NOT NULL AUTO_INCREMENT,PRIMARY KEY (id),
    title                   varchar(255),
    price                   int,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

create table categories (
    id                      bigint NOT NULL AUTO_INCREMENT,PRIMARY KEY (id),
    title                   varchar(255),
    description             varchar(5000)
);

create table products_categories (
    product_id              bigint,
    category_id             bigint,
    foreign key (product_id) references products (id),
    foreign key (category_id) references categories (id)
);

create table orders (
    id                      bigint NOT NULL AUTO_INCREMENT,PRIMARY KEY (id),
    owner_id                bigint references users (id),
    price                   int,
    address                 varchar(255),
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

create table order_items (
    id                      bigint NOT NULL AUTO_INCREMENT,PRIMARY KEY (id),
    order_id                bigint references orders (id),
    product_id              bigint references products (id),
    title                   varchar(255),
    quantity                int,
    price_per_product       int,
    price                   int,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

insert into roles (name)
values
('ROLE_USER'),
('ROLE_ADMIN');

insert into users (username, password, email)
values
('bob1', '$2y$12$yr/ojWL6JQCpku.5vlcGuuIAkROTMOkIupVUzzRMqQvBqpUgn4xA2', 'bob1@gmail.com'),
('bob2', '$2y$12$yr/ojWL6JQCpku.5vlcGuuIAkROTMOkIupVUzzRMqQvBqpUgn4xA2', 'bob2@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);

insert into products (title, price)
values
('Banana', 50),
('Bread', 40),
('Meat', 500);


insert into categories
(title, description) values
('food', 'some food products'),
('fruits', 'some fruit'),
('vegetables', 'some vegetables'),
('electronics', 'some electronics'),
('non-food', 'all non-food products');
