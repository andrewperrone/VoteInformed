package com.example.voteinformed.database.user;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "user_election")
public class User_Election{
    @ColumnInfo(name = "user_id")
    private int userId;
    @ColumnInfo(name = "election_id")
    private int electionId;
    public User_Election(int userId, int electionId){
        this.userId = userId;
        this.electionId = electionId;
    }
    public int getUserId(){
        return userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }
    public int getElectionId(){
        return electionId;
    }
    public void setElectionId(int electionId){
        this.electionId = electionId;
    }
}

