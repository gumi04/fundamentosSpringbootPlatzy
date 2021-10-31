package com.fundamentos.springboot.fundamentos.dto;

import java.time.LocalDate;

public class UserDto {
    private Long id;
    private String name;
    private LocalDate birhtday;

    public UserDto(Long id, String name, LocalDate birhtday) {
        this.id = id;
        this.name = name;
        this.birhtday = birhtday;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birhtday='" + birhtday + '\'' +
                '}';
    }

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
        this.name = name;
    }

    public LocalDate getBirhtday() {
        return birhtday;
    }

    public void setBirhtday(LocalDate birhtday) {
        this.birhtday = birhtday;
    }
}
