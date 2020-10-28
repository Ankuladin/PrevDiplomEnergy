import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class PassForFieldController {
    private int operNumb=0;
    Stage stage;
    @FXML
    Button button;
    @FXML
    TextField tf1;
    @FXML
    PasswordField tf2;
    private Main mainApp;
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    public int getOperNumb() {
        return operNumb;
    }
    public void setOperNumb(int operNumb) {
        this.operNumb = operNumb;
    }
    @FXML
    private void clickOk(ActionEvent event){
        try {
            Passwords pass = new Passwords();
            String currLogin = tf1.getText().toString();
            String currPass = tf2.getText().toString();
            if (operNumb == 1) {
                if (currLogin.equals(pass.mainEnergyLogin) && currPass.equals(pass.mainEnergyPass)) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AddDataFieldLayout.fxml"));
                    AnchorPane PSF = (AnchorPane) loader.load();
                    AddDataFieldLayoutController addFieldController = loader.getController();
                    addFieldController.setMainApp(mainApp);
                    addFieldController.setNumbField(1);
                    Scene PSFScene = new Scene(PSF, 230, 100);
                    Stage newWindow = new Stage();
                    newWindow.setTitle("Add data");
                    newWindow.setScene(PSFScene);
                    newWindow.setHeight(300);
                    newWindow.setWidth(550);
                    newWindow.setX(mainApp.primaryStage.getX() + 200);
                    newWindow.setY(mainApp.primaryStage.getY() + 100);
                    newWindow.setResizable(false);
                    newWindow.show();
                    closeStage();
                } else if (currLogin.equals(pass.greenEnergyLogin) && currPass.equals(pass.greenEnergyPass)) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AddDataFieldLayout.fxml"));
                    AnchorPane PSF = (AnchorPane) loader.load();
                    AddDataFieldLayoutController addFieldController = loader.getController();
                    addFieldController.setMainApp(mainApp);
                    addFieldController.setNumbField(2);
                    Scene PSFScene = new Scene(PSF, 230, 100);
                    Stage newWindow = new Stage();
                    newWindow.setTitle("Add data");
                    newWindow.setScene(PSFScene);
                    newWindow.setHeight(300);
                    newWindow.setWidth(550);
                    newWindow.setX(mainApp.primaryStage.getX() + 200);
                    newWindow.setY(mainApp.primaryStage.getY() + 100);
                    newWindow.setResizable(false);
                    newWindow.show();
                    closeStage();
                }
            }
            else if (operNumb == 2) {
                if (currLogin.equals(pass.mainEnergyLogin) && currPass.equals(pass.mainEnergyPass)) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MessageForBossLayout.fxml"));
                    AnchorPane PSF = (AnchorPane) loader.load();
                    MessageForBossController MFBC = loader.getController();
                    MFBC.setMainApp(mainApp);
                    MFBC.setNumbField(1);
                    Scene PSFScene = new Scene(PSF, 230, 100);
                    Stage newWindow = new Stage();
                    newWindow.setTitle("Add data");
                    newWindow.setScene(PSFScene);
                    newWindow.setHeight(300);
                    newWindow.setWidth(550);
                    newWindow.setX(mainApp.primaryStage.getX() + 200);
                    newWindow.setY(mainApp.primaryStage.getY() + 100);
                    newWindow.setResizable(false);
                    newWindow.show();
                    closeStage();
                } else if (currLogin.equals(pass.greenEnergyLogin) && currPass.equals(pass.greenEnergyPass)) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MessageForBossLayout.fxml"));
                    AnchorPane PSF = (AnchorPane) loader.load();
                    MessageForBossController MFBC = loader.getController();
                    MFBC.setMainApp(mainApp);
                    MFBC.setNumbField(2);
                    Scene PSFScene = new Scene(PSF, 230, 100);
                    Stage newWindow = new Stage();
                    newWindow.setTitle("Add data");
                    newWindow.setScene(PSFScene);
                    newWindow.setHeight(300);
                    newWindow.setWidth(550);
                    newWindow.setX(mainApp.primaryStage.getX() + 200);
                    newWindow.setY(mainApp.primaryStage.getY() + 100);
                    newWindow.setResizable(false);
                    newWindow.show();
                    closeStage();
                }
            }
            else {
                getIncorrectError();
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public void getIncorrectError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setHeaderText("Incorrect login or password");
        VBox dialogPaneContent = new VBox();
        alert.getDialogPane().setContent(dialogPaneContent);
        alert.showAndWait();
    }
    public void closeStage(){
        stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
