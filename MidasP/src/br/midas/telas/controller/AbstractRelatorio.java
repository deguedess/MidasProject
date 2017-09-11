/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.midas.telas.controller;

import br.emer.internaciolizacao.LabelEnum;
import br.emer.internaciolizacao.MessageEnum;
import br.emer.internaciolizacao.ResourceLabels;
import br.emer.internaciolizacao.ResourceMessages;
import br.midas.main.MidasP;
import br.midas.util.util.FXUtil;
import br.midas.util.util.InternacionalizaUtil;
import br.midas.util.util.SessionUtil;
import br.midas.util.util.TextLabel;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author NU92585
 */
public abstract class AbstractRelatorio<T> {

    @FXML
    private Button buttoExportar;
    @FXML
    private Button buttoFechar;
    @FXML
    private Button buttoPesquisar;
    @FXML
    private Tab tabFiltro;
    @FXML
    private Label totalItens;
    @FXML
    protected TableView<T> items;
    @FXML
    protected ObservableList<T> itensData = FXCollections.observableArrayList();

    private Tab tabRoot;
    private TabPane tbPane;
    protected Stage dialogStage;
    protected MidasP mainApp;

    public void setTabRoot(Tab tabRoot) {
        this.tabRoot = tabRoot;
    }

    public void setTbPane(TabPane tbPane) {
        this.tbPane = tbPane;
    }

    protected void doubleClickTable() {
        items.setOnMouseClicked((MouseEvent mouseEvent) -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                if (mouseEvent.getClickCount() == 2) {
                    handleShowItem();
                }
            }
        });
    }

    protected void populaComboWithNull(ComboBox combo, List values) {
        populaCombo(combo, true, values);
    }

    protected void populaComboWithoutNull(ComboBox combo, List values) {
        populaCombo(combo, false, values);
    }

    private void populaCombo(ComboBox combo, boolean addnull, List values) {
        if (addnull) {
            values.add(0, "");
        }

        combo.setItems(FXCollections.observableList(values));
    }

    public abstract void initialize();

    protected void internacionalizar(TextLabel... text) {

        Locale l = SessionUtil.getIdioma();

        buttoExportar.setText(ResourceLabels.getInfo(LabelEnum.exportar, l));
        buttoFechar.setText(ResourceLabels.getInfo(LabelEnum.fechar, l));
        tabFiltro.setText(ResourceLabels.getInfo(LabelEnum.filtro, l));
        buttoPesquisar.setText(ResourceLabels.getInfo(LabelEnum.atualizar, l));
        // atualizaTotalItem(0);

        new InternacionalizaUtil().internacioalizaLabel(text);

        //adiciona pesquisa por enter em todos texts
//        for (TextLabel t : text) {
//            if (t.getText() != null) {
//                t.getText().setOnKeyPressed((KeyEvent event) -> {
//                    if (event.getCode() == KeyCode.ENTER) {
//                        handleSearch();
//                    }
//                });
//            }
//        }
    }

    protected void atualizaTotalItem(int total) {
        totalItens.setText(ResourceLabels.getInfo(LabelEnum.totalTable, SessionUtil.getIdioma(), total));
    }

    public ObservableList<T> getTableData() {
        return itensData;
    }

    public abstract void handleShowItem();

    public void handleCancel() {
        tbPane.getTabs().remove(tabRoot);
    }

    public abstract void handleSearch();

    public abstract void handleExport();

    abstract void populateColumns();

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    protected void updateTable(List newData) {
        itensData.clear();
        itensData = FXCollections.observableArrayList(newData);

        items.setItems(itensData);
        atualizaTotalItem(itensData.size());
    }

    protected void addItemTable(T entity) {
        itensData.add(entity);
        atualizaTotalItem(itensData.size());
    }

    protected void messageAnyItemSelected() {
        FXUtil.alertaFX(Alert.AlertType.WARNING, ResourceMessages.getMessage(MessageEnum.VALIDANENHUMITEMSELECIONADOHEADER, SessionUtil.getIdioma()),
                ResourceMessages.getMessage(MessageEnum.VALIDANENHUMITEMSELECIONADO, SessionUtil.getIdioma()));
    }

    protected void erroExport(Exception e) {
        Logger.getLogger(AbstractRelatorio.class.getName()).log(Level.SEVERE, null, e);
        FXUtil.alertaFX(Alert.AlertType.ERROR, ResourceMessages.getMessage(MessageEnum.ERROEXPORTAR, SessionUtil.getIdioma()), e.toString());
    }

    public MidasP getMainApp() {
        return mainApp;
    }

    public void setMainApp(MidasP mainApp) {
        this.mainApp = mainApp;
    }

}
