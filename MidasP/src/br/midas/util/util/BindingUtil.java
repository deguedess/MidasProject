/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.midas.util.util;

import br.emer.internaciolizacao.LabelEnum;
import br.emer.internaciolizacao.MessageEnum;
import br.emer.internaciolizacao.ResourceLabels;
import br.emer.internaciolizacao.ResourceMessages;
import br.emer.utils.TextUtil;
import br.emer.validators.ValidaCampos;
import java.text.ParseException;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.NotificationPane;

/**
 *
 * @author NU92585
 */
public class BindingUtil {

    private final Button btnSalvar;
    private NotificationPane notify;
    public static char decimal =  ',';

    public BindingUtil(Button salv, NotificationPane notify) {
        this.btnSalvar = salv;
        this.notify = notify;
    }

    private void mostraNotify(String txt) {
        if (notify == null) {
            return;
        }
        notify.setText(txt);
        notify.show();
    }

    private void camposOK(TextField tf) {
        tf.setStyle(StyleUtil.setFieldVerde());
        campos();
    }

    private void camposOK(DatePicker tf) {
        tf.setStyle(StyleUtil.setFieldVerde());
        campos();
    }

    private void camposOK(TextArea tf) {
        tf.setStyle(StyleUtil.setFieldVerde());
        campos();
    }

    private void camposOK(ComboBox tf) {
        tf.setStyle(StyleUtil.setFieldVerde());
        campos();
    }

    private void campos() {
        btnSalvar.setDisable(false);
        notify.hide();
    }

    public boolean validaCampoString(TextField tf, String nome, int min, int max, boolean empty, boolean nullo) {
        try {
            //when focus lost
            ValidaCampos.validaString(min, max, empty, nullo, tf.getText(), nome, SessionUtil.getIdioma());
            camposOK(tf);
            return true;
        } catch (Exception ex) {
            mostraNotify(ex.getMessage() == null ? ex.toString() : ex.getMessage());
            tf.setStyle(StyleUtil.setFieldVermelho());
            return false;
        }
    }

    public boolean validaMaskString(MaskTextField tf, String nome, int min, int max,
            boolean empty, boolean nullo, String maskOriginal) {
        try {
            //when focus lost
            ValidaCampos.validaString(min, max, empty, nullo, tf.getText(), nome, SessionUtil.getIdioma());

            if (tf.getText() != null && !tf.getText().isEmpty()) {

                int leng = TextUtil.retiraCaracteresEspeciais(tf.getText()).trim().length();

                if ((leng == 0 && (!nullo || !empty)) || leng > 0) {
                    if (leng != TextUtil.retiraCaracteresEspeciais(maskOriginal).length()) {
                        mostraNotify(ResourceMessages.getMessage(MessageEnum.AVISOCAMPOINVALIDO, SessionUtil.getIdioma(), nome));
                        tf.setStyle(StyleUtil.setFieldVermelho());
                        return false;
                    }
                } else {
                    tf.setText(null);
                }
            }
            camposOK(tf);
            return true;
        } catch (Exception ex) {
            mostraNotify(ex.getMessage());
            tf.setStyle(StyleUtil.setFieldVermelho());
            return false;
        }
    }

    public boolean validaCampoDouble(TextField tf, String nome, boolean nullo, boolean canBeZero) {
        try {
            //when focus lost
            ValidaCampos.validaDouble(nullo, TextUtil.converteMoeda(tf.getText(), SessionUtil.getIdioma(),
                    decimal), nome, SessionUtil.getIdioma(), canBeZero);
            camposOK(tf);
            return true;
        } catch (Exception ex) {
            mostraNotify(ex.getMessage());
            tf.setStyle(StyleUtil.setFieldVermelho());
            return false;
        }
    }

    public boolean validaCampoInteger(TextField tf, String nome, boolean nullo) {
        try {
            //when focus lost
            ValidaCampos.validaInteger(nullo, tf.getText(), nome, SessionUtil.getIdioma());
            camposOK(tf);
            return true;
        } catch (Exception ex) {
            mostraNotify(ex.getMessage());
            tf.setStyle(StyleUtil.setFieldVermelho());
            return false;
        }
    }

    public boolean validaCampo(TextArea tf, String nome, int min, int max, boolean empty, boolean nullo) {
        try {
            //when focus lost
            ValidaCampos.validaString(min, max, empty, nullo, tf.getText(), nome, SessionUtil.getIdioma());
            camposOK(tf);
            return true;
        } catch (Exception ex) {
            mostraNotify(ex.getMessage());
            tf.setStyle(StyleUtil.setFieldVermelho());
            return false;
        }
    }

    public boolean validaData(DatePicker tf, boolean nullo, LabelEnum campo) {
        //when focus lost
        if (tf.getValue() == null && !nullo) {
            mostraNotify(ResourceMessages.getMessage(MessageEnum.AVISOCAMPOINVALIDO, SessionUtil.getIdioma(),
                    ResourceLabels.getInfo(campo, SessionUtil.getIdioma())));
            tf.setStyle(StyleUtil.setFieldVermelho());
            return false;
        } else {
            camposOK(tf);
            return true;
        }
    }

    public boolean validaCombo(ComboBox tf, boolean canBeNull, LabelEnum campo) {
        //when focus lost
        if (tf.getValue() == null && !canBeNull) {
            mostraNotify(ResourceMessages.getMessage(MessageEnum.AVISOSELECIONECOMBO, SessionUtil.getIdioma(), ResourceLabels.getInfo(campo, SessionUtil.getIdioma())));
            tf.setStyle(StyleUtil.setFieldVermelho());
            return false;
        }
        camposOK(tf);
        return true;
    }

    public void mensagemObjetoJaExiste(TextField textFocus) {
        mostraNotify(ResourceMessages.getMessage(MessageEnum.OBJETOJACADASTRADO, SessionUtil.getIdioma()));
        FXUtil.requestFocus(textFocus);
        textFocus.setStyle(StyleUtil.setFieldVermelho());
    }

    public void mensagemObjetoJaExiste(ComboBox textFocus) {
        mostraNotify(ResourceMessages.getMessage(MessageEnum.OBJETOJACADASTRADO, SessionUtil.getIdioma()));
        if (textFocus != null) {
            FXUtil.requestFocus(textFocus);
            // textFocus.setStyle(StyleUtil.setFieldVermelho());
        }
    }

    public void mensagemSelecioneUmItem(ComboBox textFocus) {
        mostraNotify(ResourceMessages.getMessage(MessageEnum.AVISOSELECIONARITEM, SessionUtil.getIdioma()));
        FXUtil.requestFocus(textFocus);
        textFocus.setStyle(StyleUtil.setFieldVermelho());
    }

    public void mensagemSelecioneUmItem(LabelEnum label) {
        mostraNotify(ResourceMessages.getMessage(MessageEnum.AVISOSELECIONECOMBO, SessionUtil.getIdioma(), label));
    }

    public void mensagemNenhumItem() {
        mostraNotify(ResourceMessages.getMessage(MessageEnum.MENSAGEMNENHUMITEMENCONTRADO, SessionUtil.getIdioma()));
    }

    public void mensagemErro(LabelEnum campo, TextField td) {
        mostraNotify(ResourceMessages.getMessage(MessageEnum.AVISOCAMPOINVALIDO, SessionUtil.getIdioma(), campo));
        td.setStyle(StyleUtil.setFieldVermelho());
    }

    public void mensagemErro(LabelEnum campo, DatePicker td) {
        mostraNotify(ResourceMessages.getMessage(MessageEnum.AVISOCAMPOINVALIDO, SessionUtil.getIdioma(), campo));
        td.setStyle(StyleUtil.setFieldVermelho());
    }

    public void mensagemErro(LabelEnum campo, TextField td, MessageEnum messageEnum, int ind) {
        String cp = ResourceLabels.getInfo(campo, SessionUtil.getIdioma());
        if (td != null) {
            mostraNotify(ResourceMessages.getMessage(messageEnum, SessionUtil.getIdioma(), cp));
            td.setStyle(StyleUtil.setFieldVermelho());
        } else {
            FXUtil.alertaFX(Alert.AlertType.WARNING, ResourceMessages.getMessage(messageEnum, SessionUtil.getIdioma(), cp),
                    ResourceMessages.getMessage(messageEnum, SessionUtil.getIdioma(), cp));
        }
    }

    public void mensagemErro(Throwable messageEnum) {
        mostraNotify(messageEnum.toString());
    }

    public void mensagemErro(String txt, Throwable messageEnum) {
        mostraNotify(txt + " - " + messageEnum.toString());
    }

    public void mensagemOK(String msg) {
        mostraNotify(msg);
    }

    public void mensagemErro(MessageEnum messageEnumH, MessageEnum messageEnum, int ind, Object... args) {
        FXUtil.alertaFX(Alert.AlertType.WARNING, ResourceMessages.getMessage(messageEnumH, SessionUtil.getIdioma(), args),
                ResourceMessages.getMessage(messageEnum, SessionUtil.getIdioma(), args));
    }

    public boolean mensagemConfirmacao(MessageEnum head, MessageEnum body, Object... args) {
        return FXUtil.pergunta(ResourceMessages.getMessage(head, SessionUtil.getIdioma()),
                ResourceMessages.getMessage(body, SessionUtil.getIdioma(), args));
    }

    public void mensagemErroValorMaior(LabelEnum campo1, LabelEnum campo2) {
        mostraNotify(ResourceMessages.getMessage(MessageEnum.ERROVALORMAIOR, SessionUtil.getIdioma(), campo1, campo2));
    }

    public Double converteMoeda(TextField td) {
        try {
            return TextUtil.converteMoeda(td.getText(), SessionUtil.getIdioma(),
                    decimal);
        } catch (ParseException ex) {
            mostraNotify(ex.getMessage());
            td.setStyle(StyleUtil.setFieldVermelho());
            return null;
        }
    }
}
