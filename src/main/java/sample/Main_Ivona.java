package sample;

public class Main_Ivona {
    public static void main(String[] args) {
        Repository_Ivona r = new Repository_Ivona();
        //System.out.println(r.getAllCustomers().stream().map(Customer::getFirstName).collect(Collectors.toList()));

        Label label = r.getAllLabels().stream().filter(s->s.id==33).findFirst().orElse(null);
        Price price = r.getAllPrices().stream().filter(s->s.id==120).findFirst().orElse(null);
        Name name = r.getAllNames().stream().filter(s->s.id==160).findFirst().orElse(null);
        Size size = r.getAllSizes().stream().filter(s->s.id==150).findFirst().orElse(null);

        Shoe shoe = new Shoe(40, label, price, name, size, 4);
        //System.out.println(r.getAverageRating(shoe));
        Community community = new Community(21, "Stockholm");

        Rating_alternatives ra = new Rating_alternatives(90, "Mycket nöjd", 4);
        Customer customer = new Customer(10, "Agaton", "Sax", "AgatonSax", "AgatonSax", "Sveavägen", community);

        Rating rating = new Rating(null, ra, shoe, customer);
        r.addRatingToDatabase(rating);

    }
}
