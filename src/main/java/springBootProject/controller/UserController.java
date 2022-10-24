package springBootProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springBootProject.model.User;
import springBootProject.services.UserService;


import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class UserController {

	private final UserService userService;
    @Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

//=====================@GetMapping==================================
	@GetMapping()
	public String index(Model model) {  //<<<<<<<<<<<<<<<<<<<<<<<<<СТАРТОВАЯ СТРАНИЦА СО ВСЕМИ ЮЗЕРАМИ
		model.addAttribute("users", userService.findAll());
		return "pages/index";
	}

	@GetMapping("/{id}")//<<<<<<<<<<<<<<ОТОБРАЖАЕТСЯ ЮЗЕР СО ВСЕМИ ПОЛЯМИ И КНОПКОЙ УДАЛЕНИЯ
	public String show(@PathVariable("id") int id, Model model){
		model.addAttribute("user", userService.findOne(id));
		return "pages/show";
	}

	@GetMapping("/new")//<<<<<<<<<<<<<<<<<<<<<СТРАНИЦА С СОЗДАНИЕМ НОВОГО ЮЗЕРА
    public String newUser(@ModelAttribute("user") User user){  // @ModelAttribute помещает user без параметров
		return "pages/new";}

	@GetMapping("/{id}/edit")//<<<<<<<<<<<<<<<СТРАНИЦА ДЛЯ РЕДАКТИРОВАНИЯ ЮЗЕРА
	public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("user", userService.findOne(id));
		return "pages/edit";
	}

//====================@PostMapping============================
    @PostMapping()//<<<<<<<<<<<<<<<<<<<<<<<<СОХРАНЕНИЕ ИЗМЕНЕНИЙ ЮЗЕРА
	public String create(@ModelAttribute("user") @Valid User user,
						 BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return "pages/new";

		userService.save(user);
		return "redirect:/users";         // REDIRECT переводит на нужную страницу
    }
//====================@PatchMapping============================
	@PatchMapping("/{id}") // ОБНОВЛЯЕМ ПОЛЯ ЮЗЕРА
	public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
						 @PathVariable("id") int id){
		if(bindingResult.hasErrors())
			return "pages/edit";

		userService.update(id, user);
		return "redirect:/users";
    }
//====================@DeleteMapping============================
	@DeleteMapping("/{id}")//<<<<<<<<<<<УДАЛЕНИЕ ЮЗЕРА
	public String delete(@PathVariable("id") int id) {
		userService.delete(id);
		return "redirect:/users";
    }

//==============================================================
}