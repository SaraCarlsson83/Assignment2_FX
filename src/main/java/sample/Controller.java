package sample;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {


    Repository r = new Repository();
    Repository_Ivona ri = new Repository_Ivona();
    Customer customer = null;
    List<Button> shoeButtons = new ArrayList<>();
    int chosenButtonId = 0;
    Button chosenButton = null;

    @FXML
    private Pane showRatePane;

    @FXML
    private VBox seeRateCommentsBox;

    @FXML
    private Text avgRate;

    @FXML
    private Text chosenShoeText1;

    @FXML
    private Pane giveRatePane;

    @FXML
    private Text chosenShoeText;

    @FXML
    private TextArea rateCommentText;

    @FXML
    private ComboBox<String> ratingBox;

    @FXML
    private Button addRateBtn;

    @FXML
    private Button rateProductBtn;

    @FXML
    private Button seeRateBtn;

    @FXML
    private Button latestOrderBtn;

    @FXML
    private Button newOrderBtn;

    @FXML
    private Pane LogInPane;

    @FXML
    private TextField userNameText;

    @FXML
    private Text wrongUserName;

    @FXML
    private TextField PasswordText;

    @FXML
    private Text wrongPassword;

    @FXML
    private Button logInButton;

    @FXML
    private SplitPane chooseShoesPane;

    @FXML
    private Text firtsLastNameText;

    @FXML
    private Pane yourCartPane;

    @FXML
    private Pane availableShoesPane;

    @FXML
    private FlowPane showAvShoesBox;

    @FXML
    private FlowPane showCartBox;

    @FXML
    private Button addToCartButton;

    @FXML
    private Pane productAddedToCartPane;

    @FXML
    private Text addedToCartText;

    @FXML
    private ImageView cartImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image cartIm = new Image("cartImage.png");
        cartImage.setImage(cartIm);
        setDisabled();
        ratingBox.getItems().addAll(r.getRatingStrings());
    }

    public void setDisabled(){
        addToCartButton.setDisable(true);
        seeRateBtn.setDisable(true);
        rateProductBtn.setDisable(true);
    }

   @FXML
    void logInAction(ActionEvent event) {
        wrongUserName.setVisible(false);
        wrongPassword.setVisible(false);
        boolean username = r.checkUsername(userNameText.getText());
        boolean password = r.checkPassword(PasswordText.getText(), userNameText.getText());

        if(!username)
            wrongUserName.setVisible(true);
        else if (!password)
            wrongPassword.setVisible(true);
        else {
            clearAllPanes();
            customer = r.getCustomer(userNameText.getText());
            firtsLastNameText.setText(customer.firstName + " " + customer.lastName);
            List<Shoe> shoeList = r.getAllShoes();
            addShoesToView(shoeList, showAvShoesBox);
            chooseShoesPane.setVisible(true);
            availableShoesPane.setVisible(true);
        }
    }

    @FXML
    void addToCartAction(ActionEvent event) {
        setDisabled();
        chosenButton.setDisable(false);
        addedToCartText.setText("Vill du lägga till varan i din senaste order eller i en ny order?");
        latestOrderBtn.setVisible(true);
        newOrderBtn.setVisible(true);
        productAddedToCartPane.setVisible(true);
    }

    @FXML
    void cartAction(MouseEvent event) {
        rateCommentText.clear();
        showCartBox.getChildren().clear();
        addRateBtn.setDisable(false);
        clearAllPanes();
        setDisabled();
        List<Shoe> shoeList = r.getOrderedShoes(customer.userName);
        addShoesToView(shoeList, showCartBox);
        yourCartPane.setVisible(true);
    }

    @FXML
    public void goBackAction(ActionEvent actionEvent) {
        clearAllPanes();
        showCartBox.getChildren().clear();
        setDisabled();
        seeRateCommentsBox.getChildren().clear();
        if(chosenButton!=null)
            chosenButton.setDisable(false);
        availableShoesPane.setVisible(true);
        addRateBtn.setDisable(false);
    }

    @FXML
    public void addRateAction(ActionEvent actionEvent) {
        String answer = ri.setRating(rateCommentText.getText(), ratingBox.getSelectionModel().getSelectedItem(),
               chosenButtonId, customer.id);
        setDisabled();
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        addedToCartText.setText(answer);
        newOrderBtn.setVisible(false);
        latestOrderBtn.setVisible(false);
        productAddedToCartPane.setVisible(true);
        pause.setOnFinished(e -> productAddedToCartPane.setVisible(false));
        pause.play();
        chosenButton.setDisable(false);
        addRateBtn.setDisable(true);
        rateCommentText.clear();
    }

    @FXML
    public void latestOrderAction(ActionEvent actionEvent) {
        setDisabled();
        latestOrderBtn.setVisible(false);
        newOrderBtn.setVisible(false);
        int orderId = r.getLatestOrder(customer.userName);
        setOrderId(orderId);
    }

    @FXML
    public void newOrderAction(ActionEvent actionEvent) {
        setDisabled();
        latestOrderBtn.setVisible(false);
        newOrderBtn.setVisible(false);
        setOrderId(0);
    }

    @FXML
    public void seeRateAction(ActionEvent actionEvent) {
        setDisabled();
        clearAllPanes();
        double rate = ri.getAverageRating(chosenButtonId);
        List<String> commentsList = ri.getComments(chosenButtonId);
        DecimalFormat df = new DecimalFormat("#.0");
        avgRate.setText(String.valueOf(df.format(rate)));
        chosenShoeText1.setText(chosenButton.getText());
        commentsList.forEach(e -> seeRateCommentsBox.getChildren().add(new Text("\"" + e + "\"")));
        showRatePane.setVisible(true);
    }

    @FXML
    public void rateProductAction(ActionEvent actionEvent) {
        clearAllPanes();
        giveRatePane.setVisible(true);
        chosenShoeText.setText(chosenButton.getText());
    }

    @FXML
    public void logOutAction(ActionEvent actionEvent) {
        System.exit(0);
    }


    EventHandler<MouseEvent> eventHandler = new EventHandler<>() {
        @Override
        public void handle(MouseEvent e) {
            shoeButtons.forEach(i -> i.setDisable(false));
            addToCartButton.setDisable(false);
            seeRateBtn.setDisable(false);
            rateProductBtn.setDisable(false);

            shoeButtons.forEach(i -> {if(e.getSource()==i){
                i.setDisable(true);
                chosenButton = i;
                chosenButtonId = Integer.parseInt(i.getId());
                }});
        }
    };

    public void addShoesToView(List<Shoe> shoeList, FlowPane flowPane){
        for (Shoe s: shoeList) {
            Button b = new Button();
            b.setId(String.valueOf(s.id));
            b.setText(s.label.getLabelName() + " \n" + s.name.getShoeName() + "\n" +
                    s.price.getValue() + "kr\n" + "Storlek " + s.size.getSizeName()+ "\n" );
            b.setPrefSize(100,85);
            b.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
            shoeButtons.add(b);
            flowPane.getChildren().add(b);
        }
    }

    public void setOrderId (int orderId){
        String answer = r.addToCart(customer.id, chosenButtonId, orderId);
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        addedToCartText.setText(answer);
        productAddedToCartPane.setVisible(true);
        pause.setOnFinished(e -> productAddedToCartPane.setVisible(false));
        pause.play();
    }

    public void clearAllPanes(){
        showRatePane.setVisible(false);
        giveRatePane.setVisible(false);
        LogInPane.setVisible(false);
        yourCartPane.setVisible(false);
        availableShoesPane.setVisible(false);
        productAddedToCartPane.setVisible(false);

    }

}

