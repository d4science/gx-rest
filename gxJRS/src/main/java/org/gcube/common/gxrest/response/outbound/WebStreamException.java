package org.gcube.common.gxrest.response.outbound;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.gcube.common.gxrest.response.entity.CodeEntity;
import org.gcube.common.gxrest.response.entity.EntityTag;
import org.gcube.common.gxrest.response.entity.SerializableErrorEntity;

/**
 * An exception returned to the Rest client.
 * 
 * @author Manuele Simi (ISTI CNR)
 *
 */
final class WebStreamException extends WebApplicationException {

	private static final long serialVersionUID = 822443082773903217L;

	/**
	 * Returns the exception.
	 * 
	 * @param exception
	 * @param keepLines
	 * @param status
	 *            the HTTP status to associate to the response.
	 */
	protected <E extends Exception> WebStreamException(E exception, int keepLines, Response.Status status) {
		super(exception.getCause(), Response.status(status)
				.entity(new CodeEntity(new SerializableErrorEntity(exception, keepLines))).tag(EntityTag.gxError).build());
	}
	
	/**
	 * Returns the exception.
	 * 
	 * @param exception
	 * @param status
	 *            the HTTP status to associate to the response.
	 */
	protected <E extends Exception> WebStreamException(E exception, int keepLines) {
		this(exception, keepLines, Response.Status.NOT_ACCEPTABLE);

	}

	/**
	 * Returns the exception.
	 * 
	 * @param exception
	 */
	protected <E extends Exception> WebStreamException(E exception) {
		this(exception, 0, Response.Status.NOT_ACCEPTABLE);
	}

	/**
	 * Returns the exception.
	 * 
	 * @param exception
	 * @param status
	 *            the HTTP status to associate to the response.
	 */
	protected <E extends Exception> WebStreamException(E exception, Response.Status status) {
		this(exception, 0, status);
	}
}
