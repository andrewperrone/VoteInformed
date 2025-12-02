package com.example.voteinformed.database.user;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "user_politician")
public class User_Politician {
    @ColumnInfo(name = "user_id")
    private int user_id;

    @ColumnInfo(name = "politician_id")
    private int politician_id;

    public User_Politician(int user_id, int politician_id){
        this.user_id = user_id;
        this.politician_id = politician_id;
    }

    public int getUserId(){
        return user_id;
    }
    public void setUserId(int user_id){
        this.user_id = user_id;
    }

    public int getPoliticianId(){
        return politician_id;
    }
    public void setPoliticianId(int politician_id){
        this.politician_id = politician_id;
    }
}
