package com.tinqinacademy.comments.core.processors;

import com.tinqinacademy.comments.api.exceptions.ErrorMapper;
import com.tinqinacademy.comments.api.exceptions.Errors;
import com.tinqinacademy.comments.api.operations.commentsGetListOfAllComments.DataForGetListOFAllComment;
import com.tinqinacademy.comments.api.operations.commentsGetListOfAllComments.GetListOfAllCommentOutput;
import com.tinqinacademy.comments.api.operations.commentsGetListOfAllComments.GetListOfAllComments;
import com.tinqinacademy.comments.api.operations.commentsGetListOfAllComments.GetListOfAllCommentsInput;
import com.tinqinacademy.comments.persistence.entities.Comments;
import com.tinqinacademy.comments.persistence.repositories.CommentsRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetListOfAllCommentsProcessor implements GetListOfAllComments {
    private final CommentsRepository commentsRepository;
    private final ErrorMapper errorMapper;

    @Override
    public Either<Errors, GetListOfAllCommentOutput> process(GetListOfAllCommentsInput input) {
        log.info("Start getListOfAllComments input: {}", input);
        return Try.of(() -> {
                    List<Comments> commentsByRoomId = commentsRepository.findByRoomId(UUID.fromString(input.getRoomId()));
                    List<DataForGetListOFAllComment> data = getData(commentsByRoomId);
                    GetListOfAllCommentOutput built = getCommentOutput(data);
                     log.info("End getListOfAllComments output: {}", built);
                    return built;
                }).toEither()
                .mapLeft(throwable -> errorMapper.mapError(throwable));
    }

    private GetListOfAllCommentOutput getCommentOutput(List<DataForGetListOFAllComment> data) {
        return GetListOfAllCommentOutput.builder()
                .data(data)
                .build();
    }

    private List<DataForGetListOFAllComment> getData(List<Comments> commentsByRoomId) {
        List<DataForGetListOFAllComment> data = new ArrayList<>();
        for (Comments comments : commentsByRoomId) {
            DataForGetListOFAllComment built = getDataForGetListOFAllComment(comments);
            data.add(built);
        }
        return data;
    }

    private DataForGetListOFAllComment getDataForGetListOFAllComment(Comments comments) {
        DataForGetListOFAllComment built = DataForGetListOFAllComment.builder()
                .id(String.valueOf(comments.getId()))
                .content(comments.getContent())
                .lastEditedBy(comments.getLastEditedBy())
                .publishDate(comments.getPublishDate())
                .firstName(comments.getUser().getFirstName())
                .lastName(comments.getUser().getLastName())
                .build();
        return built;
    }
}
