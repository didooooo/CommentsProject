package com.tinqinacademy.comments.restexport;

import com.tinqinacademy.comments.api.mappings.RestApiPaths;
import com.tinqinacademy.comments.api.operations.commentsGetListOfAllComments.GetListOfAllCommentOutput;
import com.tinqinacademy.comments.api.operations.deleteComments.DeleteCommentsOutput;
import com.tinqinacademy.comments.api.operations.editCommentByAdmin.EditCommentsByAdminInput;
import com.tinqinacademy.comments.api.operations.editCommentByAdmin.EditCommentsByAdminOutput;
import com.tinqinacademy.comments.api.operations.editOwnComment.EditOwnCommentOutput;
import com.tinqinacademy.comments.api.operations.leaveAComment.LeaveACommentInput;
import com.tinqinacademy.comments.api.operations.leaveAComment.LeaveACommentOutput;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

//@FeignClient("comment-service")
@Headers({"Content-Type: application/json"})
public interface RestExportComments {


    //Comment
    //@GetMapping(RestApiPaths.GET_ALL_COMMENTS)
//    @Operation(
//            summary = "Get list of all comments",
//            responses = {
//                    @ApiResponse(responseCode = "404", description = "Not found"),
//            }
//    )
    @RequestLine("GET /hotel/comment/{commentId}")
    GetListOfAllCommentOutput getListOfAllCommentOutputs(@Param("commentId") String commentId);

//    @PostMapping(RestApiPaths.LEAVE_A_COMMENT)
//    @Operation(
//            summary = "Leave a comment",
//            responses = {
//                    @ApiResponse(responseCode = "404", description = "Not found"),
//                    @ApiResponse(responseCode = "200", description = "Successful")
//            }
//    )
    @RequestLine("POST /hotel/{roomId}/comment?input={input}")
    LeaveACommentOutput leaveACommentOutput(@Param("roomId") String roomId, @Param("input") LeaveACommentInput input);

//    @PatchMapping(RestApiPaths.EDIT_A_COMMENT)
//    @Operation(
//            summary = "Edit a comment",
//            responses = {
//                    @ApiResponse(responseCode = "404", description = "Not found"),
//                    @ApiResponse(responseCode = "200", description = "Successful")
//            }
//    )
    @RequestLine("PATCH /hotel/comment/{commentId}?content={content}")
    EditOwnCommentOutput editOwnCommentOutput(@Param("commentId") String commentId, @Param("content") String content);

//
//    //System
//    @PutMapping(RestApiPaths.EDIT_A_COMMENT_BY_ADMIN)
//    @Operation(
//            summary = "Edit a comment by admin",
//            responses = {
//                    @ApiResponse(responseCode = "404", description = "Not found"),
//                    @ApiResponse(responseCode = "200", description = "Successful")
//            }
//    )
    @RequestLine("PUT /system/comment/{commentId}?input={input}")
    EditCommentsByAdminOutput editCommentsByAdminOutput(@Param("commentId") String commentId, @Param("input") EditCommentsByAdminInput input);

//    @DeleteMapping(RestApiPaths.DELETE_A_COMMENT)
//    @Operation(
//            summary = "Delete a comment",
//            responses = {
//                    @ApiResponse(responseCode = "404", description = "Not found"),
//                    @ApiResponse(responseCode = "200", description = "Successful")
//            }
//    )
    @RequestLine("DELETE /system/comment/{commentId}")
    DeleteCommentsOutput deleteCommentsOutput(@Param("commentId") String commentId);
}
