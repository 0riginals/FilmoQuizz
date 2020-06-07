package com.test.filmoquizz.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Paul VINOT, Antoine COLPAERT, Yuting JIN
 */
@DatabaseTable(tableName = "users")
public class User {
    public static final String PSEUDO = "pseudo";
    public static final String PASSWORD = "password";

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = PSEUDO, canBeNull = false, unique = true)
    private String pseudo;
    @DatabaseField(columnName = PASSWORD, canBeNull = false)
    private String password;

    public User() {
        // Constructeur par défaut vide pour OrmLite
    }

    public User(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
