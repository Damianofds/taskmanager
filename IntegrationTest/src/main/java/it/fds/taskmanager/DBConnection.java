package it.fds.taskmanager;


public enum DBConnection {

    ENDPOINT("jdbc:postgresql://localhost:5432/s_ink",
            "postgres",
            "postgres",
            "jdbc:postgresql://localhost:5432/s_ink"),

    SCHEDULER("jdbc:postgresql://localhost:5432/s_ink",
            "postgres",
            "postgres",
            "jdbc:postgresql://localhost:5432/s_ink");

    String url;
    String postgresUser;
    String postgresPassword;
    String postgresUrl;

    DBConnection(String url, String postgresUser, String postgresPassword, String postgresUrl){
        this.url = url;
        this.postgresUser = postgresUser;
        this.postgresPassword = postgresPassword;
        this.postgresUrl = postgresUrl;
    }

    public String getPostgresUrl() {
        return postgresUrl;
    }
    public String getPostgresUser() {
        return postgresUser;
    }
    public String getPostgresPassword() {
        return postgresPassword;
    }
}






