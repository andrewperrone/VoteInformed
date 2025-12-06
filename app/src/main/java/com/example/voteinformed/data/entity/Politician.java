package com.example.voteinformed.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "politician")
public class Politician {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="politician_id")
    private int politician_id;

    @ColumnInfo(name="politician_name")
    private String politician_name;

    @ColumnInfo(name="politician_party")
    private String politician_party;

    // 🔵 변경됨: BLOB → TEXT(URL)
    @ColumnInfo(name="politician_image_url")
    private String politician_image_url;

    @ColumnInfo(name="politician_contact")
    private String politician_contact;

    @ColumnInfo(name="politician_background")
    private String politician_background;

    @ColumnInfo(name="politician_location")
    private String politician_location;

    // 🔵 생성자도 URL 사용하도록 수정
    public Politician(
            String politician_name,
            String politician_party,
            String politician_image_url,
            String politician_contact,
            String politician_background,
            String politician_location
    ) {
        this.politician_name = politician_name;
        this.politician_party = politician_party;
        this.politician_image_url = politician_image_url;
        this.politician_contact = politician_contact;
        this.politician_background = politician_background;
        this.politician_location = politician_location;
    }

    // Getters & Setters
    public int getPolitician_id() { return politician_id; }
    public void setPolitician_id(int politician_id) { this.politician_id = politician_id; }

    public String getPolitician_name() { return politician_name; }
    public void setPolitician_name(String politician_name) { this.politician_name = politician_name; }

    public String getPolitician_party() { return politician_party; }
    public void setPolitician_party(String politician_party) { this.politician_party = politician_party; }

    public String getPolitician_image_url() { return politician_image_url; }
    public void setPolitician_image_url(String politician_image_url) { this.politician_image_url = politician_image_url; }

    public String getPolitician_contact() { return politician_contact; }
    public void setPolitician_contact(String politician_contact) { this.politician_contact = politician_contact; }

    public String getPolitician_background() { return politician_background; }
    public void setPolitician_background(String politician_background) { this.politician_background = politician_background; }

    public String getPolitician_location() { return politician_location; }
    public void setPolitician_location(String politician_location) { this.politician_location = politician_location; }

    @Override
    public String toString() {
        return "Politician{" +
                "politician_id=" + politician_id +
                ", politician_name='" + politician_name + '\'' +
                ", politician_party='" + politician_party + '\'' +
                ", politician_image_url='" + politician_image_url + '\'' +
                ", politician_contact='" + politician_contact + '\'' +
                ", politician_background='" + politician_background + '\'' +
                ", politician_location='" + politician_location + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Politician that = (Politician) o;
        return politician_id == that.politician_id &&
                Objects.equals(politician_name, that.politician_name) &&
                Objects.equals(politician_party, that.politician_party) &&
                Objects.equals(politician_image_url, that.politician_image_url) &&
                Objects.equals(politician_contact, that.politician_contact) &&
                Objects.equals(politician_background, that.politician_background) &&
                Objects.equals(politician_location, that.politician_location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                politician_id,
                politician_name,
                politician_party,
                politician_image_url,
                politician_contact,
                politician_background,
                politician_location
        );
    }
}
