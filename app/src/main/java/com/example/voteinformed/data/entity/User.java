package com.example.voteinformed.data.entity;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.util.Objects;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    private int user_id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "location")
    private String location;
    @ColumnInfo(name = "preference")
    private String preferences;
    @ColumnInfo(name = "isAdmin")
    private boolean is_admin;

    //Default Constructor
    public User(String name, String email, String password, String location, String preferences) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.location = location;
        this.preferences = preferences;
        this.is_admin = false;
    }

    //Set admin during construction
    @Ignore
    public User(String name, String email, String password, String location, String preferences, boolean is_admin) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.location = location;
        this.preferences = preferences;
        this.is_admin = is_admin;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + user_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", location='" + location + '\'' +
                ", preferences='" + preferences + '\'' +
                ", is_admin=" + is_admin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return user_id == user.user_id && is_admin == user.is_admin && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(location, user.location) && Objects.equals(preferences, user.preferences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, name, email, password, location, preferences, is_admin);
    }
}
