import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class RootLayoutController {
    @FXML
    private void initialize(){    /* сперва инициализация , потом другие методы*/

    }
    private Main mainApp;
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    private Button btn1;
    @FXML
    private Button btn4;
    @FXML
    private Button btn2;
    @FXML
    private void clickConnect(ActionEvent event)  {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConnectLayout.fxml"));
            AnchorPane PSB = (AnchorPane) loader.load();
            ConnectLayoutController passController = loader.getController();
            passController.setMainApp(mainApp);
            Scene PSFScene = new Scene(PSB, 230, 100);
            Stage newWindow = new Stage();
            newWindow.setTitle("Connect");
            newWindow.setScene(PSFScene);
            newWindow.setHeight(200);
            newWindow.setWidth(350);
            newWindow.setX(mainApp.primaryStage.getX() + 200);
            newWindow.setY(mainApp.primaryStage.getY() + 100);
            newWindow.setResizable(false);
            newWindow.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }
    @FXML
    private void clickAddDataField(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PassForField.fxml"));
            AnchorPane PSF = (AnchorPane) loader.load();
            PassForFieldController passController = loader.getController();
            passController.setMainApp(mainApp);
            passController.setOperNumb(1);
            Scene PSFScene = new Scene(PSF, 230, 100);
            Stage newWindow = new Stage();
            newWindow.setTitle("Pass");
            newWindow.setScene(PSFScene);
            newWindow.setHeight(200);
            newWindow.setWidth(350);
            newWindow.setX(mainApp.primaryStage.getX() + 200);
            newWindow.setY(mainApp.primaryStage.getY() + 100);
            newWindow.setResizable(false);
            newWindow.show();
        }
        catch (Exception ex){

        }
    }
    @FXML
    private void clickMessage(ActionEvent event)  {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PassForField.fxml"));
            AnchorPane PSF = (AnchorPane) loader.load();
            PassForFieldController passController = loader.getController();
            passController.setMainApp(mainApp);
            passController.setOperNumb(2);
            Scene PSFScene = new Scene(PSF, 230, 100);
            Stage newWindow = new Stage();
            newWindow.setTitle("Pass");
            newWindow.setScene(PSFScene);
            newWindow.setHeight(200);
            newWindow.setWidth(350);
            newWindow.setX(mainApp.primaryStage.getX() + 200);
            newWindow.setY(mainApp.primaryStage.getY() + 100);
            newWindow.setResizable(false);
            newWindow.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }
}
