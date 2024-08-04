package com.tinqinacademy.comments.api.operations.leaveAComment;

import com.tinqinacademy.comments.api.base.interfaces.OperationInput;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LeaveACommentInput implements OperationInput {
    @Hidden
    private String roomId;
    @NotBlank
    private String firstName;
    private String lastName;
    @Size(min = 20, max = 200)
    private String content;
    private LocalDate birthdate;

}
