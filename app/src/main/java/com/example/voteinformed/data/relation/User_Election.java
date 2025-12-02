package com.example.voteinformed.data.relation;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "user_election")
public class User_Election{
    //@ForeignKey()
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

