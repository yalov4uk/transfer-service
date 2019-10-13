package com.yalovchuk.transfer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {

    private Integer id;
    private int value;
    private int fromAccountId;
    private int toAccountId;
}
