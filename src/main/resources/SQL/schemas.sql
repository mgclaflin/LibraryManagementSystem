CREATE TABLE users (
    id SERIAL PRIMARY KEY, -- Auto-incrementing primary key
    username VARCHAR(50) UNIQUE NOT NULL, -- Unique username
    password VARCHAR(60) NOT NULL, -- Bcrypt-encoded password (60 characters)
    role VARCHAR(50) NOT NULL -- Role (e.g., ADMIN, MEMBER, LIBRARIAN)
);


CREATE TABLE books (
    bookId BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    isbn VARCHAR(30) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    description TEXT,
    pageCount INT
);
