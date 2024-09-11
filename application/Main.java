package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;

import DatePackage.DatePane;
import DatePackage.HashTable;
import MartyrScreen.Martyr;
import MartyrScreen.MartyrNode;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {
	static HashTable hst=new HashTable();
	BorderPane root;
	static private TabPane tabpane=new TabPane();
	static private Tab tab1, tab2;
	private FileChooser fileCh;
	private File file;
	private boolean isRead=false, isSaved=false;
	@Override
	public void start(Stage primaryStage) {
		try {
			root=new BorderPane();

			Tab tab1=new Tab("Date Screen");
			Tab tab2=new Tab("Martyr Screen");

			tab1.setDisable(true);
			tab2.setDisable(true);

			HBox hbox=new HBox();
			Label label=new Label("Choose File to Start");
			label.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,50));
			hbox.getChildren().addAll(label);
			hbox.setPadding(new Insets(0,0,100,0));
			hbox.setAlignment(Pos.CENTER);


			root.setBackground(new Background(new BackgroundImage(new Image("img//background.jpg"),BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.CENTER,
					BackgroundSize.DEFAULT)));

			root.setCenter(new ImageView(new Image("img//map.png")));
			root.setBottom(hbox);
			//tab1.setContent(a);

			tab1.setClosable(false);
			tab2.setClosable(false);

			MenuBar bar=new MenuBar();
			Menu menu=new Menu("File");
			bar.getMenus().addAll(menu);

			MenuItem open=new MenuItem("Open");
			open.setGraphic(new ImageView(new Image("img\\import.png",20,20,false,false)));
			MenuItem save=new MenuItem("Save");
			save.setGraphic(new ImageView(new Image("img\\save.png",20,20,false,false)));
			MenuItem newWindow=new MenuItem("New");
			newWindow.setGraphic(new ImageView(new Image("img\\newWindow.png",20,20,false,false)));

			menu.getItems().addAll(open,save,newWindow);

			save.setDisable(true);

			open.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					fileCh=new FileChooser();
					file=fileCh.showOpenDialog(primaryStage);	

					if(file==null) {
						file=new File("");
						tab1.setDisable(true);
						tab2.setDisable(true);
					}
					else {
						isRead=true;
						open.setDisable(true);
						tab1.setDisable(false);
						tab2.setDisable(false);
						save.setDisable(false);

						try {
							read();
						} catch (Exception e) {
							label.setText("Choose CSV File");
							System.exit(0);
						}

						tabpane.tabMinWidthProperty().bind(root.widthProperty().divide(2));

						tab1.setStyle("-fx-background-color:transparent;");
						tab2.setStyle("-fx-background-color:transparent;");


						tabpane.getTabs().addAll(tab1,tab2);

						tab1.setStyle("-fx-background-color:#a2a2a2");
						tab2.setStyle("-fx-background-color:transparent");

						tabpane.setStyle("-fx-background-color:#a2a2a2");

						DatePane dp=new DatePane(hst, hst.getFirstData().getData());
						tab1.setContent(dp.getRoot());
						tab2.setContent(dp.getMs().getRoot());

						tab2.setOnSelectionChanged(new EventHandler<Event>() {
							@Override
							public void handle(Event arg0) {
								if(tab2.isSelected()) {
									tab1.setStyle("-fx-background-color:transparent");
									tab2.setStyle("-fx-background-color:#a2a2a2");
									dp.getMs().getListView().getItems().clear();
									dp.getMs().getListView().setItems(FXCollections.observableArrayList(hst.getDates()));
									dp.getMs().getListView().getSelectionModel().select(dp.getDateName().getText());
									dp.getMs().getDateName().setText(dp.getDateName().getText());

								}
							}
						});

						tab1.setOnSelectionChanged(new EventHandler<Event>() {
							@Override
							public void handle(Event arg0) {
								if(tab1.isSelected()) {
									tab1.setStyle("-fx-background-color:#a2a2a2");
									tab2.setStyle("-fx-background-color:transparent");
								}
							}
						});

						root.setCenter(tabpane);
						root.setBottom(null);
					}
				}

			});

			save.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					FileChooser fsave=new FileChooser();
					File f=fsave.showSaveDialog(primaryStage);
					if(f!=null) {
						isSaved=true;
						SaveFile(f);
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("File Saved");
						alert.setHeaderText("File Saved Correctly");
						alert.setContentText(f.getPath());
					}
				}
			});

			newWindow.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					primaryStage.close();
					start(new Stage());
				}
			});

			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent arg0) {
					if(isSaved==false && isRead) {
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Please Confirm");
						alert.setHeaderText("Save File");
						alert.setContentText("If you leave without saveing you may lose your data");

						Optional<ButtonType> result = alert.showAndWait();
						if (result.isPresent() && result.get() == ButtonType.OK) {
							primaryStage.close();
						}
						if(result.isPresent() && result.get() == ButtonType.CANCEL) {
							arg0.consume();
						}
					}
				}
			});

			root.setTop(bar);
			Scene scene=new Scene(root,1200,600);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.getIcons().add(new Image("img//map.png",30,30,false,false));
			primaryStage.setTitle("Data Structure Phase3 _ AbdAlrheem Yaseen _ 1220783");

		} catch(Exception e) {
			e.printStackTrace();
		}
	}



	public void read() throws FileNotFoundException {
		Scanner input =new Scanner(file);

		input.nextLine();
		while(input.hasNextLine()) {
			String line=input.nextLine();
			String []tokens=line.split(",");

			if(tokens[2].equals("")) {
				tokens[2]="0";
			}
			Martyr martyr =new Martyr(tokens[0], tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4], tokens[5]);
			int Dtemp=hst.search(tokens[1]);
			if(Dtemp==-1) {
				hst.insert(tokens[1]);

				if(hst.getHash()[hst.search(tokens[1])].getmAvl().find(martyr)==null) {
					hst.getHash()[hst.search(tokens[1])].getmAvl().insert(martyr);
				}
			}
			else {
				hst.getHash()[hst.search(tokens[1])].getmAvl().insert(martyr);
			}
		}


	}

	public void SaveFile(File file) {
		for (int i = 0; i < hst.getHash().length; i++) {
			if (hst.getHash()[i].getState() == 'F') {
				SaveMartyr(hst.getHash()[i].getmAvl().getRoot(),file);
			}
		}
	}

	private void SaveMartyr(MartyrNode root,File file){
		try (PrintWriter write =new PrintWriter(new FileWriter(file, true));){
			if (root != null){
				SaveMartyr(root.getLeft(),file);
				write.println(root.getData().toString());
				SaveMartyr(root.getRight(),file);
			}
		}
		catch (Exception s) {

		}

	}

	public static void main(String[] args) {
		launch(args);
	}


	public static void selectTab2() {
		tabpane.getSelectionModel().select(tab2);
	}

}
