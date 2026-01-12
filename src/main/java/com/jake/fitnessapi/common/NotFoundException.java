/* Created – 8 Jan 2026
 * Last updated – 12 Jan 2026
 */

package com.jake.fitnessapi.common;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
