package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.example.demo.service.impl.UserServiceImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author
 * @since 2019-06-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Reptileconfig extends Model<Reptileconfig> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("isNeedLogin")
    private Boolean isNeedLogin;

    @TableField("loginPageUrl")
    private String loginPageUrl;

    @TableField("submitLoginUrl")
    private String submitLoginUrl;

    @TableField("loginPageUncertainParameter")
    private String loginPageUncertainParameter;

    @TableField("loginPageDetermineParameter")
    private String loginPageDetermineParameter;

    @TableField("verifyBackstageIndexUrl")
    private String verifyBackstageIndexUrl;

    /**
     * 解析页面规则
     * {
     * "targetElement": null,
     * "downElement": null,
     * "downFileType": null,
     * "pagingElement": null,
     * "reptilePageConfig": []
     * }
     * <p>
     * targetElement: 页面跳转的标签选择器, 对应reptilePageConfig解析规则,可以配置数组跳转到不同页面,对应reptilePageConfig也要配置数组
     * <p>
     * downElement: 页面下载文件的标签选择器
     * <p>
     * downFileType: 下载的文件类型, 配置了一个*表示下载所有文件, 配置downFileType键使用downFileType列
     * <p>
     * pagingElement: 分页的标签选择器
     * <p>
     * reptilePageConfig:  跳转页面后新的解析规则
     */
    @TableField("reptilePageConfig")
    private String reptilePageConfig;

    @TableField("mainUrl")
    private String mainUrl;

    @TableField("downFileType")
    private String downFileType;

    @TableField("downThreadSize")
    private Integer downThreadSize;

    @TableField("saveFilePath")
    private String saveFilePath;

    @TableField("switch")
    private Boolean _switch;


}
