package com.xxddongxx.checkinn.option.dto;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OptionDto {
    private long idx;
    private String nm;
    private String description;
}
