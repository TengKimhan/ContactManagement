package com.soramitsukhmer.contactmanagement.api.controller

import com.soramitsukhmer.contactmanagement.api.request.RequestTaskDTO
import com.soramitsukhmer.contactmanagement.api.request.TaskDTO
import com.soramitsukhmer.contactmanagement.service.TaskService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(path = ["/api/v1/tasks"])
class TaskController(val taskService: TaskService) {

    @GetMapping
    fun getAllTasks():ResponseEntity<List<TaskDTO>>
    {
        return ResponseEntity.ok(taskService.listAllTasks())
    }

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable("id") id:Long):ResponseEntity<TaskDTO>
    {
        return ResponseEntity.ok(taskService.getTasks(id))
    }

    @PostMapping
    fun createTask(@Valid @RequestBody requestTaskDTO: RequestTaskDTO):ResponseEntity<TaskDTO>
    {
        return ResponseEntity.ok(taskService.createTask(requestTaskDTO))
    }

    @PutMapping("/{id}")
    fun updateTask(@PathVariable("id") id:Long, @Valid @RequestBody requestTaskDTO: RequestTaskDTO):ResponseEntity<TaskDTO>
    {
        return ResponseEntity.ok(taskService.updateTask(requestTaskDTO,id))
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable("id") id:Long)
    {
        taskService.deleteTask(id)
    }
}