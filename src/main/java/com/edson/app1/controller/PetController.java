package com.edson.app1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edson.app1.model.Pet;

@Controller
public class PetController {

	private static List<Pet> listaPet = new ArrayList<Pet>();

	/* classe que salva a lista */
	public void save() { // int id, String nome, String raca, int idade
		listaPet.add(new Pet(1, "alberto", "raca", 23));
		listaPet.add(new Pet(2, "carlos", "raca", 3));
		listaPet.add(new Pet(3, "jose", "raca", 8));
		listaPet.add(new Pet(4, "luiz", "raca", 5));
	}

//	@GetMapping("/pet")
//	public String form() {
//		return "pet/pet-form";
//	}

//	@PostMapping("/pet")
//	public String novo(
//			@RequestParam("id") int id,
//			@RequestParam("nome") String nome,
//			@RequestParam("raca") String raca,
//			@RequestParam("idade") int idade,
//			Model model) {
//		
//		model.addAttribute("id", id);
//		model.addAttribute("nome", nome);
//		model.addAttribute("raca", raca);
//		model.addAttribute("idade", idade);
//		
//		return "pet/pet-criado";
//	}

	@GetMapping("/pet")
	public String novo() {
		return "pet/pet-form";
	}

	@PostMapping("/pet")
	public ModelAndView novo(Pet pet) {
		save();
		ModelAndView mv = new ModelAndView("pet/pet-criado");

		listaPet.add(pet);

		// insert no banco de dados
		mv.addObject("pet", pet);

		return mv;
	}

	@GetMapping("/pet/list")
	public String list(Model model) {
		model.addAttribute("pet", listaPet);
		return "pet/pet-list";
	}

	@GetMapping("/pet/{id}")
	public String detalhe(@PathVariable("id") int id, Model model) {
		for (Pet p : listaPet) {
			if (p.getId() == id) {
				model.addAttribute("pet", p);
				break;
			}
		}
		return "pet/pet-detalhe";
	}

}