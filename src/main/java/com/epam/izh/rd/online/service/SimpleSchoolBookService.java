package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements BookService {

    private BookRepository<SchoolBook> schoolBookBookRepository;
    private AuthorService authorService;

    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }

    @Override
    public boolean save(Book book) {
        SchoolBook sb = (SchoolBook) book;
        Author author = this.authorService.findByFullName(sb.getAuthorName(), sb.getAuthorLastName());
        if (author == null) return false;
        return this.schoolBookBookRepository.save(sb);
    }

    @Override
    public Book[] findByName(String name) {
        return this.schoolBookBookRepository.findByName(name);
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        return this.findByName(name).length;
    }

    @Override
    public boolean removeByName(String name) {
        return this.schoolBookBookRepository.removeByName(name);
    }

    @Override
    public int count() {
        return this.schoolBookBookRepository.count();
    }

    @Override
    public Author findAuthorByBookName(String name) {
        SchoolBook[] books = this.schoolBookBookRepository.findByName(name);
        Author resAuthor = null;
        for (SchoolBook b :books) {
            Author author = this.authorService.findByFullName(b.getAuthorName(), b.getAuthorLastName());
            if (author != null) resAuthor = author;
        }
        return resAuthor;
    }
}
