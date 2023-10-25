create table product
(
  id    bigint primary key, --create nextval('product_id_seq'::regclass)
  name  varchar not null,
  price numeric not null
);
