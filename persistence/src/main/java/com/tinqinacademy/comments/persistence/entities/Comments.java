package com.tinqinacademy.comments.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String content;
    @Column(name = "room_id",nullable = false)
    private UUID roomId;
    @Column(name = "publish_date")
    private LocalDate publishDate;
    @Column(name = "last_edited_date")
    private LocalDate lastEditedDate;
    @Column(name = "last_edited_by")
    private String lastEditedBy;
    @ManyToOne(targetEntity = User.class)
    private User user;
}
