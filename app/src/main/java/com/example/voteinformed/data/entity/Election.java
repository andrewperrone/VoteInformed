package com.example.voteinformed.data.entity;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;
import java.util.Objects;

@Entity(tableName = "election")
public class Election {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="election_id")
    private int election_id;
    @ColumnInfo(name="election_name")
    private String election_name;
    @ColumnInfo(name="election_date")
    private Date election_date;
    @ColumnInfo(name="politician_location")
    private String election_location;
    @ColumnInfo(name="description")
    private String election_description;

    public Election(String election_name, Date election_date, String election_location, String election_description) {
        this.election_name = election_name;
        this.election_date = election_date;
        this.election_location = election_location;
        this.election_description = election_description;
    }

    public int getElection_id() {
        return election_id;
    }

    public void setElection_id(int election_id) {
        this.election_id = election_id;
    }

    public String getElection_name() {
        return election_name;
    }

    public void setElection_name(String election_name) {
        this.election_name = election_name;
    }

    public Date getElection_date() {
        return election_date;
    }

    public void setElection_date(Date election_date) {
        this.election_date = election_date;
    }

    public String getElection_location() {
        return election_location;
    }

    public void setElection_location(String election_location) {
        this.election_location = election_location;
    }

    public String getElection_description() {
        return election_description;
    }

    public void setElection_description(String election_description) {
        this.election_description = election_description;
    }

    @Override
    public String toString() {
        return "Election{" +
                "election_id=" + election_id +
                ", election_name='" + election_name + '\'' +
                ", election_date=" + election_date +
                ", politician_location='" + election_location + '\'' +
                ", politician_description='" + election_description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Election election = (Election) o;
        return election_id == election.election_id && Objects.equals(election_name, election.election_name) && Objects.equals(election_date, election.election_date) && Objects.equals(election_location, election.election_location) && Objects.equals(election_description, election.election_description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(election_id, election_name, election_date, election_location, election_description);
    }
}
