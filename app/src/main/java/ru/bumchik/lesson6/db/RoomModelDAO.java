package ru.bumchik.lesson6.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RoomModelDAO {

        @Query("SELECT * FROM users")
        abstract List<RoomModel> getAll();

        @Query("SELECT * FROM users WHERE userId LIKE :name LIMIT 1")
        RoomModel findByUserId(String name);

        @Insert
        void insertAll(List<RoomModel> item);

        @Update
        void update(RoomModel item);

        @Delete
        void delete(RoomModel item);

        @Query("DELETE FROM users")
        void deleteAll();
}
