package sample;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    Repository r = new Repository();
    Customer customer = null;

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

    /*@FXML
    private ChoiceBox<String> categoryBox;

    @FXML
    private ChoiceBox<String> labelBox;

    @FXML
    private ChoiceBox<String> colorBox;

    @FXML
    private ChoiceBox<String> sizeBox;

    @FXML
    private Button searchButton;*/

    @FXML
    private Pane yourCartPane;

    @FXML
    private HBox showCartBox;

    @FXML
    private Pane availableShoesPane;

    @FXML
    private HBox showAvShoesBox;

    @FXML
    private Button addToCartButton;

    @FXML
    private Pane productAddedToCartPane;

    @FXML
    private Text addedToCartText;

    @FXML
    private ImageView cartImage;

    @FXML
    void addToCartAction(ActionEvent event) {
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        productAddedToCartPane.setVisible(true);
        pause.setOnFinished(e -> productAddedToCartPane.setVisible(false));
        pause.play();
    }

    @FXML
    void cartAction(MouseEvent event) {
        availableShoesPane.setVisible(false);
        yourCartPane.setVisible(true);
    }

   /* @FXML
    void logInAction(ActionEvent event) {
        wrongUserName.setVisible(false);
        wrongPassword.setVisible(false);
        boolean username = r.checkUsername(userNameText.getText());
        boolean password = r.checkPassword(PasswordText.getText(), userNameText.getText());
        System.out.println(username + " " + password);

        if(!username)
            wrongUserName.setVisible(true);
        else if (!password)
            wrongPassword.setVisible(true);
        else {
            customer = r.getCustomer(userNameText.getText());
            LogInPane.setVisible(false);
            chooseShoesPane.setVisible(true);
            firtsLastNameText.setText(customer.firstName + " " + customer.lastName);

        }
    }*/

    @FXML
    public void goBackAction(ActionEvent actionEvent) {
        availableShoesPane.setVisible(true);
        yourCartPane.setVisible(false);
    }


    /*@FXML
    void searchAction(ActionEvent event) {
       *//* String category = categoryBox.getValue();
        String Size = sizeBox.getValue();
        String label = labelBox.getValue();
        String color = colorBox.getValue();*//*

        yourCartPane.setVisible(false);
        availableShoesPane.setVisible(true);
    }
*/
    public void addRateAction(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // setChoiseBoxes();
        Image cartIm = new Image("cartImage.png");
        cartImage.setImage(cartIm);
    }

    public void addShoesToView(List<Shoe> shoeList){
        for (Shoe s: shoeList) {
            TextField t = new TextField();
            //t.appendText(s.);
            //Platform.runLater(() -> {
                showAvShoesBox.getChildren().add(t);
            //});

        }
    }




    /*public void setChoiseBoxes(){
        categoryBox.getItems().add("Alla");
        categoryBox.getItems().addAll(r.getCatList());
        categoryBox.setValue("Alla");
        sizeBox.getItems().add("Alla");
        sizeBox.getItems().addAll(r.getSizeList());
        sizeBox.setValue("Alla");
        labelBox.getItems().add("Alla");
        labelBox.getItems().addAll(r.getLabelList());
        labelBox.setValue("Alla");
        colorBox.getItems().add("Alla");
        colorBox.getItems().addAll(r.getColorList());
        colorBox.setValue("Alla");
    }*/
}

