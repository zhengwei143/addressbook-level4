package seedu.saveit.ui;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AutoSuggester {
    private static ContextMenu entriesPopUp = new ContextMenu();
    private static TreeSet<String> entries = new TreeSet<>();
    private static String[] keyWords = {"add", "select", "delete", "find", "list", "edit", "find", "refactor", "retrieve",
            "issue", "description", "remark", "solution", "solutionLink", "tag", "exit"};

    public static void showSuggestion(TextField textField, String firstChar) {
        addAllKeyWord();
        fillResult(textField, firstChar);
        entriesPopUp.show(textField, Side.BOTTOM, (double)textField.getCaretPosition()*8, 0);
    }

    private static void fillResult(TextField textField, String firstChar) {
        String mainText = textField.getText();
        String text;
        (mainText += firstChar).trim();
        int whiteSpaceIndex =  mainText.lastIndexOf(" ");
        int slashIndex = mainText.lastIndexOf("/");
        if (whiteSpaceIndex != -1 || slashIndex != -1) {
            if (whiteSpaceIndex > slashIndex) {
                text = mainText.substring(whiteSpaceIndex, mainText.length()).trim();
            } else {
                text = mainText.substring(slashIndex+1, mainText.length()).trim();
            }
        } else {
            text = mainText.trim();
        }
        LinkedList<String> searchResult = new LinkedList<>();
        List<CustomMenuItem> menuItems = new LinkedList<>();
        searchResult.addAll(entries.subSet(text, text + Character.MAX_VALUE));
        int maxEntries = 10;
        int count = Math.min(searchResult.size(), maxEntries);
        for (int i = 0; i < count; i++) {
            final String result = searchResult.get(i);
            final String previsouText = mainText;
            Label entryLabel = new Label(result);
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            item.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent) {
                    textField.setText(previsouText + result);
                    entriesPopUp.hide();
                }
            });
            menuItems.add(item);
        }
        entriesPopUp.getItems().clear();
        entriesPopUp.getItems().addAll(menuItems);
    }

    private static void addAllKeyWord () {
        for (String str : keyWords) {
            entries.add(str);
        }
    }
}
