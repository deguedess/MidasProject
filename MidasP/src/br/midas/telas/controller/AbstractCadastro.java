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
import br.midas.dao.enumerations.TipoCadastro;
import br.midas.main.MidasP;
import br.midas.util.util.BindingUtil;
import br.midas.util.util.FXUtil;
import br.midas.util.util.InternacionalizaUtil;
import br.midas.util.util.MaskTextField;
import br.midas.util.util.SessionUtil;
import br.midas.util.util.TextLabel;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.Notifications;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.GlyphFontRegistry;
import org.controlsfx.samples.HelloNotificationPane;

/**
 *
 * @author NU92585
 * @param <T>
 */
public abstract class AbstractCadastro<T> {

    protected Stage dialogStage;
    protected TipoCadastro tipoCad;
    protected BindingUtil bu;

    @FXML
    public Button botaoSalvar;
    @FXML
    public Button botaoCancel;
    @FXML
    public Tab tabGeral;
    @FXML
    public TabPane tabPane;
    @FXML
    public Label titulo;
    @FXML
    public Label labelErros;

    protected NotificationPane notificationPane;
    protected Notifications notificationBuilder;
    protected Tab tabRoot;
    protected TabPane tbPane;
    protected BorderPane borderRoot;

    public void setBorderRoot(BorderPane borderRoot) {
        this.borderRoot = borderRoot;
        criaNotificationWarning();
        criaNotificationOK();
    }

    private void criaNotificationWarning() {
        ImageView image = new ImageView(HelloNotificationPane.class.getResource("notification-pane-warning.png").toExternalForm());
        notificationPane = new NotificationPane(borderRoot.getCenter());
        notificationPane.setGraphic(image);
        notificationPane.getStyleClass().add(NotificationPane.STYLE_CLASS_DARK);
        borderRoot.setCenter(notificationPane);

        notificationPane.setShowFromTop(false);
    }

    private void criaNotificationOK() {
        notificationBuilder = Notifications.create()
                .hideAfter(Duration.seconds(2))
                .position(Pos.BOTTOM_RIGHT);

    }

    protected boolean okClicked = false;

    protected MidasP mainApp;

    abstract void iniciaBinding();

    public void setEntity(T entity, TipoCadastro tipo) {
        iniciaBinding();
    }

    abstract boolean isInputValid();

    @FXML
    protected void handleCancel() {
        String text = null;
        if (tipoCad.equals(TipoCadastro.Novo)) {
            text = ResourceMessages.getMessage(MessageEnum.cadastroCancelado, SessionUtil.getIdioma());
        } else if (tipoCad.equals(TipoCadastro.Editar)) {
            text = ResourceMessages.getMessage(MessageEnum.edicaoCancelada, SessionUtil.getIdioma());
        }
        notificationBuilder.text(text);
        notificationBuilder.showInformation();
        tbPane.getTabs().remove(tabRoot);
    }

    private void fechaDialogOK() {
        okClicked = true;
        tbPane.getTabs().remove(tabRoot);
    }

    protected void hideMessageBinding() {
        notificationPane.hide();
    }

    protected void showMessage() {
        String text;
        if (tipoCad.equals(TipoCadastro.Novo)) {
            text = ResourceMessages.getMessage(MessageEnum.cadastroFeito, SessionUtil.getIdioma());
        } else if (tipoCad.equals(TipoCadastro.Visualizar)) {
            text = null;
        } else {
            text = ResourceMessages.getMessage(MessageEnum.edicaoFeita, SessionUtil.getIdioma());
        }

        if (text != null) {
            notificationBuilder.text(text);
            notificationBuilder.showInformation();
        }
        fechaDialogOK();
    }

    abstract void populeEntity();

    abstract void handleOk();

    protected boolean validaMaskString(MaskTextField tx, LabelEnum lbl, int min,
            int max, boolean empty, boolean nullo, String maskOriginal) {
        return bu.validaMaskString(tx, ResourceLabels.getInfo(lbl, SessionUtil.getIdioma()),
                min, max, empty, nullo, maskOriginal);
    }

    protected boolean validaCampoString(TextField tx, LabelEnum lbl, int min, int max, boolean empty, boolean nullo) {
        return bu.validaCampoString(tx, ResourceLabels.getInfo(lbl, SessionUtil.getIdioma()), min, max, empty, nullo);
    }

    protected boolean validaCampoDouble(TextField tf, LabelEnum lbl, boolean nullo, Boolean canBeZero) {
        return bu.validaCampoDouble(tf, ResourceLabels.getInfo(lbl, SessionUtil.getIdioma()), nullo, canBeZero);
    }

    protected boolean validaCampoString(TextArea tx, LabelEnum lbl, int min, int max, boolean empty, boolean nullo) {
        return bu.validaCampo(tx, ResourceLabels.getInfo(lbl, SessionUtil.getIdioma()), min, max, empty, nullo);
    }

    protected boolean validaCampoInt(TextField tx, LabelEnum lbl, boolean nullo) {
        return bu.validaCampoInteger(tx, ResourceLabels.getInfo(lbl, SessionUtil.getIdioma()), nullo);
    }

    protected boolean validaCombo(ComboBox combo, boolean nullo, LabelEnum campo) {
        return bu.validaCombo(combo, nullo, campo);
    }

    protected boolean validaData(DatePicker dc, boolean nullo, LabelEnum campo) {
        return bu.validaData(dc, nullo, campo);
    }

    protected boolean isOnlyRead() {
        if (this.tipoCad.equals(TipoCadastro.Visualizar)) {
            okClicked = true;
            dialogStage.close();
            return true;
        }
        return false;
    }

    protected String getTexto(TextField tf) {
        if (tf.getText() == null) {
            return "";
        }
        return tf.getText().trim().toUpperCase();
    }

    protected Integer getInt(TextField tf) {
        if (tf.getText() == null) {
            return null;
        }
        return Integer.valueOf(tf.getText().trim());
    }

    protected String getTextoSemple(TextField tf) {
        if (tf.getText() == null) {
            return "";
        }
        return tf.getText().trim();
    }

    protected String getTextoSemple(TextArea tf) {
        if (tf.getText() == null) {
            return "";
        }
        return tf.getText().trim();
    }

    protected String getTexto(TextArea tf) {
        if (tf.getText() == null) {
            return "";
        }
        return tf.getText().trim().toUpperCase();
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

    protected void internacionalizar(LabelEnum lbTitulo, TextLabel... text) {

        botaoSalvar.setGraphic(GlyphFontRegistry.font("FontAwesome").create(FontAwesome.Glyph.CHECK_SQUARE_ALT));
        botaoCancel.setGraphic(GlyphFontRegistry.font("FontAwesome").create(FontAwesome.Glyph.TIMES));

        Locale l = SessionUtil.getIdioma();

        botaoCancel.setText(ResourceLabels.getInfo(LabelEnum.cancelar, l));
        botaoSalvar.setText(ResourceLabels.getInfo(LabelEnum.salvar, l));
        tabGeral.setText(ResourceLabels.getInfo(LabelEnum.geral, l));
        titulo.setText(ResourceLabels.getInfo(lbTitulo, l));

        new InternacionalizaUtil().internacioalizaLabel(text);

    }

    protected void errorEditing(Exception ex) {
        Logger.getLogger(AbstractCadastro.class.getName()).log(Level.SEVERE, null, ex);
        FXUtil.alertaFX(Alert.AlertType.WARNING, ResourceMessages.getMessage(MessageEnum.ERROEDICAO, SessionUtil.getIdioma()), ex.toString());
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setOkClicked(boolean okClicked) {
        this.okClicked = okClicked;
    }

    public MidasP getMainApp() {
        return mainApp;
    }

    public void setMainApp(MidasP mainApp) {
        this.mainApp = mainApp;
    }

    protected void messageAnyItemSelected() {
        FXUtil.alertaFX(Alert.AlertType.WARNING, ResourceMessages.getMessage(MessageEnum.VALIDANENHUMITEMSELECIONADOHEADER, SessionUtil.getIdioma()),
                ResourceMessages.getMessage(MessageEnum.VALIDANENHUMITEMSELECIONADO, SessionUtil.getIdioma()));
    }

    protected boolean askToDelete(LabelEnum label) {
        return FXUtil.pergunta(ResourceMessages.getMessage(MessageEnum.PERGUNTAINATIVACAOHEADER, SessionUtil.getIdioma(),
                ResourceLabels.getInfo(label, SessionUtil.getIdioma())),
                ResourceMessages.getMessage(MessageEnum.PERGUNTAINATIVACAO, SessionUtil.getIdioma()));
    }

    protected void errorDelete(Exception ex) {
        Logger.getLogger(AbstractCadastro.class.getName()).log(Level.SEVERE, null, ex);
        FXUtil.alertaFX(Alert.AlertType.WARNING, ResourceMessages.getMessage(MessageEnum.ERRODELETE, SessionUtil.getIdioma()), ex.toString());
    }

    protected void errorShow(Exception ex) {
        Logger.getLogger(AbstractCadastro.class.getName()).log(Level.SEVERE, null, ex);
        FXUtil.alertaFX(Alert.AlertType.WARNING, ResourceMessages.getMessage(MessageEnum.ERROVISUALIZA, SessionUtil.getIdioma()), ex.toString());
    }

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
}
