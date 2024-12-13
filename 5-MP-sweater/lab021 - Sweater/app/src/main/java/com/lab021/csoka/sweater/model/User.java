package com.lab021.csoka.sweater.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

@Entity(tableName = "Users")
public class User implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    private Long userId;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "password")
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(userId);
        dest.writeString(email);
        dest.writeString(password);
    }

    public User(Parcel in) {
        this.userId = in.readLong();
        this.email = in.readString();
        this.password = in.readString();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + this.userId.toString() + "\'" +
                ", email=" + this.email + "\'" +
                ", password=" + this.password + "\'" +
                "}\n";
    }

    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public boolean equals(Object obj) {
        User other = (User) obj;
        return this.getUserId().equals(other.getUserId());
    }

    public JSONObject toJsonObject() {
        JSONObject u = new JSONObject();
        try {
            u.put("userId", this.getUserId());
            u.put("email", this.getEmail());
            u.put("password", this.getPassword());
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return u;
    }
}
