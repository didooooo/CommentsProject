package com.tinqinacademy.comments.api.operations.deleteComments;

import com.tinqinacademy.comments.api.base.interfaces.OperationOutput;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DeleteCommentsOutput implements OperationOutput {
    private String message;
}
