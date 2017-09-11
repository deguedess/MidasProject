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
import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 *
 * @author NU92585
 */
public class FXUtil {

    public static boolean ret;
    public static String decimal =  ",";

    public static void alertaFX(Alert.AlertType tipo, String header, String message) {
        Alert alert = new Alert(tipo);
        alert.setTitle(retTitleByTipo(tipo));
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.show();
    }

    public static ExtensionFilter getFiltroImagens() {
        return new ExtensionFilter(
                "Image files", "*.jpg;*.JPG;*.png;*.PNG");
    }

    public static ExtensionFilter getFiltroPDF() {
        return new ExtensionFilter(
                "PDF files", "*.pdf;*.PDF");
    }
    
    public static ExtensionFilter getFiltroExcel() {
        return new ExtensionFilter(
                "Excel files", "*.xls;*.XLS");
    }

//    public static LocalDate montaDataVencimento(OrdemCompra ordemCompra) {
//
//        if (ordemCompra == null
//                || ordemCompra.getDiaVencimento() == null
//                || ordemCompra.getDiaVencimento() == 0) {
//            return null;
//        }
//
//        Date date = new Date();
//        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//        int day = ordemCompra.getDiaVencimento();
//
//        if (day == 31) {
//            day = 30;
//        }
//
//        if (localDate.getMonthValue() == 2) {
//            day = 28;
//        }
//        
//        localDate = localDate.withDayOfMonth(day);
//
//        return localDate;
//    }

    public static boolean pergunta(String header, String msg) {
        Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btnSim = new ButtonType(ResourceLabels.getInfo(LabelEnum.sim, SessionUtil.getIdioma()));
        ButtonType btnNao = new ButtonType(ResourceLabels.getInfo(LabelEnum.nao, SessionUtil.getIdioma()));

        dialogoExe.setTitle(ResourceMessages.getMessage(MessageEnum.sistemaPrecisaConfirmacao, SessionUtil.getIdioma()));
        dialogoExe.setHeaderText(header);
        dialogoExe.setContentText(msg);
        dialogoExe.getButtonTypes().setAll(btnSim, btnNao);
        dialogoExe.showAndWait().ifPresent((ButtonType b) -> {
            ret = b == btnSim;
        });

        return ret;
    }

//    public static StringConverter getDataConverter() {
//        StringConverter<LocalDate> sv = new StringConverter<LocalDate>() {
//
//            @Override
//            public String toString(LocalDate object) {
//                try {
//                    return DataUtil.dateToStringFormat(FXUtil.localDateToDate(object),
//                            SessionUtil.getConfiguracoes().getMascaraData().getFormat());
//                } catch (DateException ex) {
//                    Logger.getLogger(FXUtil.class.getName()).log(Level.SEVERE, null, ex);
//                    return null;
//                }
//            }
//
//            @Override
//            public LocalDate fromString(String string) {
//                try {
//                    return FXUtil.dateToLocalDate(DataUtil.stringToDate(string,
//                            SessionUtil.getConfiguracoes().getMascaraData().getFormat()));
//                } catch (ParseException | DateException ex) {
//                    Logger.getLogger(FXUtil.class.getName()).log(Level.SEVERE, null, ex);
//                    return null;
//                }
//            }
//        };
//
//        return sv;
//    }

    private static String retTitleByTipo(Alert.AlertType tipo) {
        switch (tipo) {
            case CONFIRMATION:
                return ResourceMessages.getMessage(MessageEnum.sistemaPrecisaConfirmacao, SessionUtil.getIdioma());
            case ERROR:
                return ResourceMessages.getMessage(MessageEnum.tituloErro, SessionUtil.getIdioma());
            case INFORMATION:
                return ResourceMessages.getMessage(MessageEnum.tituloInformacao, SessionUtil.getIdioma());
            case WARNING:
                return ResourceMessages.getMessage(MessageEnum.tituloAtencao, SessionUtil.getIdioma());
            default:
                return ResourceMessages.getMessage(MessageEnum.tituloDefault, SessionUtil.getIdioma());
        }
    }

    public static void requestFocus(TextField tf) {
        //focus textfield
        Platform.runLater(() -> {
            tf.requestFocus();
        });
    }

    public static void requestFocus(ComboBox tf) {
        //focus textfield
        Platform.runLater(() -> {
            tf.requestFocus();
        });
    }

    public static void requestFocus(DatePicker tf) {
        //focus textfield
        Platform.runLater(() -> {
            tf.requestFocus();
        });
    }

    public static String getCaminhoTemp() {
        File f = new File("C:\\temp");

        if (f.isFile()) {
            f.delete();
        }

        if (!f.exists()) {
            f.mkdir();
        }

        return f.getAbsolutePath() + "\\" + System.currentTimeMillis();
    }

    public static boolean isEmailValid(String email) {

        if (email == null || email.isEmpty()) {
            return true;
        }

        String EMAIL_PATTERN
                = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

    /**
     * Devido ao incremento dos caracteres das mascaras eh necessario que o
     * cursor sempre se posicione no final da string.
     *
     * @param textField TextField
     */
    private static void positionCaret(final TextField textField) {
        Platform.runLater(() -> {
            // Posiciona o cursor sempre a direita.
            textField.positionCaret(textField.getText().length());
        });
    }

    /**
     * @param textField TextField.
     * @param length Tamanho do campo.
     */
    private static void maxField(final TextField textField, final Integer length) {
        textField.textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
            if (newValue != null && newValue.length() > length) {
                textField.setText(oldValue);
            }
        });
    }

    public static void cnpjField(final TextField textField) {
        maxField(textField, 18);

        textField.lengthProperty().addListener((ObservableValue<? extends Number> observableValue,
                Number number, Number number2) -> {
            String value = textField.getText();

            value = value.replaceAll("[^0-9]", "");
            value = value.replaceFirst("(\\d{2})(\\d)", "$1.$2");
            value = value.replaceFirst("(\\d{2})\\.(\\d{3})(\\d)", "$1.$2.$3");
            value = value.replaceFirst("\\.(\\d{3})(\\d)", ".$1/$2");
            value = value.replaceFirst("(\\d{4})(\\d)", "$1-$2");

            textField.setText(value);
            positionCaret(textField);
        });

    }

    public static void cpfCnpjField(final TextField textField) {

        textField.focusedProperty().addListener((ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean fieldChange) -> {
            String value = textField.getText();
            if (!fieldChange) {
                if (textField.getText() != null && textField.getText().length() == 11) {
                    value = value.replaceAll("[^0-9]", "");
                    value = value.replaceFirst("([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})$", "$1.$2.$3-$4");
                }
                if (textField.getText() != null && textField.getText().length() == 14) {
                    value = value.replaceAll("[^0-9]", "");
                    value = value.replaceFirst("([0-9]{2})([0-9]{3})([0-9]{3})([0-9]{4})([0-9]{2})$", "$1.$2.$3/$4-$5");
                }
            }
            textField.setText(value);
            if (textField.getText() != null && !textField.getText().equals(value)) {
                textField.setText("");
                textField.insertText(0, value);
            }
        });

        maxField(textField, 18);
    }

    public static void formatterCEP(final TextField t) {
        t.textProperty().addListener((ObservableValue<? extends String> ov, String antigo, String novo) -> {
            if (novo != null && !novo.isEmpty() && antigo != null && novo.length() > antigo.length()) {
                try {
                    switch (novo.length()) {
                        case 2:
                            t.setText(novo + ".");
                            Platform.runLater(() -> {
                                t.end();
                            });

                            break;
                        case 6:
                            t.setText(novo + "-");
                            Platform.runLater(() -> {
                                t.end();
                            });

                            break;
                        case 11:
                            t.setText(antigo);
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            }
        });
    }

    public static void numericField(final TextField textField, char excpetion1, char ex2) {
        textField.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                char ch = textField.getText().charAt(oldValue.intValue());
                if (!(ch >= '0' && ch <= '9') && (excpetion1 != ch) && (ex2 != ch)) {
                    textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
                }
            }
        });
    }

    /**
     * Monta a mascara para Moeda.
     *
     * @param textField TextField
     */
    public static void monetaryField(final TextField textField) {
        textField.setAlignment(Pos.CENTER_RIGHT);
        textField.lengthProperty().addListener(
                (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                    String value = textField.getText();
                    value = value.replaceAll("[^0-9]", "");
                    value = value.replaceAll("([0-9]{1})([0-9]{14})$", "$1.$2");
                    value = value.replaceAll("([0-9]{1})([0-9]{11})$", "$1.$2");
                    value = value.replaceAll("([0-9]{1})([0-9]{8})$", "$1.$2");
                    value = value.replaceAll("([0-9]{1})([0-9]{5})$", "$1.$2");
                    value = value.replaceAll("([0-9]{1})([0-9]{2})$", "$1" + decimal + "$2");
                    //se o tamanho foir mmaior, atualiza normal
                    if (newValue.doubleValue() > oldValue.doubleValue()) {
                        textField.setText(value);
                    } else if (newValue.doubleValue() != 2
                    && newValue.doubleValue() != 3
                    && newValue.doubleValue() != 6
                    && newValue.doubleValue() != 7
                    && newValue.doubleValue() != 10
                    && newValue.doubleValue() != 11
                    && newValue.doubleValue() != 14
                    && newValue.doubleValue() != 15) {
                        textField.setText(value);
                    }
                    positionCaret(textField);

                    textField.textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue1, String newValue1) -> {
                        if (newValue1.length() > 17) {
                            textField.setText(oldValue1);
                        }
                    });
                });
        textField.focusedProperty().addListener((ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean fieldChange) -> {
            if (!fieldChange) {
                final int length = textField.getText().length();
                if (length > 0 && length < 3) {
                    textField.setText(textField.getText() + "00");
                }
            }
        });
    }

    public static ObservableList<String> getUFS() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("AC");
        list.add("AL");
        list.add("AM");
        list.add("AP");
        list.add("BA");
        list.add("CE");
        list.add("DF");
        list.add("ES");
        list.add("GO");
        list.add("MA");
        list.add("MG");
        list.add("MS");
        list.add("MT");
        list.add("PA");
        list.add("PB");
        list.add("PE");
        list.add("PI");
        list.add("PR");
        list.add("RJ");
        list.add("RN");
        list.add("RO");
        list.add("RR");
        list.add("RS");
        list.add("SC");
        list.add("SE");
        list.add("SP");
        list.add("TO");

        return list;
    }

    public static Date localDateToDate(LocalDate local) {
        if (local == null) {
            return null;
        }
        return Date.from(local.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date dateWithTime(LocalDate local) {
        Date dt = localDateToDate(local);
        dt.setTime(System.currentTimeMillis());
        return dt;
    }

    public static LocalDate dateToLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        Instant instant = Instant.ofEpochMilli(date.getTime());
        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

    public static LocalTime dateToLocalTime(Date date) {
        if (date == null) {
            return null;
        }
        //convert to calendar
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

//convert the date to a local time
        Instant instant = Instant.ofEpochMilli(cal.getTimeInMillis());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();

    }
}
