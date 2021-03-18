package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (this.findByFullName(author.getName(), author.getLastName()) != null) return false;
        this.authors = Arrays.copyOf(this.authors, this.count() + 1);
        this.authors[this.count() - 1] = author;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author a : this.authors) {
            if (a.getName().equals(name) && a.getLastName().equals(lastname)) return a;
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (this.findByFullName(author.getName(), author.getLastName()) == null) return false;
        this.authors = Arrays.stream(this.authors)
                .filter(a -> !(a.getName().equals(author.getName()) && a.getLastName().equals(author.getLastName())))
                .toArray(Author[]::new);
        return true;
    }

    @Override
    public int count() {
        return this.authors.length;
    }
}
