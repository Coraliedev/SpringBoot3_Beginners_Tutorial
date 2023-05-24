CREATE TABLE IF NOT EXISTS Content(
id INTEGER AUTO_INCREMENT,
title VARCHAR(255) NOT NULL,
description text,
status VARCHAR(28) NOT NULL,
content_type VARCHAR(50) NOT NULL,
date_created TIMESTAMP NOT NULL,
date_updated TIMESTAMP,
url VARCHAR(255),
primary key (id)
);

INSERT INTO Content(title, description, status, content_type, date_created)
VALUES('My Spring Data Blog post', 'This is a blog post about Spring data', 'IDEA', 'ARTICLE', CURRENT_TIMESTAMP);