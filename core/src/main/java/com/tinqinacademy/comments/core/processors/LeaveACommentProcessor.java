package com.tinqinacademy.comments.core.processors;

import com.tinqinacademy.comments.api.exceptions.ErrorMapper;
import com.tinqinacademy.comments.api.exceptions.Errors;
import com.tinqinacademy.comments.api.operations.leaveAComment.LeaveAComment;
import com.tinqinacademy.comments.api.operations.leaveAComment.LeaveACommentInput;
import com.tinqinacademy.comments.api.operations.leaveAComment.LeaveACommentOutput;
import com.tinqinacademy.comments.persistence.entities.Comments;
import com.tinqinacademy.comments.persistence.entities.User;
import com.tinqinacademy.comments.persistence.repositories.CommentsRepository;
import com.tinqinacademy.comments.persistence.repositories.UserRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class LeaveACommentProcessor implements LeaveAComment {
    private final CommentsRepository commentsRepository;
    private final ErrorMapper errorMapper;
    private final UserRepository userRepository;

    @Override
    public Either<Errors, LeaveACommentOutput> process(LeaveACommentInput input) {
        log.info("Start leaveAComment input: {}", input);
        return Try.of(() -> {
                    User built = getUser(input);
                    User save = userRepository.save(built);
                    Comments builtComment = getComments(input, save);
                    commentsRepository.save(builtComment);
                    LeaveACommentOutput leaveACommentOutput = getLeaveACommentOutput(input);
                    log.info("End leaveAComment output: {}", leaveACommentOutput);
                    return leaveACommentOutput;
                }).toEither()
                .mapLeft(throwable -> errorMapper.mapError(throwable));
    }

    private LeaveACommentOutput getLeaveACommentOutput(LeaveACommentInput input) {
        LeaveACommentOutput leaveACommentOutput = LeaveACommentOutput.builder()
                .roomId(input.getRoomId())
                .build();
        return leaveACommentOutput;
    }

    private Comments getComments(LeaveACommentInput input, User built) {
        Comments builtComment = Comments.builder()
                .roomId(UUID.fromString(input.getRoomId()))
                .content(input.getContent())
                .publishDate(LocalDate.now())
                .lastEditedDate(LocalDate.now())
                .lastEditedBy(input.getFirstName())
                .user(built)
                .build();
        return builtComment;
    }

    private User getUser(LeaveACommentInput input) {
        User built = User.builder()
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .birthdate(input.getBirthdate())
                .build();
        return built;
    }
}
