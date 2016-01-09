package pl.lodz.p.bindy_generator;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeSpec;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import javax.lang.model.element.Modifier;

/**
 * Created by maciek on 08/01/16.
 */
public class CsvModelBuilder {

    private TypeSpec.Builder csvModelBuilder;

    public CsvModelBuilder(String name) {
        AnnotationSpec csvRecord = AnnotationSpec.builder(CsvRecord.class)
                .addMember("separator", "$S", ",")
                .build();

        this.csvModelBuilder = TypeSpec.classBuilder(name)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(csvRecord)
                .addJavadoc(Config.getInstance().generationMark());
    }

    public CsvModelBuilder withField(Class type, String name) {
        FieldSpec field = FieldSpec.builder(type, name)
                .addModifiers(Modifier.PRIVATE)
                .addAnnotation(AnnotationSpec.builder(DataField.class)
                        .addMember("pos", "$L", 4)
                        .build())
                .build();
        this.csvModelBuilder.addField(field);
        return this;
    }

    public TypeSpec build() {
        return this.csvModelBuilder.build();
    }

}
