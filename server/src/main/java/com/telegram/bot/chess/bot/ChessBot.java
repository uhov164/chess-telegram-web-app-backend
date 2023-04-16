package com.telegram.bot.chess.bot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.telegram.bot.chess.controller.GameController;
import com.telegram.bot.chess.dto.request.ConnectRequestDTO;
import com.telegram.bot.chess.model.Game;
import com.telegram.bot.chess.service.GameService;

import lombok.Getter;

@Getter
// @Component
public class ChessBot extends TelegramLongPollingBot {

    @Autowired
    GameController gameController;

    @Autowired
    GameService gameService;


    private String botUsername;
    private String botToken;
    private String gameURL;

    public ChessBot(TelegramBotsApi telegramBotsApi, @Value("${telegram-bot.name}") String botUsername
                                                   , @Value("${telegram-bot.token}") String botToken
                                                   , @Value("${app.http.frontend}") String gameURL) throws TelegramApiException {
        this.botUsername = botUsername;
        this.botToken = botToken;
        this.gameURL = gameURL;

        telegramBotsApi.registerBot(this);
    }


    private InlineKeyboardMarkup createUrlButton(String gameID) {
        var inlineKeyboardMarkup = new InlineKeyboardMarkup();

        var button = new InlineKeyboardButton();
        button.setText("url on game");
        button.setUrl("http://telegram.me/" + botUsername + "?start=" + gameID);

        var keyboard = new ArrayList<List<InlineKeyboardButton>>();
        var row = new ArrayList<InlineKeyboardButton>();

        row.add(button);
        keyboard.add(row);
        inlineKeyboardMarkup.setKeyboard(keyboard);

        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup openGameButton(String gameID, String userLogin) {
        var inlineKeyboardMarkup = new InlineKeyboardMarkup();

        var button = new InlineKeyboardButton();
        button.setText("game");

        // var webApp = new WebAppInfo(gameURL + "/" + gameID + "/" + userLogin);
        var webApp = new WebAppInfo("https://www.youtube.com/");
        button.setWebApp(webApp);

        var keyboard = new ArrayList<List<InlineKeyboardButton>>();
        var row = new ArrayList<InlineKeyboardButton>();

        row.add(button);
        keyboard.add(row);
        inlineKeyboardMarkup.setKeyboard(keyboard);

        return inlineKeyboardMarkup;
    }


    @Override
    public void onUpdateReceived(Update update) {
        try {
            var sendMessage = processCalbackQuery(update);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public SendMessage processCalbackQuery(Update update) throws Exception {

        System.out.println(update.getMessage().getText());

        var msg = new SendMessage();
        msg.setChatId(update.getMessage().getChatId());

        var text = update.getMessage().getText().split(" ");

        if (text.length > 0) {
            if (text[0].split("@").length > 1) {
                if (text[0].split("@")[1].equals(botUsername)) {
                    if (text[1].charAt(0) == '@') {
                        var playerNick = text[1].split("@")[1]; //Change

                        var game = new Game();
                        var gameID = game.getGameId();

                        var requestForWhite = new ConnectRequestDTO(gameID, update.getMessage().getFrom().getUserName());
                        var requestForBlack = new ConnectRequestDTO(gameID, playerNick);

                        gameService.connectToWhite(requestForWhite);
                        gameService.connectToBlack(requestForBlack);

                        msg.setText("CONNECT");
                        msg.setReplyMarkup(createUrlButton(gameID));
                    }
                }
            }

            if (text[0].equals("/start")) {
                var gameID = text[1];
                var userLogin = update.getMessage().getFrom().getUserName();
                msg.setText("Connect");
                msg.setReplyMarkup(openGameButton(gameID, userLogin));
            }
        }

        return msg;
    }
}
