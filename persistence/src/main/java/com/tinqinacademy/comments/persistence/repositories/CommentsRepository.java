package com.tinqinacademy.comments.persistence.repositories;

import com.tinqinacademy.comments.persistence.entities.Comments;
import com.tinqinacademy.comments.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface CommentsRepository extends JpaRepository<Comments, UUID> {
    List<Comments> findByRoomId(UUID roomId);
}
