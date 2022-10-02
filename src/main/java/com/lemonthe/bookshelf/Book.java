package com.lemonthe.bookshelf;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "BOOKS")
public class Book implements Serializable {
    @Id
    @SequenceGenerator(name = "b_s", 
        sequenceName = "BOOK_SEQUENCE", 
        initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "b_s")
    //@GeneratedValue(generator = "sequence-generator")
    //@GenericGenerator(
    //  name = "sequence-generator",
    //  strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
    //  parameters = {
    //    @Parameter(name = "sequence_name", value = "BOOK_SEQUENCE"),
    //    @Parameter(name = "initial_value", value = "4"),
    //    @Parameter(name = "increment_size", value = "1")
    //    }
    //)
    @Column(nullable = false)
    private long id;
    @NotEmpty(message = "Book title is required")
    private String title;
    private String annotation;
    //private int pages;
    @ManyToOne
    @JoinColumn(name = "PHOTO_ID", referencedColumnName = "ID")
    private Photo photo;
    @NotNull(message = "Book genre is required")
    @ManyToMany
    @JoinTable(name = "BOOKS_GENRES",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "GENRE_ID"))
    private List<Genre> genres = new LinkedList<>();
    @NotEmpty(message = "Book author is required")
    @ManyToMany
    @JoinTable(name = "BOOKS_AUTHORS",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID"))
    private List<Author> authors = new LinkedList<>();
    

    public void addGenre(Genre genre) {
        genres.add(genre);
    }
    public void addAuthor(Author author) {
        authors.add(author);
    }

    ////////////////////////////////////////////////////////////
    public void setId(long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
    ////////////////////////////////////////////////////////////    
    public long getId() {
        return this.id;
    }
    public String getTitle() {
        return this.title;
    }
    public String getAnnotation() {
        return this.annotation;
    }
    public List<Genre> getGenres() {
        return this.genres;
    }
    public List<Author> getAuthors() {
        return this.authors;
    }
    public Photo getPhoto() {
        return this.photo;
    }
    ////////////////////////////////////////////////////////////
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null)
            return false;
        if (getClass() != otherObject.getClass())
            return false;
        Book other = (Book)otherObject;
        return id == other.id
            && Objects.equals(title, other.title)
            && Objects.equals(annotation, other.annotation)
            && Objects.equals(genres, other.genres)
            && Objects.equals(authors, other.authors)
            && Objects.equals(photo, other.photo);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, title, annotation, genres,
                authors, photo);
    }
    @Override
    public String toString() {
        return getClass().getName() + "[id=" + id
            + ", title=" + title
            + ", annotation=" + annotation
            + ", genres=" + genres
            + ", photo=" + photo
            + ", authors=" + authors + "]";
    }
}
