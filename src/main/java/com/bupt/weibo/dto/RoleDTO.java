package com.bupt.weibo.dto;

import java.io.Serializable;

/**
 * @anthor tanshangou
 * @time 2018/7/12
 * @description
 */
public class RoleDTO implements Serializable {
    private long id;
    private String name;
    private String desc;

    public RoleDTO() {
    }

    public RoleDTO(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public RoleDTO(long id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleDTO roleDto = (RoleDTO) o;

        if (id != roleDto.id) return false;
        if (name != null ? !name.equals(roleDto.name) : roleDto.name != null) return false;
        return desc != null ? desc.equals(roleDto.desc) : roleDto.desc == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        return result;
    }
}
