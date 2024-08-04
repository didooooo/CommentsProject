package com.tinqinacademy.comments.api.operations.commentsGetListOfAllComments;

import com.tinqinacademy.comments.api.base.interfaces.OperationInput;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetListOfAllCommentsInput implements OperationInput {
    private  String roomId;
}
