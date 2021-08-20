package hello.springmvc.itemservice.web.basic

import hello.springmvc.itemservice.domain.item.Item
import hello.springmvc.itemservice.domain.item.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.annotation.PostConstruct

@Controller
@RequestMapping("/basic/items")
class BasicItemController @Autowired constructor (private val itemRepository: ItemRepository) {
  @GetMapping
  fun items(model: Model): String {
    val items = itemRepository.findAll()
    model.addAttribute("items", items)
    return "basic/items"
  }

  /**
   * For test
   */
  @PostConstruct
  fun init() {
    itemRepository.save(Item("itemA", 10000, 10))
    itemRepository.save(Item("itemB", 20000, 20))
  }
}