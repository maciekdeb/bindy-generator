package pl.lodz.p.bindy_generator.builder;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeSpec;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import pl.lodz.p.bindy_generator.params.MainParams;
import pl.lodz.p.bindy_generator.util.Config;
import pl.lodz.p.bindy_generator.factory.AnnotationsFactory;

import javax.lang.model.element.Modifier;

/**
 * Created by maciek on 08/01/16.
 */
public class CsvModelBuilder {

    private static final Class CLASS_ANNOTATION = CsvRecord.class;

    private TypeSpec.Builder csvModelBuilder;
    private MainParams params;

    public CsvModelBuilder(MainParams params) {
        this.params = params;

        AnnotationSpec csvRecord = AnnotationsFactory.getAnnotation(CLASS_ANNOTATION, params);

        this.csvModelBuilder = TypeSpec.classBuilder(params.getClassName())
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(csvRecord)
                .addJavadoc(Config.getInstance().generationMark());
    }

    public CsvModelBuilder withField(Class type, String name) {
        AnnotationSpec annotation = AnnotationsFactory.getAnnotation(DataField.class, this.params, 1);

        FieldSpec field = FieldSpec.builder(type, name)
                .addModifiers(Modifier.PRIVATE)
                .addAnnotation(annotation)
                .build();
        this.csvModelBuilder.addField(field);
        return this;
    }

    public TypeSpec build() {
        return this.csvModelBuilder.build();
    }

}
