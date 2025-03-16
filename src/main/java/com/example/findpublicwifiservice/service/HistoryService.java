package com.example.findpublicwifiservice.service;

import com.example.findpublicwifiservice.dao.HistoryDao;
import com.example.findpublicwifiservice.dto.HistoryDto;

import java.util.List;
import java.util.logging.Logger;

public class HistoryService {
    private static final Logger logger = Logger.getLogger(HistoryService.class.getName());
    private HistoryDao historyDao;

    public HistoryService() {
        historyDao = new HistoryDao();
    }

    public List<HistoryDto> selectAll() {
        return historyDao.getHistoryInfo();
    }

    public void save(String x, String y) {
        logger.info("historyDao save");
        historyDao.insert(Double.parseDouble(x), Double.parseDouble(y));
    }

    public void delete(int id) {
        historyDao.delete(id);
    }
}
