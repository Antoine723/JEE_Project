package com.videoGamesWeb.vgcore.repository;


import com.videoGamesWeb.vgcore.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
