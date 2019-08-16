package com.example.myapplication.DataFollowerHistory;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity(primaryKeys = {"userTwitterId","followerId","historyDate"})
@Data
@AllArgsConstructor
public class FollowerHistory {
    @ColumnInfo@NonNull
    private Long userTwitterId;
    @ColumnInfo@NonNull
    private Long followerId;
    @ColumnInfo@NonNull
    private Date historyDate;
    @ColumnInfo@NonNull
    private HistoryType historyType;

    public FollowerHistory(Long userTwitterId, Long followerId,HistoryType historyType) {
        this.userTwitterId = userTwitterId;
        this.followerId = followerId;
        this.historyType = historyType;
        this.historyDate = new Date();

    }

    public enum HistoryType
    {
        follow(1),
        remove(2),
        init(3);

        private final long type;
        HistoryType(long i) {
            this.type = i;
        }

        public Long getType() {
            return type;
        }
    };
}
