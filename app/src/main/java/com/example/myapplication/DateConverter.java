package com.example.myapplication;

import androidx.room.TypeConverter;

import com.example.myapplication.DataFollowerHistory.FollowerHistory;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public Date fromDate(Long value) {
        Date ret = null;
        if (value != null) {
            ret = new Date(value);
        }
        return ret;
    }

    @TypeConverter
    public Long toDate(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }

    @TypeConverter
    public FollowerHistory.HistoryType fromFollowerHistory(Long value) {
        for (FollowerHistory.HistoryType historyType : FollowerHistory.HistoryType.values()) {
            if(historyType.getType() == value)
            {
                return historyType;
            }
        }
        return null;
    }
    @TypeConverter
    public Long toFollowerHistory(FollowerHistory.HistoryType historyType)
    {
        if (historyType == null)
        {
            return null;
        }else
        {
            return historyType.getType();
        }
    }


}