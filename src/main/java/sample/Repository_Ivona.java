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
            p.load(new FileInputStream("src/Övningar/Settings"));
            //Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Community> getAllCommunities (){
        List<Community> allCommunities = new ArrayList<>();

        try(
                Connection connection = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from community")
        ) {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String communityName = resultSet.getString("com_name");
                allCommunities.add(new Community(id, communityName));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allCommunities;
    }

    public List<Customer> getAllCustomers(){
        List<Customer> allCustomers = new ArrayList<>();
        List<Community> allCommunities = getAllCommunities();

        try(
                Connection connection = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from customer")
        ) {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("First_name");
                String lastName = resultSet.getString("Last_Name");
                String userName = resultSet.getString("User_Name");
                String Password = resultSet.getString("Password");
                String adress = resultSet.getString("Adress");
                int communityId = resultSet.getInt("Community_id");
                Community community = allCommunities.stream().filter(s->s.id ==communityId).findFirst().orElse(null);
                allCustomers.add(new Customer(id, firstName,lastName,userName,Password,adress,community));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allCustomers;
    }

    public List<Label> getAllLabels(){
        List<Label> allLabels = new ArrayList<>();
        try(
                Connection connection = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from label")
        ) {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String labelName = resultSet.getString("label_name");
                allLabels.add(new Label(id, labelName));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allLabels;
    }

    public List<Name> getAllNames(){
        List<Name> allNames = new ArrayList<>();
        try(
                Connection connection = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from name")
        ) {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String shoeName = resultSet.getString("Shoe_name");
                allNames.add(new Name(id, shoeName));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allNames;
    }

    public List<Price> getAllPrices(){
        List<Price> allPrices = new ArrayList<>();
        try(
                Connection connection = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from price")
        ) {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int value = resultSet.getInt("value");
                allPrices.add(new Price(id, value));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allPrices;
    }

    public List<Orders> getAllOrders(){
        List<Orders> allOrders = new ArrayList<>();
        List<Customer> allCustomers = getAllCustomers();
        try(
                Connection connection = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from order")
        ) {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                int customerId = resultSet.getInt("Customer_id");
                Customer customer = allCustomers.stream().filter(s->s.id==customerId).findFirst().orElse(null);
                allOrders.add(new Orders(id, date,customer));
                            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allOrders;
    }

    public List<Rating_alternatives> getAllRatingAlternatives(){
        List<Rating_alternatives> allRatingAlternatives = new ArrayList<>();
        try(
                Connection connection = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from rating_alternatives")
        ) {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String options = resultSet.getString("options");
                int ratingNumbers = resultSet.getInt("rating_numbers");
                allRatingAlternatives.add(new Rating_alternatives(id, options, ratingNumbers));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allRatingAlternatives;
    }

    public List<Size> getAllSizes(){
        List<Size> allSizes = new ArrayList<>();
        try(
                Connection connection = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from size")
        ) {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String sizeName = resultSet.getString("Size_name");
                allSizes.add(new Size(id, sizeName));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allSizes;
    }

    public List<Color> getAllColors(){
        List<Color> allColors = new ArrayList<>();
        try(
                Connection connection = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from color")
        ) {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String colorName = resultSet.getString("col_name");
                allColors.add(new Color(id, colorName));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allColors;
    }

    public List<Category> getAllCategories(){
        List<Category> allCategories = new ArrayList<>();
        try(
                Connection connection = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from category")
        ) {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String categoryName = resultSet.getString("cat_name");
                allCategories.add(new Category(id, categoryName));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allCategories;
    }

    public List<Shoe> getAllShoes(){
        List<Shoe> allShoes = new ArrayList<>();
        List<Label> allLabels = getAllLabels();
        List<Price> allPrices = getAllPrices();
        List<Name> allNames = getAllNames();
        List<Size> allSizes = getAllSizes();
        try(
                Connection connection = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from order")
        ) {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int labelId = resultSet.getInt("Label_id");
                int PriceId = resultSet.getInt("Price_id");
                int NameId = resultSet.getInt("Name_id");
                int SizeId = resultSet.getInt("Size_id");
                int shoeStock = resultSet.getInt("shoe_stock");
                Label label = allLabels.stream().filter(s->s.id==labelId).findFirst().orElse(null);
                Price price = allPrices.stream().filter(s->s.id==PriceId).findFirst().orElse(null);
                Name name = allNames.stream().filter(s->s.id==NameId).findFirst().orElse(null);
                Size size = allSizes.stream().filter(s->s.id==SizeId).findFirst().orElse(null);
                allShoes.add(new Shoe(id, label,price, name, size, shoeStock));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allShoes;
    }

    public List<Rating> getAllRatings(){
        List<Rating> allRating = new ArrayList<>();
        List<Rating_alternatives> allRatingAlternatives = getAllRatingAlternatives();
        List<Shoe> allShoes = getAllShoes();
        List<Customer> allCustomers = getAllCustomers();
        try(
                Connection connection = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from rating")
        ) {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String comment = resultSet.getString("Comment");
                int ratingAlternativesId = resultSet.getInt("RatingAlternatives_id");
                int shoeId = resultSet.getInt("Shoe_id");
                int customerId = resultSet.getInt("Customer_id");
                Rating_alternatives ra = allRatingAlternatives.stream().filter(s->s.id==ratingAlternativesId).findFirst().orElse(null);
                Shoe shoe = allShoes.stream().filter(s->s.id==shoeId).findFirst().orElse(null);
                Customer customer = allCustomers.stream().filter(s->s.id==customerId).findFirst().orElse(null);
                allRating.add(new Rating(id, comment, ra, shoe, customer));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allRating;
    }

    public String addRatingToDatabase (Rating rating){
        int rowChanged = 0;
        try (
                Connection connection = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                PreparedStatement statement = connection.prepareStatement("insert into rating (Comment, RatingAlternatives_id, Shoe_id, Customer_id) values (?,?,?,?)");

        ) {
            statement.setString(1, rating.getComment());
            statement.setInt(2, rating.getRatingAlternatives().getId());
            statement.setInt(3, rating.getShoe().getId());
            statement.setInt(4, rating.getCustomer().getId());

            rowChanged = statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rating.getComment() + " har tillagt in databasen" + "\n" + rowChanged;
    }

    public double getAverageRating(Shoe shoe){
        double result = 0;

        try (
                Connection connection = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                CallableStatement statement = connection.prepareCall("select average_rate (?) as avg");
        ) {
            statement.setInt(1, shoe.getId());
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































}

