package id.go.kejaripalu.bdi.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BDIErrorResponse implements Serializable {
	
	private static final long serialVersionUID = 6449300299410224123L;

	private int status;
	
	private String message;
	
	private long timestamp;

}
