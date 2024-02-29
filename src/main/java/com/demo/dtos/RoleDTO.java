package com.demo.dtos;

import java.util.Set;

public class RoleDTO {
    private Integer id;
    private String name;
    private Set<AccountDTO> accounts;

    public RoleDTO() {
    }

    public RoleDTO(Integer id, String name, Set<AccountDTO> accounts) {
        this.id = id;
        this.name = name;
        this.accounts = accounts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<AccountDTO> accounts) {
        this.accounts = accounts;
    }
}
