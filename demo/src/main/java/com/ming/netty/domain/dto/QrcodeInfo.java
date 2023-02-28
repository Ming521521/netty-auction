package com.ming.netty.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QrcodeInfo {
    private String type;
    private String subject;
    private  String username;
    private  String encrypt;
}
