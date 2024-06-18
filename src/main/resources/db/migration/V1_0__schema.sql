DROP TABLE IF EXISTS message_user CASCADE;
DROP TABLE IF EXISTS message CASCADE;

CREATE TABLE message_user (
   id INT AUTO_INCREMENT PRIMARY KEY,
   first_name VARCHAR(255),
   last_name VARCHAR(255),
   email VARCHAR(255),
   password VARCHAR(255)
);

CREATE TABLE message (
    id INT AUTO_INCREMENT PRIMARY KEY,
    summary VARCHAR(255),
    text VARCHAR(255),
    from_id INT references message_user(id),
    to_id INT references message_user(id)
);