package com.lemonthe.bookshelf.data;

import com.lemonthe.bookshelf.File;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository
    extends CrudRepository<File, Long>{
}
