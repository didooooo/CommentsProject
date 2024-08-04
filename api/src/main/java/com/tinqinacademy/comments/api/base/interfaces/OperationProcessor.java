package com.tinqinacademy.comments.api.base.interfaces;

import com.tinqinacademy.comments.api.exceptions.Errors;
import io.vavr.control.Either;

public interface OperationProcessor<O extends OperationOutput, I extends OperationInput> {
    Either<Errors,O> process(I input);
}
