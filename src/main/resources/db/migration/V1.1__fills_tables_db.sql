INSERT INTO board (board_number) VALUES (1);
INSERT INTO board (board_number) VALUES (2);

INSERT INTO users (first_name) VALUES ('Eugene');
INSERT INTO users (first_name) VALUES ('Inna');

INSERT INTO books (title, author, board_id) VALUES ('master & margo', 'Bulgakov', 1);
INSERT INTO books (title, author, board_id) VALUES ('Java', 'Herberdt Shildt', 1);

INSERT INTO book_user (book_id, user_id) VALUES (1, 1);
INSERT INTO book_user (book_id, user_id) VALUES (1, 2);
INSERT INTO book_user (book_id, user_id) VALUES (2, 1);
