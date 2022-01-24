package com.benjamin.CryptoEx.api.domains.coins.coinData;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReposUrl {
    @JsonProperty("github")
    private List<String> github;
    @JsonProperty("bitbucket")
    private List<Object> bitbucket;

}
