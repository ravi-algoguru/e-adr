/*
 * Copyright (c) 2016-2017 Oliver Kopp, Olaf Zimmermann
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Oliver Kopp, Olaf Zimmermann - initial API and implementation
 */
package io.github.adr.embedded.demo.annproc;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import io.github.adr.embedded.DecisionMade;

@SupportedAnnotationTypes("io.github.adr.embedded.DecisionMade")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
// @AutoService(Processor.class)
public class EadrAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {

        System.out.println("Annotation Processor Invoked for " + roundEnv.toString());

        for (final Element element : roundEnv.getRootElements()) {
            System.out.println("Element: " + element.getSimpleName());
            DecisionMade dm = element.getAnnotation(DecisionMade.class);
            System.out.println("Found an AD made: " + dm.id() + dm.chosenOption());
        }
        /*
        for( final Element element: roundEnv.getElementsAnnotatedWith(DecisionMade.class ) ) {

                final TypeElement typeElement = ( TypeElement )element;

                for( final Element eclosedElement: typeElement.getEnclosedElements() ) {
                    if( eclosedElement instanceof VariableElement ) {
                        final VariableElement variableElement = ( VariableElement )eclosedElement;

                        /*
                        if( !variableElement.getModifiers().contains( Modifier.FINAL ) ) {
                            processingEnv.getMessager().printMessage( Diagnostic.Kind.ERROR,
                                String.format(
                                    "Class '%s' is annotated as @Immutable, but field '%s' is not declared as final",
                                        typeElement.getSimpleName(), variableElement.getSimpleName()
                                )
                            );
                        }
                    }
                }
            }

            DecisionMade[] dmAnnotations = element.getAnnotationsByType(DecisionMade.class);
            for(int i=0;i<dmAnnotations.length;i++) {
                DecisionMade dm = dmAnnotations[i];
                System.out.println("Found an AD made: " + dm.id() + dm.chosenOption());
            }
        }
        */

        // Claiming that annotations have been processed by this processor
        return true;
    }
}
