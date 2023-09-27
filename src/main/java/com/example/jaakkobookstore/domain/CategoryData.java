package com.example.jaakkobookstore.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoryData implements CommandLineRunner{
	
	private final CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryData(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	
	@Override
	public void run(String... args) {
		Category category1 = new Category();
		category1.setName("Fiction");
		categoryRepository.save(category1);
		
		Category category2 = new Category();
		category1.setName("Action");
		categoryRepository.save(category2);
		
		Category category3 = new Category();
		category1.setName("Romance");
		categoryRepository.save(category3);
	}
}
