package com.backbase.setup.api.models.article;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Author {

    @JsonProperty("username")
    private String username;

    @JsonProperty("image")
    private String image;

    @JsonProperty("following")
    private boolean following;
}
