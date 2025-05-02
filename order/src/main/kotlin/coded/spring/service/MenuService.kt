package coded.spring.service
import coded.spring.controller.MenuRequest
import coded.spring.entity.MenuEntity
import coded.spring.repository.MenuRepository
import org.springframework.stereotype.Service
import kotlin.text.set

@Service
class MenuService(
    val menuRepository: MenuRepository,
) {

    fun addItems(menu: MenuRequest) {

        menuRepository.save(
            MenuEntity(
                name = menu.name,
                price = menu.price
            )
        )
    }

    fun listMenuItems(): List<MenuEntity> = menuRepository.findAll()



}


