INSERT INTO scavenger_hunt.team (team_id, name, color) VALUES
  (1, 'Team A', '#000000');
INSERT INTO scavenger_hunt.team (team_id, name, color) VALUES
  (2, 'Team B', '#ffffff');
INSERT INTO scavenger_hunt.team (team_id, name, color) VALUES
  (3, 'Unassigned', '#ffffff');

INSERT INTO scavenger_hunt.checkpoint (checkpoint_id, description) VALUES
  (1, 'First Checkpoint');

INSERT INTO scavenger_hunt.code_tag (code_tag_id, checkpoint_id, placement_description, lat, lng, unlock_code) VALUES
  (1, 1, 'somewhere', 23, 23, 'asdf');

INSERT INTO scavenger_hunt.task (task_id, checkpoint_id, question, answer) VALUES
  (1, 1, '2+2?', '4');

INSERT INTO scavenger_hunt.clue (clue_id, checkpoint_id, clue) VALUES
  (1, 1, 'Go to point A');
