ALTER TABLE IF EXISTS ONLY public.product
    DROP CONSTRAINT IF EXISTS fk_product_category CASCADE,
    DROP CONSTRAINT IF EXISTS fk_product_category CASCADE;

DROP TABLE IF EXISTS public.product;
DROP TABLE IF EXISTS public.category;
DROP TABLE IF EXISTS public.supplier;
DROP TABLE IF EXISTS public.customer;



CREATE TABLE public.product
(
    id               int GENERATED ALWAYS AS IDENTITY,
    name             text,
    default_price    float,
    default_currency text,
    description      text,
    category         int,
    supplier         int,
    PRIMARY KEY (id)
);

CREATE TABLE public.category
(
    id          int GENERATED ALWAYS AS IDENTITY,
    name        text,
    department  text,
    description text,
    PRIMARY KEY (id)
);

CREATE TABLE public.supplier
(
    id          int GENERATED ALWAYS AS IDENTITY,
    name        text,
    description text,
    PRIMARY KEY (id)
);

CREATE TABLE public.customer
(
    id       int GENERATED ALWAYS AS IDENTITY,
    username text,
    password VARCHAR,
    email    VARCHAR(60),
    first_name text,
    last_name text,
    phone_number text,
    billing_address text,
    PRIMARY KEY (id)
);



ALTER TABLE public.product
    ADD CONSTRAINT fk_product_category FOREIGN KEY (category) REFERENCES category (id),
    ADD CONSTRAINT fk_product_supplier FOREIGN KEY (supplier) REFERENCES supplier (id);



INSERT INTO public.supplier (name, description)
VALUES ('Amazon', 'Digital content and services');
INSERT INTO public.supplier (name, description)
VALUES ('Lenovo', 'Computers');


INSERT INTO public.category (name, department, description)
VALUES ('Tablet', 'Hardware', 'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');
INSERT INTO public.category (name, department, description)
VALUES ('Phone', 'Hardware', 'A Phone computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');


INSERT INTO public.product (name,default_price, default_currency, description, category, supplier)
VALUES ('Amazon Fire',49.9,'USD', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 1, 1);

INSERT INTO public.product (name,default_price, default_currency, description, category, supplier)
VALUES ('Lenovo IdeaPad Miix 700',479, 'USD', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 1, 2);


INSERT INTO public.product (name,default_price, default_currency, description, category, supplier)
VALUES ('Amazon Fire HD 8',89, 'USD', 'Amazon''s latest Fire HD 8 tablet is a great value for media consumption.', 1, 1);


INSERT INTO public.product (name,default_price, default_currency, description, category, supplier)
VALUES ('Amazon Ice HD 6',99, 'USD', 'Amazon''s latest Ice HD 6 phone is a great value for media consumption.', 2, 1);

INSERT INTO public.product (name,default_price, default_currency, description, category, supplier)
VALUES ('Lenovo Legion 2 Pro',878, 'USD', 'Lenovo Legion introduces the next evolution of mobile gaming.', 2, 2);

