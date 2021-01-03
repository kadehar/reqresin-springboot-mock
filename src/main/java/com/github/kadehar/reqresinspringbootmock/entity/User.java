package com.github.kadehar.reqresinspringbootmock.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Модель User.
 * Для создания нового пользователя в БД необходимо передавать
 * в JSON только name и job.
 *
 * @author kadehar
 */
@Entity
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
public class User {
    private @Id @GeneratedValue Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @NotNull(message = "name can not be null")
    @NotEmpty(message = "name is mandatory")
    private String name;
    @NotNull(message = "job can not be null")
    @NotBlank(message = "job is mandatory")
    private String job;

    public User(LocalDateTime createdAt, LocalDateTime updatedAt, String name, String job) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.job = job;
    }

    public User(String name, String job) {
        this(LocalDateTime.now(), null, name, job);
    }
}
