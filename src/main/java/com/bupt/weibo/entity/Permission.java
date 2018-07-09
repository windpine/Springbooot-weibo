package com.bupt.weibo.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @anthor tanshangou
 * @time 2018/7/8
 * @description
 */
@Entity
public class Permission {
    private int permissonId;
    private String resourceUrl;

    @Id
    @Column(name = "permisson_id", nullable = false)
    public int getPermissonId() {
        return permissonId;
    }

    public void setPermissonId(int permissonId) {
        this.permissonId = permissonId;
    }

    @Basic
    @Column(name = "resource_url", nullable = false, length = 256)
    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return permissonId == that.permissonId &&
                Objects.equals(resourceUrl, that.resourceUrl);
    }

    @Override
    public int hashCode() {

        return Objects.hash(permissonId, resourceUrl);
    }
}
