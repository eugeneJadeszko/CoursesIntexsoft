CREATE TABLE board(
    id bigserial,
    board_number INT,
    CONSTRAINT board_pk PRIMARY KEY(id)
);

CREATE TABLE books(
	id bigserial,
    title VARCHAR,
    author varchar,
    board_id bigint REFERENCES board(id),
    CONSTRAINT book_pk PRIMARY KEY(id)
);

CREATE TABLE users(
	id bigserial,
    first_name VARCHAR,
    CONSTRAINT user_pk PRIMARY KEY(id)
);

CREATE TABLE book_user(
	id bigserial,
    book_id BIGINT REFERENCES books(id),
    user_id bigint REFERENCES users(id),
    CONSTRAINT book_user_pk PRIMARY KEY(id)
);
