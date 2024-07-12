package fr.whitedev.technicaltest.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import io.micrometer.core.instrument.util.IOUtils;

import java.io.InputStream;

/*
Explain abstract, protected, why final method

- abstract
An abstract class is a class that cannot be instantiated directly.
It serves as a template for child classes that inherit its functionality.

- protected
protected is used to indicate that fields and methods are accessible to the class itself and all its inherited child classes.
So, objectMapper will be accessible in the AlbumRepository and UserRepository child classes.

- Why final method?
final is used so that child classes cannot change its behavior by redefining it.
*/

public abstract class AbstractRepository {

	protected ObjectMapper objectMapper = new ObjectMapper();

	protected final String getBodyByFileName(String filename) {
		InputStream notFoundStream = AbstractRepository.class.getClassLoader()
															 .getResourceAsStream(filename);
		return IOUtils.toString(notFoundStream, Charsets.UTF_8);
	}
}
