package com.example.webapi.pojo.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Month;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@Data
public class UserRewardDTO {
    private String firstName;
    private String lastName;
    private int total;
    private List<RewardsEachMonth> rewardsEachMonthList;

    @Data
    @AllArgsConstructor
    public static class RewardsEachMonth {
        private Month month;
        private int Rewards;
    }
}
