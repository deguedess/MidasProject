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
import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author NU92585
 * @param <T>
 */
public abstract class AbstractListagem<T> {

    @FXML
    public Button buttoNew;
    @FXML
    public Button buttoEditar;
    @FXML
    public Button buttoExcluir;
    @FXML
    public Button buttoExportar;
    @FXML
    public Button buttoFechar;
    @FXML
    private Button buttoPesquisar;
    @FXML
    private Tab tabInicio;
    @FXML
    private Tab tabPesquisar;
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

    public Tab getTabRoot() {
        return tabRoot;
    }

    public void setTabRoot(Tab tabRoot) {
        this.tabRoot = tabRoot;
    }

    public TabPane getTbPane() {
        return tbPane;
    }

    public void setTbPane(TabPane tbPane) {
        this.tbPane = tbPane;
    }

    protected void doubleClickTable(boolean temPerm) {
        if (!temPerm) {
            return;
        }
        items.setOnMouseClicked((MouseEvent mouseEvent) -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                if (mouseEvent.getClickCount() == 2) {
                    handleEditItem();
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

    protected abstract void verifyPermission();

    public abstract void initialize();

    protected void internacionalizar(TextLabel... text) {

//        GlyphFont fontAwesome = GlyphFontRegistry.font("FontAwesome");
//
//        buttoNew.setGraphic(fontAwesome.create(FontAwesome.Glyph.PLUS).size(16));
//        buttoEditar.setGraphic(fontAwesome.create(FontAwesome.Glyph.EDIT));
//        buttoExcluir.setGraphic(fontAwesome.create(FontAwesome.Glyph.REMOVE));
//        buttoExportar.setGraphic(fontAwesome.create(FontAwesome.Glyph.FILE_PDF_ALT));
//        buttoFechar.setGraphic(fontAwesome.create(FontAwesome.Glyph.CLOSE));
//        buttoPesquisar.setGraphic(fontAwesome.create(FontAwesome.Glyph.SEARCH));
        Locale l = SessionUtil.getIdioma();

        buttoNew.setText(ResourceLabels.getInfo(LabelEnum.novo, l));
        if (buttoEditar != null) {
            buttoEditar.setText(ResourceLabels.getInfo(LabelEnum.editar, l));
        }
        if (buttoExcluir != null) {
            buttoExcluir.setText(ResourceLabels.getInfo(LabelEnum.excluir, l));
        }
        buttoExportar.setText(ResourceLabels.getInfo(LabelEnum.exportar, l));
        buttoFechar.setText(ResourceLabels.getInfo(LabelEnum.fechar, l));
        tabInicio.setText(ResourceLabels.getInfo(LabelEnum.inicio, l));
        tabPesquisar.setText(ResourceLabels.getInfo(LabelEnum.pesquisar, l));
        buttoPesquisar.setText(ResourceLabels.getInfo(LabelEnum.pesquisar, l));
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

    public abstract void handleNewItem();

    public abstract void handleEditItem();

    public abstract void handleDeleteItem();

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

    protected boolean askToDelete(LabelEnum label, boolean inativa) {
        if (inativa) {
            return FXUtil.pergunta(ResourceMessages.getMessage(MessageEnum.PERGUNTAINATIVACAOHEADER, SessionUtil.getIdioma(),
                    ResourceLabels.getInfo(label, SessionUtil.getIdioma())),
                    ResourceMessages.getMessage(MessageEnum.PERGUNTAINATIVACAO, SessionUtil.getIdioma()));
        } else {
            return FXUtil.pergunta(ResourceMessages.getMessage(MessageEnum.PERGUNTAREATIVARHEADER, SessionUtil.getIdioma(),
                    ResourceLabels.getInfo(label, SessionUtil.getIdioma())),
                    ResourceMessages.getMessage(MessageEnum.PERGUNTAREATIVAR, SessionUtil.getIdioma()));
        }
    }

    protected void messageAlreadyRemoved(LabelEnum label) {
        FXUtil.alertaFX(Alert.AlertType.WARNING, ResourceMessages.getMessage(MessageEnum.ValidaObjetoJaInativadoHeader, SessionUtil.getIdioma(), ResourceLabels.getInfo(label, SessionUtil.getIdioma())),
                ResourceMessages.getMessage(MessageEnum.ValidaObjetoJaInativado, SessionUtil.getIdioma(), ResourceLabels.getInfo(label, SessionUtil.getIdioma())));

    }

    protected void errorDelete(Exception ex) {
        Logger.getLogger(AbstractListagem.class.getName()).log(Level.SEVERE, null, ex);
        FXUtil.alertaFX(Alert.AlertType.WARNING, ResourceMessages.getMessage(MessageEnum.ERRODELETE, SessionUtil.getIdioma()), ex.toString());
    }

    protected void erroExport(Exception e) {
        Logger.getLogger(AbstractListagem.class.getName()).log(Level.SEVERE, null, e);
        FXUtil.alertaFX(Alert.AlertType.ERROR, ResourceMessages.getMessage(MessageEnum.ERROEXPORTAR, SessionUtil.getIdioma()), e.toString());
    }

    public MidasP getMainApp() {
        return mainApp;
    }

    public void setMainApp(MidasP mainApp) {
        this.mainApp = mainApp;
    }

    protected void addClearebleFields(CustomTextField... tx) {
        try {
            for (CustomTextField tx1 : tx) {
                setupClearButtonField(tx1);
            }
        } catch (Exception ex) {
            Logger.getLogger(AbstractListagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void setupClearButtonField(CustomTextField customTextField) throws Exception {
        Method m = TextFields.class.getDeclaredMethod("setupClearButtonField", TextField.class, ObjectProperty.class);
        m.setAccessible(true);
        m.invoke(null, customTextField, customTextField.rightProperty());
    }

}
