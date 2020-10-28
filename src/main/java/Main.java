import java.io.IOException;
import java.net.Socket;
import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Main extends Application {
    static ArrayList<WorkDay> workDay = new  ArrayList<WorkDay>();
    public Main() {
    }
    public Stage primaryStage;
    public BorderPane rootLayout;
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Energy");
        initRootLayout();
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    Thread myThread;
    BufferedReader reader;
    ObjectInputStream ois;
    PrintWriter writer;
    ObjectOutputStream oos;
    Socket sock;
    public void go(){
        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();
    }
    public void setUpNetworking(String a){
        try{
            sock = new Socket(a, 5000);
            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            ois = new ObjectInputStream(sock.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(sock.getOutputStream());
            oos = new ObjectOutputStream(sock.getOutputStream());
        }
        catch(IOException ex){
            showError(ex);
        }
    }
    public class IncomingReader implements  Runnable{
        public void run(){
            Object message;
            try{
                while ((message = ois.readObject())!= null){
                    String mes;
                    mes = (String) message;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            showAlertWithHeaderText(mes);
                        }
                    });
                }
            }
            catch(Exception ex){
                showError(ex);
            }
        }
    }
    private void showAlertWithHeaderText(String mes) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message from director");
        alert.setHeaderText("Message:");
        alert.setContentText(mes);
        alert.showAndWait();
    }
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
}
