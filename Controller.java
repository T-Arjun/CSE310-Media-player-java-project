package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    MediaPlayer player;

    @FXML
    private MediaView mediaView;

    @FXML
    private Slider timeSlider;

    @FXML
    private Button prevBtn;

    @FXML
    private Button playBtn;

    @FXML
    private Button nextBtn;

    @FXML
    void openFileMenu(ActionEvent event) {
        try {
            FileChooser chooser = new FileChooser();
            File file = chooser.showOpenDialog(null);

            Media m = new Media(file.toURI().toURL().toString());
            player = new MediaPlayer(m);
            mediaView.setMediaPlayer(player);

            player.setOnReady(() -> {
                timeSlider.setMin(0);
                timeSlider.setMax(player.getMedia().getDuration().toMinutes());
                timeSlider.setValue(0);
            });

            player.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                Duration d = player.getCurrentTime();
                timeSlider.setValue(d.toMinutes());
            });

            timeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (timeSlider.isPressed()) {
                    double val = timeSlider.getValue();
                    player.seek(new Duration(val * 60 * 1000));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void play(ActionEvent event) {
        try {
            MediaPlayer.Status status = player.getStatus();

            if (status == MediaPlayer.Status.PLAYING) {
                player.pause();
             //   playBtn.setGraphic(new ImageView(new Image(new FileInputStream("src/icons/play.png"))));
                playBtn.setGraphic(new ImageView(new Image(new FileInputStream("E:\\eva\\eva\\src\\icons\\play.png"))));
            } else {
                player.play();
                playBtn.setGraphic(new ImageView(new Image(new FileInputStream("src/icons/pause.png"))));
//                playBtn.setGraphic(new ImageView(new Image(new FileInputStream("E:\\eva\\eva\\src\\icons\\pause.png"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void play1(ActionEvent event) {
        try {
            MediaPlayer.Status status = player.getStatus();

            if (status != MediaPlayer.Status.PLAYING) {
                player.play();
                   playBtn.setGraphic(new ImageView(new Image(new FileInputStream("src/icons/play.png"))));
//                playBtn.setGraphic(new ImageView(new Image(new FileInputStream("E:\\eva\\eva\\src\\icons\\pause.png"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void pause1(ActionEvent event) {
        try {
            MediaPlayer.Status status = player.getStatus();

            if (status == MediaPlayer.Status.PLAYING) {
                player.pause();
                   playBtn.setGraphic(new ImageView(new Image(new FileInputStream("src/icons/play.png"))));
//                 playBtn.setGraphic(new ImageView(new Image(new FileInputStream("E:\\eva\\eva\\src\\icons\\play.png"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
           playBtn.setGraphic(new ImageView(new Image(new FileInputStream("src/icons/play.png"))));
//            playBtn.setGraphic(new ImageView(new Image(new FileInputStream("E:\\eva\\eva\\src\\icons\\play.png"))));
            prevBtn.setGraphic(new ImageView(new Image(new FileInputStream("src/icons/previous.png"))));
//            prevBtn.setGraphic(new ImageView(new Image(new FileInputStream("E:\\eva\\eva\\src\\icons\\previous.png"))));
            nextBtn.setGraphic(new ImageView(new Image(new FileInputStream("src/icons/next.png"))));
//            nextBtn.setGraphic(new ImageView(new Image(new FileInputStream("E:\\eva\\eva\\src\\icons\\next.png"))));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void preBtnClick(ActionEvent event) {
        double d = player.getCurrentTime().toSeconds();
        player.seek(new Duration((d - 10) * 1000));
    }

    @FXML
    void nextBtnClick(ActionEvent event) {
        double d = player.getCurrentTime().toSeconds();
        player.seek(new Duration((d + 10) * 1000));
    }
  /*
    @FXML
    private void toggleFullscreen(ActionEvent event) {
        Stage stage = (Stage) mediaView.getScene().getWindow();

        stage.setFullScreen(!stage.isFullScreen());
    }
*/
/*
    @FXML
    private void toggleFullscreen(ActionEvent event) {
        Stage stage = (Stage) mediaView.getScene().getWindow();

        if (!stage.isFullScreen()) {
            stage.setFullScreen(true);
            mediaView.fitWidthProperty().bind(stage.widthProperty());
            mediaView.fitHeightProperty().bind(stage.heightProperty());
        } else {
            mediaView.fitWidthProperty().unbind();
            mediaView.fitHeightProperty().unbind();
            stage.setFullScreen(false);
        }
    }
*/


    @FXML
    private void toggleFullscreen(ActionEvent event) {
        Stage stage = (Stage) mediaView.getScene().getWindow();

        if (!stage.isFullScreen()) {
            stage.setFullScreen(true);
            mediaView.fitWidthProperty().bind(stage.widthProperty());
            mediaView.fitHeightProperty().bind(stage.heightProperty());
        } else {
            mediaView.fitWidthProperty().unbind();
            mediaView.fitHeightProperty().unbind();
            stage.setFullScreen(false);
            mediaView.setFitWidth(stage.getWidth());
            mediaView.setFitHeight(stage.getHeight());
        }
    }

    @FXML
    private void minimizeVideo(ActionEvent event) {
        Stage stage = (Stage) mediaView.getScene().getWindow();

        if (!stage.isIconified()) {
            stage.setIconified(true);
        } else {
            stage.setIconified(false);
        }
    }
/*
    @FXML
    private void toggleOriginalScreen(ActionEvent event) {
        Stage stage = (Stage) mediaView.getScene().getWindow();

        if (mediaView.getFitWidth() != 0 && mediaView.getFitHeight() != 0) {
            // store current size of MediaView
            double currentWidth = mediaView.getFitWidth();
            double currentHeight = mediaView.getFitHeight();

            // set MediaView to original size (100% of media size)
            mediaView.setFitWidth(0);
            mediaView.setFitHeight(0);

            // center MediaView in the stage
            double newX = (stage.getWidth() - mediaView.getBoundsInLocal().getWidth()) / 2;
            double newY = (stage.getHeight() - mediaView.getBoundsInLocal().getHeight()) / 2;
            mediaView.setLayoutX(newX);
            mediaView.setLayoutY(newY);
        } else {
            // restore MediaView to previous size and position
            mediaView.setFitWidth(stage.getWidth());
            mediaView.setFitHeight(stage.getHeight());
            mediaView.setLayoutX(0);
            mediaView.setLayoutY(0);
        }
    }
*/

    public void runYoutubeDownloader(ActionEvent actionEvent) {

        VideoDownloader obj=new VideoDownloader();

    }

    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) mediaView.getScene().getWindow();
        stage.close();
    }

    public void showAboutDialog(ActionEvent actionEvent) {
        Alert aboutDialog = new Alert(Alert.AlertType.INFORMATION);
        aboutDialog.setTitle("About");
        aboutDialog.setHeaderText(null);
        aboutDialog.setContentText("This program was created by T Arjun, Vaibhav, and Suraj. It features a media player that can play videos selected by the user. The media player has basic controls such as play, pause, previous, and next buttons. The program also includes a YouTube downloader that can download videos from YouTube. The About section has been updated to reflect these features.");
        DialogPane dialogPane = aboutDialog.getDialogPane();
        dialogPane.setPrefSize(600, 250); // set width and height in pixels
        aboutDialog.showAndWait();

    }

}
