package com.travisolbrich.scavenger.hunt.controller;

import com.travisolbrich.scavenger.hunt.entity.TaskEntity;
import com.travisolbrich.scavenger.hunt.entity.UnlockedClueEntity;
import com.travisolbrich.scavenger.hunt.entity.UserEntity;
import com.travisolbrich.scavenger.hunt.model.Clue;
import com.travisolbrich.scavenger.hunt.model.Task;
import com.travisolbrich.scavenger.hunt.model.TaskResponse;
import com.travisolbrich.scavenger.hunt.service.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/secured/tasks/")
public class TaskController {

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @Autowired
    ClueService clueService;

    @Autowired
    CodeTagService codeTagService;

    @Autowired
    CurrentUserInfoService currentUserInfoService;

    /**
     * Get all of the tasks that a given user has access to.
     * @param userId the user we want tasks for
     * @return tasks the user is authorized on
     * @throws IOException
     */
    @RequestMapping(value = "", params = {"userId"}, method = RequestMethod.GET)
    @PreAuthorize("@SecurityService.allowedUserAccess(#userId) or hasRole('ROLE_ADMIN')")
    public Iterable<Task> getTasksForUser(@RequestParam(value = "userId") Long userId) throws IOException {
        UserEntity userEntity = userService.getUserEntity(userId);
        List<TaskEntity> taskEntityList = taskService.getTasksForUser(userEntity);

        Type listType = new TypeToken<List<Task>>() {
        }.getType();
        return new ModelMapper().map(taskEntityList, listType);
    }

    /**
     * Get a specific task. The user must be authorized on it.
     * @param taskIdString ID of the task we want to view
     * @return The task
     * @throws IOException
     */
    @RequestMapping(value = "{taskIdString}", method = RequestMethod.GET)
    @PreAuthorize("@SecurityService.hasUnlockedTask(#taskIdString) or hasRole('ROLE_ADMIN')")
    public Task getTask(@PathVariable("taskIdString") String taskIdString) throws IOException {
        TaskEntity taskEntity = taskService.getTask(Long.valueOf(taskIdString));

        return new ModelMapper().map(taskEntity, Task.class);
    }

    @RequestMapping(value = "current", method = RequestMethod.GET)
    public Task getLatestTaskForCurrentUser() throws IOException {
        UserEntity userEntity = currentUserInfoService.getCurrentUserEntity();
        TaskEntity taskEntity = taskService.getLatestTaskForUser(userEntity);

        return new ModelMapper().map(taskEntity, Task.class);
    }

    /**
     * Answer a task. User must be authorized on the task.
     * @param taskIdString ID of the task we want to answer
     * @param taskResponse Object containing the answer to the task
     * @return Clue unlocked by the task.
     * @throws IOException
     */
    @RequestMapping(value = "{taskIdString}/answer", method = RequestMethod.POST)
    @PreAuthorize("@SecurityService.hasUnlockedTask(#taskIdString) or hasRole('ROLE_ADMIN')")
    public @ResponseBody Clue unlockClue(@PathVariable("taskIdString") String taskIdString, @RequestBody TaskResponse taskResponse) throws IOException {
        TaskEntity taskEntity = taskService.getTask(Long.valueOf(taskIdString));
        UserEntity userEntity = currentUserInfoService.getCurrentUserEntity();
        UnlockedClueEntity unlockedClueEntity = taskService.unlockClue(taskEntity, taskResponse.getResponse(), userEntity);

        return new ModelMapper().map(unlockedClueEntity.getClueEntity(), Clue.class);
    }
}
