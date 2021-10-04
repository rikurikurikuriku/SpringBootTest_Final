package fi.spring.bookstore.Bookstore;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import fi.spring.bookstore.Bookstore.domain.Book;
import fi.spring.bookstore.Bookstore.domain.BookRepository;
import fi.spring.bookstore.Bookstore.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookstoreApplicationTests {
	
	
	@Autowired
	private BookRepository bepository;
	
public void findByTitleBooks() {
	
		List<Book> books = bepository.findByTitle("The Old Man and The Sea");
		
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Ernest Hemingway");
		
	}

	@Test
	public void createnewBook() {
		
		Book book = new Book(3, "The Test Testbook", "Some Other Dude", 1980, 123456, 100.00, new Category("Advertisement"));
		bepository.save(book);
		assertThat(book.getId()).isNotNull();
		
		
		
		
	}

}
