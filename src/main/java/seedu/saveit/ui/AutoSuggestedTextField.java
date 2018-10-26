package seedu.saveit.ui;

import java.util.ArrayList;
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
import seedu.saveit.logic.Logic;
import seedu.saveit.model.Issue;

public class AutoSuggestedTextField extends TextField {

    private static ContextMenu popUpWindow;
    private static TreeSet<String> storageSet;
    private static Logic logic;
    private List<String> keyWords = new ArrayList<>();

    public AutoSuggestedTextField() {
        super();
    }

    public void initialise(Logic logic) {
        this.logic = logic;
        for (Issue issue: logic.getFilteredIssueList()) {
            this.keyWords.add(issue.getStatement().issue);
        }
        popUpWindow = new ContextMenu();
        storageSet = new TreeSet<>();
        addAllKeyWord();
        System.out.println(storageSet);
        this.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (getText().length() == 0 || !getText().contains("find")) {
                    popUpWindow.hide();
                } else {
                    showResult(AutoSuggestedTextField.this);
                }
            }
        });
    }

    public void update(Logic logic) {
        this.logic = logic;
        keyWords.clear();
        for (Issue issue: logic.getFilteredIssueList()) {
            this.keyWords.add(issue.getStatement().issue);
        }
        addAllKeyWord();
        System.out.println(storageSet);
    }

    private void showResult(TextField textField) {
        String mainText = textField.getText();
        String text;
        int whiteSpaceIndex = mainText.lastIndexOf(" ");
        int slashIndex = mainText.lastIndexOf("/");
        int startingIndex = 0;
        if (whiteSpaceIndex != -1 || slashIndex != -1) {
            if (whiteSpaceIndex > slashIndex) {
                startingIndex = whiteSpaceIndex;
            } else {
                startingIndex = slashIndex;
            }
            text = mainText.substring(startingIndex, mainText.length()).trim();
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
                int initIndex = startingIndex+1;
                item.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        textField.setText(previousText.substring(0, initIndex) + result);
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
        storageSet.clear();
        for (String str : keyWords) {
            storageSet.add(str);
        }
    }
}
