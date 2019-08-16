package com.example.myapplication.DataLastFollower;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(primaryKeys = {"userTwitterId","followerId"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LastFollower {
    @ColumnInfo@NonNull
    Long userTwitterId;
    @ColumnInfo@NonNull
    Long followerId;

}
