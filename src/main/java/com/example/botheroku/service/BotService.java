package com.example.botheroku.service;

import com.example.botheroku.BotherokuApplication;
import com.example.botheroku.entity.Report;
import com.example.botheroku.entity.Rule;
import com.example.botheroku.entity.Supervisor;
import com.example.botheroku.payload.ApiResponse;
import com.example.botheroku.payload.ChatDto;
import com.example.botheroku.payload.DateDto;
import com.example.botheroku.payload.TimeDto;
import com.example.botheroku.repository.ReportRepository;
import com.example.botheroku.repository.RuleRepository;
import com.example.botheroku.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BotService {

    @Autowired
    SupervisorRepository supervisorRepository;
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    RuleRepository ruleRepository;
    @Autowired
    BotherokuApplication botApplication;

    public ApiResponse getAllSupervisors() {
        List<Supervisor> all = supervisorRepository.findAll();
        return new ApiResponse("Supervisor list:", true, all);
    }

    public ApiResponse byDate(String start, String end) throws ParseException {
        DateDto dateDto = new DateDto(start, end);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date date = dateFormat.parse(dateDto.getStart());
        long time = date.getTime();
        Timestamp timestamp = new Timestamp(time);


        Date date2 = dateFormat.parse(dateDto.getEnd());
        long time2 = date2.getTime();
        Timestamp timestamp2 = new Timestamp(time2);
        timestamp2.setHours(23);
        timestamp2.setMinutes(59);
        timestamp2.setSeconds(59);

        List<Report> allReportList = reportRepository.findByDateBetween(timestamp, timestamp2);

        return new ApiResponse("All Supervisor report by date : ", true, allReportList);
    }


    public ApiResponse byDateSupervisor(Long id, String start, String end) throws ParseException {
        DateDto dateDto = new DateDto(start, end);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date date = dateFormat.parse(dateDto.getStart());
        long time = date.getTime();
        Timestamp timestamp = new Timestamp(time);

        Date date2 = dateFormat.parse(dateDto.getEnd());
        long time2 = date2.getTime();
        Timestamp timestamp2 = new Timestamp(time2);
        timestamp2.setHours(23);
        timestamp2.setMinutes(59);
        timestamp2.setSeconds(59);

        List<Report> byDateSupervisor = reportRepository.findBySupervisorIdAndDateBetween(id, timestamp, timestamp2);

        return new ApiResponse("All Supervisor report by date And by Supervisor Id: ", true, byDateSupervisor);
    }

    public ApiResponse editTime(TimeDto timeDto) {
        Optional<Rule> byId = ruleRepository.findById(1);
        if (byId.isPresent()){
            Rule rule = byId.get();
            rule.setStartHour(timeDto.getStartHour());
            rule.setStartMinute(timeDto.getStartMinute());
            rule.setEndHour(timeDto.getEndHour());
            rule.setEndMinute(timeDto.getEndMinute());
            ruleRepository.save(rule);

            ///
            boolean b = botApplication.pinGroup(timeDto);
            if (b){
                return new ApiResponse("Waqit o'zgerdi!", true);
            }
        }
        return new ApiResponse("Error", false);
    }


    public ApiResponse getAllReport() {
        List<Report> all = reportRepository.findAll();
        return new ApiResponse("All Report list", true, all);
    }

    public ApiResponse getRule() {
        Optional<Rule> byId = ruleRepository.findById(1);
        return new ApiResponse("Qag`iyda", true, byId.get());
    }

    public ApiResponse editChatId(ChatDto chatDto) {
        Optional<Rule> byId = ruleRepository.findById(1);
        if (byId.isPresent()){
            Rule rule = byId.get();
            rule.setChatId(chatDto.getChatId());
            ruleRepository.save(rule);
            return new ApiResponse("Chat id o'zgerdi!!!", true);
        }
        return new ApiResponse("Error", false);
    }
}
