ALTER TABLE `scavenger_hunt`.`unlocked_task`
  ADD UNIQUE INDEX `unique_index` (`task_id` ASC, `checkpoint_id` ASC, `team_id` ASC);
