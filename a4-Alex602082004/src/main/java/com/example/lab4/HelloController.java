package com.example.lab4;

import Domain.Comanda;
import Domain.ComandaStr;
import Domain.Tort;
import Exceptions.RepoException;
import Repos.SqlRepoComanda;
import Repos.SqlRepoTort;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

public class HelloController {
    public Label M;
    public Label I;
    public Label S;
    public Label O;
    public Label m;
    public Label N;
    public Label labelId;
    public TextField textFieldId;
    public Label labelName;
    public TextField textFieldName;
    public Button buttonAdd;
    ;
    public Label labelIdComanda;
    public TextField textFieldIdComanda;
    public TextField textFieldNameComanda;
    public Label labelDataComanda;
    public TextField textFieldDataComanda;
    public Button buttonAddComanda;
    public Button buttonDel;
    public Label labelIdDel;
    public TextField textFieldIdDel;
    public Label labelIdUpdate;
    public TextField textFieldIdUpdate;
    public Button buttonUpdate;
    public TextField textFieldTorturiUpdate;
    public Label LabelIdDelComanda;
    public TextField TextFieldidDelComanda;
    public Button ButtonDelComanda;
    public Label labelIdUpdateComanda;
    public TextField textFieldIdUpdateComanda;
    public Button buttonUpdateComanda;
    public TextField textFieldCakeTypeUpdate;
    public Label labelCakeTypeUpdate;
    public Label labelTorturiAddComanda;
    public Label labelTorturiComandaUpdate;
    public Label labelUpDateComanda;
    public TextField textUpDateComanda;


    @FXML
    private Label welcomeText;

    public ObservableList<Tort> listTort;
    private SqlRepoTort repotort;
    public ListView<Tort> listViewTorturi;

    public ObservableList<ComandaStr> listComanda;
    private SqlRepoComanda repocomanda;
    public ListView<ComandaStr> listViewComanda;


    public void init(SqlRepoTort repotort, SqlRepoComanda repocomanda) {
        this.repotort = repotort;
        this.repocomanda = repocomanda;
        listTort = FXCollections.observableList(new ArrayList<>(repotort.getTorturi()));
        listViewTorturi.setItems(listTort);
        listComanda = FXCollections.observableArrayList(new ArrayList<>(repocomanda.getComenzistr()));
        listViewComanda.setItems(listComanda);

    }

    public void onAddTortButtonClick(ActionEvent actionEvent) {

        try {

            var tortid = Integer.parseInt(textFieldId.getText());
            var torttype = textFieldName.getText();
            Tort tort = new Tort(tortid, torttype);
            repotort.add(tort);
            listTort.add(tort);
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        } catch (RepoException e) {
            Alert hopa = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            hopa.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            textFieldId.clear();
            textFieldName.clear();
        }
    }

    public void onDelTortButtonClick(ActionEvent actionEvent) {
        var tortid = Integer.parseInt(textFieldIdDel.getText());
        try {
            repotort.remove(tortid);
            listTort = FXCollections.observableList(new ArrayList<>(repotort.getTorturi()));
        } catch (RepoException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        } finally {
            textFieldIdDel.clear();
        }
    }

    public void onUpTortButtonClick(ActionEvent actionEvent) {
        try {
            int tortid = Integer.parseInt(textFieldIdUpdate.getText());
            String CakeType = textFieldCakeTypeUpdate.getText();
            var tort = new Tort(tortid, CakeType);

            repotort.remove(tortid);


            repotort.add(tort);
            listTort = FXCollections.observableList(new ArrayList<>(repotort.getTorturi()));
        } catch (RepoException | IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        } finally {
            textFieldIdUpdate.clear();
            textFieldCakeTypeUpdate.clear();
        }
    }

    public void onAddComandaButtonClick(ActionEvent actionEvent) {
        var comandaid = Integer.parseInt(textFieldIdComanda.getText());
        var torturi = textFieldNameComanda.getText();
        var data = Date.valueOf(textFieldDataComanda.getText());
        try {
            ComandaStr comandaStr = new ComandaStr(comandaid, torturi, data);
            repocomanda.addstr(comandaStr);
            listComanda.add(comandaStr);
        } catch (IOException | RepoException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        } finally {
            textFieldIdComanda.clear();
            textFieldNameComanda.clear();
            textFieldDataComanda.clear();
        }
    }

    public void onDelComandaButtonClick(ActionEvent actionEvent) {
        var comandaid = Integer.parseInt(TextFieldidDelComanda.getText());
        try {
            repocomanda.remove(comandaid);
            listComanda = FXCollections.observableArrayList(new ArrayList<>(repocomanda.getComenzistr()));
        } catch (RepoException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        } finally {
            TextFieldidDelComanda.clear();
        }
    }

    public void onUpComandaButtonClick(ActionEvent actionEvent) {
        var comandaid = Integer.parseInt(textFieldIdUpdateComanda.getText());
        var torturi = textFieldTorturiUpdate.getText();
        var date = Date.valueOf(textUpDateComanda.getText());
        try {
            ComandaStr comandastr = new ComandaStr(comandaid, torturi, date);
            repocomanda.remove(comandaid);
            listComanda = FXCollections.observableArrayList(new ArrayList<>(repocomanda.getComenzistr()));

            repocomanda.addstr(comandastr);
            listComanda = FXCollections.observableArrayList(new ArrayList<>(repocomanda.getComenzistr()));
        } catch (RepoException | IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        } finally {
            textFieldIdUpdateComanda.clear();
            textFieldTorturiUpdate.clear();
            textUpDateComanda.clear();
        }

    }
}