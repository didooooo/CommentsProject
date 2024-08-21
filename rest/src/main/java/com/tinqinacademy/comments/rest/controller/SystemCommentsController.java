package com.tinqinacademy.comments.rest.controller;

import com.tinqinacademy.comments.api.mappings.RestApiPaths;
import com.tinqinacademy.comments.api.operations.deleteComments.DeleteComments;
import com.tinqinacademy.comments.api.operations.deleteComments.DeleteCommentsInput;
import com.tinqinacademy.comments.api.operations.editCommentByAdmin.EditCommentsByAdmin;
import com.tinqinacademy.comments.api.operations.editCommentByAdmin.EditCommentsByAdminInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SystemCommentsController extends BaseController {
    private final DeleteComments deleteCommentsProcessor;
    private final EditCommentsByAdmin editCommentsByAdminProcessor;

    public SystemCommentsController(DeleteComments deleteCommentsProcessor, EditCommentsByAdmin editCommentsByAdminProcessor) {
        this.deleteCommentsProcessor = deleteCommentsProcessor;
        this.editCommentsByAdminProcessor = editCommentsByAdminProcessor;
    }


    @PutMapping(RestApiPaths.EDIT_A_COMMENT_BY_ADMIN)
    @Operation(
            summary = "Edit a comment by admin",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "200", description = "Successful")
            }
    )
    public ResponseEntity<?> editCommentsByAdminOutput(@PathVariable String commentId, @Valid @RequestBody EditCommentsByAdminInput input) {
        input.setCommentId(commentId);
        return handleTheEither(editCommentsByAdminProcessor.process(input));
    }

    @DeleteMapping(RestApiPaths.DELETE_A_COMMENT)
    @Operation(
            summary = "Delete a comment",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "200", description = "Successful")
            }
    )
    public ResponseEntity<?> deleteCommentsOutput(@PathVariable String commentId) {
        DeleteCommentsInput input = DeleteCommentsInput.builder()
                .commentId(commentId)
                .build();
        return handleTheEither(deleteCommentsProcessor.process(input));
    }
}
