package sample;

import java.sql.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

public class Repository {

    Properties p = new Properties();
    List<Community> communities = new ArrayList<>();
    List<Label> labels = new ArrayList<>();
    List<Color> colors = new ArrayList<>();
    List<Price> prices = new ArrayList<>();
    List<Size> sizes = new ArrayList<>();
    List<Name> names = new ArrayList<>();
    List<Category> categories = new ArrayList<>();
    List<Rating_alternatives> rating_alternatives = new ArrayList<>();

    public Repository(){
        try {
            p.load(new FileInputStream("src/main/java/sample/settings.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("name"),
                p.getProperty("password"));
            PreparedStatement community_stmt = con.prepareStatement("select * from community");
            PreparedStatement label_stmt = con.prepareStatement("select * from label");
            PreparedStatement color_stmt = con.prepareStatement("select * from color");
            PreparedStatement price_stmt = con.prepareStatement("select * from price");
            PreparedStatement size_stmt = con.prepareStatement("select * from size");
            PreparedStatement name_stmt = con.prepareStatement("select * from name");
            PreparedStatement category_stmt = con.prepareStatement("select * from category");
            PreparedStatement ratingAlt_stmt = con.prepareStatement("select * from rating_alternatives")) {

            ResultSet rs = community_stmt.executeQuery();
            while(rs.next()){
                communities.add(new Community(rs.getInt("id"), rs.getString("com_name")));
            }
            rs = label_stmt.executeQuery();
            while(rs.next()){
                labels.add(new Label(rs.getInt("id"), rs.getString("label_name")));
            }
            rs = color_stmt.executeQuery();
            while(rs.next()){
                colors.add(new Color(rs.getInt("id"), rs.getString("col_name")));
            }
            rs = price_stmt.executeQuery();
            while(rs.next()){
                prices.add(new Price(rs.getInt("id"), rs.getInt("value")));
            }
            rs = size_stmt.executeQuery();
            while(rs.next()){
                sizes.add(new Size(rs.getInt("id"), rs.getString("size_name")));
            }
            rs = name_stmt.executeQuery();
            while(rs.next()){
                names.add(new Name(rs.getInt("id"), rs.getString("shoe_name")));
            }
            rs = category_stmt.executeQuery();
            while (rs.next()){
                categories.add(new Category(rs.getInt("id"), rs.getString("cat_name")));
            }
            rs = ratingAlt_stmt.executeQuery();
            while(rs.next()){
                rating_alternatives.add(new Rating_alternatives(rs.getInt("id"), rs.getString("options"),
                        rs.getInt("rating_numbers")));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Shoe> getCategoryandColor(String category, String color){
        List<Shoe> temp = new ArrayList<>();
        if(category.equals("Alla") && color.equals("Alla"))
            return getAllShoes();
            try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("name"),
                    p.getProperty("password"));
                PreparedStatement stmt1 = con.prepareStatement(
                        "select * from shoe " +
                            "join divide_into_category " +
                            "on shoe.id = divide_into_category.Shoe_id " +
                            "join category " +
                            "on category.id = divide_into_category.category_id " +
                            "join shoe_color " +
                            "on shoe_color.Shoe_id = shoe.id " +
                            "join color " +
                            "on color.id = shoe_color.color_id " +
                            "where category.cat_name = ? and color.col_name = ? ");
                PreparedStatement stmt2 = con.prepareStatement(
                        "select * from shoe " +
                            "join divide_into_category " +
                            "on shoe.id = divide_into_category.Shoe_id " +
                            "join category " +
                            "on category.id = divide_into_category.category_id " +
                            "where category.cat_name = ?");
                PreparedStatement stmt3 = con.prepareStatement(
                        "select * from shoe " +
                            "join shoe_color " +
                            "on shoe_color.Shoe_id = shoe.id " +
                            "join color " +
                            "on color.id = shoe_color.color_id " +
                            " where color.col_name = ?")){

                ResultSet rs = null;

                if(!category.equals("Alla") && !color.equals("Alla")) {
                    stmt1.setString(1, category);
                    stmt1.setString(2, color);
                    rs = stmt1.executeQuery();

                }
                else if(!category.equals("Alla")) {
                    stmt2.setString(1, category);
                    rs = stmt2.executeQuery();
                }
                else {
                    stmt3.setString(1, color);
                    rs = stmt3.executeQuery();
                }

                while(rs!=null && rs.next()){
                    temp.add(new Shoe(rs.getInt("id"), getLabel(rs.getInt("label_id")),
                            getPrice(rs.getInt("price_id")), getName(rs.getInt("name_Id")),
                            getSize(rs.getInt("size_id")), rs.getInt("shoe_stock")));
                }
                return temp;

            } catch(SQLException e){
                e.printStackTrace();
            }

        return temp;
    }


    public boolean checkUsername(String name){
        boolean temp = false;
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("name"),
                        p.getProperty("password"));
            PreparedStatement stmt = con.prepareStatement("select * from customer where user_name = ?");){
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String tempName = rs.getString("user_name");
                if (tempName.equalsIgnoreCase(name))
                    temp = true;
            }
        }
        catch (SQLException e ){
            System.out.println("SQL exception connection till databas");
            e.printStackTrace();
        }
        return temp;
    }

    public boolean checkPassword(String password , String name){
        boolean temp = false;
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("name"),
                p.getProperty("password"));
            PreparedStatement stmt = con.prepareStatement("select * from customer where user_name = ?")){

            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                String tempPassword = rs.getString("Password");
                if(tempPassword.equals(password))
                    temp = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public Community getCommunity(int id){
        Community temp = null;
        for (Community c : communities) {
            if(c.id == id){
                temp = c;
            }
        }
        return temp;
    }

    public Label getLabel(int id){
        Label temp = null;
        for (Label l : labels){
            if(l.id == id){
                temp = l;
            }
        }
        return temp;
    }

    public Color getColor(int id){
        Color temp = null;
        for (Color l : colors){
            if(l.id == id){
                temp = l;
            }
        }
        return temp;
    }

    public Size getSize(int id){
        Size temp = null;
        for (Size l : sizes){
            if(l.id == id){
                temp = l;
            }
        }
        return temp;
    }

    public Price getPrice(int id){
        Price temp = null;
        for (Price l : prices){
            if(l.id == id){
                temp = l;
            }
        }
        return temp;
    }

    public Name getName(int id){
        //return names.stream().filter(e -> e.getId()==id).collect();
        Name temp = null;
        for (Name l : names){
            if(l.id == id){
                temp = l;
            }
        }
        return temp;
    }

    public List<String> getRatingStrings(){
        List<String> temp = new ArrayList<>();
        rating_alternatives.forEach(e -> temp.add(e.options));
        return temp;
    }

    public int getLatestOrder(String userName){
        int orderIdTemp = 0;
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("name"),
                p.getProperty("password"));
            CallableStatement stmt = con.prepareCall("call getLatestOrderId(?,?)")){

            stmt.setString(1, userName);
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.execute();
            orderIdTemp = stmt.getInt(2);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return orderIdTemp;
    }

    public Customer getCustomer(String userName){
        Customer temp = null;

        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("name"),
                p.getProperty("password"));
            PreparedStatement pstm = con.prepareStatement("Select * from Customer where User_name = ?")){
            pstm.setString(1, userName);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                int counrtyId = rs.getInt("community_id");

                temp = new Customer(rs.getInt("id"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("user_name"),
                        rs.getString("password"), rs.getString("adress"),
                        getCommunity(rs.getInt("community_id")));
            }
        }
        catch(SQLException e){
            System.out.println("SQL exception vid select");
            e.printStackTrace();
        }
        return temp;
    }

    public List<Shoe> getAllShoes(){
        List<Shoe> temp = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("name"),
                p.getProperty("password"));
            PreparedStatement pstm = con.prepareStatement("Select * from Shoe")) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Shoe shoe = new Shoe(rs.getInt("id"),getLabel(rs.getInt("label_id")),
                        getPrice(rs.getInt("price_id")),getName(rs.getInt("name_Id")),
                        getSize(rs.getInt("size_id")), rs.getInt("shoe_stock"));
                temp.add(shoe);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return temp;
    }

    public List<Shoe> getOrderedShoes(String userName){
        List<Shoe> allShoes = getAllShoes();
        List<Shoe> temp = new ArrayList<>();
        int orderId = getLatestOrder(userName);
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("name"),
                p.getProperty("password"));
            PreparedStatement pstm = con.prepareStatement("Select * from Order_includes where orders_id = ?")) {
            pstm.setInt(1,orderId);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                for(Shoe s : allShoes){
                    if(s.id == rs.getInt("shoe_id"))
                        temp.add(s);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return temp;
    }

    public String addToCart(int customerId, int shoeId,  int orderId){
        String message = "";
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("name"),
                p.getProperty("password"));
            CallableStatement pstm = con.prepareCall("call AddToCart(?,?,?)") ) {

            pstm.setInt(1, customerId);
            pstm.setInt(2, shoeId);
            pstm.setInt(3, orderId);

            ResultSet rs = pstm.executeQuery();
            while (rs != null && rs.next()) {
                message = rs.getString("message");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return e.getMessage();
        }
        return message;
    }


    public static void main(String[] args) {
        Repository r = new Repository();
        int test = r.getLatestOrder("MussePigg");
        System.out.println(test);

        List<Shoe> shoeList = r.getCategoryandColor("Sandals", "Black");
        shoeList.forEach(e -> System.out.println(e.labelId.getLabelName()));
    }
}
