package com.G01.onlineFishAuction.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CooperativeMemberDTO {
    String username;
    String mail;
    String password;
    String code;
}
