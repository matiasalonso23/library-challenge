-- Customers
INSERT INTO customer (name) VALUES ('Bob');
INSERT INTO customer (name) VALUES ('Alice');
INSERT INTO customer (name) VALUES ('Tom');


-- Authors
INSERT INTO author (name) VALUES ('Jane Austen');
INSERT INTO author (name) VALUES ('Nicholas Sparks');
INSERT INTO author (name) VALUES ('Nora Roberts');
INSERT INTO author (name) VALUES ('Stephen King');
INSERT INTO author (name) VALUES ('H.P. Lovecraft');
INSERT INTO author (name) VALUES ('Anne Rice');
INSERT INTO author (name) VALUES ('Isaac Asimov');
INSERT INTO author (name) VALUES ('Philip K. Dick');
INSERT INTO author (name) VALUES ('Arthur C. Clarke');
INSERT INTO author (name) VALUES ('Agatha Christie');
INSERT INTO author (name) VALUES ('Raymond Chandler');
INSERT INTO author (name) VALUES ('Gillian Flynn');
INSERT INTO author (name) VALUES ('J.R.R. Tolkien');
INSERT INTO author (name) VALUES ('George R.R. Martin');
INSERT INTO author (name) VALUES ('Patrick Rothfuss');

-- Categories
INSERT INTO category (name) VALUES ('Romance');
INSERT INTO category (name) VALUES ('Horror');
INSERT INTO category (name) VALUES ('Science Fiction');
INSERT INTO category (name) VALUES ('Mystery');
INSERT INTO category (name) VALUES ('Fantasy');

-- Books
INSERT INTO book (title, author_id, category_id) VALUES ('Pride and Prejudice', 1, 1);
INSERT INTO book (title, author_id, category_id) VALUES ('The Notebook', 2, 1);
INSERT INTO book (title, author_id, category_id) VALUES ('Nights in Rodanthe', 3, 1);
INSERT INTO book (title, author_id, category_id) VALUES ('The Shining', 4, 2);
INSERT INTO book (title, author_id, category_id) VALUES ('At the Mountains of Madness', 5, 2);
INSERT INTO book (title, author_id, category_id) VALUES ('Interview with the Vampire', 6, 2);
INSERT INTO book (title, author_id, category_id) VALUES ('Foundation', 7, 3);
INSERT INTO book (title, author_id, category_id) VALUES ('Do Androids Dream of Electric Sheep?', 8, 3);
INSERT INTO book (title, author_id, category_id) VALUES ('2001: A Space Odyssey', 9, 3);
INSERT INTO book (title, author_id, category_id) VALUES ('Murder on the Orient Express', 10, 4);
INSERT INTO book (title, author_id, category_id) VALUES ('The Big Sleep', 11, 4);
INSERT INTO book (title, author_id, category_id) VALUES ('Gone Girl', 12, 4);
INSERT INTO book (title, author_id, category_id) VALUES ('The Lord of the Rings', 13, 5);
INSERT INTO book (title, author_id, category_id) VALUES ('A Game of Thrones', 14, 5);
INSERT INTO book (title, author_id, category_id) VALUES ('The Name of the Wind', 15, 5);
INSERT INTO book (title, author_id, category_id) VALUES ('Carrie', 4, 2);
INSERT INTO book (title, author_id, category_id) VALUES ('The Stand', 4, 2);
INSERT INTO book (title, author_id, category_id) VALUES ('It', 4, 2);
INSERT INTO book (title, author_id, category_id) VALUES ('Foundation and Empire', 7, 3);
INSERT INTO book (title, author_id, category_id) VALUES ('Second Foundation', 7, 3);
INSERT INTO book (title, author_id, category_id) VALUES ('The Robots of Dawn', 7, 3);
INSERT INTO book (title, author_id, category_id) VALUES ('The Caves of Steel', 7, 3);
INSERT INTO book (title, author_id, category_id) VALUES ('The Green Mile', 4, 2);
INSERT INTO book (title, author_id, category_id) VALUES ('The Dark Tower: The Gunslinger', 4, 2);