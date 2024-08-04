package com.tinqinacademy.comments.core.processors;

import com.tinqinacademy.comments.api.exceptions.ErrorMapper;
import com.tinqinacademy.comments.api.exceptions.Errors;
import com.tinqinacademy.comments.api.exceptions.customExceptions.CommentNotFoundException;
import com.tinqinacademy.comments.api.operations.editCommentByAdmin.EditCommentsByAdmin;
import com.tinqinacademy.comments.api.operations.editCommentByAdmin.EditCommentsByAdminInput;
import com.tinqinacademy.comments.api.operations.editCommentByAdmin.EditCommentsByAdminOutput;
import com.tinqinacademy.comments.persistence.entities.Comments;
import com.tinqinacademy.comments.persistence.entities.User;
import com.tinqinacademy.comments.persistence.repositories.CommentsRepository;
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
public class EditCommentsByAdminProcessor implements EditCommentsByAdmin {
    private final CommentsRepository commentsRepository;
    private final ErrorMapper errorMapper;

    @Override
    public Either<Errors, EditCommentsByAdminOutput> process(EditCommentsByAdminInput input) {
        log.info("Start editCommentsByAdmin input: {}", input);
        return Try.of(() -> {
                    Comments comment = commentsRepository.findById(UUID.fromString(input.getCommentId())).orElseThrow(() -> new CommentNotFoundException());
                    User user = updateUser(input, comment);
                    Comments built = getComments(input, comment, user);
                    commentsRepository.save(built);
                    EditCommentsByAdminOutput output = getEditCommentsByAdminOutput(input);
                    log.info("End editCommentsByAdmin output: {}", output);
                    return output;
                }).toEither()
                .mapLeft(throwable -> errorMapper.mapError(throwable));
    }

    private EditCommentsByAdminOutput getEditCommentsByAdminOutput(EditCommentsByAdminInput input) {
        EditCommentsByAdminOutput output = EditCommentsByAdminOutput.builder()
                .id(input.getCommentId())
                .build();
        return output;
    }

    private Comments getComments(EditCommentsByAdminInput input, Comments comment, User user) {
        Comments built = Comments.builder()
                .id(UUID.fromString(input.getCommentId()))
                .lastEditedBy(comment.getLastEditedBy())
                .content(input.getContent())
                .user(user)
                .lastEditedDate(LocalDate.now())
                .lastEditedBy(input.getFirstName())
                .roomId(UUID.fromString(input.getRoomNo()))
                .build();
        return built;
    }

    private User updateUser(EditCommentsByAdminInput input, Comments comment) {
        User user = comment.getUser();
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        return user;
    }
}
