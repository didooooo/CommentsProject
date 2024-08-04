package com.tinqinacademy.comments.rest.controller;

import com.tinqinacademy.comments.api.operations.commentsGetListOfAllComments.GetListOfAllCommentsInput;
import com.tinqinacademy.comments.api.operations.editOwnComment.EditOwnCommentInput;
import com.tinqinacademy.comments.api.operations.leaveAComment.LeaveACommentInput;
import com.tinqinacademy.comments.core.processors.EditOwnCommentsProcessor;
import com.tinqinacademy.comments.core.processors.GetListOfAllCommentsProcessor;
import com.tinqinacademy.comments.core.processors.LeaveACommentProcessor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentsController extends BaseController {

    private final LeaveACommentProcessor leaveACommentProcessor;
    private final GetListOfAllCommentsProcessor getListOfAllCommentsProcessor;
    private final EditOwnCommentsProcessor editOwnCommentsProcessor;

    public CommentsController(LeaveACommentProcessor leaveACommentProcessor, GetListOfAllCommentsProcessor getListOfAllCommentsProcessor, EditOwnCommentsProcessor editOwnCommentsProcessor) {
        this.leaveACommentProcessor = leaveACommentProcessor;
        this.getListOfAllCommentsProcessor = getListOfAllCommentsProcessor;
        this.editOwnCommentsProcessor = editOwnCommentsProcessor;
    }


    @GetMapping(RestApiPaths.GET_ALL_COMMENTS)
    @Operation(
            summary = "Get list of all comments",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Not found"),
            }
    )
    public ResponseEntity<?> getListOfAllCommentOutputs(@PathVariable String roomId) {
        GetListOfAllCommentsInput input = GetListOfAllCommentsInput.builder()
                .roomId(roomId)
                .build();
        return handleTheEither(getListOfAllCommentsProcessor.process(input));
    }

    @PostMapping(RestApiPaths.LEAVE_A_COMMENT)
    @Operation(
            summary = "Leave a comment",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "200", description = "Successful")
            }
    )
    public ResponseEntity<?> leaveACommentOutput(@PathVariable String roomId, @RequestBody LeaveACommentInput input) {
        input.setRoomId(roomId);
        return handleTheEither(leaveACommentProcessor.process(input));
    }

    @PatchMapping(RestApiPaths.EDIT_A_COMMENT)
    @Operation(
            summary = "Edit a comment",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "200", description = "Successful")
            }
    )
    public ResponseEntity<?> editOwnCommentOutput(@PathVariable String commentId, @RequestParam String content) {
        EditOwnCommentInput input = EditOwnCommentInput.builder()
                .content(content)
                .build();
        return handleTheEither(editOwnCommentsProcessor.process(input));
    }
}
