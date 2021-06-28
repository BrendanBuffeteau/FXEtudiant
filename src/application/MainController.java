package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MainController implements Initializable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8206508429931645781L;
	@FXML
	private AnchorPane mainpane;
	@FXML
	private TabPane tabpane;
	@FXML
	private Tab logintab;
	@FXML
	private AnchorPane loginpane;
	@FXML
	private Text loginlabel;
	@FXML
	private TextField logininput;
	@FXML
	private Text mdplabel;
	@FXML
	private PasswordField passwordinput;
	@FXML
	private Button loginconfirm;

	@FXML
	private Tab notestab;
	@FXML
	private AnchorPane opepane;
	@FXML
	private Text opelabel1;
	@FXML
	private TextField opeinput1;
	@FXML
	private Text opelabel2;
	@FXML
	private TextField opeinput2;
	@FXML
	private Text opelabel3;
	@FXML
	private TextField opeinput3;
	@FXML
	private Text opelabel4;
	@FXML
	private TextField opeinput4;
	@FXML
	private Button opeconfirm;

	@FXML
	private Tab creatab;
	@FXML
	private AnchorPane creapane;
	@FXML
	private Text crealabel1;
	@FXML
	private TextField creanom;
	@FXML
	private Text crealabel2;
	@FXML
	private TextField creaprenom;
	@FXML
	private Text crealabel3;
	@FXML
	private TextField creaddn;
	@FXML
	private TextField creainput3;

	@FXML
	private Button creaconfirm;
	@FXML
	private Button creareset;

	@FXML
	private Label actuallogin;
	@FXML
	private Label currentlogin;

	@FXML
	private Tab modiftab;

	@FXML
	Tab listetab;

	@FXML
	private TableView<Map.Entry<Long, Student>> table;
	@FXML
	private ComboBox<Map.Entry<Long, Student>> combo;

	@FXML
	private TilePane photopane;
	@FXML
	private ImageView photoprev;
	@FXML
	private Button photochoix;
	@FXML
	private Button modifsave;
	@FXML
	private TextField photopath;

	@FXML
	private ComboBox<Map.Entry<Long, Student>> modifcombo;

	@FXML
	private TilePane modifphotopane;
	@FXML
	private ImageView modifphotoprev;
	@FXML
	private Button modifphotochoix;
	@FXML
	private TextField modifnom;
	@FXML
	private TextField modifprenom;
	@FXML
	private TextField modifddn;
	@FXML
	private TextField modifphotopath;
	@FXML
	private Button modifbutton;
	@FXML
	private Button modifsavebutton;

	ObservableList<Student> data;

	ObservableList<Map.Entry<Long, Student>> items;

	ObservableMap<Long, Student> datamap;

	@FXML
	private Button noteidconfirm;
	@FXML
	private TextField noteidinput;
	@FXML
	private TextField noteinput1;
	@FXML
	private TextField noteinput2;
	@FXML
	private TextField noteinput3;
	@FXML
	private TextField noteinput4;
	@FXML
	private Label noteidactuel;
	@FXML
	private Label notenometudiant;
	@FXML
	private Button noteaddnotes;

//	@FXML
//	TableColumn<Student, String> columnid;
//	@FXML
//	TableColumn<Student, String> columnnom;
//	@FXML
//	TableColumn<Student, String> columnprenom;
//	@FXML
//	TableColumn<Student, String> columnddn;
//	@FXML
//	TableColumn<Student, String> columnmodif;

	@FXML
	TableColumn<Entry<Long, Student>, String> columnid;
	@FXML
	TableColumn<Entry<Long, Student>, String> columnnom;
	@FXML
	TableColumn<Entry<Long, Student>, String> columnprenom;
	@FXML
	TableColumn<Entry<Long, Student>, String> columnddn;
	@FXML
	TableColumn<Entry<Long, Student>, String> columnmodif;
	@FXML
	TableColumn<Entry<Long, Student>, String> columnmoy;

	@FXML
	Label modifresult;
	Path temp = null;

	@FXML
	protected void browsephoto(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
		chooser.getExtensionFilters().add(imageFilter);
		File file = chooser.showOpenDialog(new Stage());
		photopath.setText(file.getAbsolutePath());
		Image img = new Image(file.toURI().toString());
		photoprev.setImage(img);
	}

	@FXML
	protected void modifBrowsephoto(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
		chooser.getExtensionFilters().add(imageFilter);
		File file = chooser.showOpenDialog(new Stage());
		modifphotopath.setText(file.getAbsolutePath());
		Image img = new Image(file.toURI().toString());
		modifphotoprev.setImage(img);
	}

	@FXML
	protected void browsephotostring(ActionEvent event) {

		File file = new File(photopath.getText());
		Image img = new Image(file.toURI().toString());
		photoprev.setImage(img);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		creatab.setDisable(true);
		notestab.setDisable(true);
		modiftab.setDisable(true);
		listetab.setDisable(true);
		noteidactuel.setVisible(false);
		currentlogin.setText("");
		DecimalFormat df = new DecimalFormat("#.##");

		String url = "E:\\Images\\517715d323d91c7a93be977398c6fd2a.png";
		File photo = new File(url);
		String url2 = "E:\\Images\\aldopng.png";
		File photo2 = new File(url2);
		Student dany = new Student("Targaryen", "Daenerys", "03/02/283", new Image(photo.toURI().toString()));
		Student aldo = new Student("Cat", "Aldo", "03/02/1983", new Image(photo2.toURI().toString()));
		dany.notes.add(16.5d);
		dany.notes.add(13.5d);

		try {
			temp = Files.createTempFile("etudiants", "ser");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		datamap = FXCollections.observableHashMap();
		datamap.put(dany.getId(), dany);
		datamap.put(aldo.getId(), aldo);

		// write(datamap,temp);
		// datamap = read(temp);

		columnid = new TableColumn<Map.Entry<Long, Student>, String>("Id");
		columnid.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Map.Entry<Long, Student>, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Entry<Long, Student>, String> p) {
						return new SimpleStringProperty(p.getValue().getKey().toString());
					}
				});

		columnnom = new TableColumn<Map.Entry<Long, Student>, String>("Nom");
		columnnom.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Map.Entry<Long, Student>, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Entry<Long, Student>, String> p) {
						return new SimpleStringProperty(p.getValue().getValue().getNom());
					}
				});

		columnprenom = new TableColumn<Map.Entry<Long, Student>, String>("Prénom");
		columnprenom.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Map.Entry<Long, Student>, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Entry<Long, Student>, String> p) {
						return new SimpleStringProperty(p.getValue().getValue().getPrenom());
					}
				});

		columnddn = new TableColumn<Map.Entry<Long, Student>, String>("Date naissance");
		columnddn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Map.Entry<Long, Student>, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Entry<Long, Student>, String> p) {
						return new SimpleStringProperty(p.getValue().getValue().getDdn());
					}
				});

		columnmoy = new TableColumn<Map.Entry<Long, Student>, String>("Moyenne de notes");
		columnmoy.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Map.Entry<Long, Student>, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Entry<Long, Student>, String> p) {
						Double moy = p.getValue().getValue().getMoyenne();
						if (moy != -1d)
							return new SimpleStringProperty(df.format(p.getValue().getValue().getMoyenne()).toString());
						else
							return new SimpleStringProperty("Aucune note");
					}
				});

		table.setRowFactory(tv -> {
			TableRow<Entry<Long, Student>> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					Entry<Long, Student> rowData = row.getItem();
					SingleSelectionModel<Tab> selectionModel = tabpane.getSelectionModel();
					selectionModel.select(modiftab);
					modifcombo.getSelectionModel().select(rowData);
					System.out.println(rowData.getKey());
				}
			});
			return row;
		});

		table.getColumns().setAll(columnid, columnnom, columnprenom, columnddn, columnmoy);
		items = FXCollections.observableArrayList(datamap.entrySet());
		table.setItems(items);

		modifcombo.setItems(items);
		modifcombo.setEditable(false);
		modifnom.setDisable(true);
		modifprenom.setDisable(true);
		modifddn.setDisable(true);
		modifphotopath.setDisable(true);
		modifbutton.setDisable(true);
		modifsavebutton.setVisible(false);
		modifphotochoix.setDisable(true);

	}

	@SuppressWarnings("unchecked")
	public void ajouterEtudiant(ActionEvent event) {
		Student nouveau = new Student(creanom.getText(), creaprenom.getText(), creaddn.getText(), photoprev.getImage());
		System.out.println(nouveau.toString() + " créé");
		datamap.put(nouveau.getId(), nouveau);
		System.out.println(nouveau.toString() + " ajouté");
		Alert dialog = new Alert(AlertType.INFORMATION);
		dialog.setTitle("Ajout étudiant");
		dialog.setHeaderText("Ajout d'étudiant réussi.");
		dialog.setContentText("Nom : " + creanom.getText() + "\n" + "Prénom : " + creaprenom.getText() + "\n"
				+ "Date de naissance : " + creaddn.getText());
		dialog.showAndWait();
		creanom.setText("");
		creaprenom.setText("");
		creaddn.setText("");
		photoprev.setImage(null);
		photopath.setText("");
		items = FXCollections.observableArrayList(datamap.entrySet());
		table.setItems(items);
		table.getColumns().setAll(columnid, columnnom, columnprenom, columnddn, columnmoy);
		modifcombo.setItems(items);

		//write(datamap, temp);
	}

	public void modifChoixEleve(ActionEvent event) {
		Long id = modifcombo.getSelectionModel().getSelectedItem().getKey();
		Student a = datamap.get(id);
		modifnom.setText(a.getNom());
		modifnom.setDisable(true);
		modifprenom.setText(a.getPrenom());
		modifprenom.setDisable(true);
		modifddn.setText(a.getDdn());
		modifddn.setDisable(true);
		modifphotoprev.setImage(a.getPhoto());
		modifphotopath.setDisable(true);
		modifphotochoix.setDisable(true);
		modifbutton.setDisable(false);
		modifphotopath.setText("");

	}

	public void modifactivate(ActionEvent event) {

		modifnom.setDisable(false);
		modifprenom.setDisable(false);
		modifddn.setDisable(false);
		modifphotopath.setDisable(false);
		modifphotochoix.setDisable(false);
		modifsavebutton.setVisible(true);
	}

	@SuppressWarnings("unchecked")
	public void modifconfirm(ActionEvent event) {
		Long id = modifcombo.getSelectionModel().getSelectedItem().getKey();
		datamap.get(id).setNom(modifnom.getText());
		datamap.get(id).setPrenom(modifprenom.getText());
		datamap.get(id).setDdn(modifddn.getText());
		datamap.get(id).setPhoto(modifphotoprev.getImage());
		
		Alert dialog = new Alert(AlertType.INFORMATION);
		dialog.setTitle("Modification étudiant");
		dialog.setHeaderText("Modification d'étudiant réussie.");
		dialog.setContentText("Nom : " + modifnom.getText() + "\n" + "Prénom : " + modifprenom.getText() + "\n"
				+ "Date de naissance : " + modifddn.getText());
		dialog.showAndWait();
		
		items = FXCollections.observableArrayList(datamap.entrySet());
		table.setItems(items);
		table.getColumns().setAll(columnid, columnnom, columnprenom, columnddn, columnmoy);
		modifcombo.setItems(items);
	}

	public void gointomodif(ActionEvent event) {
		System.out.println("test");
	}

	public void resetCrea(ActionEvent event) {
		creanom.setText("");
		creaprenom.setText("");
		creaddn.setText("");
		photoprev.setImage(null);
		photopath.setText("");
	}

	/*
	 * 
	 * private Button noteidconfirm; private TextField noteidinput; private
	 * TextField noteinput1; private TextField noteinput2; private TextField
	 * noteinput3; private TextField noteinput4; private Label noteidactuel; private
	 * Label notenometudiant; private Button noteaddnotes;
	 */

	public void noteIdConfirm(ActionEvent event) {
		Long id = Long.parseLong(noteidinput.getText());
		noteidactuel.setVisible(false);
		noteidactuel.setText(noteidinput.getText());
		notenometudiant.setText(datamap.get(id).toString());
		noteinput1.setDisable(false);
		noteinput2.setDisable(false);
		noteinput3.setDisable(false);
		noteinput4.setDisable(false);
	}

	@SuppressWarnings("unchecked")
	public void noteAjouterNotes(ActionEvent event) {
		Long id = Long.parseLong(noteidactuel.getText());

		if (noteinput1.getText() != null)
			datamap.get(id).notes.add(Double.parseDouble(noteinput1.getText()));
		if (noteinput2.getText() != null)
			datamap.get(id).notes.add(Double.parseDouble(noteinput2.getText()));
		if (noteinput3.getText() != null)
			datamap.get(id).notes.add(Double.parseDouble(noteinput3.getText()));
		if (noteinput4.getText() != null)
			datamap.get(id).notes.add(Double.parseDouble(noteinput4.getText()));
		DecimalFormat df = new DecimalFormat("#.##");
		Alert dialog = new Alert(AlertType.INFORMATION);
		dialog.setTitle("Ajout de notes");
		dialog.setHeaderText("Ajout de notes à l'étudiant : " + datamap.get(id).toString());
		dialog.setContentText("Nouvelle moyenne : " + df.format(datamap.get(id).getMoyenne()) + "/20.");
		dialog.showAndWait();

		items = FXCollections.observableArrayList(datamap.entrySet());
		table.setItems(items);
		table.getColumns().setAll(columnid, columnnom, columnprenom, columnddn, columnmoy);
		modifcombo.setItems(items);
	}

	///
	/// Login
	///

	public void login(ActionEvent event) {
		if (logininput.getText().equals("admin") && passwordinput.getText().equals("mdp")) {
			currentlogin.setText(logininput.getText());

			Alert dialog = new Alert(AlertType.INFORMATION);
			dialog.setTitle("Login");
			dialog.setHeaderText("Connexion réussie.");
			dialog.setContentText("Vous êtes maintenant connecté en tant que : " + logininput.getText());
			dialog.showAndWait();
			creatab.setDisable(false);
			notestab.setDisable(false);
			modiftab.setDisable(false);
			listetab.setDisable(false);
			logininput.setText("");
			passwordinput.setText("");
		} else if (logininput.getText().equals(null)) {
			currentlogin.setText("null!!!");
		}
	}

	public void disconnect(ActionEvent event) {
		SingleSelectionModel<Tab> selectionModel = tabpane.getSelectionModel();
		selectionModel.select(logintab);
		currentlogin.setText("-déconnecté-");
		creatab.setDisable(true);
		notestab.setDisable(true);
		modiftab.setDisable(true);
		listetab.setDisable(true);

		Alert dialog = new Alert(AlertType.INFORMATION);
		dialog.setTitle("Login");
		dialog.setHeaderText("Déconnexion.");
		dialog.setContentText("Vous êtes maintenant déconnecté.");
	}

	///
	///
	///

	public void saveListeEleves() {

		File file = new File("gestioneleves.txt");

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static ObservableList<Student> restitutionListeEleves() {

		ObservableList<Student> data = null;

		File file = new File("gestioneleves.txt");

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ObjectInputStream ois = null;

		try {
			ois = new ObjectInputStream(fis);
			try {
				data = (ObservableList<Student>) ois.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	@SuppressWarnings("unused")
	private static void write(ObservableMap<Long, Student> datamap, Path file) {
		try {
			OutputStream fos = Files.newOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(datamap.size());

			for (Entry<Long, Student> me : datamap.entrySet()) {
				oos.writeObject(me.getValue());
				System.out.println("Ecriture" + me.toString());
			}
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static ObservableMap<Long, Student> read(Path file) {

		ObservableMap<Long, Student> datamap = FXCollections.observableHashMap();

		try {
			InputStream in = Files.newInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(in);
			int a = (int) ois.readObject();

			for (int i = 0; i < a; i++) {
				Student b = (Student) ois.readObject();
				datamap.put(b.getId(), b);
			}
			return FXCollections.observableMap(datamap);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return FXCollections.emptyObservableMap();
	}
}