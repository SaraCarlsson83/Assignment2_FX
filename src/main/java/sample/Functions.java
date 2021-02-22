package sample;

import java.util.List;

public class Functions {

    Rating rating = new Rating();
    Repository_Ivona r = new Repository_Ivona();
    List<Rating> allRatings = r.getAllRatings();


    public void setRating (int id, String comment, Rating_alternatives ratingAlternatives, Shoe shoe, Customer customer){
        rating.setId(id);
        rating.setComment(comment);
        rating.setRatingAlternatives(ratingAlternatives);
        rating.setShoe(shoe);
        rating.setCustomer(customer);
        allRatings.add(rating);
    }

    public void setRating (String comment, Rating_alternatives ratingAlternatives, Shoe shoe, Customer customer){
        rating.setComment(comment);
        rating.setRatingAlternatives(ratingAlternatives);
        rating.setShoe(shoe);
        rating.setCustomer(customer);
        allRatings.add(rating);
    }





}
