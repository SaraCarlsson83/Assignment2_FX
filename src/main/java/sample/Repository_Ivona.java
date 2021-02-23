package sample;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Repository_Ivona {

    private Properties p = new Properties();
    Community c = new Community();

    public Repository_Ivona() {

        try {
            p.load(new FileInputStream("src/main/java/sample/settings.properties"));
            //Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getAverageRating(int shoeId){
        double result = 0;
        try (
                Connection connection = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                CallableStatement statement = connection.prepareCall("select average_rate (?) as avg");
        ) {
            statement.setInt(1, shoeId);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while(rs.next()){
                result = rs.getDouble("avg");
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    public List<String> getComments(int shoeId){
        List<String> result = new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                CallableStatement statement = connection.prepareCall("select comment from rating where Shoe_id = ?");
        ) {
            statement.setInt(1, shoeId);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while(rs.next()){
                String comment = rs.getString("comment");
                if (comment != null)
                    result.add(comment);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    public String setRating (String comment, String ratingAlternatives, int shoeId, int customerId){
        String errorMessage = "";
        try (
                Connection connection = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                CallableStatement statement = connection.prepareCall("call Rate_Strings (?,?,?,?)");
        ) {
            statement.setString(1, ratingAlternatives);
            statement.setString(2, comment);
            statement.setInt(3, shoeId);
            statement.setInt(4, customerId);

            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs != null && rs.next()) {
                errorMessage = rs.getString("error");
            }
            if (!errorMessage.equals("")) {
                return errorMessage;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Kunde inte lägga till betyget";
        }
        return "Betyget har läggt till";

    }































}

