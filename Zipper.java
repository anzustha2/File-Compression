import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.util.*;
import java.util.zip.*;
import java.io.*;
 
public final class Zipper extends Application 
{
    private Desktop desktop = Desktop.getDesktop();
 
    @Override
    public void start(final Stage stage) 
    {
        stage.setTitle("File Chooser");
 
        final FileChooser fileChooser = new FileChooser();
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        final Button openFileButton = new Button("Select File");
        final Button openDirectoryButton = new Button("Select Directory");
 
        openFileButton.setOnAction(new EventHandler<ActionEvent>() 
        {
        	@Override
            public void handle(final ActionEvent e) 
            {
            	File src = fileChooser.showOpenDialog(null);
                if (src != null)
                	createZip(src);  
                else
                    System.out.println("No File Selected.");
            }
        });
 
        openDirectoryButton.setOnAction(new EventHandler<ActionEvent>() 
        {
        	@Override
            public void handle(final ActionEvent e) 
            {
            	File src = directoryChooser.showDialog(null);
                if (src != null)
                    createZip(src);
                else
                    System.out.println("No Directory Selected.");
            }
        });
 
        final GridPane inputGridPane = new GridPane();
 
        GridPane.setConstraints(openFileButton, 0, 0);
        GridPane.setConstraints(openDirectoryButton, 1, 0);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(openFileButton, openDirectoryButton);
 
        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));
 
        stage.setScene(new Scene(rootGroup));
        stage.show();
    }
    
    private static void createZip(File src)
    {
        // TODO
        ZipFile f = new ZipFile(src);
        f.fillFileList(src);
        f.makeZip(src);
    }
 
    public static void main(String[] args) {
        Application.launch(args);
    }
}