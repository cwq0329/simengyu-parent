package com.cwq.core.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="bbs_brand")
public class Brand implements Serializable {
    /**
     * ID
     */
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 图片Url
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 品牌网址
     */
    @Column(name = "web_site")
    private String webSite;

    /**
     * 排序:最大最排前
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 是否可见 1:可见 0:不可见
     */
    @Column(name = "is_display")
    private Integer isDisplay;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite == null ? null : webSite.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", webSite=").append(webSite);
        sb.append(", sort=").append(sort);
        sb.append(", isDisplay=").append(isDisplay);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}