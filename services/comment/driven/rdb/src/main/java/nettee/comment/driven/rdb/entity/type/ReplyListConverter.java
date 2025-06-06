package nettee.comment.driven.rdb.entity.type;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.ArrayList;
import java.util.List;
import nettee.reply.driven.rdb.entity.ReplyEntity;

import static nettee.comment.exception.CommentCommandErrorCode.DEFAULT;

@Converter
public class ReplyListConverter implements AttributeConverter<List<ReplyEntity>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // 날짜 필드를 숫자가 아닌 ISO 8601 문자열("2025-06-06T04:37:00Z")로 직렬화합니다.

    @Override
    public String convertToDatabaseColumn(List<ReplyEntity> replies) {
        try {
            return objectMapper.writeValueAsString(replies);
        } catch (JsonProcessingException e) {
            throw DEFAULT.exception();
        }
    }

    @Override
    public List<ReplyEntity> convertToEntityAttribute(String dbData) {
        try {
            if (dbData == null || dbData.isBlank()) return new ArrayList<>();
            return objectMapper.readValue(dbData, new TypeReference<List<ReplyEntity>>() {});
        } catch (JsonProcessingException e) {
            throw DEFAULT.exception();
        }
    }
}
