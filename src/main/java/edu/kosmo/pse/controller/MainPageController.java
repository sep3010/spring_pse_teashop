package edu.kosmo.pse.controller;

import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.kosmo.pse.service.MainPageService;
import edu.kosmo.pse.service.OrderService;
import edu.kosmo.pse.vo.CartVO;
import edu.kosmo.pse.vo.ProductVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

// @RequestMapping("/main/*")
@Log4j
@AllArgsConstructor
@RestController
public class MainPageController {
	
	@Autowired
	private MainPageService pageService;
	
	@Autowired
	private OrderService orderService;
		
	@GetMapping("/main/menu/{categoryId}")
	public ModelAndView getCategoryList(ModelAndView view, ProductVO productVO, Principal principal) {
		log.info("getCategoryList()..");
		if(productVO.getCategoryId() == 0)
			view.addObject("productList", pageService.getAllTeaList());
		else
			view.addObject("productList", pageService.getCategoryList(productVO.getCategoryId()));
		if(principal != null) {
			String userId = principal.getName();
			view.addObject("userId", userId);
		}
		
		view.setViewName("main/menu");
		return view;
	}

	@GetMapping("/main/menuDetails/{productId}")
	public ModelAndView getmenuDetail(ModelAndView view, ProductVO productVO, Principal principal) {
		log.info("getmenuDetail()..");		
		view.addObject("product", pageService.getProduct(productVO.getProductId()));

		if(principal != null) {
			String userId = principal.getName();
			view.addObject("userId", userId);
		}
		
		view.setViewName("main/menuDetails/{productId}");
		return view;
	}
	
	/*
	@PutMapping("/menu/{categoryId}")
	public ResponseEntity<String> inCart(@RequestBody CartVO cartVO, Principal principal) {
		ResponseEntity<String> entity = null;
		log.info("inCart.. cartVO" + cartVO);
		try {
			orderService.inCart(cartVO.getProductId(), cartVO.getCartAmount(), cartVO.getCartPrice(), principal.getName());
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	*/

	
}
