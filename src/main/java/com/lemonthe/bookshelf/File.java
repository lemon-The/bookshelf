package com.lemonthe.bookshelf;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lemonthe.bookshelf.data.PathByStringConverter;

@Entity
@Table(name = "files")
public class File {
    @Id
    @SequenceGenerator(name = "f_s",
        sequenceName = "FILE_SEQUENCE",
        initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "f_s")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID")
    private Book book;
    @Column(name = "file_path")
    @Convert(converter = PathByStringConverter.class)
    private Path path;
    ////////////////////////////////////////////////////////////
    public void setId(Long id) {
        this.id = id;
    }
    public void setPath(Path path) {
        this.path = path;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    ////////////////////////////////////////////////////////////
    public Long getId() {
        return this.id;
    }
    @Column(name = "PATH")
    public Path getPath() {
        return this.path;
    }
    public Book getBook() {
        return this.book;
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
        File other = (File)otherObject;
        return Objects.equals(id, other.id)
            && Objects.equals(path, other.path)
            && Objects.equals(book, other.book);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, path, book);
    }
    @Override
    public String toString() {
        return getClass().getName() + "[id=" + id
            + ", path=" + path
            + ", book=" + book + "]";
    }
}
