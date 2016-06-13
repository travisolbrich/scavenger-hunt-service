CREATE TABLE team
(
  team_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name    VARCHAR(100)                   NOT NULL,
  color   VARCHAR(100)                   NOT NULL
);

CREATE TABLE user
(
  user_id     INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  team_id     VARCHAR(100)                   NOT NULL REFERENCES team (team_id),
  auth0_id    VARCHAR(100)                   NOT NULL UNIQUE,
  given_name  VARCHAR(100)                   NOT NULL,
  family_name VARCHAR(100)                   NOT NULL
);

CREATE TABLE code_tag
(
  code_tag_id           INT PRIMARY KEY NOT NULL,
  checkpoint_id         INT             NOT NULL REFERENCES checkpoint (checkpoint_id),
  placement_description VARCHAR(200)    NOT NULL,
  lat                   DECIMAL         NOT NULL,
  lng                   DECIMAL         NOT NULL,
  unlock_code           VARCHAR(200)    NOT NULL
);

CREATE TABLE task
(
  task_id       INT PRIMARY KEY NOT NULL,
  checkpoint_id INT             NOT NULL REFERENCES checkpoint (checkpoint_id),
  question      VARCHAR(5000)   NOT NULL,
  answer        VARCHAR(100)    NOT NULL
);

CREATE TABLE clue
(
  clue_id            INT PRIMARY KEY  NOT NULL,
  checkpoint_id      INT              NOT NULL REFERENCES checkpoint (checkpoint_id),
  target_code_tag_id INT              NULL     REFERENCES code_tag (code_tag_id),
  clue               VARCHAR(5000)    NOT NULL
);

CREATE TABLE unlocked_task
(
  unlocked_task_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  checkpoint_id    INT                            NOT NULL REFERENCES checkpoint (checkpoint_id),
  task_id          INT                            NOT NULL REFERENCES task (task_id),
  team_id          INT                            NOT NULL REFERENCES team (team_id),
  user_id          INT                            NOT NULL REFERENCES user (user_id),
  unlocked_at      DATETIME                       NOT NULL
);

CREATE TABLE unlocked_clue
(
  unlocked_clue_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  checkpoint_id    INT                            NOT NULL REFERENCES checkpoint (checkpoint_id),
  clue_id          INT                            NOT NULL REFERENCES clue (clue_id),
  team_id          INT                            NOT NULL REFERENCES team (team_id),
  user_id          INT                            NOT NULL REFERENCES user (user_id),
  unlocked_at      DATETIME                       NOT NULL
);

CREATE TABLE checkpoint
(
  checkpoint_id INT PRIMARY KEY NOT NULL,
  description   VARCHAR(100)    NOT NULL
)