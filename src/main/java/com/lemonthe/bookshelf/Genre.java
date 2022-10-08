package com.lemonthe.bookshelf;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "GENRES")
public class Genre implements Serializable {
    @Id
    @SequenceGenerator(name = "g_s", 
        sequenceName = "GENRE_SEQUENCE", 
        initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "g_s")
    //@GeneratedValue(generator = "sequence-generator")
    //@GenericGenerator(
    //  name = "sequence-generator",
    //  strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
    //  parameters = {
    //    @Parameter(name = "sequence_name", value = "GENRE_SEQUENCE"),
    //    @Parameter(name = "initial_value", value = "4"),
    //    @Parameter(name = "increment_size", value = "1")
    //    }
    //)
    @Column(nullable = false)
    private Long id;
    @NotBlank(message = "Genre name is required")
    private String name;
    @OneToMany(mappedBy = "parent")
    private List<Genre> subgenres = new LinkedList<>();
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Genre parent;
    @ManyToMany(mappedBy = "genres", cascade = CascadeType.ALL)
    private List<Book> books;

    public void addSubgenre(Genre genre) {
        subgenres.add(genre);
    }
    public void addBook(Book book) {
        this.books.add(book);
    }
    ////////////////////////////////////////////////////////////
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSubgenres(List<Genre> subgenres) {
        this.subgenres = subgenres;
    }
    public void setParent(Genre parent) {
        this.parent = parent;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    ////////////////////////////////////////////////////////////
    public Long getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public List<Genre> getSubgenres() {
        return this.subgenres;
    }
    public Genre getParent() {
        return this.parent;
    }
    public List<Book> getBooks() {
        return this.books;
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
        Genre other = (Genre)otherObject;
        return Objects.equals(id, other.id)
            && Objects.equals(name, other.name)
            && Objects.equals(parent, other.parent)
            && Objects.equals(subgenres, other.subgenres);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, parent, subgenres);
    }
    @Override
    public String toString() {
        return getClass().getName() + "[id=" + id
            + ", name=" + name
            + ", parent=" + (parent == null ? null : parent.name)
            + ", subgenres=" + subgenres + "]";
    }
}
