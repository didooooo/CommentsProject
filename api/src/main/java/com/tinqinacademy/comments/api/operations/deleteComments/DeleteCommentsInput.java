package com.tinqinacademy.comments.api.operations.deleteComments;

import com.tinqinacademy.comments.api.base.interfaces.OperationInput;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DeleteCommentsInput implements OperationInput {
    private String commentId;
}
