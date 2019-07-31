package com.indusfo.spc.sdk.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Descirption
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/21 22:45
 */
@ApiModel
@Table(name = "spc_industry")
@Data
public class Industry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "行业id")
    private Integer id;

    @ApiModelProperty(value = "行业名称")
    private String industryName;


}
