package abopijservice.code.bankcardmanagement.card.masked;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Converter
@Service
@RequiredArgsConstructor
public class EncryptStringConverter implements AttributeConverter<String, String> {

    private final AESEncryptionUtil aesEncryptionUtil;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return attribute == null ? null : aesEncryptionUtil.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData == null ? null : aesEncryptionUtil.decrypt(dbData);
    }
}