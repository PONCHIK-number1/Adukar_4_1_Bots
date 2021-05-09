package by.adukar.telegrambot.buttons.reply;

import by.adukar.telegrambot.consts.Paths;
import by.adukar.telegrambot.service.TextService;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.IOException;
import java.util.ArrayList;

public class ReplyButtons {

    TextService textService = new TextService();

    public ReplyKeyboardMarkup keyboardMarkupForSelectStudentOrTeacher() {

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);
        keyboardFirstRow.add("Телефон");
        keyboardFirstRow.add("Просмотр");


        keyboard.add(keyboardFirstRow);

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;

    }

    public ReplyKeyboardMarkup keyboardMarkupForSelectPlace() {

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        keyboardFirstRow.add("Отель 1");
        keyboardFirstRow.add("Отель 2");
        keyboardFirstRow.add("Отель 3");
        keyboardFirstRow.add("Место 2");
        keyboardFirstRow.add("Место 3");

        keyboardSecondRow.add("Назад");


        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;

    }
}
