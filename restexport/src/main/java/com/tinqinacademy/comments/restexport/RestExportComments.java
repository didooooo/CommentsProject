package com.tinqinacademy.comments.restexport;

import com.tinqinacademy.comments.api.mappings.RestApiPaths;
import com.tinqinacademy.comments.api.operations.commentsGetListOfAllComments.GetListOfAllCommentOutput;
import com.tinqinacademy.comments.api.operations.deleteComments.DeleteCommentsOutput;
import com.tinqinacademy.comments.api.operations.editCommentByAdmin.EditCommentsByAdminInput;
import com.tinqinacademy.comments.api.operations.editCommentByAdmin.EditCommentsByAdminOutput;
import com.tinqinacademy.comments.api.operations.editOwnComment.EditOwnCommentOutput;
import com.tinqinacademy.comments.api.operations.leaveAComment.LeaveACommentInput;
import com.tinqinacademy.comments.api.operations.leaveAComment.LeaveACommentOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("comment-service")
public interface RestExportComments {


    //Comment
    @GetMapping(RestApiPaths.GET_ALL_COMMENTS)
    @Operation(
            summary = "Get list of all comments",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Not found"),
            }
    )
    GetListOfAllCommentOutput getListOfAllCommentOutputs(@PathVariable String roomId);

    @PostMapping(RestApiPaths.LEAVE_A_COMMENT)
    @Operation(
            summary = "Leave a comment",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "200", description = "Successful")
            }
    )
    LeaveACommentOutput leaveACommentOutput(@PathVariable String roomId, @RequestBody LeaveACommentInput input);

    @PatchMapping(RestApiPaths.EDIT_A_COMMENT)
    @Operation(
            summary = "Edit a comment",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "200", description = "Successful")
            }
    )
    EditOwnCommentOutput editOwnCommentOutput(@PathVariable String commentId, @RequestParam String content);


    //System
    @PutMapping(RestApiPaths.EDIT_A_COMMENT_BY_ADMIN)
    @Operation(
            summary = "Edit a comment by admin",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "200", description = "Successful")
            }
    )
    EditCommentsByAdminOutput editCommentsByAdminOutput(@PathVariable String commentId, @Valid @RequestBody EditCommentsByAdminInput input);

    @DeleteMapping(RestApiPaths.DELETE_A_COMMENT)
    @Operation(
            summary = "Delete a comment",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "200", description = "Successful")
            }
    )
    DeleteCommentsOutput deleteCommentsOutput(@PathVariable String commentId);
}
