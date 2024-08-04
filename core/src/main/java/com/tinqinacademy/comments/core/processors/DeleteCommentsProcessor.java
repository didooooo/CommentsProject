package com.tinqinacademy.comments.core.processors;

import com.tinqinacademy.comments.api.exceptions.ErrorMapper;
import com.tinqinacademy.comments.api.exceptions.Errors;
import com.tinqinacademy.comments.api.exceptions.customExceptions.CommentNotFoundException;
import com.tinqinacademy.comments.api.operations.deleteComments.DeleteComments;
import com.tinqinacademy.comments.api.operations.deleteComments.DeleteCommentsInput;
import com.tinqinacademy.comments.api.operations.deleteComments.DeleteCommentsOutput;
import com.tinqinacademy.comments.persistence.repositories.CommentsRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeleteCommentsProcessor implements DeleteComments {
    private final CommentsRepository commentsRepository;
    private final ErrorMapper errorMapper;

    @Override
    public Either<Errors, DeleteCommentsOutput> process(DeleteCommentsInput input) {
        log.info("Start deleteComments input: {}", input);
        return Try.of(() -> {
                    checkIfCommentExists(input);
                    commentsRepository.deleteById(UUID.fromString(input.getCommentId()));
                    DeleteCommentsOutput output = getDeleteCommentsOutput();
                    log.info("End deleteComments output: {}", output);
                    return output;
                }).toEither()
                .mapLeft(throwable -> errorMapper.mapError(throwable));
    }

    private DeleteCommentsOutput getDeleteCommentsOutput() {
        DeleteCommentsOutput output = DeleteCommentsOutput.builder()
                .message("Successfully deleted comment")
                .build();
        return output;
    }

    private void checkIfCommentExists(DeleteCommentsInput input) throws CommentNotFoundException {
        if (!commentsRepository.existsById(UUID.fromString(input.getCommentId())))
            throw new CommentNotFoundException();
    }
}
