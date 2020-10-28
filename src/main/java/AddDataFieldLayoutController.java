import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class AddDataFieldLayoutController {
    int numbField = 0;
    Stage stage;
    private Main mainApp;
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    private TextField Tf11second;
    private double sTf11second = 0;
    @FXML
    private TextField Tf21second;
    private double sTf21second = 0;
    @FXML
    private TextField Tf31second;
    private double sTf31second = 0;
    @FXML
    private TextField Tf41second;
    private double sTf41second = 0;
    @FXML
    private TextField Tf71second;
    private double sTf71second = 0;
    @FXML
    private Button buttonApply;
    @FXML
    private void clickSumm(ActionEvent event) {
        checkEmptyField();
        Tf71second.setText(Double.toString(sTf11second + sTf21second + sTf31second + sTf41second ));
    }
    @FXML
    private void clickApply(ActionEvent event) {
        try {
            checkEmptyField();
            WorkDay currentDay = new WorkDay();
            if (numbField == 1) {
                currentDay.setFirstEnSourse(sTf11second);
                currentDay.setSecondEnSourse(sTf21second);
                currentDay.setThirdEnSourse(sTf31second);
                currentDay.setFourthEnSourse(sTf41second);
                currentDay.setMainEnergy(true);
                mainApp.oos.writeObject(currentDay);
                mainApp.writer.flush();
                closeStage();
            } else if (numbField == 2) {
                currentDay.setCurrBatteryFirstPart(sTf11second);
                currentDay.setCurrBatterySecondPart(sTf21second);
                currentDay.setCurrBatteryThirdPart(sTf31second);
                currentDay.setCurrBatteryFourthPart(sTf41second);
                currentDay.setGreenEnergy(true);
                mainApp.oos.writeObject(currentDay);
                mainApp.writer.flush();
                closeStage();
            }  else {
                getEmptyError();
            }
        } catch (Exception ex) {
            ex.printStackTrace();  //изменить на норм обработку ошибки
        }
    }
    public void setNumbField(int numbField) {
        this.numbField = numbField;
    }

    public int getNumbField() {
        return numbField;
    }

    public void checkEmptyField() {

        if (Tf11second.getText().toString().trim().isEmpty() != true) {
            sTf11second = Double.parseDouble(Tf11second.getText());
        }
        if (Tf21second.getText().toString().trim().isEmpty() != true) {
            sTf21second = Double.parseDouble(Tf21second.getText());
        }
        if (Tf31second.getText().toString().trim().isEmpty() != true) {
            sTf31second = Double.parseDouble(Tf31second.getText());
        }
        if (Tf41second.getText().toString().trim().isEmpty() != true) {
            sTf41second = Double.parseDouble(Tf41second.getText());
        }
    }
    public void getEmptyError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setHeaderText("This day already was changed");
        VBox dialogPaneContent = new VBox();
        alert.getDialogPane().setContent(dialogPaneContent);
        alert.showAndWait();
    }

    public void closeStage() {
        stage = (Stage) buttonApply.getScene().getWindow();
        stage.close();
    }
}
