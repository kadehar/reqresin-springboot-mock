package com.github.kadehar.reqresinspringbootmock.repository;

import com.github.kadehar.reqresinspringbootmock.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Расширяет стандартный JpaRepository для поиска, удаления и добавления
 * объектов User по ключу Long.
 *
 * @author kadehar
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
