package com.example.commercialsite.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AdminDashboardResponse {
    private long totalRequests;
    private long rejectedRequests;
    private long acceptedRequests;
    private long totalSwaps;
    private long ReceivedItems;
    private long totalCustomers;
    private double totalProfit;
    private double totalGoldMembership;
    private double platinumMembership;
    private double diamondMembership;


}
