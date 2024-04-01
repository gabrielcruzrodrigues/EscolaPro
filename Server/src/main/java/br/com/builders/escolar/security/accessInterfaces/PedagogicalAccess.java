package br.com.builders.escolar.security.accessInterfaces;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( {ElementType.METHOD, ElementType.TYPE} )
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyRole('PEDAGOGICAL', 'ADMIN')")
public @interface PedagogicalAccess {
}
