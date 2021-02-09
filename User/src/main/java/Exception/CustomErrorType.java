package Exception;

import com.umg.entidad.*;

public class CustomErrorType extends UsersDTO {

	private String errorMessage;
	 
    public CustomErrorType(final String errorMessage){
        this.errorMessage = errorMessage;
    }
 
    public String getErrorMessage() {
        return errorMessage;
    }
}
