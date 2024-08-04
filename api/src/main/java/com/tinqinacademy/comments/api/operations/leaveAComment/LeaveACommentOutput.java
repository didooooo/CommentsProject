package com.tinqinacademy.comments.api.operations.leaveAComment;

import com.tinqinacademy.comments.api.base.interfaces.OperationOutput;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LeaveACommentOutput implements OperationOutput {
    private String roomId;
    
}
