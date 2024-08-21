package com.tinqinacademy.comments.core.processors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.tinqinacademy.comments.api.exceptions.ErrorMapper;
import com.tinqinacademy.comments.api.exceptions.Errors;
import com.tinqinacademy.comments.api.exceptions.customExceptions.CommentNotFoundException;
import com.tinqinacademy.comments.api.operations.editOwnComment.EditOwnCommentInput;
import com.tinqinacademy.comments.api.operations.editOwnComment.EditOwnCommentOutput;
import com.tinqinacademy.comments.api.operations.editOwnComment.EditOwnComments;
import com.tinqinacademy.comments.persistence.entities.Comments;
import com.tinqinacademy.comments.persistence.repositories.CommentsRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class EditOwnCommentsProcessor implements EditOwnComments {
    private final CommentsRepository commentsRepository;
    private final ErrorMapper errorMapper;
    private final ObjectMapper objectMapper;

    @Override
    public Either<Errors, EditOwnCommentOutput> process(EditOwnCommentInput input) {
        log.info("Start editOwnComments input: {}", input);
        return Try.of(() -> {
                    Comments commentById = commentsRepository.findById(input.getId()).orElseThrow(() -> new CommentNotFoundException());
                    JsonNode roomNode = objectMapper.valueToTree(commentById);
                    JsonNode inputNode = objectMapper.valueToTree(input);
                    try {
                        JsonMergePatch patch = JsonMergePatch.fromJson(inputNode);
                        JsonNode patchedNode = patch.apply(roomNode);
                        Comments comments = objectMapper.treeToValue(patchedNode, Comments.class);
                        comments.setLastEditedDate(LocalDate.now());
                        comments.setLastEditedBy(commentById.getUser().getFirstName());
                        log.info("Patched room {}", comments);
                        commentsRepository.save(comments);
                    } catch (JsonPatchException | JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    EditOwnCommentOutput output = getEditOwnCommentOutput(commentById);
                    log.info("End editOwnComments output: {}", output);
                    return output;
                }).toEither()
                .mapLeft(throwable -> errorMapper.mapError(throwable));
    }

    private static EditOwnCommentOutput getEditOwnCommentOutput(Comments commentById) {
        EditOwnCommentOutput output = EditOwnCommentOutput.builder()
                .id(String.valueOf(commentById.getId()))
                .build();
        return output;
    }
}
