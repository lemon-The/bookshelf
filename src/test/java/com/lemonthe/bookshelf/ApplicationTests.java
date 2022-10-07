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
import com.lemonthe.bookshelf.web.services.AuthorService;
import com.lemonthe.bookshelf.web.services.BookService;
import com.lemonthe.bookshelf.web.services.GenreService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
class ApplicationTests {
    private final AuthorService aServ;
    private final BookService bServ;
    private final GenreService gServ;
    private final PhotoRepository pRepo;

    private final List<Photo> photos = new LinkedList<>();
    Logger logger = LoggerFactory.getLogger(ApplicationTests.class);

    @Autowired
    public ApplicationTests(AuthorService aServ, BookService bServ,
            GenreService gServ, PhotoRepository pRepo) {
        this.aServ = aServ;
        this.bServ = bServ;
        this.gServ = gServ;
        this.pRepo = pRepo;
    }

	//@Test
    //@Transactional
    @Test
    @Transactional
    public void testPhotoSaving() {
        gServ.deleteGenreById(1L);
        logger.info("All genres:" + gServ.getAllGenres());
        logger.info("All authors:" + aServ.getAllAuthors());
        logger.info("All books:" + bServ.getAllBooks());
    }
    //private Book createBook1() {
    //    Book bk = new Book();
    //    bk.setTitle("Мои университеты");
    //    bk.setAnnotation("В нашем новом проекте вы найдете как книги из"
    //            + "\"золотого фонда\" классической русской литературы, "
    //            + "так и редкие, почти забытые, произведения авторов, "
    //            + "оставшихся в тени своих великих современников. Эти "
    //            + "книги не утратили самобытной актуальности, сегодня, "
    //            + "как и сто лет назад, они определяют пути русской "
    //            + "культуры, ищут ответы на \"проклятые вопросы\". Без "
    //            +"этих книг невозможно понять, кто мы, кем мы были, куда "
    //            + "движемся. Мы отобрали для вас самые важные тексты "
    //            + "русских классиков, представили их в наиболее полных "
    //            + "редакциях, без каких-либо сокращений и цензурных "
    //            + "изъятий, и составили серию \"Rugram-классика\"."
    //            + "Вашему вниманию представляется книга Максима Горького "
    //            + "\"Мои университеты\". ");
    //    return bk;
    //}
    //private Author createAuthor1() {
    //    Author ar = new Author();
    //    ar.setName("Максим Горький");
    //    ar.setBirthDay(LocalDate.parse("1868-03-28"));
    //    return aRepo.save(ar);
    //}
    //private Genre createGenre1() {
    //    Genre gr = new Genre();
    //    gr.setName("Temp");
    //    gr.setParent(null);
    //    gr.setSubgenres(null);
    //    return gRepo.save(gr);
    //}
   // private Photo createPhoto() {
   //     Photo ph = new Photo();
   //     Path path = Paths.get("./target/classes/static/images/books_covers");
   //     ph.setPath(path);
   // }
}
