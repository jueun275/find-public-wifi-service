package com.example.findpublicwifiservice.service;

import com.example.findpublicwifiservice.dao.BookmarkGroupDao;
import com.example.findpublicwifiservice.dto.BookmarkGroupDto;

import java.util.List;

public class BookmarkGroupService {
    private BookmarkGroupDao bookmarkGroupDao;

    public BookmarkGroupService() {
        this.bookmarkGroupDao = new BookmarkGroupDao();
    }

    public void addBookMarkGroup(String name, String orderValue) {
        bookmarkGroupDao.insert(name, Integer.parseInt(orderValue));
    }

    public void removeBookMarkGroup(String id) {
        bookmarkGroupDao.delete(Integer.parseInt(id));
    }

    // 북마크 그룹 목록 조회
    public List<BookmarkGroupDto> getAllBookMarkGroups() {
        return bookmarkGroupDao.selectAll();
    }

    // 특정 북마크 그룹 조회
    public BookmarkGroupDto getBookMarkGroup(String id) {
        return bookmarkGroupDao.select(Integer.parseInt(id));
    }

    public void updateBookMarkGroup(String id, String orderValue, String name) {
        bookmarkGroupDao.update(Integer.parseInt(id), name, Integer.parseInt(orderValue));
    }
}
