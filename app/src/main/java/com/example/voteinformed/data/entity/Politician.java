package com.example.voteinformed.data.entity;// Politician.java - Entity that represents a politicians's information
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Arrays;
import java.util.Objects;

@Entity(tableName = "politician")
public class Politician
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="politician_id")
    private int politician_id;
    @ColumnInfo(name="politician_name")
    private String politician_name;
    @ColumnInfo(name="politician_party")
    private String politician_party;
    @ColumnInfo(name="politician_image", typeAffinity = ColumnInfo.BLOB)
    public byte[] politician_image;
    @ColumnInfo(name="politician_contact")
    private String politician_contact;
    @ColumnInfo(name="politician_background")
    private String politician_background;
    @ColumnInfo(name="politician_location")
    private String politician_location;

    public Politician(String politician_name, String politician_party, byte[] politician_image, String politician_contact, String politician_background, String politician_location)
    {
        this.politician_name = politician_name;
        this.politician_party = politician_party;
        this.politician_image = politician_image;
        this.politician_contact = politician_contact;
        this.politician_background = politician_background;
        this.politician_location = politician_location;
    }

    // Getters and Setters
    public int getPolitician_id() {
        return politician_id;
    }

    public void setPolitician_id(int politician_id) {
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

    public byte[] getPolitician_image()
    {
        return politician_image;
    }

    public void setPolitician_image(byte[] politician_image)
    {
        this.politician_image = politician_image;
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

    @Override
    public String toString() {
        return "Politician{" +
                "politician_id=" + politician_id +
                ", politician_name='" + politician_name + '\'' +
                ", politician_party='" + politician_party + '\'' +
                ", politician_image=" + Arrays.toString(politician_image) +
                ", politician_contact='" + politician_contact + '\'' +
                ", politician_background='" + politician_background + '\'' +
                ", politician_location='" + politician_location + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Politician that = (Politician) o;
        return politician_id == that.politician_id && Objects.equals(politician_name, that.politician_name) && Objects.equals(politician_party, that.politician_party) && Objects.deepEquals(politician_image, that.politician_image) && Objects.equals(politician_contact, that.politician_contact) && Objects.equals(politician_background, that.politician_background) && Objects.equals(politician_location, that.politician_location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(politician_id, politician_name, politician_party, Arrays.hashCode(politician_image), politician_contact, politician_background, politician_location);
    }
}
