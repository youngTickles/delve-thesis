package com.example.delve.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"page",
"total_results",
"total_pages",
"results"
})
public class SearchExample {

@JsonProperty("page")
private Integer page;
@JsonProperty("total_results")
private Integer totalResults;
@JsonProperty("total_pages")
private Integer totalPages;
@JsonProperty("results")
private List<SearchResult> results = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("page")
public Integer getPage() {
return page;
}

@JsonProperty("page")
public void setPage(Integer page) {
this.page = page;
}

@JsonProperty("total_results")
public Integer getTotalResults() {
return totalResults;
}

@JsonProperty("total_results")
public void setTotalResults(Integer totalResults) {
this.totalResults = totalResults;
}

@JsonProperty("total_pages")
public Integer getTotalPages() {
return totalPages;
}

@JsonProperty("total_pages")
public void setTotalPages(Integer totalPages) {
this.totalPages = totalPages;
}

@JsonProperty("results")
public List<SearchResult> getResults() {
return results;
}

@JsonProperty("results")
public void setResults(List<SearchResult> results) {
this.results = results;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

@Override
public String toString() {
return new ToStringBuilder(this).append("page", page).append("totalResults", totalResults).append("totalPages", totalPages).append("results", results).append("additionalProperties", additionalProperties).toString();
}

}