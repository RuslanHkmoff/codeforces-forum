package ru.itmo.wp.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PostCredentials {
    @NotNull
    @Size(min = 1, max = 60)
    @NotBlank
    private String title;

    @NotNull
    @Size(min = 1, max = 65000)
    @NotBlank
    private String text;

    @NotNull
    @Pattern(regexp = "\\s*([a-zA-Z]+\\s+)*[a-zA-Z]*\\s*", message = "Only lowercase latin letters expected")
    private String tagsString;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTagsString() {
        return tagsString;
    }

    public void setTagsString(String tagsString) {
        this.tagsString = tagsString;
    }
}
