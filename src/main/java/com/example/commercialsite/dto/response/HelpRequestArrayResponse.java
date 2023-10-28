package com.example.commercialsite.dto.response;

import com.example.commercialsite.entity.HelpSupport;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HelpRequestArrayResponse {
    private String message;
    private List<HelpSupport> data;
}
