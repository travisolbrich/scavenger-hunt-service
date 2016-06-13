ALTER TABLE `scavenger_hunt`.`unlocked_clue`
  ADD UNIQUE INDEX `unique_index` (`clue_id` ASC, `checkpoint_id` ASC, `team_id` ASC);
