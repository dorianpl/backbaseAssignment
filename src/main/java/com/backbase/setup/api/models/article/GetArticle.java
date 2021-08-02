package com.backbase.setup.api.models.article;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetArticle {

    @JsonProperty("article")
    private GetArticleData article;

}
