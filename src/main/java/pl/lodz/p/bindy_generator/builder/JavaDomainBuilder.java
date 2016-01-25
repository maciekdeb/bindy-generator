package pl.lodz.p.bindy_generator.builder;

import com.google.common.collect.ImmutableList;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.commons.lang3.StringUtils;
import pl.lodz.p.bindy_generator.params.MainParams;
import pl.lodz.p.bindy_generator.util.Config;
import pl.lodz.p.bindy_generator.factory.AnnotationsFactory;

import javax.lang.model.element.Modifier;
import java.util.List;

/**
 * Created by maciek on 08/01/16.
 */
public class JavaDomainBuilder {

    private Class classAnnotation;
    private TypeSpec.Builder javaDomainBuilder;
    private MainParams params;

    public JavaDomainBuilder(MainParams params, Class classAnnotation) {
        this.params = params;
        this.classAnnotation = classAnnotation;

        AnnotationSpec csvRecord = AnnotationsFactory.getAnnotation(this.classAnnotation, this.params);

        this.javaDomainBuilder = TypeSpec.classBuilder(params.getClassName())
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(csvRecord)
                .addJavadoc(Config.getInstance().generationMark());
    }

    public JavaDomainBuilder withField(Class type, int pos, String name) {
        AnnotationSpec annotation = AnnotationsFactory.getAnnotation(DataField.class, this.params, pos);

        FieldSpec field = FieldSpec.builder(type, name)
                .addModifiers(Modifier.PRIVATE)
                .addAnnotation(annotation)
                .build();
        this.javaDomainBuilder.addField(field);

        List<MethodSpec> methods = ImmutableList.of(createGetter(type, name), createSetter(type, name));
        this.javaDomainBuilder.addMethods(methods);

        return this;
    }

    private MethodSpec createGetter(Class type, String fieldName) {
        return MethodSpec
                .methodBuilder("get" + StringUtils.capitalize(fieldName))
                .addModifiers(Modifier.PUBLIC)
                .returns(type)
                .addStatement("return $N", fieldName)
                .build();
    }

    private MethodSpec createSetter(Class type, String fieldName) {
        return MethodSpec
                .methodBuilder("set" + StringUtils.capitalize(fieldName))
                .addModifiers(Modifier.PUBLIC)
                .addParameter(type, fieldName)
                .addStatement("this.$N = $N", fieldName, fieldName)
                .build();
    }

    public TypeSpec build() {
        return this.javaDomainBuilder.build();
    }

}
