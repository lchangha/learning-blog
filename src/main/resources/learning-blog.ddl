DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    username   VARCHAR(50)  NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    email      VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
DROP TABLE IF EXISTS learning_posts;
CREATE TABLE learning_posts
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT,
    title      VARCHAR(200) NOT NULL,
    content    TEXT         NOT NULL,
    category   VARCHAR(50)  NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
DROP TABLE IF EXISTS learning_post_images;
CREATE TABLE learning_post_images
(
    id      BIGSERIAL PRIMARY KEY,
    post_id BIGINT,
    path    varchar(500)
);
DROP TABLE IF EXISTS learning_tags;
CREATE TABLE learning_tags
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);
DROP TABLE IF EXISTS learning_post_tags;
CREATE TABLE learning_post_tags
(
    post_id BIGINT NOT NULL,
    tag_id  BIGINT NOT NULL,
    PRIMARY KEY (post_id, tag_id)
);
