package com.example.findpublicwifiservice.service;

import com.example.findpublicwifiservice.dao.BookmarkDao;
import com.example.findpublicwifiservice.dto.BookmarkDto;

import java.util.List;

public class BookmarkService {
    private BookmarkDao bookmarkDao;

    public BookmarkService() {
        this.bookmarkDao = new BookmarkDao();
    }

    // 북마크 추가 메서드
    public void addBookmark(String xSwifiMgrNo, int bookmarkGroupId) {
        bookmarkDao.insert(xSwifiMgrNo, bookmarkGroupId);
    }

    // 북마크 목록 조회 메서드
    public List<BookmarkDto> getAllBookmarks() {
        return bookmarkDao.selectAll();
    }

    // 북마크 삭제 메서드
    public void deleteBookmark(int id) {
        bookmarkDao.delete(id);
    }

    public BookmarkDto selectBookmark(int id) {
       return bookmarkDao.select(id);
    }
}
