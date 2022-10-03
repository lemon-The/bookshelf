package com.lemonthe.bookshelf;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.transaction.Transactional;

import com.lemonthe.bookshelf.data.AuthorRepository;
import com.lemonthe.bookshelf.data.BookRepository;
import com.lemonthe.bookshelf.data.GenreRepository;
import com.lemonthe.bookshelf.data.PhotoRepository;
import com.lemonthe.bookshelf.web.AuthorByIdConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
class ApplicationTests {
    private final AuthorRepository aRepo;
    private final BookRepository bRepo;
    private final GenreRepository gRepo;
    private final PhotoRepository pRepo;

    private final List<Author> authors = new LinkedList<>();
    private final List<Book> books = new LinkedList<>();
    private final List<Genre> genres = new LinkedList<>();
    private final List<Photo> photos = new LinkedList<>();
    Logger logger = LoggerFactory.getLogger(ApplicationTests.class);

    @Autowired
    public ApplicationTests(AuthorRepository aRepo, BookRepository bRepo,
            GenreRepository gRepo, PhotoRepository pRepo) {
        this.aRepo = aRepo;
        this.bRepo = bRepo;
        this.gRepo = gRepo;
        this.pRepo = pRepo;
    }

	//@Test
    //@Transactional
    @BeforeEach
	public void init() {
        bRepo.findAll().forEach(i -> books.add(i));
        aRepo.findAll().forEach(i -> authors.add(i));
        gRepo.findAll().forEach(i -> genres.add(i));
        pRepo.findAll().forEach(i -> photos.add(i));
	}
    @Test
    @Transactional
    public void testPhotoSaving() {
        //Book book = createBook1();
        //book.addGenre(createGenre1());
        //book.addAuthor(createAuthor1());

        //bRepo.save(book);
        final String dir = System.getProperty("user.dir");
        logger.info(dir);
    }
    private Book createBook1() {
        Book bk = new Book();
        bk.setTitle("Мои университеты");
        bk.setAnnotation("В нашем новом проекте вы найдете как книги из"
                + "\"золотого фонда\" классической русской литературы, "
                + "так и редкие, почти забытые, произведения авторов, "
                + "оставшихся в тени своих великих современников. Эти "
                + "книги не утратили самобытной актуальности, сегодня, "
                + "как и сто лет назад, они определяют пути русской "
                + "культуры, ищут ответы на \"проклятые вопросы\". Без "
                +"этих книг невозможно понять, кто мы, кем мы были, куда "
                + "движемся. Мы отобрали для вас самые важные тексты "
                + "русских классиков, представили их в наиболее полных "
                + "редакциях, без каких-либо сокращений и цензурных "
                + "изъятий, и составили серию \"Rugram-классика\"."
                + "Вашему вниманию представляется книга Максима Горького "
                + "\"Мои университеты\". ");
        return bk;
    }
    private Author createAuthor1() {
        Author ar = new Author();
        ar.setName("Максим Горький");
        ar.setBirthDay(LocalDate.parse("1868-03-28"));
        return aRepo.save(ar);
    }
    private Genre createGenre1() {
        Genre gr = new Genre();
        gr.setName("Temp");
        gr.setParent(null);
        gr.setSubgenres(null);
        return gRepo.save(gr);
    }
   // private Photo createPhoto() {
   //     Photo ph = new Photo();
   //     Path path = Paths.get("./target/classes/static/images/books_covers");
   //     ph.setPath(path);
   // }
}
