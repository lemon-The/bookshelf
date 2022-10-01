package com.lemonthe.bookshelf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.transaction.Transactional;

import com.lemonthe.bookshelf.data.AuthorRepository;
import com.lemonthe.bookshelf.data.BookRepository;
import com.lemonthe.bookshelf.data.GenreRepository;
import com.lemonthe.bookshelf.web.AuthorByIdConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private AuthorRepository aRepo;
    @Autowired
    private BookRepository bRepo;
    @Autowired
    private GenreRepository gRepo;
    Logger logger = LoggerFactory.getLogger(ApplicationTests.class);

	@Test
    @Transactional
	void contextLoads() {
        List<Genre> genres = new LinkedList<>();
        logger.warn("" + gRepo.count());
        logger.info("TESTTEST");
        System.out.println(genres);

        logger.info("Saving gr");
        Genre gr = createGenre1();
        gr = gRepo.save(gr);
        logger.info("GR saved");
        gRepo.findAll().forEach(i -> genres.add(i));
        for (Genre g : genres) {
            System.out.println(g);
        }
        logger.info("" + gRepo.count());
        Author ar = createAuthor1();
        aRepo.save(ar);

        List<Book> books = new ArrayList<>();
        bRepo.findAll().forEach(i -> books.add(i));
        for (Book b : books) {
            System.out.println(b);
        }
        Book bk = createBook1();
        bk.addGenre(gr);
        bk.addAuthor(ar);
        bRepo.save(bk);
        books.clear();
        bRepo.findAll().forEach(i -> books.add(i));
        for (Book b : books) {
            System.out.println(b);
        }
        logger.info("" + bRepo.count());

        aRepo.findAll().forEach(i -> System.out.println(i));
        System.out.println(new AuthorByIdConverter(aRepo).convert(1L));

        //Genre gr = createGenre1();
        //Author ar = createAuthor1();
        //Book bk = createBook1();
        ////gRepo.save(gr);
        //aRepo.save(ar);

        //bk.addGenre(genres.get(1));
        //bk.addAuthor(ar);
        //bRepo.save(bk);
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
        bk.setPages(200);
        return bk;
    }
    private Author createAuthor1() {
        Author ar = new Author();
        ar.setName("Максим Горький");
        ar.setBirthDay(LocalDate.parse("1868-03-28"));
        return ar;
    }
    private Genre createGenre1() {
        Genre gr = new Genre();
        gr.setName("Temp");
        gr.setParent(null);
        gr.setSubgenres(null);
        //gr.setId(120);
        return gr;
    }
}
