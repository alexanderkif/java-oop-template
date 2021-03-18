package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        this.schoolBooks = Arrays.copyOf(this.schoolBooks, this.count() + 1);
        this.schoolBooks[this.count() - 1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        return Arrays.stream(this.schoolBooks)
                .filter(sb -> sb.getName().equals(name)).toArray(SchoolBook[]::new);
    }

    @Override
    public boolean removeByName(String name) {
        if (this.findByName(name).length == 0) return false;
        this.schoolBooks = Arrays.stream(this.schoolBooks)
                .filter(sb -> !sb.getName().equals(name)).toArray(SchoolBook[]::new);
        return true;
    }

    @Override
    public int count() {
        return this.schoolBooks.length;
    }
}
