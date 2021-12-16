drop schema if exists planit;
create schema if not exists planit collate utf8mb4_0900_ai_ci;
use planit;
CREATE TABLE IF NOT EXISTS planit.users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    CONSTRAINT email_UNIQUE UNIQUE (email),
    CONSTRAINT idUsers_UNIQUE UNIQUE (id)
);

CREATE TABLE IF NOT EXISTS planit.projects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    start DATE NOT NULL,
    finish DATE NOT NULL,
    actual_hours INT DEFAULT 0 NULL,
    actual_cost INT DEFAULT 0 NULL,
    budget INT NOT NULL,
    CONSTRAINT id_UNIQUE UNIQUE (id),
    CONSTRAINT fkuser FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);
create index fkuser_id_idx
    on projects (user_id);

CREATE TABLE IF NOT EXISTS planit.tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    project_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    start DATE NOT NULL,
    finish DATE NOT NULL,
    hours INT DEFAULT 0 NULL,
    cost INT DEFAULT 0 NOT NULL,
    CONSTRAINT id_UNIQUE UNIQUE (id),
    CONSTRAINT fkprojectId FOREIGN KEY (project_id)
        REFERENCES projects (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS planit.subtasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    task_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    estimated_hours INT NOT NULL,
    cost INT NOT NULL,
    CONSTRAINT id_UNIQUE UNIQUE (id),
    CONSTRAINT fktaskId FOREIGN KEY (task_id)
        REFERENCES tasks (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

create index fk_idx
	on subtasks (task_id);
