package com.example.botheroku.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeDto {

    private String startHour;
    private String endHour;

    private String startMinute;
    private String endMinute;


}
