package fr.whitedev.technicaltest.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import io.micrometer.core.instrument.util.IOUtils;

import java.io.InputStream;

// TODO: explain abstract, protected, why final method

public abstract class AbstractRepository {

	protected ObjectMapper objectMapper = new ObjectMapper();

	protected final String getBodyByFileName(String filename) {
		InputStream notFoundStream = AbstractRepository.class.getClassLoader()
															 .getResourceAsStream(filename);
		return IOUtils.toString(notFoundStream, Charsets.UTF_8);
	}
}
