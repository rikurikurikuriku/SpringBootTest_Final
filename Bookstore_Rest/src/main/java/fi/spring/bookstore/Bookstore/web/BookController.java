package fi.spring.bookstore.Bookstore.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.spring.bookstore.Bookstore.domain.Book;
import fi.spring.bookstore.Bookstore.domain.BookRepository;
import fi.spring.bookstore.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository bookspository;
	
	@Autowired
	private CategoryRepository crepository;

	@RequestMapping("/books")
	public String allBooks(Model model) {
		
		model.addAttribute("books", bookspository.findAll());
		return "books";
	}
	
	// RESTful to get all books
    @RequestMapping(value="/bookies", method = RequestMethod.GET)
    public @ResponseBody List<Book>booktListRest() {	
        return (List<Book>) bookspository.findAll();
    }    
    
 // RESTful to get book by id
    @RequestMapping(value="/bookie/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return bookspository.findById(bookId);
    }       
	
	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {
	    Book book = bookspository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    
	    model.addAttribute("book", book);
	    model.addAttribute("categories", crepository.findAll());
	    return "edit";
	}
	
	@PostMapping(value = "/update/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid Book book, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        book.setId(id);
	        model.addAttribute("categories", crepository.findAll());
	        return "edit";
	    }
	    bookspository.save(book);
	    return "redirect:/books";
	    
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(@Valid Book book, BindingResult binding, Model model) {
		if (binding.hasErrors()) {
			model.addAttribute("categories", crepository.findAll());
			return "addbook";
		}
		bookspository.save(book);
		return "redirect:books";
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	 @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	    public String deleteBook(@PathVariable("id") Long id, Model model) {
	    	bookspository.deleteById(id);
	        return "redirect:../books";
	    }     
}
