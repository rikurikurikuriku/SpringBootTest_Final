package fi.spring.bookstore.Bookstore;

import org.springframework.boot. CommandLineRunner;   
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import fi.spring.bookstore.Bookstore.domain.Book;
import fi.spring.bookstore.Bookstore.domain.BookRepository;
import fi.spring.bookstore.Bookstore.domain.Category;
import fi.spring.bookstore.Bookstore.domain.CategoryRepository;
import fi.spring.bookstore.Bookstore.domain.User;
import fi.spring.bookstore.Bookstore.domain.UserRepository;
 
@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(CategoryRepository repository1, BookRepository repository, UserRepository upository) {
	return (args) -> {
		
		repository1.save(new Category("Fiction"));
		repository1.save(new Category("Non-Fiction"));
		
		repository.save(new Book(1, "The Old Man and The Sea", "Ernest Hemingway", 1934, 1111, 19.90, repository1.findByName("Fiction").get(0)));	
		repository.save(new Book(1, "Testbook", "Some Dude", 2021, 1234, 10.00, repository1.findByName("Fiction").get(0)));
		
		User riku = new User("riku", "$2a$10$PfsP09QvIUHfh3EAJYRKlOYvNVBKdY5XWlZLudfb2SxhNBP/.tuM.", "USER");
		User admin = new User("admin", "$2a$10$6xZzUl8TaDJWt4EWlWNPrefma6pDyWkvUKMI4570bN6m/lwNdJLNq", "ADMIN");
		upository.save(riku);
		upository.save(admin);
	};
	}
	
}
