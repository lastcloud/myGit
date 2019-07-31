package com.indusfo.spc.sdk.domain;

import com.indusfo.spc.sdk.common.Domain;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Descirption
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/18 16:37
 */
@Table(name ="spc_user")
public class User extends Domain {

    private static final long serialVersionUID = 9100597163090694653L;

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    @ApiModelProperty(value = "用户编码")
    private String userCode;/** 用户编码 */
    private String contact;/** 手机号 */
    private String nickname;/** 昵称 */
    private String headImg;/** 头像 */
    private String password;/** 登录密码 */
    private String login_ip;/** 登录IP */

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public Integer getId() {
        return id;
    }

    public String getUserCode() {
        return userCode;
    }

    public String getContact() {
        return contact;
    }

    public String getNickname() {
        return nickname;
    }

    public String getHeadImg() {
        return headImg;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin_ip() {
        return login_ip;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin_ip(String login_ip) {
        this.login_ip = login_ip;
    }
}
