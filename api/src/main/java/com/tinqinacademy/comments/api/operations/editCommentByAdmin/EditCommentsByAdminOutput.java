package com.tinqinacademy.comments.api.operations.editCommentByAdmin;

import com.tinqinacademy.comments.api.base.interfaces.OperationOutput;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EditCommentsByAdminOutput implements OperationOutput {
    private String id;
}
