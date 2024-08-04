package com.tinqinacademy.comments.api.operations.editOwnComment;

import com.tinqinacademy.comments.api.base.interfaces.OperationInput;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EditOwnCommentInput implements OperationInput {
    @Hidden
    private UUID id;
    private String content;
}
