package com.soramitsukhmer.contactmanagement.repository

import com.soramitsukhmer.contactmanagement.domain.model.Task
import org.springframework.data.repository.CrudRepository

interface TaskRepository : CrudRepository<Task, Long> {
}