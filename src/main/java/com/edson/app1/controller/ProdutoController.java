package com.edson.app1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.edson.app1.model.Produto;

@Controller
public class ProdutoController {

	private static List<Produto> listaProdutos = new ArrayList<Produto>();
	private static int next = 1;

	@GetMapping("/produto/list")
	public String list(Model model) {

		model.addAttribute("produtos", listaProdutos);

		return "lista-produtos";
	}

	@GetMapping("/produto/novo")
	public String novo(Model model) {
		model.addAttribute("produto", new Produto());
		return "novo-produto";
	}

	@GetMapping("/produto/{id}")
	public String detalhe(@PathVariable("id") int id, Model model) {
		Produto p = buscarProdutoPeloId(id);
		if (p == null) {
			return "produto-nao-encontrado";
		}
		model.addAttribute("produto", p);
		return "detalhe-produto";
	}

	@PostMapping("/produto/novo")
	public ModelAndView novo(Produto prod) {
	ModelAndView mv = 
	new ModelAndView("redirect:/produto/list");
	if (prod.getId() == 0) {
	prod.setId(next++);
	listaProdutos.add(prod);
	} else {
	updateProduto(prod);
	}
	return mv;
	}

 

	private Produto buscarProdutoPeloId(int id) {
		for (Produto p : listaProdutos) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}

	private void updateProduto(Produto prod) {
		ListIterator<Produto> i = listaProdutos.listIterator();
		while (i.hasNext()) {
			Produto atual = i.next();
			if (atual.getId() == prod.getId()) {
				i.set(prod);
			}
		}
	}
	@GetMapping("/produto/{id}/edit")
	public String edit(
	@PathVariable("id") int id, Model model) {
	Produto p = buscarProdutoPeloId(id);
	if (p == null) {
	return "produto-nao-encontrado";
	}
	model.addAttribute("produto", p);
	return "novo-produto";
	}
}