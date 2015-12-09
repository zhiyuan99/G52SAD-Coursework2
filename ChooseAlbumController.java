import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChooseAlbumController implements Initializable{
	@FXML
	private Button button;
	@FXML
	private void chooseAlbum(ActionEvent event)throws IOException {
		Parent choose_album_parent = FXMLLoader.load(getClass().getResource("ImageView.fxml"));
		Scene choose_album_scene = new Scene(choose_album_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(choose_album_scene);
		app_stage.show();
	}

@Override
public void initialize(URL url, ResourceBundle rb) {
	// TODO Auto-generated method stub
	
}

}
