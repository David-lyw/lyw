package com.example.david.Dto;

/**
 * Created by David on 16/10/19.
 */
public class GetCashDto {
    public String userId;
    public String mentionAccount;
    public String money;
    public GetCashDto(String str1,String str2,String str3){
        this.userId=str1;
        this.mentionAccount=str2;
        this.money=str3;
    }
}
