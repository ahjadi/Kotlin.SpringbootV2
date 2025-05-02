package coded.spring.repository

import coded.spring.entity.MenuEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuRepository : JpaRepository<MenuEntity, Long>{

}
