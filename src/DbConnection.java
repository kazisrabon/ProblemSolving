import java.sql.*;

public class DbConnection {
    private String dbURL = "jdbc:mysql://localhost:3306/userdetails";
    private String userName = "root";
    private String password = "";
    private String driverName = "com.mysql.cj.jdbc.Driver";
    private final String INSERTION = "INSERT INTO userdetail " +
            "(userName, password, phoneNumber) VALUES ";
    private final String UPDATE = "UPDATE userdetail SET ";
    private final String WHERE = "where";
    private final String DELETE = "DELETE FROM userdetail Where id = ";
    private final String SEARCH = "select * from userdetail where phoneNumber = ";
    private final String AUTH = "select * from userdetail where ";
    private Connection connection;

    public static void main(String[] args){
        DbConnection dbConnection = new DbConnection();
    }

    public DbConnection() {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(dbURL, userName, password);
            if (connection!=null){
                System.out.println("Success");
                Statement statement = connection.createStatement();
//                statement.executeUpdate(
//                        insertUser("test3", "testPass3", 1234567));

                authenticateUser("test3", "testPass3", statement);

//                statement.executeUpdate(
//                        deleteUser(1)
//                );
//
//                statement.executeUpdate(
//                        updateUser(2, "test2", "testPassword2")
//                );
//
//                searchUser(1234567, statement);

            }
            connection.close();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    private void searchUser(int phoneNumber, Statement statement) throws SQLException {
        ResultSet rs = statement.executeQuery(
                SEARCH + phoneNumber
        );
        while(rs.next()){
            //Display values
            System.out.print("Name: " + rs.getString("userName"));
            System.out.print(", Password: " + rs.getString("password"));
            System.out.print(", Phone number: " + rs.getInt("phoneNumber"));
            System.out.println(", User Id: " + rs.getInt("userId"));
        }
        rs.close();
    }

    private String updateUser(int userId, String userName, String password) {
        return new String(
                UPDATE+
                        " userName = \""+ userName+"\"," +
                        " password = \""+ password+"\" " +
                        WHERE+
                        " userId = "+ userId
        );
    }

    private String deleteUser(int userId) {
        return new String(
                DELETE + userId
        );
    }

    private void authenticateUser
            (String userName, String password, Statement statement)
            throws SQLException{
        String s = AUTH +
                " userName = \""+userName+"\" AND"+
                " password = \""+password+"\"";
        ResultSet rs = statement.executeQuery(s);
        if(rs!= null){
            //Display values
            System.out.println("User Authenticated");
            rs.close();
        }
        else System.out.println("User Not Found!!!");
    }

    private String insertUser(String userName, String password, int phoneNumber) {
        return new String(INSERTION
                            +"( "
                            + "\""+userName + "\", "
                            + "\""+password + "\", "
                            +phoneNumber
                            +" )");

    }
}
