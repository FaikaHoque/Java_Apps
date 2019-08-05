package ca.jrvs.apps.twitter.example.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "symbol",
        "compnayName",
        "exchange",
        "description",
        "CEO",
        "sector",
        "financials",
        "dividends"
})
public class Company {

    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("exchange")
    private String exchnage;
    @JsonProperty("description")
    private String description;
    @JsonProperty("CEO")
    private String ceo;
    @JsonProperty("sector")
    private String sector;
    @JsonProperty("financials")
    private List<Financial> financials = null;
    @JsonProperty("divideds")
    private List<Divided> dividends = null;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getExchnage() {
        return exchnage;
    }

    public void setExchnage(String exchnage) {
        this.exchnage = exchnage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public List<Financial> getFinancials() {
        return financials;
    }

    public void setFinancials(List<Financial> financials) {
        this.financials = financials;
    }

    public List<Divided> getDivideds() {
        return dividends;
    }

    public void setDivideds(List<Divided> dividends) {
        this.dividends = dividends;
    }
}
