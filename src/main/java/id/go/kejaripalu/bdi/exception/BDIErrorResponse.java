package id.go.kejaripalu.bdi.exception;

public record BDIErrorResponse(
	int status,
	String message,
	long timestamp) {
}
