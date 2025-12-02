package com.example.voteinformed.database.issue;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Issue {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "issue_id")
    private int issue_id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "location")
    private String location;

    public Issue (int issue_id, String title, String description, String type, String location){
        this.issue_id = issue_id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.location = location;
    }

    public int getIssueId(){
        return issue_id;
    }
    public void setIssueId(int issue_id){
        this.issue_id = issue_id;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(){
        this.description = description;
    }

    public String getType(){
        return type;
    }
    public void setType(){
        this.type = type;
    }

    public String getLocation(){
        return location;
    }
    public void setLocation(){
        this.location = location;
    }

}
