package TNT.SE_2024_02_TNT.repository

import TNT.SE_2024_02_TNT.entity.Container
import org.springframework.data.jpa.repository.JpaRepository

interface ContainerRepository: JpaRepository<Container, String> {
    fun findByContainerId(containerId: String): Container?
}