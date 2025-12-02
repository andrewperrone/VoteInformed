package com.example.voteinformed.database.politician;// Politician.java - Entity that represents a politician's information
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "politician_table")
public class Politician
{
    @PrimaryKey(autoGenerate = true)
    private int politician_id;

    //@ColumnInfo(name="politician_name")
    private String politician_name;
    //@ColumnInfo(name="politician_party")
    private String politician_party;
    //@ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    //public byte[] politician_image;
    //@ColumnInfo(name="politician_contact")
    private String politician_contact;
    //@ColumnInfo(name="politician_background")
    private String politician_background;
    //@ColumnInfo(name="politician_location")
    private String politician_location;

    public Politician(String politician_name, String politician_party, /*byte[] politician_image,*/ String politician_contact, String politician_background, String politician_location)
    {
        this.politician_name = politician_name;
        this.politician_party = politician_party;
        //this.politician_image = politician_image;
        this.politician_contact = politician_contact;
        this.politician_background = politician_background;
        this.politician_location = politician_location;
    }

    // Getters and Setters
    public int getPolitician_id()
    {
        return politician_id;
    }

    public void setPolitician_id(int politician_id)
    {
        this.politician_id = politician_id;
    }

    public String getPolitician_name()
    {
        return politician_name;
    }

    public void setPolitician_name(String politician_name)
    {
        this.politician_name = politician_name;
    }

    public String getPolitician_party()
    {
        return politician_party;
    }

    public void setPolitician_party(String politician_party)
    {
        this.politician_party = politician_party;
    }

    public String getPolitician_contact()
    {
        return politician_contact;
    }

    public void setPolitician_contact(String politician_contact)
    {
        this.politician_contact = politician_contact;
    }

    public String getPolitician_background()
    {
        return politician_background;
    }

    public void setPolitician_background(String politician_background)
    {
        this.politician_background = politician_background;
    }

    public String getPolitician_location()
    {
        return politician_location;
    }

    public void setPolitician_location(String politician_location)
    {
        this.politician_location = politician_location;
    }
}
