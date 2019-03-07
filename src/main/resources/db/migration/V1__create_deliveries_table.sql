CREATE TABLE deliveries
(
  id       BIGSERIAL NOT NULL CONSTRAINT orders_pkey PRIMARY KEY,
  order_id BIGINT
)