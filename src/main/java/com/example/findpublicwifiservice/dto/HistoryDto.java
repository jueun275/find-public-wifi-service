package com.example.findpublicwifiservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


@Getter
@Setter
public class HistoryDto {
    private int id;
    private String x;
    private String y;
    private String date;      // 조회일자

    @Builder
    public HistoryDto(int id, String x, String y, String date) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.date = date;
    }

    public static HistoryDto fromResultSet(ResultSet rs) throws SQLException {
        return HistoryDto.builder()
            .id(rs.getInt("ID"))
            .x(rs.getString("LAT"))
            .y(rs.getString("LNT"))
            .date(rs.getString("CREATE_DT"))
            .build();
    }
}
