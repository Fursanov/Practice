package org.example;

public class Config {
    public static final String url  = "jdbc:postgresql://localhost:5432/market";
    public static final String password = System.getenv("DB_PASSWORD");
    public static final String username = System.getenv("DB_USERNAME");
}
