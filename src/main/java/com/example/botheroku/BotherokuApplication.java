package com.example.botheroku;

import com.example.botheroku.entity.Report;
import com.example.botheroku.entity.Rule;
import com.example.botheroku.entity.Supervisor;
import com.example.botheroku.payload.ReportDto;
import com.example.botheroku.payload.TimeDto;
import com.example.botheroku.repository.ReportRepository;
import com.example.botheroku.repository.RuleRepository;
import com.example.botheroku.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.pinnedmessages.PinChatMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.Timestamp;
import java.util.*;

@SpringBootApplication
@EnableScheduling
public class BotherokuApplication extends TelegramLongPollingBot {


    @Autowired
    SupervisorRepository supervisorRepository;
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    RuleRepository ruleRepository;

    public static void main(String[] args) {
        SpringApplication.run(BotherokuApplication.class, args);
    }

    @Override
    public String getBotUsername() {
        return Constatns.BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return Constatns.BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
//        System.out.println(update.getMessage().getForwardDate());
        checkSupervisor(update);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));

        Optional<Rule> optionalRule = ruleRepository.findById(1);
        Rule rule = optionalRule.get();

        if (update.getMessage().hasVideoNote() && update.getMessage().getForwardDate() == null) {

            ///////////////////////
//            Optional<Report> byId = reportRepository.findById(6L);
//            Report tst = byId.get();
//            System.out.println(tst.getDate().toString());
            /////////////////


            Date date1 = convertIntToDate(update.getMessage().getDate());

            int hours = date1.getHours();
            int minutes = date1.getMinutes();

            /////////////


            if (hours >= Integer.valueOf(rule.getStartHour())) {
                if (hours==Integer.valueOf(rule.getStartHour()) && minutes >=Integer.valueOf(rule.getStartMinute())) {
                    if (hours <= Integer.valueOf(rule.getEndHour())) {
                        if (hours==Integer.valueOf(rule.getStartHour()) && minutes <= Integer.valueOf(rule.getEndMinute())) {
                            //TODO
                            System.out.println(update.getMessage().getChat().getId());
//                            String txt = "tg://openmessage?chat_id=" + (update.getMessage().getChatId() * (-1)) + "&message_id=" + update.getMessage().getMessageId();
                            //https://t.me/c/1651510322/14
//                        String urls = "https://api.telegram.org/file/bot" +Constants.BOT_TOKEN+ '/' +filePath;

                            /////

                            Optional<Supervisor> byChatId = supervisorRepository.findByChatId(update.getMessage().getFrom().getId().toString());
                            Supervisor supervisor = byChatId.get();
                            String txt = "https://t.me/c/" + (update.getMessage().getChatId() % 10000000000l*(-1)) + "/" + update.getMessage().getMessageId();
                            Report report = new Report(
                                    txt,
                                    supervisor,
                                    update.getMessage().getMessageId().toString()
                            );
                            reportRepository.save(report);

                            sendMessage.setText(txt);


                        }else if (minutes <= Integer.valueOf(rule.getEndMinute()) || minutes >= Integer.valueOf(rule.getEndMinute())){
                            //TODO
                            System.out.println(update.getMessage().getChat().getId());
//                            String txt = "tg://openmessage?chat_id=" + (update.getMessage().getChatId() * (-1)) + "&message_id=" + update.getMessage().getMessageId();
                            //https://t.me/c/1651510322/14
//                        String urls = "https://api.telegram.org/file/bot" +Constants.BOT_TOKEN+ '/' +filePath;

                            /////
                            Optional<Supervisor> byChatId = supervisorRepository.findByChatId(update.getMessage().getFrom().getId().toString());
                            Supervisor supervisor = byChatId.get();
                            String txt = "https://t.me/c/" + (update.getMessage().getChatId() % 10000000000l*(-1)) + "/" + update.getMessage().getMessageId();

//                            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                            Report report = new Report(
                                    txt,
                                    supervisor,
                                    update.getMessage().getMessageId().toString()
                            );
//                            Report report1 = new Report(
//                                    txt,
//                                    timestamp,
//                                    supervisor,
//                                    update.getMessage().getMessageId().toString()
//                            );
                            reportRepository.save(report);

                            sendMessage.setText(txt);
                        }
                    }
                }else if (hours > Integer.valueOf(rule.getStartHour()) && (minutes >=Integer.valueOf(rule.getStartMinute()) || minutes <=Integer.valueOf(rule.getStartMinute()))){
                    if (hours <= Integer.valueOf(rule.getEndHour())) {
                        if (hours==Integer.valueOf(rule.getEndHour()) && minutes <= Integer.valueOf(rule.getEndMinute())) {
                            //TODO
                            System.out.println(update.getMessage().getChat().getId());
//                            String txt = "tg://openmessage?chat_id=" + (update.getMessage().getChatId() * (-1)) + "&message_id=" + update.getMessage().getMessageId();
                            //https://t.me/c/1651510322/14
//                        String urls = "https://api.telegram.org/file/bot" +Constants.BOT_TOKEN+ '/' +filePath;

                            /////
                            Optional<Supervisor> byChatId = supervisorRepository.findByChatId(update.getMessage().getFrom().getId().toString());
                            Supervisor supervisor = byChatId.get();
                            String txt = "https://t.me/c/" + (update.getMessage().getChatId() % 10000000000l*(-1)) + "/" + update.getMessage().getMessageId();
                            Report report = new Report(
                                    txt,
                                    supervisor,
                                    update.getMessage().getMessageId().toString()
                            );
                            reportRepository.save(report);

                            sendMessage.setText(txt);
                        }else if (hours < Integer.valueOf(rule.getEndHour()) && (minutes <=Integer.valueOf(rule.getEndMinute()) || minutes >= Integer.valueOf(rule.getEndMinute()))){
                            //TODO
                            System.out.println(update.getMessage().getChat().getId());
//                            String txt = "tg://openmessage?chat_id=" + (update.getMessage().getChatId() * (-1)) + "&message_id=" + update.getMessage().getMessageId();
                            //https://t.me/c/1651510322/14
//                        String urls = "https://api.telegram.org/file/bot" +Constants.BOT_TOKEN+ '/' +filePath;

                            /////
                            Optional<Supervisor> byChatId = supervisorRepository.findByChatId(update.getMessage().getFrom().getId().toString());
                            Supervisor supervisor = byChatId.get();
                            String txt = "https://t.me/c/" + (update.getMessage().getChatId() % 10000000000l*(-1)) + "/" + update.getMessage().getMessageId();
                            Report report = new Report(
                                    txt,
                                    supervisor,
                                    update.getMessage().getMessageId().toString()
                            );
                            reportRepository.save(report);

                            sendMessage.setText(txt);
                        }
                    }
                }
            }



            ////////////


//            if (hours >= 8) {
////                if (minutes>=30){
//                if (hours <= 22) {
//                    if (hours == 22 && minutes <= 59) {
//                        //TODO
//                        System.out.println(update.getMessage().getChat().getId());
//                        String txt = "tg://openmessage?chat_id=" + (update.getMessage().getChatId() * (-1)) + "&message_id=" + update.getMessage().getMessageId();
////                        String urls = "https://api.telegram.org/file/bot" +Constants.BOT_TOKEN+ '/' +filePath;
//                        sendMessage.setText(txt);
//
//                        /////
//                        Optional<Supervisor> byChatId = supervisorRepository.findByChatId(update.getMessage().getChatId().toString());
//                        Supervisor supervisor = byChatId.get();
//                        Report report = new Report(
//                                txt,
//                                supervisor,
//                                update.getMessage().getMessageId().toString()
//                        );
//                        reportRepository.save(report);
//
//                    }
//                    System.out.println("OKKKKKKKKKKK");
//                }
//            }

        }

    }

    private void checkSupervisor(Update update) {
        if (!update.getMessage().getFrom().getIsBot()) {
            boolean existsByChatId = supervisorRepository.existsByChatId(update.getMessage().getFrom().getId().toString());
            if (!existsByChatId) {
                Supervisor supervisor = new Supervisor(
                        update.getMessage().getFrom().getFirstName(),
                        update.getMessage().getFrom().getLastName(),
                        update.getMessage().getFrom().getUserName(),
                        update.getMessage().getFrom().getId().toString(),
                        update.getMessage().getChatId().toString()
                );
                supervisorRepository.save(supervisor);
            }
        }
    }

    public Date convertIntToDate(Integer intDate) {

        int intYear = intDate / 100;
//        int intMonth = intDate - (intYear * 100);
        int intMonth = intDate % 100;
        Calendar result = new GregorianCalendar();
//        result.set(intYear, intMonth - 1, 1, 0, 0, 0);
        result.set(Calendar.YEAR, intYear);
        result.set(Calendar.MONTH, intMonth - 1);
        result.set(Calendar.DAY_OF_MONTH, 1);
        return result.getTime();
    }


    @Scheduled(cron = "0 " + Constatns.SEND_MINUTE +" " + Constatns.SEND_HOUR + " * * *")
    public void test() throws Exception {
        Timestamp start = new Timestamp(System.currentTimeMillis());
        Timestamp end = new Timestamp(System.currentTimeMillis());

        start.setHours(0);
        start.setMinutes(0);
        start.setSeconds(1);

        end.setHours(23);
        end.setMinutes(59);
        end.setSeconds(59);

        List<Report> dayReport = reportRepository.findByDateBetween(start, end);

        List<Supervisor> supervisors = supervisorRepository.findAll();

//        sendApiMethodAsync(PinChatMessage.builder()
//                .chatId(supervisors.get(1).getGroupChatId())
//                .messageId(55)
//                .build());

        Set<ReportDto> reportDtoSet = new HashSet<>();

        for (Supervisor supervisor : supervisors) {
            ReportDto reportDto = new ReportDto();
            reportDto.setSupervisor(supervisor);
            for (Report report : dayReport) {
                if (supervisor.getId()==report.getSupervisor().getId()){
                    reportDto.setTime(report.getDate().toString());
                }
            }
            reportDtoSet.add(reportDto);
        }

        SendMessage sendMessage = new SendMessage();
        Optional<Rule> byId = ruleRepository.findById(1);
        if (!byId.isPresent()){
            throw new Exception("Bunday qagi'yda ya'ki chat_id tabilmadi!!!");
        }
        Rule rule = byId.get();
        String chatId = rule.getChatId();

//        sendMessage.setChatId(Constatns.SENDER_CHAT_ID);
        sendMessage.setChatId(chatId);

        String repo = "";
        int count = 1;
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        repo = repo + date + "\n\n";
        for (ReportDto reportDto : reportDtoSet) {
            if (reportDto.getTime()==null){
                repo = repo + count +" - " + reportDto.getSupervisor().getFirstName() + " "+ reportDto.getSupervisor().getLastName() + "     " +"Taslamadi\n";
            }else {
                repo = repo+ count +" - " + reportDto.getSupervisor().getFirstName()+" "+ reportDto.getSupervisor().getLastName() + "     " + reportDto.getTime().substring(11,19) + "\n";
            }
            count++;
        }

        sendMessage.setText(repo);
        execute(sendMessage);
    }

    public boolean pinGroup(TimeDto timeDto) {
        try {
            SendMessage sendMessage = new SendMessage();
            Optional<Supervisor> optionalSupervisor = supervisorRepository.findById(1l);

//            sendMessage.setChatId("-775391212");
            sendMessage.setChatId(optionalSupervisor.get().getGroupChatId());
            sendMessage.setText("Video jiberiw waqitlar o'zgerdi!!! " + timeDto.getStartHour() + ":" + timeDto.getStartMinute() + " dan " +
                    timeDto.getEndHour() + ":" + timeDto.getEndMinute() + " qa o'zgerdi!!!");

            Message execute = execute(sendMessage);
//            Integer messageId = execute(sendMessage).getMessageId();

            // Pin group message
            Optional<Supervisor> byId = supervisorRepository.findById(1l);

            Supervisor supervisor = byId.get();

//            Optional<Report> optionalReport = reportRepository.findById(supervisor.getId());
//            if (!optionalReport.isPresent()){
//                return false;
//            }
//            Report report = optionalReport.get();

            sendApiMethodAsync(PinChatMessage.builder()
                    .chatId(String.valueOf(supervisor.getGroupChatId()))
                    .messageId(Integer.valueOf(execute.getMessageId()))
                    .build());

            return true;
        }catch (Exception e){
            return false;
        }
    }


}
