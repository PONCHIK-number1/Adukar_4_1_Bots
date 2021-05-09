package by.adukar.telegrambot;

import by.adukar.telegrambot.buttons.reply.ReplyButtons;
import by.adukar.telegrambot.consts.Commands;
import by.adukar.telegrambot.consts.Paths;
import by.adukar.telegrambot.consts.Photos;
import by.adukar.telegrambot.consts.Text;
import by.adukar.telegrambot.enums.Color;
import by.adukar.telegrambot.service.FileService;
import by.adukar.telegrambot.service.TextService;
import by.adukar.telegrambot.service.UserService;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;


public class Bot extends TelegramLongPollingBot {

    ReplyButtons replyButtons = new ReplyButtons();

    UserService userService = new UserService();
    TextService textService = new TextService();
    FileService fileService = new FileService();

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        sendAnswerFromBot(update);
    }

    @SneakyThrows
    public void sendAnswerFromBot(Update update) {
        Long chatId = update.getMessage().getChatId();
        if (update.getMessage().getText().equals("/start")) {
            sendMsg("что тебе надо?", chatId);
            sendMsgWithButtons("Дарова", replyButtons.keyboardMarkupForSelectStudentOrTeacher(), chatId);
        }
        if (update.getMessage().getText().equals("Отель 1")) {
            sendMsg("Место 1 \n\nТам вы найдете не только отель где можно временно снять номер, а также кучу красивых мест разрисованных художниками по графити", chatId);
            sendPhoto("https://www.holiday.by/files/byblog/555c4c751df029e88d68d9b75ae244d6-thumb-780x1500-w.jpg", chatId);
            sendPhoto("https://content.onliner.by/news/original_size/33897312cd7cf85ff45daf4b0961f6a9.jpeg", chatId);
            sendPhoto("https://easy-going.ru/wp-content/uploads/2020/07/oktyabrskaya-minsk-8.jpg", chatId);
            sendLocation(chatId, "53.8894693", "27.5772021");
            sendPoll(chatId);
        }
        if (update.getMessage().getText().equals("Отель 2")) {
            sendMsg("Место 1 \n\nОтель - фигня, не идите туда! ", chatId);
            sendPhoto("https://lh5.googleusercontent.com/p/AF1QipOBgyiF3sFWVgeqWRf9S-U00U6cGUL6whVz3lmW=w426-h240-k-no", chatId);
            sendLocation(chatId, "53.915003", "27.5505633");
            sendPoll(chatId);
        }
        if (update.getMessage().getText().equals("Отель 3")) {
            sendMsg("Место 1 \n\nкакашка!!!!", chatId);
            sendPhoto("https://lh5.googleusercontent.com/proxy/03EJWkOOFCVnQYZUu_giXhqIbjO5NxLiRaQC6EeiTRve64Q9IG6R8xShxxW_6xhm4QRffBgqHnHOm-gg3oszNRMyLt3WCzHMBRTSI0x89CKoGfityvqvuuNXKQkbW-C4EtQam5UNjacApsVO7mEyzNEFw6d4oo4=w408-h272-k-no", chatId);
            sendLocation(chatId, "53.880869", "27.516337");
            sendPoll(chatId);
        }
        if (update.getMessage().getText().equals("Место 2")) {
            sendMsg("Место 2 \n\n кароче говоря иди туда", chatId);
            sendPhoto("https://korona-city.by/images/gallery/korona-city-photo-1.JPG", chatId);
            sendPhoto("https://www.korona.by/upload/medialibrary/3b1/3b18ab4e51832b5d2ffeaea459c251fd.jpg", chatId);
            sendPhoto("https://img.tam.by/g/7/0c/d/kinoteatr_3d-kino-v-korona-siti_minsk-denisovskaya-8-2_about_4-1.jpg", chatId);
            sendLocation(chatId, "53.8715422", "27.5722101");
            sendPoll(chatId);
        }
        if (update.getMessage().getText().equals("Место 3")) {
            sendMsg("Место 2 \n\n ", chatId);
            sendPhoto("https://minsknews.by/wp-content/uploads/2021/01/LK2A8506-kopiya.jpg", chatId);
            sendLocation(chatId, "53.868499", "27.5907434");
            sendPoll(chatId);
        }


        if (update.getMessage().getText().equals("Просмотр")) {
            sendMsgWithButtons("На, смотри", replyButtons.keyboardMarkupForSelectPlace(), chatId);
        }
        if (update.getMessage().getText().equals("Назад")) {
            sendMsgWithButtons("Пшол от сюда!", replyButtons.keyboardMarkupForSelectStudentOrTeacher(), chatId);
        }
        if (update.getMessage().getText().equals("Вопрос")) {
            sendPoll(chatId);
        }
        if (update.getMessage().getText().equals("Кубик")) {
            sendDice(chatId);
        }
        if (update.getMessage().getText().equals("Телефон")) {
            sendContact(chatId);
        }

    }



    public synchronized void sendMsg(String message, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println( "Exception: " + e.toString());
        }
    }

    @SneakyThrows
    public synchronized void sendPoll(Long chatId){
        SendPoll sendPoll = new SendPoll();
        sendPoll.enableNotification();
        sendPoll.setQuestion("Понравилось ли вам это место?");
        sendPoll.setOptions(List.of("Очень понравилось!:-D", "Ну лучше, чем могло быть:-|", "Фу, ерунда:-("));
        sendPoll.setChatId(chatId);
        execute(sendPoll);
    }

    @SneakyThrows
    public synchronized void sendDice(Long chatId){
        SendDice sendDice = new SendDice();
        sendDice.setChatId(chatId);
        execute(sendDice);
    }

    public synchronized void sendContact(Long chatId) {
        SendContact sendContact = new SendContact();
        sendContact.setPhoneNumber("+375255470035");
        sendContact.setFirstName("Daniil");
        sendContact.setLastName("Pershin");
        sendContact.setChatId(chatId);
        try {
            execute(sendContact);
        } catch (TelegramApiException e) {
            System.out.println( "Exception: " + e.toString());
        }
    }

  /*  public synchronized void sendDocument(Long chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setDocument("http://www.africau.edu/images/default/sample.pdf");
        sendDocument.setCaption("Текст к документу");
        try {
            execute(sendDocument);
        } catch (TelegramApiException e) {
            System.out.println( "Exception: " + e.toString());
        }
    }*/

    public synchronized void sendLocation(Long chatId, String latitude, String longitude){
        SendLocation sendLocation = new SendLocation();
        sendLocation.setChatId(chatId);
        sendLocation.setLatitude(Float.valueOf(latitude));
        sendLocation.setLongitude(Float.valueOf(longitude));

        try {
            execute(sendLocation);
        } catch (TelegramApiException e) {
            System.out.println( "Exception: " + e.toString());
        }
    }

    @SneakyThrows
    public synchronized void sendPhoto(String pathToPhoto, Long chatId) {
        SendPhoto sendPhotoRequest = new SendPhoto();
        sendPhotoRequest.setChatId(chatId);
        sendPhotoRequest.setPhoto(pathToPhoto);
        try {
            execute(sendPhotoRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendMsgWithButtons(String message, ReplyKeyboardMarkup replyKeyboardMarkup, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println( "Exception: " + e.toString());
        }
    }



    @Override
    public String getBotUsername() {
        return "Givard228_bot";
    }

    @Override
    public String getBotToken() {
        return "1770185547:AAGG5We728TGvjKdRTPWAqu0b0qoCsRn-DE";
    }}