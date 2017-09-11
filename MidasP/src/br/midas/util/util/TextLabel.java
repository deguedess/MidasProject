/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.midas.util.util;

import br.emer.internaciolizacao.LabelEnum;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeTableColumn;

/**
 *
 * @author NU92585
 */
public class TextLabel {

    private Label label;
    private LabelEnum labelEnum;
   // private TextField text;
    private TableColumn column;
    private CheckBox check;
    private Button button;
    private Tab tab;
    private TitledPane titledPane;
    private TreeTableColumn tableC;

    public TreeTableColumn getTableC() {
        return tableC;
    }

    public void setTableC(TreeTableColumn tableC) {
        this.tableC = tableC;
    }

    public TextLabel(Tab tab, LabelEnum labelEnum) {
        this.labelEnum = labelEnum;
        this.tab = tab;
    }

    public TextLabel(Label label, LabelEnum labelEnum) {
        this.label = label;
        this.labelEnum = labelEnum;
    }

//    public TextLabel(TextField text, LabelEnum labelEnum) {
//        this.labelEnum = labelEnum;
//        this.text = text;
//    }
//    
    public TextLabel(TitledPane text, LabelEnum labelEnum) {
        this.labelEnum = labelEnum;
        this.titledPane = text;
    }

    public TextLabel(TableColumn column, LabelEnum labelEnum) {
        this.labelEnum = labelEnum;
        this.column = column;
    }

    public TextLabel(Button button, LabelEnum labelEnum) {
        this.labelEnum = labelEnum;
        this.button = button;
    }

    public TextLabel(CheckBox check, LabelEnum labelEnum) {
        this.labelEnum = labelEnum;
        this.check = check;
    }
    
     public TextLabel(TreeTableColumn check, LabelEnum labelEnum) {
        this.labelEnum = labelEnum;
        this.tableC = check;
    }

    public CheckBox getCheck() {
        return check;
    }

    public void setCheck(CheckBox check) {
        this.check = check;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public LabelEnum getLabelEnum() {
        return labelEnum;
    }

    public void setLabelEnum(LabelEnum labelEnum) {
        this.labelEnum = labelEnum;
    }

    public TableColumn getColumn() {
        return column;
    }

    public void setColumn(TableColumn column) {
        this.column = column;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Tab getTab() {
        return tab;
    }

    public void setTab(Tab tab) {
        this.tab = tab;
    }

    public TitledPane getTitledPane() {
        return titledPane;
    }

    public void setTitledPane(TitledPane titledPane) {
        this.titledPane = titledPane;
    }

}
