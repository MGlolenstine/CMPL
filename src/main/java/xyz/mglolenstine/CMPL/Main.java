package xyz.mglolenstine.CMPL;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    /*public static void Main(String[] args) throws Exception{
        if (args.length != 2) {
            System.err.println("USAGE: CurseDownloader* manifest.json output-directory");
            System.exit(-1);
        }
        CurseManifest mf = CurseManifest.fromFile(new File(args[0]));
        new Downloader(new File(args[1])).download(mf);
        new GUI().start(new Stage());
    }*/
    private CurseManifest mf;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Curseforge Modpack Launcher");
        TextField installLoc = new TextField();
        installLoc.setPromptText("Absolute path to installation directory");
        installLoc.setFocusTraversable(false);
        installLoc.setPrefWidth(350);
        TextField json = new TextField();
        json.setPromptText("Absolute path to modpack's json");
        json.setFocusTraversable(false);
        json.setPrefWidth(350);
        Text error = new Text();
        error.setStyle("-fx-text-inner-color: red;");
        Button btn = new Button();
        btn.setText("Submit");
        btn.setPrefWidth(350);
        btn.setOnAction(event -> {
            try {
                mf = CurseManifest.fromFile(new File(json.getText()));
            } catch (IOException e) {
                System.out.println("Json file doesn't exist on that path.");
                error.setText("Json file doesn't exist on that path.");
                System.exit(-1);
            }
            try {
                new Downloader(new File(installLoc.getText())).download(mf);
            } catch (IOException e) {
                System.out.println("Installation directory doesn't exist.");
                error.setText("Installation directory doesn't exist.");
                System.exit(-1);
            }
        });
        //Grid setup
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        // End grid setup
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        grid.add(installLoc, 0,0);
        grid.add(json, 0,1);
        grid.add(btn, 0, 2);
        grid.add(error, 0, 3);
        primaryStage.show();
    }
}
