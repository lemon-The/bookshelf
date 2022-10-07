DROP ALL OBJECTS;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS books_genres;
DROP TABLE IF EXISTS books_authors;
CREATE SEQUENCE IF NOT EXISTS genre_sequence
    START WITH 1
    INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS author_sequence
    START WITH 1
    INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS book_sequence
    START WITH 1
    INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS photo_sequence
    START WITH 1
    INCREMENT 1;

CREATE TABLE photos(
    id INTEGER PRIMARY KEY,
    photo_data BLOB--,
    --photo_path VARCHAR(300)
);

CREATE TABLE books(
    id INTEGER PRIMARY KEY,
    title VARCHAR(60),
    annotation VARCHAR(1000),
    photo_id INTEGER,
    CONSTRAINT fk_books_photo FOREIGN KEY(photo_id)
        REFERENCES photos(id)
);

CREATE TABLE genres(
    id INTEGER PRIMARY KEY,
    name VARCHAR(60),
    parent_id INTEGER,
    CONSTRAINT fk_genres_parent FOREIGN KEY(parent_id)
        REFERENCES genres(id) ON DELETE CASCADE
);

CREATE TABLE authors(
    id INTEGER PRIMARY KEY,
    name VARCHAR(60),
    birth_day DATE
);

CREATE TABLE books_genres(
    book_id INTEGER,
    genre_id INTEGER,
    PRIMARY KEY(book_id, genre_id),
    CONSTRAINT fk_books_genres_book_id FOREIGN KEY(book_id)
        REFERENCES books(id) ON DELETE CASCADE,
    CONSTRAINT fk_books_genres_genre_id FOREIGN KEY(genre_id)
        REFERENCES genres(id) ON DELETE CASCADE
);

CREATE TABLE books_authors(
    book_id INTEGER,
    author_id INTEGER,
    PRIMARY KEY(book_id, author_id),
    CONSTRAINT fk_books_authors_book_id FOREIGN KEY(book_id)
        REFERENCES books(id) ON DELETE CASCADE,
    CONSTRAINT fk_books_authors_author_id FOREIGN KEY(author_id)
        REFERENCES authors(id) ON DELETE CASCADE
);
