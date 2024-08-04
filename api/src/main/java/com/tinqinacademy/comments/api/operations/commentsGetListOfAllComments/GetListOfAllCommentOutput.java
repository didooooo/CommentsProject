package com.tinqinacademy.comments.api.operations.commentsGetListOfAllComments;

import com.tinqinacademy.comments.api.base.interfaces.OperationOutput;
import lombok.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class GetListOfAllCommentOutput implements OperationOutput {
  private List<DataForGetListOFAllComment> data;

    public GetListOfAllCommentOutput() {
        data= new ArrayList<>();
    }
}
