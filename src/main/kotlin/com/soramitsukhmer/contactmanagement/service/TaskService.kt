package com.soramitsukhmer.contactmanagement.service

import com.soramitsukhmer.contactmanagement.api.request.RequestTaskDTO
import com.soramitsukhmer.contactmanagement.api.request.TaskDTO
import com.soramitsukhmer.contactmanagement.domain.model.Task
import com.soramitsukhmer.contactmanagement.repository.StaffRepository
import com.soramitsukhmer.contactmanagement.repository.TaskRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class TaskService(val taskRepository: TaskRepository, val staffRepository: StaffRepository) {

    fun listAllTasks(): List<TaskDTO>{
        return taskRepository.findAll().map { it.toDTO() }
    }

    fun getTasks(id:Long):TaskDTO{
        return taskRepository.findById(id).orElseThrow(){
            throw RuntimeException("Task [$id] is not found.")
        }.toDTO()
    }

    fun createTask(requestTaskDTO: RequestTaskDTO):TaskDTO{
        val staff = staffRepository.findById(requestTaskDTO.staff).orElseThrow(){
            throw RuntimeException("Staff [${requestTaskDTO.staff}] is not found.")
        }

        val newTask = Task.fromReqDTO(requestTaskDTO, staff)
        return taskRepository.save(newTask).toDTO()
    }

    fun updateTask(requestTaskDTO: RequestTaskDTO, id:Long):TaskDTO{
        val staff = staffRepository.findById(requestTaskDTO.staff).orElseThrow(){
            throw RuntimeException("Staff [${requestTaskDTO.staff}] is not found.")
        }

        val task = taskRepository.findById(id).orElseThrow(){
            throw RuntimeException("Task [${id}] is not found.")
        }.updateTask(requestTaskDTO,staff)

        return taskRepository.save(task).toDTO()
    }

    fun deleteTask(id:Long)
    {
        val task = taskRepository.findById(id).orElseThrow(){
            throw RuntimeException("Task [${id}] is not found.")
        }

        return taskRepository.delete(task)
    }

}