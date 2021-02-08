package com.embl.ebi.person.util;

import com.embl.ebi.person.constant.StatusEnum;
import com.embl.ebi.person.response.Response;
import java.util.UUID;

/**
 * Class that will have static utility methods
 */

public class CommonUtil {

	// UID length
	private static final int UID_LENGTH = 8;

	/**
	 * Generate success response response.
	 * @param <T>
	 *          the type parameter
	 * @param data
	 *          the data
	 * @return the response
	 */
	public static <T> Response<T> generateSuccessResponse(T data) {
		Response<T> response = generateSuccessResponse();
		response.setPerson(data);
		return response;

	}

	/**
	 * Generate success response response.
	 * @param <T>
	 *          the type parameter
	 * @return the response
	 */
	public static <T> Response<T> generateSuccessResponse() {
		Response<T> response = new Response<>();
		response.setStatus(StatusEnum.SUCCESS);
		return response;

	}

	/**
	 * Generate UID
	 * @return UID
	 */
	public static String generateUid() {
		return UUID.randomUUID().toString();
	}

}

