package com.soramitsukhmer.contactmanagement.domain.model

import com.soramitsukhmer.contactmanagement.api.request.RequestTaskDTO
import com.soramitsukhmer.contactmanagement.api.request.TaskDTO
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "Tasks")
data class Task(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Long = 0,
    @Column(name = "name")
    var name: String,
    @Column(name = "status")
    var status: String,
    @CreationTimestamp
    @Column(name = "created_at")
    var created_at : LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    @Column(name = "updated_at")
    var updated_at : LocalDateTime = LocalDateTime.now()
) {
    @ManyToOne
    @JoinColumn(name = "staff_id")
    lateinit var staff: Staff

    fun toDTO() = TaskDTO(
            id = id,
            name = name,
            status = status,
            staff = staff.toDTO(),
            created_at = created_at,
            updated_at = updated_at
    )

    fun updateTask(reqTaskDto:RequestTaskDTO, staff:Staff):Task
    {
        return this.apply {
            name  = reqTaskDto.name
            status = reqTaskDto.status
            this.staff = staff
        }
    }

    companion object{
        fun fromReqDTO(reqTaskDto:RequestTaskDTO, staff:Staff):Task
        {
            return Task(
                    name = reqTaskDto.name,
                    status = reqTaskDto.status
            ).apply {
                this.staff = staff
            }
        }
    }
}