import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ConnectLayoutController {
    private Main mainApp;
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    Stage stage;
    @FXML
    Button buttonConnect;
    @FXML
    TextField TF1;
    @FXML
    TextField TF2;
    @FXML
    PasswordField TF3;
    Passwords pass = new Passwords();
    @FXML
    public void clickConnect(ActionEvent event){
        try{
            String currLogin = TF2.getText();
            String currPass = TF3.getText();
            if (currLogin.equals(pass.mainEnergyLogin) && currPass.equals(pass.mainEnergyPass)){
                new Thread(task).start();
                closeStage();
            }else
            if (currLogin.equals(pass.greenEnergyLogin) && currPass.equals(pass.greenEnergyPass)){
                new Thread(task).start();
                closeStage();
            }else {
                getEmptyError();
            }
        }
        catch (Exception ex){
            showError(ex);
        }
    }
    Task<Integer> task = new Task<Integer>() {
        @Override protected Integer call() throws Exception {
            int iterations = 0;
            for (iterations = 0; iterations < 1; iterations++) {
                if (isCancelled()) {
                    break;
                }
                try{
                    mainApp.setUpNetworking(TF1.getText());
                    mainApp.go();
                }catch (Exception e){
                    showError(e);
                }
            }
            return iterations;
        }
        @Override protected void succeeded() {
            super.succeeded();
            stage.close();
            updateMessage("Done!");
        }
        @Override protected void cancelled() {
            super.cancelled();
            updateMessage("Cancelled!");
        }
        @Override protected void failed() {
            super.failed();
            updateMessage("Failed!");
        }
    };
    public void closeStage(){
        stage = (Stage) buttonConnect.getScene().getWindow();
        stage.close();
    }
    //Alert
    private void showError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setHeaderText(e.getMessage());
        VBox dialogPaneContent = new VBox();
        Label label = new Label("Stack Trace:");
        String stackTrace = this.getStackTrace(e);
        TextArea textArea = new TextArea();
        textArea.setText(stackTrace);
        dialogPaneContent.getChildren().addAll(label, textArea);
        alert.getDialogPane().setContent(dialogPaneContent);
        alert.showAndWait();
    }
    private String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String s = sw.toString();
        return s;
    }
    public void getEmptyError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setHeaderText("Incorrect data");
        VBox dialogPaneContent = new VBox();
        alert.getDialogPane().setContent(dialogPaneContent);
        alert.showAndWait();
    }

}
