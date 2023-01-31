package com.example.resttemplatepractice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserRequestDTO {
    @JsonProperty("data")
    private List<UserInfo> userInfos;
}
