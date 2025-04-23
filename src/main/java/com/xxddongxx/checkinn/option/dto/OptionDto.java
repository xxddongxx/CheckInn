package com.xxddongxx.checkinn.option.dto;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OptionDto {
    private Long idx;
    private String nm;
    private String description;
}
