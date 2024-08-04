package com.tinqinacademy.comments.api.operations.editOwnComment;

import com.tinqinacademy.comments.api.base.interfaces.OperationOutput;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EditOwnCommentOutput implements OperationOutput {
    private String id;
}
