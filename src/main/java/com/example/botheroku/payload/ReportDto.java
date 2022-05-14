package com.example.botheroku.payload;

import com.example.botheroku.entity.Supervisor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {

    private Supervisor supervisor;

    private String time;

}
