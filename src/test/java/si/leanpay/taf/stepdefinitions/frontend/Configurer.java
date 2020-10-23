package si.leanpay.taf.stepdefinitions.frontend;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.datatable.TableCellByTypeTransformer;
import io.cucumber.datatable.TableEntryByTypeTransformer;

import java.lang.reflect.Type;
import java.util.Locale;
import java.util.Map;

public class Configurer implements TypeRegistryConfigurer {
    @Override
    public void configureTypeRegistry(TypeRegistry registry) {

        JacksonTableTransformer jacksonTableTransformer = new JacksonTableTransformer();
        registry.setDefaultDataTableEntryTransformer(jacksonTableTransformer);
        registry.setDefaultDataTableCellTransformer(jacksonTableTransformer);
    }

    @Override
    public Locale locale() {
        return Locale.ENGLISH;
    }

    private static final class JacksonTableTransformer implements TableEntryByTypeTransformer, TableCellByTypeTransformer {
        private final ObjectMapper objectMapper = new ObjectMapper();


        public <T> T transform(Map<String, String> entry, Class<T> type, TableCellByTypeTransformer cellTransformer) {
            return objectMapper.convertValue(entry, type);
        }

        public <T> T transform(String value, Class<T> cellType) {
            return objectMapper.convertValue(value, cellType);
        }

        @Override
        public Object transform(String cellValue, Type toValueType) throws Throwable {
            return null;
        }

        @Override
        public Object transform(Map<String, String> entryValue, Type toValueType, TableCellByTypeTransformer cellTransformer) throws Throwable {
            return null;
        }
    }
}