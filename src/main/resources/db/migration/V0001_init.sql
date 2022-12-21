create table customer
(
    customer_id uuid not null constraint customer_pk primary key,
    customer_email_id text not null,
    customer_password text not null,
    customer_name text not null,
    customer_role text not null,
    customer_added_date text not null
);