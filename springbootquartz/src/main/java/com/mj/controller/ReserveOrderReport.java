package com.mj.controller;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Data
@Getter
public class ReserveOrderReport implements Serializable {

    private String groupTypeName;
    private String skuName;
    private Integer reservationCount;
    private Integer forceRedeemedCount;
    private Integer forceUnreservedRedeemedCount;
    private Integer normalRedeemedCount;
    private Integer storeRedeemed;
    private Integer sstTmallCount;
    private Integer esstWechatCount;
    private Integer esstB2bCount;
    private String storeId;
}
