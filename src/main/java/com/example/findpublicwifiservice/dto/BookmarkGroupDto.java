package com.example.findpublicwifiservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Setter
@Getter
public class BookmarkGroupDto {
    private int id;
    private String name;
    private String createDate;
    private String updateDate;
    private int orderValue;

    @Builder
    public BookmarkGroupDto(int id, String name, String createDate, String updateDate, int orderValue) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.orderValue = orderValue;
    }

    public static BookmarkGroupDto fromResultSet(ResultSet rs) throws SQLException {
        return BookmarkGroupDto.builder()
            .id(rs.getInt("ID"))
            .name(rs.getString("NAME"))
            .createDate(rs.getString("CREATE_DT"))
            .updateDate(rs.getString("UPDATE_DT"))
            .orderValue(rs.getInt("ORDER_VALUE"))
            .build();
    }
}
