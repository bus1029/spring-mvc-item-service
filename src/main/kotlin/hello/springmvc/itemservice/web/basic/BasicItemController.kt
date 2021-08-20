package hello.springmvc.itemservice.web.basic

import hello.springmvc.itemservice.domain.item.Item
import hello.springmvc.itemservice.domain.item.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
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

  @GetMapping("/{itemId}")
  fun item(@PathVariable itemId: Long, model: Model): String {
    val item = itemRepository.findById(itemId)
    model.addAttribute("item", item)
    return "basic/item"
  }

  @GetMapping("/add")
  fun addForm(): String {
    return "basic/addForm"
  }

  //  @PostMapping("/add")
  fun addItemV1(@RequestParam itemName: String,
                @RequestParam price: Int,
                @RequestParam quantity: Int,
                model: Model): String {
    val save = itemRepository.save(Item(itemName, price, quantity))
    model.addAttribute("item", save)
    return "basic/item"
  }

//  @PostMapping("/add")
  fun addItemV2(@ModelAttribute("item") item: Item): String {
    itemRepository.save(item)
//    model.addAttribute("item", save)
    return "basic/item"
  }

//  @PostMapping("/add")
  fun addItemV3(@ModelAttribute item: Item): String {
    // Item (class name) -> item
    itemRepository.save(item)
    return "basic/item"
  }

//  @PostMapping("/add")
  fun addItemV4(@ModelAttribute item: Item): String {
    // Item (class name) -> item
    itemRepository.save(item)
    return "redirect:/basic/items/" + item.id
  }

  @PostMapping("/add")
  fun addItemV5(@ModelAttribute item: Item, redirectAttribute: RedirectAttributes): String {
    // Item (class name) -> item
    val savedItem = itemRepository.save(item)
    redirectAttribute.addAttribute("itemId", savedItem.id)
    // 나머지 attribute 들은 query parameter 형식으로 들어감
    redirectAttribute.addAttribute("status", true)
    return "redirect:/basic/items/{itemId}"
  }

  @GetMapping("/{itemId}/edit")
  fun editItem(@PathVariable itemId: Long, model: Model): String {
    val findById = itemRepository.findById(itemId)
    model.addAttribute("item", findById)
    return "basic/editForm"
  }

  @PostMapping("/{itemId}/edit")
  fun edit(@PathVariable itemId: Long, @ModelAttribute item: Item): String {
    itemRepository.update(itemId, item)
    return "redirect:/basic/items/{itemId}"
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