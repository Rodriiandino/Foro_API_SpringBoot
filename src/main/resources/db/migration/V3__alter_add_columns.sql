ALTER TABLE topics
    ADD COLUMN status VARCHAR(20) NOT NULL;

ALTER TABLE topics
    ADD COLUMN created_at TIMESTAMP NOT NULL;

ALTER TABLE topics
    ADD COLUMN updated_at TIMESTAMP NOT NULL;

ALTER TABLE topics
    ADD COLUMN deleted_at TIMESTAMP;

ALTER TABLE users
    ADD COLUMN created_at TIMESTAMP NOT NULL;

ALTER TABLE users
    ADD COLUMN deleted_at TIMESTAMP;

ALTER TABLE posts
    ADD COLUMN created_at TIMESTAMP NOT NULL;

ALTER TABLE comments
    ADD COLUMN created_at TIMESTAMP NOT NULL;

