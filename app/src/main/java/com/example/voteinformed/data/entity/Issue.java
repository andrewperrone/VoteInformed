package com.example.voteinformed.data.entity;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "issue")
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

    public Issue (String title, String description, String type, String location){
        this.title = title;
        this.description = description;
        this.type = type;
        this.location = location;
    }

    public int getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(int issue_id) {
        this.issue_id = issue_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "issue_id=" + issue_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return issue_id == issue.issue_id && Objects.equals(title, issue.title) && Objects.equals(description, issue.description) && Objects.equals(type, issue.type) && Objects.equals(location, issue.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(issue_id, title, description, type, location);
    }
}
