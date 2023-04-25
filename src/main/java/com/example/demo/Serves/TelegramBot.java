package com.example.demo.Serves;

import com.example.demo.Config.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {
    final BotConfig config;
    public TelegramBot(BotConfig config){
        this.config = config;
    }
    @Override
    public String getBotUsername() {
        return (config.getBotName());
    }

    @Override
    public String getBotToken() {
        return (config.getToken());
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            long chatId = update.getMessage().getChatId();
            String massageText = update.getMessage().getText();
            switch (massageText){
                case "/start":

                        startCommandReceived(chatId,update.getMessage().getChat().getFirstName());
                        break;
                default:

                        sendMassage(chatId,"Sorry, command was not reginaized");


            }
        }

    }
    private void startCommandReceived(long chatId,String name) {
        String answer = "Hy, "+name+",nice to meat you";
        log.info("Replied to user "+name);

        sendMassage(chatId, answer);

    }
    private void sendMassage(long chatId,String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
                execute(message);
            } catch (TelegramApiException e) {
            log.error("Error occurred: "+e.getMessage());

        }
    }
}
