package seedu.saveit.ui;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AutoSuggestedTextField extends TextField {

    private static ContextMenu popUpWindow;
    private static TreeSet<String> storageSet;
    private String[] keyWords = {"add", "select", "delete", "find", "list", "edit", "find", "refactor",
            "retrieve", "issue", "description", "remark", "solution", "solutionLink", "tag", "exit"};

    public AutoSuggestedTextField() {
        super();
        popUpWindow = new ContextMenu();
        storageSet = new TreeSet<>();
        addAllKeyWord();
        this.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (getText().length() == 0) {
                    popUpWindow.hide();
                } else {
                    showResult(AutoSuggestedTextField.this);
//                    if (storageSet.size() > 0) {
//                        fillResult(AutoSuggestedTextField.this);
//                        if (!popUpWindow.isShowing()) {
//                            popUpWindow.show(AutoSuggestedTextField.this, Side.BOTTOM, 0, 0);
//                        }
//                    } else {
//                        popUpWindow.hide();
//                    }
                }
            }
        });
    }

    private void showResult(TextField textField) {
        String mainText = textField.getText();
        String text;
        int whiteSpaceIndex = mainText.lastIndexOf(" ");
        int slashIndex = mainText.lastIndexOf("/");
        if (whiteSpaceIndex != -1 || slashIndex != -1) {
            if (whiteSpaceIndex > slashIndex) {
                text = mainText.substring(whiteSpaceIndex, mainText.length()).trim();
            } else {
                text = mainText.substring(slashIndex + 1, mainText.length()).trim();
            }
        } else {
            text = mainText.trim();
        }
        LinkedList<String> searchResult = new LinkedList<>();
        searchResult.addAll(storageSet.subSet(text, text + Character.MAX_VALUE));
        if (storageSet.size() > 0) {
            int maxNum = 5;
            int count = Math.min(searchResult.size(), maxNum);
            List<CustomMenuItem> menuItems = new LinkedList<>();
            for (int i = 0; i < count; i++) {
                final String result = searchResult.get(i);
                final String previousText = textField.getText();
                Label entryLabel = new Label(result);
                textField.requestFocus();
                CustomMenuItem item = new CustomMenuItem(entryLabel, true);
                item.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        textField.setText(previousText + result);
                        textField.positionCaret(textField.getLength());
                        popUpWindow.hide();
                    }
                });
                menuItems.add(item);
            }
            popUpWindow.getItems().clear();
            popUpWindow.getItems().addAll(menuItems);
            popUpWindow.show(textField, Side.BOTTOM, (double) textField.getCaretPosition() * 8, 0);
        } else {
            popUpWindow.hide();
        }
    }

    private void addAllKeyWord() {
        for (String str : keyWords) {
            storageSet.add(str);
        }
    }
}
