package com.example.findpublicwifiservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Setter
@Getter
public class BookmarkDto {
    private int id;
    private String wifiMgrNo;
    private int bookMarkGroupId;
    private String createDate;
    private String wifiName;
    private String bookMarkGroupName;

    @Builder
    public BookmarkDto(int id, String wifiMgrNo, int bookMarkGroupId, String createDate, String wifiName, String bookMarkGroupName) {
        this.id = id;
        this.wifiMgrNo = wifiMgrNo;
        this.bookMarkGroupId = bookMarkGroupId;
        this.createDate = createDate;
        this.wifiName = wifiName;
        this.bookMarkGroupName = bookMarkGroupName;
    }

    public static BookmarkDto fromResultSet(ResultSet rs) throws SQLException {
        return BookmarkDto.builder()
            .id(rs.getInt("ID"))
            .wifiMgrNo(rs.getString("X_SWIFI_MGR_NO"))
            .bookMarkGroupId(rs.getInt("BOOK_MARK_GROUP_ID"))
            .createDate(rs.getString("CREATE_DT"))
            .wifiName(rs.getString("X_SWIFI_MAIN_NM"))
            .bookMarkGroupName(rs.getString("BOOK_MARK_GROUP_NAME"))
            .build();
    }

}
